package com.app.demo.controller;

import com.app.demo.pojo.DispositivoDto;
import com.app.demo.pojo.UsuarioDto;
import com.app.demo.service.DispositivoService;
import com.app.demo.service.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping("/paciente/{idPaciente}")
    public ResponseEntity<List<DispositivoDto>> getDispositivoByPaciente(@PathVariable Integer idPaciente) {
        return ResponseEntity.ok(dispositivoService.getDispositivoByPaciente(idPaciente));
    }

}
