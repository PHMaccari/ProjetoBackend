package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.model.Tarefa;
import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.repository.TarefaRepository;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class TarefaService {


    private final TarefaRepository tarefaRepository;
    private final AparelhoRepository aparelhoRepository;


    @Value("${tarefa.permitido.inicio:08:00}")
    private String inicioConfig;


    @Value("${tarefa.permitido.fim:18:00}")
    private String fimConfig;


    public TarefaService(TarefaRepository tarefaRepository, AparelhoRepository aparelhoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.aparelhoRepository = aparelhoRepository;
    }


    public List<Tarefa> listarPorAparelho(Long aparelhoId) {
        return tarefaRepository.findByAparelhoId(aparelhoId);
    }


    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }


    public Optional<Tarefa> salvar(Long aparelhoId, Tarefa tarefa) {
        return aparelhoRepository.findById(aparelhoId).map(aparelho -> {
            tarefa.setAparelho(aparelho);
            tarefa.setSituacao(avaliarSituacao(tarefa.getHora(), tarefa.getPeriodo()));
            return tarefaRepository.save(tarefa);
        });
    }


    public Optional<Tarefa> atualizar(Long tarefaId, Tarefa dadosAtualizados) {
        return tarefaRepository.findById(tarefaId).map(tarefa -> {
            tarefa.setTarefa(dadosAtualizados.getTarefa());
            tarefa.setHora(dadosAtualizados.getHora());
            tarefa.setRepetir(dadosAtualizados.getRepetir());
            tarefa.setPeriodo(dadosAtualizados.getPeriodo());
            tarefa.setSituacao(avaliarSituacao(dadosAtualizados.getHora(), dadosAtualizados.getPeriodo()));
            return tarefaRepository.save(tarefa);
        });
    }


    public boolean deletar(Long id) {
        if(tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private LocalTime getInicioPermitido() {
        return LocalTime.parse(inicioConfig);
    }


    private LocalTime getFimPermitido() {
        return LocalTime.parse(fimConfig);
    }


    private String avaliarSituacao(String horaStr, String periodo) {
        if(periodo == null || !periodo.equalsIgnoreCase("dentro")) {
            return "permitida";
        }


        if(horaStr == null || horaStr.isBlank()) return "horario-invalido";
        LocalTime hora = LocalTime.parse(horaStr);


        if(hora.isAfter(getInicioPermitido()) && hora.isBefore(getFimPermitido())) {
            return "permitida";
        }
        return "fora-do-periodo";
    }
}
