package br.com.luger.dev.precad.controller.publics;


import br.com.luger.dev.precad.controller.base.CursoController;
import br.com.luger.dev.precad.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/registercourses")
public class PublicRegisterCourses {
    private final CursoController cursoController;

    @Autowired
    public PublicRegisterCourses(CursoController cursoController) {
        this.cursoController = cursoController;
    }

    @GetMapping()
    public ResponseEntity<List<Curso>> putCourse() {
        List<Curso> classe = cursoController.findAllList();

        return ResponseEntity.status(200).body(classe);
    }
}

