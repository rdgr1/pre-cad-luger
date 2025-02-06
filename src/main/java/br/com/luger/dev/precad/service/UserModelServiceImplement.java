package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.model.Users;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.UserModelRepository;
import br.com.luger.dev.precad.service.interfaceService.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserModelServiceImplement extends CRUDserviceImplement<Users> implements UserModelService, UserDetailsService {


    private final UserModelRepository entityRepository;
    @Autowired
    private UserModelRepository userRepository;


    @Autowired
    public UserModelServiceImplement(UserModelRepository entityRepository) {

        this.entityRepository = entityRepository;


    }

    @Override
    protected AbstractRepository<Users, UUID> getRepository() {

        return entityRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);


        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email + "tente novamente");
        }

        return user;
    }

    @Override
    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> findByCpf(String cpf) {
        return userRepository.findByCpf(cpf);
    }
}


