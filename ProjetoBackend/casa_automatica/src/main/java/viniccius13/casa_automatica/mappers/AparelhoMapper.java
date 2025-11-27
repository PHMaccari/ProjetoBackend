package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.AparelhoDTO;
import viniccius13.casa_automatica.model.Aparelho;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AparelhoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    AparelhoDTO toDTO(Aparelho aparelho);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "tarefas", ignore = true)
    Aparelho toEntity(AparelhoDTO aparelhoDTO);
}
