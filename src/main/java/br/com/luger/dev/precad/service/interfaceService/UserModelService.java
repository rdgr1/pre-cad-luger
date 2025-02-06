package br.com.luger.dev.precad.service.interfaceService;

import br.com.luger.dev.precad.model.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserModelService extends CRUDservice<Users> {

    UserDetails findByEmail(String email);

    Optional<Users> findByCpf(String cpf);
}
