package fr.kevinmilet.myfreezermanager.jwt;

import fr.kevinmilet.myfreezermanager.configuration.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author k.milet
 */
@RestController
public class JwtController {

    private final JwtUtils jwtUtils;
    private final MyUserDetailService myUserDetailService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public JwtController(JwtUtils jwtUtils, MyUserDetailService myUserDetailService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.jwtUtils = jwtUtils;
        this.myUserDetailService = myUserDetailService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = logUser(jwtRequest.getEmail(), jwtRequest.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        Object principal = authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(((User) principal).getUsername()), httpHeaders, HttpStatus.OK);
    }

    public Authentication logUser(String mail, String password) {
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

}
