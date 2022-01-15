package vn.techmaster.pollapi.service;

import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.techmaster.pollapi.error.AppException;
import vn.techmaster.pollapi.error.ResourceNotFoundException;
import vn.techmaster.pollapi.error.UserAlreadyExistException;
import vn.techmaster.pollapi.model.Role;
import vn.techmaster.pollapi.model.RoleName;
import vn.techmaster.pollapi.model.User;
import vn.techmaster.pollapi.payload.SignUpRequest;
import vn.techmaster.pollapi.repository.RoleRepository;
import vn.techmaster.pollapi.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(SignUpRequest signUpRequest) {
        if (isUserExistedByUserName(signUpRequest.getUsername())) {
            throw new UserAlreadyExistException("Username is already taken!");
        }

        if (isUserExistedByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistException("Email is already in use!");
        }
        // Create user's account and save in DB
        Role userRole = getRoleByName(RoleName.ROLE_USER);
        User user = User.builder().withName(signUpRequest.getName())
                .withUsername(signUpRequest.getUsername())
                .withEmail(signUpRequest.getEmail())
                .withPassword(passwordEncoder.encode(signUpRequest.getPassword()))
                .withRoles(Collections.singleton(userRole))
                .build();
        return userRepository.save(user);
    }

    public boolean isUserExistedByUserName(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean isUserExistedByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private Role getRoleByName(RoleName roleName) {
        return roleRepository.findByName(roleName).orElseThrow(() -> new AppException("User Role not set"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
