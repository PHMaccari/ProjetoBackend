package viniccius13.casa_automatica.service;

import viniccius13.casa_automatica.model.Usuario;
import viniccius13.casa_automatica.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }


    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> atualizar(Long id, Usuario dadosAtualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(dadosAtualizados.getNome());
            usuario.setEmail(dadosAtualizados.getEmail());
            return usuarioRepository.save(usuario);
        });
    }


    public boolean deletar(Long id) {
        if(usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
