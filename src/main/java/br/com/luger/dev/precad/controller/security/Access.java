package br.com.luger.dev.precad.controller.security;

import br.com.luger.dev.precad.controller.base.UserModelController;
import br.com.luger.dev.precad.dto.UserModelDTO;
import br.com.luger.dev.precad.model.Users;
import br.com.luger.dev.precad.service.TokenService;
import br.com.luger.dev.precad.utils.TokenUtil;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
public class Access {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserModelController userModelController;
    @Autowired
    private TokenUtil tokenUtil;


    @PostMapping("/public/login")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(type = MediaType.APPLICATION_JSON_VALUE, example = "{" +
                            "email: String," +
                            "password: string" +
                            "}")

            )

    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(
                    mediaType = "*/*",
                    examples = {
                            @ExampleObject(value = "bearer token")
                    }

            ))
    public String login(@RequestBody Users userModel) throws AuthenticationException {
        System.out.println("login line 69");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword());

        try {
            System.out.println("login line 74");
            Authentication authentication = this.authenticationManager
                    .authenticate(usernamePasswordAuthenticationToken);

            var usuario = (Users) authentication.getPrincipal();
            System.out.println("login line 79");
            return tokenService.gerarToken(usuario);

        } catch (Exception e) {
            System.out.println("login line 83");
            throw new AuthenticationException("Authentication failed");
        }
    }

    @PostMapping("/access/admin")
    public ResponseEntity<String> loginAdmin(@RequestBody Users userModel) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword());

        Authentication authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var usuario = (Users) authentication.getPrincipal();

        if (!usuario.getAdmin()) {
            return ResponseEntity.status(403).body("forbiden");
        }
        return ResponseEntity.ok(tokenService.gerarTokenAdmin(usuario));
    }

    @PostMapping("/access/changePasswordFirstLogin")
    public ResponseEntity<String> changePasswordFirstLogin(@RequestBody UserModelDTO userModelDTO, @RequestHeader HttpHeaders headers) {

        Optional<Users> userModelOpitional = userModelController.findById(tokenUtil.getUserIdByToken(headers));

        if (userModelOpitional.isEmpty()) {
            return ResponseEntity.status(404).body("user is Empty");
        }
        if (!userModelOpitional.get().getPrimary_access()) {
            return ResponseEntity.status(404).body("user is not primary access");
        }
        if (Objects.equals(userModelOpitional.get().getCpf(), userModelDTO.getPassword())) {
            return ResponseEntity.status(404).body("password is equal to password already in use");
        }

        Users userModel = userModelOpitional.get();
        userModel.setAdmin(false);
        userModel.setPassword(new BCryptPasswordEncoder().encode(userModelDTO.getPassword()));
        userModel.setPrimary_access(false);
        return ResponseEntity.status(200).body(userModelController.save(userModel).toString());
    }

    @GetMapping("/isfirstlogin")
    public ResponseEntity<String> isFirstLogin(@RequestHeader HttpHeaders headers) {

        try {
            Optional<Users> userModelOptional = userModelController.findById(tokenUtil.getUserIdByToken(headers));

            if (userModelOptional.isEmpty()) {
                return ResponseEntity.status(404).body("user is Empty");
            }
            if (!userModelOptional.get().getPrimary_access()) {
                return ResponseEntity.status(404).body("user is not primary access");
            }
            return ResponseEntity.status(200).body("is First Login ");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Token inválido");
        }
    }

}
