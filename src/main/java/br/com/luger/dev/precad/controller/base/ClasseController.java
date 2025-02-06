package br.com.luger.dev.precad.controller.base;

import br.com.luger.dev.precad.model.Classe;
import br.com.luger.dev.precad.service.ClasseServiceImplement;
import br.com.luger.dev.precad.service.interfaceService.CRUDservice;
import br.com.luger.dev.precad.service.interfaceService.ClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/classe")
public class ClasseController extends CRUDcontroller<Classe> {

    private final ClasseService classeService;
    private final ClasseServiceImplement classeServiceImplement;


    @Autowired
    public ClasseController(ClasseService classeService, ClasseServiceImplement classeServiceImplement) {

        this.classeService = classeService;

        this.classeServiceImplement = classeServiceImplement;
    }


    @Override
    public CRUDservice<Classe> getService() {

        return classeService;

    }

    public List<Classe> findAllList() {
        return classeServiceImplement.findAll();
    }
}


