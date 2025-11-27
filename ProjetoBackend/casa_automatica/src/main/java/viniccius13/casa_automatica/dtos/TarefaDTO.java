package viniccius13.casa_automatica.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;


@Data
public class TarefaDTO {
    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(min = 5, max = 200, message = "Descrição deve ter entre 5 e 200 caracteres")
    private String descricao;

    @NotNull(message = "Hora é obrigatória")
    private LocalTime hora;

    @Size(max = 50, message = "Repetir deve ter no máximo 50 caracteres")
    private String repetir;

    @Size(max = 20, message = "Período deve ter no máximo 20 caracteres")
    private String periodo; // dentro, fora, sem restricao


    private LocalTime inicioPeriodo; // HH:mm
    private LocalTime fimPeriodo; // HH:mm


    @NotNull(message = "Aparelho é obrigatório")
    private Long aparelhoId;
}
