package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.dtos.TarefaDTO;
import viniccius13.casa_automatica.exception.BusinessException;
import viniccius13.casa_automatica.exception.NotFoundException;
import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.model.Tarefa;
import viniccius13.casa_automatica.mappers.TarefaMapper;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import viniccius13.casa_automatica.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final AparelhoRepository aparelhoRepository;

    public TarefaService(TarefaRepository tarefaRepository,
                         TarefaMapper tarefaMapper,
                         AparelhoRepository aparelhoRepository) {
        this.tarefaRepository = tarefaRepository;
        this.tarefaMapper = tarefaMapper;
        this.aparelhoRepository = aparelhoRepository;
    }

    public TarefaDTO criarTarefa(TarefaDTO dto) {

        if (dto.getHora() == null) {
            throw new BusinessException("Data e hora são obrigatórias");
        }

        if (dto.getHora().isBefore(LocalTime.from(LocalDateTime.now()))) {
            throw new BusinessException("Não é possível agendar uma tarefa no passado");
        }

        Aparelho aparelho = aparelhoRepository.findById(dto.getAparelhoId())
                .orElseThrow(() -> new NotFoundException("Aparelho não encontrado"));

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

    public TarefaDTO buscarPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
        return tarefaMapper.toDTO(tarefa);
    }

    public TarefaDTO atualizar(Long id, TarefaDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));

        if (dto.getHora() == null) {
            throw new BusinessException("Data e hora são obrigatórias");
        }

        if (dto.getHora().isBefore(LocalTime.from(LocalDateTime.now()))) {
            throw new BusinessException("Não é possível agendar uma tarefa no passado");
        }

        Aparelho aparelho = aparelhoRepository.findById(dto.getAparelhoId())
                .orElseThrow(() -> new NotFoundException("Aparelho não encontrado"));

        tarefa.setDescricao(dto.getDescricao());
        tarefa.setHora(dto.getHora() != null ? dto.getHora().toString() : null);
        tarefa.setRepetir(dto.getRepetir());
        tarefa.setPeriodo(dto.getPeriodo());
        tarefa.setAparelho(aparelho);

        Tarefa atualizado = tarefaRepository.save(tarefa);
        return tarefaMapper.toDTO(atualizado);
    }

    public void deletarTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new NotFoundException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }
}
