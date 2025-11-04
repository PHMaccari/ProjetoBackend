package viniccius13.casa_automatica.repository;

import viniccius13.casa_automatica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
