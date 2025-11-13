package viniccius13.casa_automatica.repository;

import viniccius13.casa_automatica.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTipo(String tipo);
}
