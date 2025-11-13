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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Aparelho getAparelho() {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }
}
