package br.com.luger.dev.precad.repository;


import br.com.luger.dev.precad.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserModelRepository extends AbstractRepository<Users, UUID> {
    UserDetails findByEmail(String username);

    Optional<Users> findByCpf(String cpf);
}
