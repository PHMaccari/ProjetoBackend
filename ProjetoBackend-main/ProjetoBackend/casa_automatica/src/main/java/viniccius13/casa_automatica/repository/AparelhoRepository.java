package viniccius13.casa_automatica.repository;

import viniccius13.casa_automatica.model.Aparelho;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
    List<Aparelho> findByUsuarioId(Long usuarioId);
}
