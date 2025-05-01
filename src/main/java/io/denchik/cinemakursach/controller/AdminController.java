//package io.denchik.cinemakursach.controller;
//
//import org.kursach.zakazbiletov.kursach.models.Role;
//import org.kursach.zakazbiletov.kursach.models.UserEntity;
//import org.kursach.zakazbiletov.kursach.repository.RoleRepository;
//import org.kursach.zakazbiletov.kursach.repository.UserRepository;
//import org.kursach.zakazbiletov.kursach.security.SecurityUtil;
//import org.kursach.zakazbiletov.kursach.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//public class AdminController {
//    private final UserService userService;
//    private final RoleRepository roleRepository;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public AdminController(UserService userService, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.roleRepository = roleRepository;
//        this.userService = userService;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @GetMapping("/usersControl")
//    public String controlPage(Model model) {
//        List<UserEntity> users = userService.findAllCommonUsers();
//        UserEntity currentUser = userService.findByUsername(SecurityUtil.getSessionUser());
//        users.remove(currentUser);
//        Role adminRole = roleRepository.findByName("ADMIN");
//        model.addAttribute("users", users);
//        model.addAttribute("adminRole", adminRole);
//        return "controlUsers";
//    }
//    @GetMapping("/usersControl/{userId}/addAdmin")
//    public String addAdmin(@PathVariable("userId") Long userId) {
//        UserEntity user = userService.findById(userId);
//        Role adminRole = roleRepository.findByName("ADMIN");
//        List<Role> roles = user.getRoles();
//        roles.add(adminRole);
//        user.setRoles(roles);
//        userRepository.save(user);
//        return "redirect:/usersControl";
//    }
//    @GetMapping("/usersControl/{userId}/lock")
//    public String lock(@PathVariable("userId") Long userId) {
//        UserEntity user = userService.findById(userId);
//        user.setLock(true);
//        userRepository.save(user);
//        return "redirect:/usersControl";
//    }
//    @GetMapping("/usersControl/{userId}/unlock")
//    public String unlock(@PathVariable("userId") Long userId) {
//        UserEntity user = userService.findById(userId);
//        user.setLock(false);
//        userRepository.save(user);
//        return "redirect:/usersControl";
//    }
//
//    @GetMapping("/usersControl/{userId}/delete")
//    public String delete(@PathVariable("userId") Long userId) {
//        userService.deleteUserById(userId);
//        return "redirect:/usersControl";
//    }
//
//    @GetMapping("/profile/{userId}")
//    public String profileUser(@PathVariable("userId") Long userId,Model model) {
//        UserEntity user = userService.findById(userId);
//        model.addAttribute("user", user);
//        return "userProfile";
//    }
//
//    @PostMapping("/profile/{userId}/username")
//    public String changeUsernameAtAdmin(@PathVariable("userId") Long userId,@RequestParam(value="username") String username) {
//        UserEntity user = userService.findById(userId);
//        if (user == null) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        if (!username.equals(user.getUsername()) && userService.findByUsername(username) != null) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        user.setUsername(username);
//        userRepository.save(user);
//        return "redirect:/profile/{userId}?success";
//    }
//    @PostMapping("/profile/{userId}/email")
//    public String changeEmailAtAdmin(@PathVariable("userId") Long userId,@RequestParam(value="email") String username) {
//        UserEntity user = userService.findById(userId);
//        if (user == null) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        if (!username.equals(user.getUsername()) && userService.findByUsername(username) != null) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        user.setEmail(username);
//        userRepository.save(user);
//        return "redirect:/profile/{userId}?success";
//    }
//
//    @PostMapping("/profile/{userId}/password")
//    public String changePasswordAtAdmin(@PathVariable("userId") Long userId,@RequestParam(value="password1") String password1,@RequestParam(value="password2") String password2) {
//        UserEntity user = userService.findById(userId);
//        if (user == null) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        if (!password1.equals(password2)) {
//            return "redirect:/profile/{userId}?fail";
//        }
//        user.setPassword(  passwordEncoder.encode(password1));
//        userRepository.save(user);
//        return "redirect:/profile/{userId}?success";
//    }
//}
