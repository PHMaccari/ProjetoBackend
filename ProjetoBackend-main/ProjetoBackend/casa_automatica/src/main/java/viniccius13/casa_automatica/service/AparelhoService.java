package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.model.Aparelho;
import viniccius13.casa_automatica.model.Categoria;
import viniccius13.casa_automatica.model.Usuario;
import viniccius13.casa_automatica.repository.AparelhoRepository;
import viniccius13.casa_automatica.repository.CategoriaRepository;
import viniccius13.casa_automatica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class AparelhoService {


    private final AparelhoRepository aparelhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;


    public AparelhoService(AparelhoRepository aparelhoRepository, UsuarioRepository usuarioRepository, CategoriaRepository categoriaRepository) {
        this.aparelhoRepository = aparelhoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }


    public List<Aparelho> listarPorUsuario(Long usuarioId) {
        return aparelhoRepository.findByUsuarioId(usuarioId);
    }


    public Optional<Aparelho> buscarPorId(Long id) {
        return aparelhoRepository.findById(id);
    }


    public Aparelho salvar(Long usuarioId, String categoriaTipo, Aparelho aparelho) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        Categoria categoria = categoriaRepository.findByTipo(categoriaTipo)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));


        aparelho.setUsuario(usuario);
        aparelho.setCategoria(categoria);


        return aparelhoRepository.save(aparelho);
    }


    public Optional<Aparelho> atualizar(Long id, Aparelho dadosAtualizados) {
        return aparelhoRepository.findById(id).map(aparelho -> {
            aparelho.setNome(dadosAtualizados.getNome());
            aparelho.setDescricao(dadosAtualizados.getDescricao());
            return aparelhoRepository.save(aparelho);
        });
    }


    public boolean deletar(Long id) {
        if(aparelhoRepository.existsById(id)) {
            aparelhoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
