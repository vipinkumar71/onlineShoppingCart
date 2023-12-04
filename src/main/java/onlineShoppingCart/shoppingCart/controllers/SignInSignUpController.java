package onlineShoppingCart.shoppingCart.controllers;

import lombok.extern.slf4j.Slf4j;
import onlineShoppingCart.shoppingCart.JwtUtils.JwtUtils;
import onlineShoppingCart.shoppingCart.dto.AuthRequest;
import onlineShoppingCart.shoppingCart.dto.AuthResp;
import onlineShoppingCart.shoppingCart.dto.UsersDTO;
import onlineShoppingCart.shoppingCart.entities.Users;
import onlineShoppingCart.shoppingCart.services.CustomUserDetails;
import onlineShoppingCart.shoppingCart.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class SignInSignUpController {

    //dep : JWT utils : for generating JWT
    @Autowired
    private JwtUtils utils;

    // dep : Auth mgr
    @Autowired
    private AuthenticationManager manager;

    // dep : user service for handling users
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    // add a method to authenticate user . In case of success --send back token , o.w send back err mesg
    @PostMapping("/signin")
    public ResponseEntity<?> validateUserCreateToken(@RequestBody @Valid AuthRequest request) {
        // store incoming user details(not yet validated) into Authentication object
        // Authentication i/f ---> implemented by UserNamePasswordAuthToken
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword());
        log.info("auth token " + authToken);
        Authentication authentication = manager.authenticate(authToken);
        log.info("auth token again " + authentication.getPrincipal().getClass());
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Users user = userDetails.getUser();
        AuthResp resp = mapper.map(user, AuthResp.class);
        resp.setToken(utils.generateJwtToken(authentication));
        return ResponseEntity.ok(resp);

    }

    // add request handling method for user registration
    @PostMapping("/signup")
    public ResponseEntity<?> userRegistration(@RequestBody @Valid UsersDTO user) {
        System.out.println("in reg user : user ");
        // invoke service layer method , for saving : user info + associated roles info
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUserDetails(user));
    }
}

