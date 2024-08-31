package com.app.demo.controller;

import com.app.demo.command.FiltrosCommand;
import com.app.demo.command.GeradorTokenCommand;
import com.app.demo.command.ValidacaoTokenCommand;
import com.app.demo.model.Usuario;
import com.app.demo.pojo.DispositivoDto;
import com.app.demo.pojo.RelatorioDto;
import com.app.demo.service.DispositivoService;
import com.app.demo.service.MonitoramentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitoramento")
public class MonitoramentoController {

    @Autowired
    private MonitoramentoService monitoramentoService;

    @PostMapping("/gerar-token")
    public ResponseEntity<String> getToken(@RequestBody GeradorTokenCommand command) {
        return ResponseEntity.ok(monitoramentoService.gerarToken(command));
    }

    @PostMapping("/validar-token")
    public ResponseEntity validartoken(@RequestBody ValidacaoTokenCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return ResponseEntity.badRequest().build();
        Usuario user = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(monitoramentoService.adicionarUsuarioToken(command.getToken(), user));
    }

    @PostMapping("/relatorio")
    public ResponseEntity<List<RelatorioDto>> getRelatorio(@RequestBody FiltrosCommand command) {
        return ResponseEntity.ok(monitoramentoService.getRelatorio(command));
    }


}