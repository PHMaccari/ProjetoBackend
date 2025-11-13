package viniccius13.casa_automatica.controller;

import viniccius13.casa_automatica.model.Tarefa;
import viniccius13.casa_automatica.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/usuario/{usuarioId}/aparelhos/{aparelhoId}/tarefas")
public class TarefaController {


    private final TarefaService tarefaService;


    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }


    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@PathVariable Long aparelhoId) {
        return ResponseEntity.ok(tarefaService.listarPorAparelho(aparelhoId));
    }


    @GetMapping("/{tarefaId}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long tarefaId) {
        return tarefaService.buscarPorId(tarefaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@PathVariable Long aparelhoId, @RequestBody Tarefa tarefa) {
        return tarefaService.salvar(aparelhoId, tarefa)
                .map(t -> ResponseEntity.status(201).body(t))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/{tarefaId}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long tarefaId, @RequestBody Tarefa tarefa) {
        return tarefaService.atualizar(tarefaId, tarefa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long tarefaId) {
        if (tarefaService.deletar(tarefaId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
