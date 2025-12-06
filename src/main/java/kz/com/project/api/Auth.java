package kz.com.project.api;

import kz.com.project.Dto.UserDto;
import kz.com.project.model.User;
import kz.com.project.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class Auth {

    private final UserServiceImpl userService;

    public Auth(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public void register(@RequestBody UserDto userDto){
        userService.register(userDto);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<?>> getallSrudent(){
        return ResponseEntity.ok(userService.getAll());
    }





}
