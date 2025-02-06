package br.com.luger.dev.precad.controller.management;


import br.com.luger.dev.precad.controller.base.CursoController;
import br.com.luger.dev.precad.enums.StatusCurso;
import br.com.luger.dev.precad.model.Curso;
import br.com.luger.dev.precad.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/registercourses")
public class RegisterCourses {
    private final CursoController cursoController;
    private final TokenUtil tokenUtil;

    @Autowired
    public RegisterCourses(CursoController cursoController, TokenUtil tokenUtil) {
        this.cursoController = cursoController;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(@RequestBody Curso cursoDto, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }
        if (cursoController.findByTitulo(cursoDto.getTitulo()).isPresent()) {
            return ResponseEntity.status(406).body("exists Courses in database");
        }

        Curso curso = new Curso();
        BeanUtils.copyProperties(cursoDto, curso);

        if (cursoDto.getStatus().toUpperCase().equals(StatusCurso.A.getLabel())) {
            curso.setStatus(StatusCurso.A.getLabel());
        } else if (cursoDto.getStatus().toUpperCase().equals(StatusCurso.INA.getLabel())) {
            curso.setStatus(StatusCurso.INA.getLabel());
        } else {
            return ResponseEntity.status(406).body("Invalid status");
        }

        String cursoString = cursoController.save(curso).toString();


        return ResponseEntity.status(200).body(cursoString);
    }

    @PutMapping("/update")
    public ResponseEntity<String> putCourse(@RequestBody Curso cursoDTO, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }

        Optional<Curso> optionalCurso = cursoController.findById(cursoDTO.getId());

        if (optionalCurso.isEmpty()) {
            return ResponseEntity.status(404).body("Course not existes in database");
        }
        Curso curso = optionalCurso.get();
        UUID idCourse = curso.getId();
        BeanUtils.copyProperties(cursoDTO, curso);
        curso.setId(idCourse);

        if (cursoDTO.getStatus().toUpperCase().equals(StatusCurso.A.getLabel())) {
            curso.setStatus(StatusCurso.A.getLabel());
        } else if (cursoDTO.getStatus().toUpperCase().equals(StatusCurso.INA.getLabel())) {
            curso.setStatus(StatusCurso.INA.getLabel());
        } else {
            return ResponseEntity.status(406).body("Invalid status");
        }

        String cursoString = cursoController.save(curso).toString();

        return ResponseEntity.status(200).body(cursoString);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam(name = "id") UUID id, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }
        Optional<Curso> optionalCurso = cursoController.findById(id);
        if (optionalCurso.isEmpty()) {
            return ResponseEntity.status(404).body("Course not exists in database");
        }

        Curso curso = optionalCurso.get();
        curso.setStatus(StatusCurso.INA.getLabel());

        String cursoString = cursoController.save(curso).toString();

        return ResponseEntity.status(200).body(cursoString);
    }
}

