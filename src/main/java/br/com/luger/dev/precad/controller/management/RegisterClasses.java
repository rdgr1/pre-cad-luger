package br.com.luger.dev.precad.controller.management;

import br.com.luger.dev.precad.controller.base.ClasseController;
import br.com.luger.dev.precad.enums.StatusCurso;
import br.com.luger.dev.precad.model.Classe;
import br.com.luger.dev.precad.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin/registerclasse")
public class RegisterClasses {
    private final ClasseController classeController;
    private final TokenUtil tokenUtil;

    @Autowired
    public RegisterClasses(ClasseController classeController, TokenUtil tokenUtil) {
        this.classeController = classeController;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(@RequestBody Classe classeDto, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }

        Classe classe = new Classe();
        BeanUtils.copyProperties(classeDto, classe);

        if (classeDto.getStatus().toUpperCase().equals(StatusCurso.A.getLabel())) {
            classe.setStatus(StatusCurso.A.getLabel());
        } else if (classeDto.getStatus().toUpperCase().equals(StatusCurso.INA.getLabel())) {
            classe.setStatus(StatusCurso.INA.getLabel());
        } else {
            return ResponseEntity.status(406).body("Invalid status");
        }

        String cursoString = classeController.save(classe).toString();


        return ResponseEntity.status(200).body(cursoString);
    }

    @PutMapping("/update")
    public ResponseEntity<String> putCourse(@RequestBody Classe classeDTO, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }

        Optional<Classe> optionalClasse = classeController.findById(classeDTO.getId());

        if (optionalClasse.isEmpty()) {
            return ResponseEntity.status(404).body("Course not existes in database");
        }
        Classe classe = optionalClasse.get();
        UUID idClasse = classe.getId();
        BeanUtils.copyProperties(classeDTO, classe);
        classe.setId(idClasse);

        if (classeDTO.getStatus().toUpperCase().equals(StatusCurso.A.getLabel())) {
            classe.setStatus(StatusCurso.A.getLabel());
        } else if (classeDTO.getStatus().toUpperCase().equals(StatusCurso.INA.getLabel())) {

            classe.setStatus(StatusCurso.INA.getLabel());
        } else {
            return ResponseEntity.status(406).body("Invalid status");
        }

        String cursoString = classeController.save(classe).toString();

        return ResponseEntity.status(200).body(cursoString);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam(name = "id") UUID id, @RequestHeader HttpHeaders headers) {
        if (!tokenUtil.isAdmin(headers)) {
            return ResponseEntity.status(403).body("access denied: not an administrator");
        }
        Optional<Classe> optionalClasse = classeController.findById(id);
        if (optionalClasse.isEmpty()) {
            return ResponseEntity.status(404).body("Course not exists in database");
        }

        Classe classe = optionalClasse.get();
        classe.setStatus(StatusCurso.INA.getLabel());

        String cursoString = classeController.save(classe).toString();

        return ResponseEntity.status(200).body(cursoString);
    }


}
