package br.com.luger.dev.precad.controller.management;

import br.com.luger.dev.precad.controller.base.UserModelController;
import br.com.luger.dev.precad.model.Users;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Controller
public class RegistrationController {
    private final UserModelController userModelController;
    @Value("${front.return.url}")
    private String frontUrl;

    @Autowired
    public RegistrationController(UserModelController userModelController) {
        this.userModelController = userModelController;
    }


    @GetMapping("/alterarsenha/{cpf}")
    public ModelAndView showRegistrationForm(@PathVariable String cpf) {
        ModelAndView modelAndView = new ModelAndView("registration-form");
        modelAndView.addObject("cpf", cpf);
        return modelAndView;
    }

    @PostMapping("/register")
    public @ResponseBody String processRegistration(HttpServletResponse response,
                                                    @RequestParam("cpf") String cpf,
                                                    @RequestParam("password") String password
    ) throws IOException {
        Optional<Users> optionalUsers = userModelController.findByCpf(cpf);
        if (optionalUsers.isEmpty()) {
            response.sendRedirect("/error");
            return null;
        }
        if (!optionalUsers.get().getPrimary_access()) {
            return "/acessrestrito";
        }
        optionalUsers.get().setEnabled(true);
        optionalUsers.get().setPrimary_access(false);
        optionalUsers.get().setPassword(new BCryptPasswordEncoder().encode(password));
        userModelController.save(optionalUsers.get());

        response.sendRedirect(frontUrl + "/");
        return null;
    }
}
