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
class SecurityService implements ISecurityService, UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder encoder;

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


    User admin = new User("admin", encoder.encode("123"));
    admin.addRole(roleAdmin);
    userRepository.save(admin);

    User bob =  new User("bob", encoder.encode("123"));
    bob.addRole(roleUser);
    userRepository.save(bob);

    User alice =  new User("alice", encoder.encode("123"));
    alice.addRole(roleEditor);
    userRepository.save(alice);

    User tom =  new User("tom", encoder.encode("123"));
    tom.addRole(roleEditor);
    tom.addRole(roleUser);
    userRepository.save(tom);

    User jane =  new User("jane", encoder.encode("123"));
    jane.addRole(roleAuthor);
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
