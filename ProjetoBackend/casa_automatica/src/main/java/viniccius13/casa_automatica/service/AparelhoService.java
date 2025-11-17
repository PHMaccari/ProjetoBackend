package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.dtos.AparelhoDTO;
import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.model.Usuario;
import viniccius13.casa_automatica.model.Categoria;
import viniccius13.casa_automatica.mappers.AparelhoMapper;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import viniccius13.casa_automatica.repository.UsuarioRepository;
import viniccius13.casa_automatica.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AparelhoService {

    private final AparelhoRepository aparelhoRepository;
    private final AparelhoMapper aparelhoMapper;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public AparelhoService(
            AparelhoRepository aparelhoRepository,
            AparelhoMapper aparelhoMapper,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository) {
        this.aparelhoRepository = aparelhoRepository;
        this.aparelhoMapper = aparelhoMapper;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public AparelhoDTO criarAparelho(AparelhoDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Aparelho aparelho = aparelhoMapper.toEntity(dto);
        aparelho.setUsuario(usuario);
        aparelho.setCategoria(categoria);

        return aparelhoMapper.toDTO(aparelhoRepository.save(aparelho));
    }

    public List<AparelhoDTO> listarAparelhos() {
        return aparelhoRepository.findAll()
                .stream()
                .map(aparelhoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletarAparelho(Long id) {
        aparelhoRepository.deleteById(id);
    }
}

