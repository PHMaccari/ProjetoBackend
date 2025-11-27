package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.dtos.AparelhoDTO;
import viniccius13.casa_automatica.exception.BusinessException;
import viniccius13.casa_automatica.exception.NotFoundException;
import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.model.Usuario;
import viniccius13.casa_automatica.model.Categoria;
import viniccius13.casa_automatica.mappers.AparelhoMapper;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import viniccius13.casa_automatica.repository.UsuarioRepository;
import viniccius13.casa_automatica.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

        if (dto.getUsuarioId() == null || dto.getCategoriaId() == null) {
            throw new BusinessException("Usuário e categoria são obrigatórios");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

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

    public AparelhoDTO buscarPorId(Long id) {
        Aparelho aparelho = aparelhoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aparelho não encontrado"));
        return aparelhoMapper.toDTO(aparelho);
    }

    public AparelhoDTO atualizar(Long id, AparelhoDTO dto) {
        Aparelho aparelho = aparelhoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aparelho não encontrado"));

        if (dto.getUsuarioId() == null || dto.getCategoriaId() == null) {
            throw new BusinessException("Usuário e categoria são obrigatórios");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        aparelho.setNome(dto.getNome());
        aparelho.setDescricao(dto.getDescricao());
        aparelho.setUsuario(usuario);
        aparelho.setCategoria(categoria);

        Aparelho atualizado = aparelhoRepository.save(aparelho);
        return aparelhoMapper.toDTO(atualizado);
    }

    public void deletarAparelho(Long id) {
        if (!aparelhoRepository.existsById(id)) {
            throw new NotFoundException("Aparelho não encontrado");
        }
        aparelhoRepository.deleteById(id);
    }
}
