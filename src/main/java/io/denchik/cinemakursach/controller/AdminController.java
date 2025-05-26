package io.denchik.cinemakursach.controller;


import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.mapper.UserMapper;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.RoleRepository;
import io.denchik.cinemakursach.repository.UserRepository;
import io.denchik.cinemakursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/usersControl")
    public ResponseEntity<List<RegistrationDto>> controlPage() {
        List<RegistrationDto> users = userService.findAllCommonUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/usersControl/{userId}/addAdmin")
    public ResponseEntity<String> addAdmin(@PathVariable("userId") Long userId) {
        userService.addAdmin(userId);
        return ResponseEntity.ok("Админ добавлен");
    }
    @PostMapping("/usersControl/{userId}/lock")
    public ResponseEntity<String>  lock(@PathVariable("userId") Long userId) {
        UserEntity user = userService.findById(userId);
        user.setLock(true);
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь заблокирован");
    }
    @PostMapping("/usersControl/{userId}/unlock")
    public ResponseEntity<String> unlock(@PathVariable("userId") Long userId) {
        UserEntity user = userService.findById(userId);
        user.setLock(false);
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь разблокирован");
    }

    @PostMapping("/usersControl/{userId}/delete")
    public ResponseEntity<String> delete(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok("Пользователь успешно удален");
    }
    @GetMapping("/profile/{userId}")
    public ResponseEntity<RegistrationDto> profileUserAtAdmin(@PathVariable("userId") Long userId) {
        UserEntity currentUser = userService.findById(userId);
        RegistrationDto registrationDto = UserMapper.mapToRegistrationDto(currentUser);
        return ResponseEntity.ok(registrationDto);
    }


    @PostMapping("/profile/{userId}/username")
    public ResponseEntity<String> changeUsernameAtAdmin(@PathVariable("userId") Long userId,@RequestBody() String username ) {
        UserEntity currentUser = userService.findById(userId);
        username = username.replaceAll("^\"|\"$", "");
        if (!username.equals(currentUser.getUsername()) && userService.findByUsername(username) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка изменения имени пользователя");
        }
        currentUser.setUsername(username);
        userRepository.save(currentUser);
        return ResponseEntity.ok("Имя пользователя успешно изменено");
    }

    @PostMapping("/profile/{userId}/email")
    public ResponseEntity<String> changeEmailAtAdmin(@PathVariable("userId") Long userId,@RequestBody() String email) {
        UserEntity user = userService.findById(userId);
        System.out.println(email);
        email = email.replaceAll("^\"|\"$", "");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователя не нашло");
        }
        System.out.println("Darova");
        if (!email.equals(user.getEmail()) && userService.findByEmail(email) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка изменения email");
        }
        user.setEmail(email);
        userRepository.save(user);
        return ResponseEntity.ok("Email успешно изменен");
    }

    @PostMapping("/profile/{userId}/password")
    public ResponseEntity<String> changePasswordAtAdmin(@PathVariable("userId") Long userId,@RequestBody() String password) {
        UserEntity user = userService.findById(userId);
        password = password.replaceAll("^\"|\"$", "");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователя не нашло");
        }
        user.setPassword(  passwordEncoder.encode(password));
        userRepository.save(user);
        return ResponseEntity.ok("Пароль успешно изменен");
    }
}
