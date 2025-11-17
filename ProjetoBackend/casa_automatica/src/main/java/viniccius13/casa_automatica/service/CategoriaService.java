package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.dtos.CategoriaDTO;
import viniccius13.casa_automatica.model.Categoria;
import viniccius13.casa_automatica.mappers.CategoriaMapper;
import viniccius13.casa_automatica.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaDTO criarCategoria(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        return categoriaMapper.toDTO(categoriaRepository.save(categoria));
    }

    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

