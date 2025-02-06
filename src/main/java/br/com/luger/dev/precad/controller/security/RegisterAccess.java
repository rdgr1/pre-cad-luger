package br.com.luger.dev.precad.controller.security;

import br.com.luger.dev.precad.controller.base.UserModelController;
import br.com.luger.dev.precad.dto.UserModelRegister;
import br.com.luger.dev.precad.model.Users;
import br.com.luger.dev.precad.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/admin")
public class RegisterAccess {
    private final UserModelController userModelController;
    private final TokenUtil tokenUtil;

    @Autowired
    public RegisterAccess(UserModelController userModelController, TokenUtil tokenUtil) {
        this.userModelController = userModelController;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserModelRegister userModel, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }
        Optional<Users> optionalUsers = userModelController.findByCpf(userModel.getCpf());

        if (optionalUsers.isPresent()) {
            return ResponseEntity.status(409).body("is user already registered");
        }


        Users newUserModel = new Users();
        newUserModel.setAdmin(false);
        newUserModel.setEnabled(true);
        newUserModel.setName(userModel.getName());
        newUserModel.setPrimary_access(true);
        newUserModel.setCpf(userModel.getCpf());
        newUserModel.setEmail(userModel.getEmail());
        newUserModel.setPassword(new BCryptPasswordEncoder().encode(userModel.getCpf()));

        userModelController.save(newUserModel);

        return ResponseEntity.ok("access created by" + tokenUtil.getSubjectByHeader(headers) + "for" + userModel.getName());
    }


}
