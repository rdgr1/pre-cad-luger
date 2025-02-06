package br.com.luger.dev.precad.service;

import br.com.luger.dev.precad.model.CadastroCurso;
import br.com.luger.dev.precad.model.Classe;
import br.com.luger.dev.precad.repository.AbstractRepository;
import br.com.luger.dev.precad.repository.CadastroCursoRepository;
import br.com.luger.dev.precad.service.interfaceService.CadastroCursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CadastroCursoServiceImplement extends CRUDserviceImplement<CadastroCurso> implements CadastroCursoService {


    private final CadastroCursoRepository entityRepository;
    @Autowired
    private ClasseServiceImplement classeService;

    @Autowired
    public CadastroCursoServiceImplement(CadastroCursoRepository entityRepository) {

        this.entityRepository = entityRepository;


    }

    public void salvarCadastroCurso(UUID classeId) {
        Optional<Classe> classe = classeService.findById(classeId);
        if (classe.isEmpty()) {
            return;
        }
        long totalCadastros = contarCadastrosPorClasse(classe.get());
        long totalVagas = classe.get().getNumeroVagas();

        if (totalCadastros >= totalVagas) {
            classe.get().setStatus("INATIVO");
            classeService.save(classe.get());
        }
    }

    public void removerCadastroCurso(UUID classeId) {
        Optional<Classe> classe = classeService.findById(classeId);
        if (classe.isEmpty()) {
            return;
        }
        long totalCadastros = contarCadastrosPorClasse(classe.get());
        long totalVagas = classe.get().getNumeroVagas();

        if (totalCadastros < totalVagas) {
            classe.get().setStatus("ATIVO");
            classeService.save(classe.get());
        }
    }

    @Override
    protected AbstractRepository<CadastroCurso, UUID> getRepository() {

        return entityRepository;

    }

    @Override
    public long contarCadastrosPorClasse(Classe classe) {
        return entityRepository.countCadastroCursoByClasse(classe);
    }

    @Override
    public Optional<CadastroCurso> findByCpf(String cpf) {
        return entityRepository.findByCpf(cpf);
    }

    @Override
    public void deleteByCpf(String cpf) {
        entityRepository.deleteCadastroCursosByCpf(cpf);
    }

    @Override
    public Page<CadastroCurso> findByStatus(String status, Pageable pageable) {
        return entityRepository.findByStatus(status, pageable);
    }


    public List<CadastroCurso> findAll() {
        return entityRepository.findAll();
    }
}


