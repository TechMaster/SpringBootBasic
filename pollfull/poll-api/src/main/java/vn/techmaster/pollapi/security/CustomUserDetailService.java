package vn.techmaster.pollapi.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.techmaster.pollapi.error.ResourceNotFoundException;
import vn.techmaster.pollapi.model.User;
import vn.techmaster.pollapi.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
            .orElseThrow( () -> new UsernameNotFoundException("Not found user with username or email: " + usernameOrEmail));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepo.findById(id)
            .orElseThrow( () -> new ResourceNotFoundException("User", "id", "" + id));
    return UserPrincipal.create(user);
    }
}