package com.lungvision.lungdiseasexrayclassificationbe.controller;

import com.lungvision.lungdiseasexrayclassificationbe.dto.NewUserDto;
import com.lungvision.lungdiseasexrayclassificationbe.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @GetMapping("validate")
    public ResponseEntity<Void> validate() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody NewUserDto newUserDto) {

        if(newUserDto.getEmail() == null || newUserDto.getPassword() == null)
            return ResponseEntity.badRequest().build();

        String response = userService.register(newUserDto);

        return ResponseEntity.ok("{\"message\": \"" + response + "\"}");
    }

}
