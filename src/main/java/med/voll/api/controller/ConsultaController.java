package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.dtoDatosDetalleConsulta;
import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid dtoDatosReservaConsulta dtoDatosReservaConsulta){
        System.out.println(dtoDatosReservaConsulta);
        return ResponseEntity.ok(new dtoDatosDetalleConsulta(null , null , null , null)) ;
    }
}
