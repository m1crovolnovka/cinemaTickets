package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.UserRepository;
import io.denchik.cinemakursach.security.SecurityUtil;
import io.denchik.cinemakursach.service.UserService;
import jakarta.validation.Valid;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthController(UserService userService, UserRepository userRepository , PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/username")
    public String changeUsername(@RequestParam(value="username") String username, Principal principal) {
        UserEntity currentUser = userService.findByUsername(principal.getName());
        if (currentUser == null) {
            return "redirect:/profile?fail";
        }
        if (!username.equals(currentUser.getUsername()) && userService.findByUsername(username) != null) {
            return "redirect:/profile?fail";
        }
        currentUser.setUsername(username);
        userRepository.save(currentUser);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                currentUser, authentication.getCredentials(), authentication.getAuthorities()));
        return "redirect:/profile?success";
    }
    @PostMapping("/profile/email")
    public String changeEmail(@RequestParam(value="email") String email, Principal principal) {
        UserEntity currentUser = userService.findByUsername(principal.getName());
        if (currentUser == null) {
            return "redirect:/profile?fail";
        }
        if (!email.equals(currentUser.getEmail()) && userService.findByEmail(email) != null) {
            return "redirect:/profile?fail";
        }
        currentUser.setEmail(email);
        userRepository.save(currentUser);
        return "redirect:/profile?success";
    }

    @PostMapping("/profile/password")
    public String changePassword(@RequestParam(value="password1") String password1,@RequestParam(value="password2") String password2,Principal principal) {
        UserEntity currentUser = userService.findByUsername(principal.getName());
        if (currentUser == null) {
            return "redirect:/profile?fail";
        }
        if (!password1.equals(password2)) {
            return "redirect:/profile?fail";
        }
        currentUser.setPassword(passwordEncoder.encode(password1));
        userRepository.save(currentUser);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                currentUser, authentication.getCredentials(), authentication.getAuthorities()));
        return "redirect:/profile?success";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail()!= null && !existingUserEmail.getEmail().isEmpty()) {
            result.rejectValue("email","error.user","Этот email уже зарегистрирован");
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername()!= null && !existingUserUsername.getUsername().isEmpty()) {
            result.rejectValue("username","error.user","Это имя пользователя уже используется");
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login?username=";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
}
