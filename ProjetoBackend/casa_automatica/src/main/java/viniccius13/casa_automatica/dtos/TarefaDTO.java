package viniccius13.casa_automatica.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "10:00:00")
    private LocalTime hora;

    @Size(max = 50, message = "Repetir deve ter no máximo 50 caracteres")
    private String repetir;

    @Size(max = 20, message = "Período deve ter no máximo 20 caracteres")
    private String periodo; // dentro, fora, sem restricao

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "08:00:00")
    private LocalTime inicioPeriodo; // HH:mm

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", pattern = "HH:mm:ss", example = "18:00:00")
    private LocalTime fimPeriodo; // HH:mm


    @NotNull(message = "Aparelho é obrigatório")
    private Long aparelhoId;
}
