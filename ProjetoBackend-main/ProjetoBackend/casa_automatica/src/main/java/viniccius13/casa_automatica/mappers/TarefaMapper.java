package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.TarefaDTO;
import viniccius13.casa_automatica.model.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AparelhoMapper.class)
public interface TarefaMapper {

    @Mapping(source = "aparelho.id", target = "aparelhoId")
    TarefaDTO toDTO(Tarefa tarefa);

    @Mapping(source = "aparelhoId", target = "aparelho.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);
}
