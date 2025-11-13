package viniccius13.casa_automatica.dtos;

import lombok.Data;


@Data
public class TarefaDTO {
    private Long id;
    private String tarefa;
    private String hora;
    private String repetir;
    private String periodo; // dentro, fora, sem restricao


    private String inicioPeriodo; // HH:mm
    private String fimPeriodo; // HH:mm


    private Long aparelhoId;
}
