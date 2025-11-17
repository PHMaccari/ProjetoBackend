package viniccius13.casa_automatica.service;

import org.springframework.stereotype.Service;
import viniccius13.casa_automatica.dtos.UsuarioDTO;
import viniccius13.casa_automatica.model.Usuario;
import viniccius13.casa_automatica.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail(), dto.getSenha());
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(salvo.getId(), salvo.getNome(), salvo.getEmail(), salvo.getSenha());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }

    public List<UsuarioDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getNome(), u.getEmail(), u.getSenha()))
                .collect(Collectors.toList());
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        Usuario atualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(atualizado.getId(), atualizado.getNome(), atualizado.getEmail(), atualizado.getSenha());
    }

    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}



