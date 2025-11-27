package viniccius13.casa_automatica.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CategoriaDTO {
    private Long id;

    @NotBlank(message = "Tipo é obrigatório")
    @Size(min = 2, max = 50, message = "Tipo deve ter entre 2 e 50 caracteres")
    private String tipo;

    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres")
    private String descricao;
}
