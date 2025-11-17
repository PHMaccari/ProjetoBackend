package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(UsuarioDTO usuario);

    UsuarioDTO toEntity(UsuarioDTO usuarioDTO);
}
