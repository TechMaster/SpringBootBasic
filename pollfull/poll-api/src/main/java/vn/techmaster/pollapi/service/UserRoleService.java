package vn.techmaster.pollapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.pollapi.error.AppException;
import vn.techmaster.pollapi.error.ResourceNotFoundException;
import vn.techmaster.pollapi.model.Role;
import vn.techmaster.pollapi.model.RoleName;
import vn.techmaster.pollapi.model.User;
import vn.techmaster.pollapi.repository.RoleRepository;
import vn.techmaster.pollapi.repository.UserRepository;

@Service
public class UserRoleService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleByName(RoleName roleName ) {
    return roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User insertUser(User user) {
        return userRepository.save(user);
    }

    public User getUserUsername(String username) {
        return userRepository.findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
}
