package viniccius13.casa_automatica.controller;

import viniccius13.casa_automatica.dtos.TarefaDTO;
import viniccius13.casa_automatica.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@Valid @RequestBody TarefaDTO dto) {
        var saved = tarefaService.criarTarefa(dto);
        return ResponseEntity.created(URI.create("/api/tarefas/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> getAll() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}

