package server.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth")
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
}