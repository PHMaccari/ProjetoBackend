package viniccius13.casa_automatica.repository;

import viniccius13.casa_automatica.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByAparelhoId(Long aparelhoId);
}
