package com.app.demo.controller;

import com.app.demo.command.GeradorTokenCommand;
import com.app.demo.command.StatusCommand;
import com.app.demo.service.MonitoramentoService;
import com.app.demo.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @PostMapping("/api")
    public ResponseEntity resgistrarStatus(@RequestPart("file") MultipartFile file, @RequestPart("uuid") String uuid) {
        statusService.registrarStatusDispositivo(file, uuid);
        return ResponseEntity.ok().build();
    }

}