package viniccius13.casa_automatica.service;


import viniccius13.casa_automatica.dtos.TarefaDTO;
import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.model.Tarefa;
import viniccius13.casa_automatica.mappers.TarefaMapper;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import viniccius13.casa_automatica.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final AparelhoRepository aparelhoRepository;

    public TarefaService(TarefaRepository tarefaRepository, TarefaMapper tarefaMapper, AparelhoRepository aparelhoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
        this.aparelhoRepository = aparelhoRepository;
    }

    public TarefaDTO criarTarefa(TarefaDTO dto) {

        if (dto.getHora() == null || dto.getHora().isBefore(LocalTime.now())) {
            throw new RuntimeException("Horário inválido para tarefa");
        }

        Aparelho aparelho = aparelhoRepository.findById(dto.getAparelhoId())
                .orElseThrow(() -> new RuntimeException("Aparelho não encontrado"));

        Tarefa tarefa = tarefaMapper.toEntity(dto);
        tarefa.setAparelho(aparelho);

        return tarefaMapper.toDTO(tarefaRepository.save(tarefa));
    }

    public List<TarefaDTO> listarTarefas() {
        return tarefaRepository.findAll()
                .stream()
                .map(tarefaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}

