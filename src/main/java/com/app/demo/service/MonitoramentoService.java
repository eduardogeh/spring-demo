package com.app.demo.service;

import com.app.demo.command.FiltrosCommand;
import com.app.demo.command.GeradorTokenCommand;
import com.app.demo.model.PacienteMonitoramento;
import com.app.demo.model.Status;
import com.app.demo.model.Usuario;
import com.app.demo.pojo.RelatorioDto;
import com.app.demo.repository.PacienteMonitoramentoRepository;
import com.app.demo.repository.StatusRepository;
import com.app.demo.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@Slf4j
public class MonitoramentoService {

    @Autowired
    private PacienteMonitoramentoRepository pacienteMonitoramentoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private StatusRepository statusRepository;

    public String gerarToken(GeradorTokenCommand dto) {
        String uuidCode = UUID.randomUUID().toString();
        if(adicionarTokeConviteMonitoramento(dto.getIdProfissional(), uuidCode)){
            return uuidCode;
        }
        throw new RuntimeException("Erro ao gerar token de monitoramento");
    }

    private boolean adicionarTokeConviteMonitoramento(Integer idProfissional, String uuidCode) {
        try {
            PacienteMonitoramento pacienteMonitoramento = new PacienteMonitoramento();
            pacienteMonitoramento.setMonitor(usuarioRepository.findById(idProfissional).orElseThrow());
            pacienteMonitoramento.setCodigoConvite(uuidCode);
            pacienteMonitoramento.setDataInicioMonitoramento(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toLocalDateTime());

            pacienteMonitoramentoRepository.save(pacienteMonitoramento);
            return true;
        } catch (Exception ex){
            log.error("Erro ao adicionar token de monitoramento", ex);
            return false;
        }
    }

    public List<RelatorioDto> getRelatorio(FiltrosCommand command) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd");
        String diasAnterioresBuscar = buscarPeriodo(command.getPeriodo());
        if(diasAnterioresBuscar==null)return null;
        List<Status> dadosJson = statusRepository.getDadosRelatorios(diasAnterioresBuscar, command.getIdPaciente());
        List<RelatorioDto> dadosRelatorio = new ArrayList<>();
        for(Status dado : dadosJson){
            JSONObject json = new JSONObject(dado.getDadosJson());
            if(json.has(command.getApp())) {
                dadosRelatorio.add(RelatorioDto.builder()
                        .label(sdf.format(dado.getDataRecebimento()))
                        .value(json.getInt(command.getApp()))
                        .build());
            }
        }
        return dadosRelatorio;
    }

    private String buscarPeriodo(String periodo) {
        switch (periodo){
            case "day":
                return "1 day";
            case "week":
                return "7 days";
            case "month":
                return "30 days";
            default:
                return null;
        }
    }

    public String adicionarUsuarioToken(String token, Usuario user) {
        PacienteMonitoramento monitoramento = pacienteMonitoramentoRepository.findByCodigoConvite(token);
        monitoramento.setPaciente(user);
        return "OK";
    }
}

