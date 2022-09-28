package server.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/auth")
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid InputCredentials loginCredentials) {
        return ResponseEntity.ok(authenticationService.authenticateUserCredentials(loginCredentials));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid InputCredentials signupCredentials) {
        
        return ResponseEntity.ok("ok");
    }
}