package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.dto.RegistrationDto;
import io.denchik.cinemakursach.mapper.UserMapper;
import io.denchik.cinemakursach.models.Role;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.*;
import io.denchik.cinemakursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private TicketRepository ticketRepository;
    private final CartRepository cartRepository;
    private final BookingRepository bookingRepository;
    private PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TicketRepository ticketRepository, CartRepository cartRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
         this.passwordEncoder = passwordEncoder;
         this.ticketRepository = ticketRepository;
        this.cartRepository = cartRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        user.setLock(false);
        userRepository.save(user);
    }


    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<RegistrationDto> findAllCommonUsers() {
        List<UserEntity> users = userRepository.findAll();
        Role admin = roleRepository.findByName("ADMIN");
        return users.stream().filter(userEntity -> !userEntity.getRoles().contains(admin)).map(UserMapper::mapToRegistrationDto).sorted(Comparator.comparing(RegistrationDto::getUsername)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        UserEntity user = findById(id);
        user.setRoles(null);
        for(Ticket loc : user.getCart().getTickets()){
            loc.setCart(null);
            loc.setStatus(false);
            loc.setPayType(null);
            ticketRepository.save(loc);
            }
       for(Ticket loc : user.getBooking().getTickets()){
           loc.setBooking(null);
           loc.setStatus(false);
           loc.setPayType(null);
           ticketRepository.save(loc);
       }
        userRepository.save(user);
        userRepository.deleteById(id);
    }

    @Override
    public void addAdmin(Long userId) {
            UserEntity user = findById(userId);
            Role adminRole = roleRepository.findByName("ADMIN");
            List<Role> roles = user.getRoles();
            roles.add(adminRole);
            user.setRoles(roles);
            userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        UserEntity user = userRepository.findUserByUsername(username);

        if(!user.getUsername().equals(username)){
            throw new UsernameNotFoundException("Пользователь с таким именем отсутствует");
        }
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Пароль не верный");
        }
        if(user.getLock()){
            throw new BadCredentialsException("Пользователь заблокирован");
        }

        return true;
    }
}
