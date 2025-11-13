package viniccius13.casa_automatica.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String tipo;


    private String descricao;


    @OneToMany(mappedBy = "categoria")
    private List<Aparelho> aparelhos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Aparelho> getAparelhos() {
        return aparelhos;
    }

    public void setAparelhos(List<Aparelho> aparelhos) {
        this.aparelhos = aparelhos;
    }
}
