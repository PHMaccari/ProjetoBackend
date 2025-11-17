package viniccius13.casa_automatica.controller;

import viniccius13.casa_automatica.dtos.CategoriaDTO;
import viniccius13.casa_automatica.mappers.CategoriaMapper;
import viniccius13.casa_automatica.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    @PostMapping
    public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO dto) {
        var saved = categoriaService.criarCategoria(dto);
        return ResponseEntity.created(URI.create("/api/categorias/" + saved.getId()))
                .body(saved);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAll() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}

