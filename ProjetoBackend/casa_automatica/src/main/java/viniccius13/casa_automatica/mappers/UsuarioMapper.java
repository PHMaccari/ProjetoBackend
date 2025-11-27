package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.UsuarioDTO;
import viniccius13.casa_automatica.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "senha", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(target = "aparelhos", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);
}
