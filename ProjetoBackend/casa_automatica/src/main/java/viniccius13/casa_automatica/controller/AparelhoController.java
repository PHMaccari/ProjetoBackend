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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aparelhoService.deletarAparelho(id);
        return ResponseEntity.noContent().build();
    }
}

