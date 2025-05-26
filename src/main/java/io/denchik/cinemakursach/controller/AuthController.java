package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.LoginDto;
import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.mapper.UserMapper;
import io.denchik.cinemakursach.models.Role;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.RoleRepository;
import io.denchik.cinemakursach.repository.UserRepository;
import io.denchik.cinemakursach.security.SecurityUtil;
import io.denchik.cinemakursach.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@CrossOrigin("*")
@RestController
@RequestMapping()
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthController(UserService userService, UserRepository userRepository , PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @PostMapping("/register/save")
    public ResponseEntity<RegistrationDto> register(@RequestBody() RegistrationDto user ) {
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null && existingUserEmail.getEmail()!= null && !existingUserEmail.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null && existingUserUsername.getUsername()!= null && !existingUserUsername.getUsername().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
         userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        try{
            boolean isAuthenticated = userService.authenticate(loginDto.getUsername(), loginDto.getPassword());

            if(isAuthenticated) {
                session.setAttribute("user", loginDto.getUsername());
                UserEntity currentUser = userService.findByUsername(loginDto.getUsername());
                Role role = roleRepository.findByName("ADMIN");
                if(currentUser.getRoles().contains(role)) {
                    return ResponseEntity.ok("ADMIN");
                }
                else{
                    return ResponseEntity.ok("USER");
                }

            }
            else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильное имя пользователя или пароль");
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла неизвестная ошибка");
        }
    }


    @GetMapping("/profile")
    public ResponseEntity<RegistrationDto> profileUser() {
        UserEntity currentUser = userService.findByUsername(SecurityUtil.getSessionUser());
        RegistrationDto registrationDto = UserMapper.mapToRegistrationDto(currentUser);
        return ResponseEntity.ok(registrationDto);
    }


    @PostMapping("/profile/username")
    public ResponseEntity<String> changeUsernameAtAdmin(@RequestBody() String username ) {
        UserEntity currentUser = userService.findByUsername(SecurityUtil.getSessionUser());
        username = username.replaceAll("^\"|\"$", "");
        if (!username.equals(currentUser.getUsername()) && userService.findByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Такой пользователь уже существует");
        }
        currentUser.setUsername(username);
        userRepository.save(currentUser);
        return ResponseEntity.ok("Имя пользователя успешно изменено");
    }
    @PostMapping("/profile/email")
    public ResponseEntity<String> changeEmailAtAdmin(@RequestBody() String email ) {
        UserEntity currentUser = userService.findByUsername(SecurityUtil.getSessionUser());
        email = email.replaceAll("^\"|\"$", "");
        if (!email.equals(currentUser.getEmail()) && userService.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Пользователь с таким email уже существует");
        }
        currentUser.setEmail(email);
        userRepository.save(currentUser);
        return ResponseEntity.ok("Email успешно изменен");
    }

    @PostMapping("/profile/password")
    public ResponseEntity<String> changePassword(@RequestBody() String password ) {
        UserEntity currentUser = userService.findByUsername(SecurityUtil.getSessionUser());
        password = password.replaceAll("^\"|\"$", "");
        currentUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(currentUser);
        return ResponseEntity.ok("Пароль успешно изменен");
                }

    @PostMapping("/profile/delete")
    public ResponseEntity<String> delete() {
        Long currentUserId = userService.findByUsername(SecurityUtil.getSessionUser()).getId();
        userService.deleteUserById(currentUserId);
        return ResponseEntity.ok("Пользователь успешно удален");
    }


}
