package com.app.demo.service;

import com.app.demo.command.StatusCommand;
import com.app.demo.model.PacienteMonitoramento;
import com.app.demo.model.Status;
import com.app.demo.repository.PacienteMonitoramentoRepository;
import com.app.demo.repository.StatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PacienteMonitoramentoRepository monitoramento;
    private final RestTemplate restTemplate = new RestTemplate();

    public void registrarStatusDispositivo(MultipartFile file, String uuid) {

        try {
            PacienteMonitoramento pacienteMonitoramento = monitoramento.findByCodigoConvite(uuid);
            if (pacienteMonitoramento == null) {
                log.error("Paciente não encontrado ou não sendo monitorado");
                return;
            }
            Status status = new Status();
            status.setPaciente(pacienteMonitoramento.getPaciente());
            status.setDataRecebimento(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());
            String dadosJson = converterDadosCsv(file);
            status.setDadosJson(dadosJson);

            statusRepository.save(status);
        } catch (Exception ex){
            log.error("Erro ao registrar status", ex);
        }
    }

    private String converterDadosCsv(MultipartFile file) {
        try {
            String url = "https://felipecolpo.shop/webhook/c02f254c-e4c7-45a2-b9d6-229ae364f704";

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

            body.add("file", new InputStreamResource(file.getInputStream()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            return response.getBody();
        } catch (Exception ex) {
            log.error("Erro ao converter dados CSV", ex);
            return null;
        }
    }
}

