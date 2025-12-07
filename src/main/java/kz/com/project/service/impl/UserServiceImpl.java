package kz.com.project.service.impl;

import kz.com.project.Dto.UserDto;
import kz.com.project.model.Permission;
import kz.com.project.model.User;
import kz.com.project.repository.PermissionRepository;
import kz.com.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements  UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(Objects.nonNull(user)) {
            return user;
        }

        throw new UsernameNotFoundException("User Not Found");
    }

    public void register(UserDto model){
        User check = userRepository.findByEmail(model.getEmail());
        if (check == null){
            User user2 = new User();
            user2.setPassword(passwordEncoder.encode(model.getPassword()));
            user2.setEmail(model.getEmail());
            user2.setUsername(model.getUsername());
            List<Permission> permissions = List.of(permissionRep.findByName("USER"));
            user2.setPermissions(permissions);
            userRepository.save(user2);
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }




}
