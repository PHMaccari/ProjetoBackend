package viniccius13.casa_automatica.controller;

import viniccius13.casa_automatica.dtos.AparelhoDTO;
import viniccius13.casa_automatica.service.AparelhoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/aparelhos")
@RequiredArgsConstructor
public class AparelhoController {

    private final AparelhoService aparelhoService;

    @PostMapping
    public ResponseEntity<AparelhoDTO> create(@Valid @RequestBody AparelhoDTO dto) {
        var saved = aparelhoService.criarAparelho(dto);
        return ResponseEntity.created(URI.create("/api/aparelhos/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AparelhoDTO>> getAll() {
        return ResponseEntity.ok(aparelhoService.listarAparelhos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AparelhoDTO> getById(@PathVariable Long id) {
        AparelhoDTO aparelho = aparelhoService.buscarPorId(id);
        return ResponseEntity.ok(aparelho);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AparelhoDTO> update(@PathVariable Long id, @Valid @RequestBody AparelhoDTO dto) {
        AparelhoDTO atualizado = aparelhoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aparelhoService.deletarAparelho(id);
        return ResponseEntity.noContent().build();
    }
}

