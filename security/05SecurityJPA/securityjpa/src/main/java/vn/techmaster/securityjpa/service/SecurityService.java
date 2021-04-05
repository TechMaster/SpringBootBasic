package vn.techmaster.securityjpa.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.techmaster.securityjpa.model.Role;
import vn.techmaster.securityjpa.model.User;
import vn.techmaster.securityjpa.repository.RoleRepository;
import vn.techmaster.securityjpa.repository.UserRepository;

@Service
public class SecurityService implements ISecurityService, UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder encoder;

  public User createUser(String username, String password, Role ...roles) {
    User user =  new User(username, encoder.encode(password));
    for (Role role : roles) {
      user.addRole(role);
    }
    return user;    
  }

  @Override
  @Transactional
  public void generateUsersRoles() {
    Role roleAdmin = new Role("ADMIN");
    Role roleUser = new Role("USER");
    Role roleAuthor = new Role("AUTHOR");
    Role roleEditor = new Role("EDITOR");

    roleRepository.save(roleAdmin);
    roleRepository.save(roleUser);
    roleRepository.save(roleAuthor);
    roleRepository.save(roleEditor);
    roleRepository.flush();


    User admin = createUser("admin", "123", roleAdmin);
    userRepository.save(admin);

    User bob =  createUser("bob", "123", roleUser);
    userRepository.save(bob);

    User alice =  createUser("alice", "123", roleEditor);
    userRepository.save(alice);

    User tom =  createUser("tom", "123", roleUser, roleEditor);
    userRepository.save(tom);

    User jane =  createUser("jane", "123", roleAuthor);
    userRepository.save(jane);
    
    userRepository.flush();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);      
    if (!user.isPresent()) {
        throw new UsernameNotFoundException("Could not find user");
    }
         
    return user.get();
  }  
}
