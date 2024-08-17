package com.aakash.Projexio.controller;

import com.aakash.Projexio.config.JwtProvider;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.repository.UserRepository;
import com.aakash.Projexio.request.LoginRequest;
import com.aakash.Projexio.response.AuthResponse;
import com.aakash.Projexio.service.CustomUserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {
        User isUserExist=userRepository.findByEmail(user.getEmail());

        if(isUserExist!=null){
            throw new Exception("Email already exist with another account");
        }

        User createdUser=new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());

        User savedUser=userRepository.save(createdUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);

        AuthResponse res= new AuthResponse();
        res.setMessage("SignUp Successful");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest){
                String username= loginRequest.getEmail();
                String password=loginRequest.getPassword();

        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt= JwtProvider.generateToken(authentication);
        AuthResponse res= new AuthResponse();
        res.setMessage("SignIn Successful");
        res.setJwt(jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }

        // This should throw an exception if the password does NOT match
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
