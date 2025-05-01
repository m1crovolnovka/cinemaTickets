package io.denchik.cinemakursach.security;

import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,DisabledException {
        UserEntity user = userRepository.findFirstByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("Неправильный логин или пароль");
        if (user.getLock()) {
            throw new DisabledException("Пользователь заблокирован");
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                !user.getLock(),
                true,
                true,
                true,
                user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

}
