package viniccius13.casa_automatica.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String tarefa; // Ex: "ligar aspirador de pó por 10 minutos"


    private String hora; // Ex: "16:20"


    private String repetir; // Ex: "segunda"


    private String periodo; // Ex: "dentro" ou "fora"


    private String situacao; // Ex: "permitida" ou "fora-do-periodo"


    @ManyToOne
    @JoinColumn(name = "aparelho_id", nullable = false)
    private Aparelho aparelho;
}
