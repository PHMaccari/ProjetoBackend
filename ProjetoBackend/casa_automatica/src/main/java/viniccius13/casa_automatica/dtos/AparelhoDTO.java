package viniccius13.casa_automatica.dtos;

import lombok.Data;


@Data
public class AparelhoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Long categoriaId;
    private Long usuarioId;
}
