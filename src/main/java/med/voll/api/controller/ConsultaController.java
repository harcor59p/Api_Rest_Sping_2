package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ReservaDeConsultas;
import med.voll.api.domain.consulta.dtoDatosDetalleConsulta;
import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reservaDeConsultas ;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid dtoDatosReservaConsulta dtoDatosReservaConsulta){

        reservaDeConsultas.reservar(dtoDatosReservaConsulta);

        return ResponseEntity.ok(new dtoDatosDetalleConsulta(null , null , null , null)) ;
    }
}
