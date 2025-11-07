package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.UsuarioDTO;
import viniccius13.casa_automatica.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);
}
