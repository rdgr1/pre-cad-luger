package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.model.Curso;
import br.com.luger.dev.precad.service.CursoServiceImplement;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import br.com.luger.dev.precad.service.interfaceService.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/curso")
public class CursoController extends CRUDcontroller<Curso> {


    private final CursoService cursoService;
    private final CursoServiceImplement cursoServiceImplement;


    @Autowired
    public CursoController(CursoService cursoService, CursoServiceImplement cursoServiceImplement) {

        this.cursoService = cursoService;

        this.cursoServiceImplement = cursoServiceImplement;
    }


    @Override
    public CRUDservice<Curso> getService() {

        return cursoService;

    }

    public Optional<Curso> findByTitulo(String titulo) {
        return cursoService.findByTitulo(titulo);
    }

    public List<Curso> findAllList() {
        return cursoServiceImplement.findAll();
    }
}


