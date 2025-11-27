package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.TarefaDTO;
import viniccius13.casa_automatica.model.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(source = "aparelho.id", target = "aparelhoId")
    @Mapping(source = "hora", target = "hora", qualifiedByName = "stringToLocalTime")
    @Mapping(target = "inicioPeriodo", ignore = true)
    @Mapping(target = "fimPeriodo", ignore = true)
    TarefaDTO toDTO(Tarefa tarefa);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "aparelhoId", target = "aparelho.id")
    @Mapping(source = "hora", target = "hora", qualifiedByName = "localTimeToString")
    @Mapping(target = "situacao", ignore = true)
    Tarefa toEntity(TarefaDTO tarefaDTO);
    
    @Named("stringToLocalTime")
    default LocalTime stringToLocalTime(String hora) {
        if (hora == null || hora.isEmpty()) {
            return null;
        }
        try {
            return LocalTime.parse(hora);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Named("localTimeToString")
    default String localTimeToString(LocalTime hora) {
        return hora != null ? hora.toString() : null;
    }
}
