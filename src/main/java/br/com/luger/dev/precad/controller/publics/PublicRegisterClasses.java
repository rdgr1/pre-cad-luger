package br.com.luger.dev.precad.controller.publics;

import br.com.luger.dev.precad.controller.base.ClasseController;
import br.com.luger.dev.precad.model.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/registerclasse")
public class PublicRegisterClasses {
    private final ClasseController classeController;

    @Autowired
    public PublicRegisterClasses(ClasseController classeController) {
        this.classeController = classeController;
    }

    @GetMapping()
    public ResponseEntity<List<Classe>> putCourse() {
        List<Classe> classe = classeController.findAllList();
        System.out.println(classe);
        return ResponseEntity.status(200).body(classe);
    }

}
