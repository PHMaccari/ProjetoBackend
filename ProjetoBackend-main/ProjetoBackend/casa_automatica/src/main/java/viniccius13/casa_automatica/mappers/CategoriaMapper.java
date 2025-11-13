package viniccius13.casa_automatica.mappers;

import viniccius13.casa_automatica.dtos.CategoriaDTO;
import viniccius13.casa_automatica.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO toDTO(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);
}
