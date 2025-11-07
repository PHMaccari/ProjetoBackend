package viniccius13.casa_automatica.repository;

import viniccius13.casa_automatica.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


    List<Tarefa> findByAparelhoId(Long aparelhoId);


}
