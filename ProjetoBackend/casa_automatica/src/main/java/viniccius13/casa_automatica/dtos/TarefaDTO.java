package viniccius13.casa_automatica.dtos;

import lombok.Data;

import java.time.LocalTime;


@Data
public class TarefaDTO {
    private Long id;
    private String tarefa;
    private LocalTime hora;
    private String repetir;
    private String periodo; // dentro, fora, sem restricao


    private LocalTime inicioPeriodo; // HH:mm
    private LocalTime fimPeriodo; // HH:mm


    private Long aparelhoId;
}
