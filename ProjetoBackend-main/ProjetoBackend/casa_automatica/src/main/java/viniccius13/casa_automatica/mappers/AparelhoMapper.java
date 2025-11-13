package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.AparelhoDTO;
import viniccius13.casa_automatica.model.Aparelho;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, CategoriaMapper.class})
public interface AparelhoMapper {

    @Mapping(source = "usuario.id", target = "usuarioId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    AparelhoDTO toDTO(Aparelho aparelho);

    @Mapping(source = "usuarioId", target = "usuario.id")
    @Mapping(source = "categoriaId", target = "categoria.id")
    Aparelho toEntity(AparelhoDTO aparelhoDTO);
}
