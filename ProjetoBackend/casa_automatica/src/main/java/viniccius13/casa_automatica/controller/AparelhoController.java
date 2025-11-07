/*package viniccius13.casa_automatica.controller;

import viniccius13.casa_automatica.model.Tarefa;
import viniccius13.casa_automatica.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/usuario/{usuarioId}/aparelhos")
public class AparelhoController {


    private final AparelhoService aparelhoService;


    public AparelhoController(AparelhoService aparelhoService) {
        this.aparelhoService = aparelhoService;
    }


    @GetMapping
    public ResponseEntity<List<Aparelho>> listarAparelhos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(aparelhoService.listarPorUsuario(usuarioId));
    }


    @GetMapping("/{aparelhoId}")
    public ResponseEntity<Tarefa> buscarAparelho(@PathVariable Long aparelhoId) {
        return aparelhoService.buscarPorId(aparelhoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Aparelho> criarAparelho(@PathVariable Long usuarioId, @RequestBody String categoriaTipo, Aparelho aparelho) {
        return aparelhoService.salvar(aparelhoId, tarefa)
                .map(t -> ResponseEntity.status(201).body(t))
                .orElse(ResponseEntity.badRequest().build());
    }


    @PutMapping("/{aparelhoId}")
    public ResponseEntity<Tarefa> atualizarAparelho(@PathVariable Long aparelhoId, @RequestBody String categoriaTipo, Aparelho aparelho) {
        return aparelhoService.atualizar(tarefaId, tarefa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{aparelhoId}")
    public ResponseEntity<Void> deletarAparelho(@PathVariable Long aparelhoId) {
        if (aparelhoService.deletar(aparelhoId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}*/
