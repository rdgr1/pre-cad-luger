package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.model.Users;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import br.com.luger.dev.precad.service.interfaceService.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/usermodel")
public class UserModelController extends CRUDcontroller<Users> {

    private final UserModelService userModelService;


    @Autowired
    public UserModelController(UserModelService userModelService) {

        this.userModelService = userModelService;

    }


    @Override
    public CRUDservice<Users> getService() {

        return userModelService;

    }

    public Optional<Users> findByCpf(String cpf) {
        return userModelService.findByCpf(cpf);
    }

    public UserDetails findByEmail(String email) {
        return userModelService.findByEmail(email);
    }
}


