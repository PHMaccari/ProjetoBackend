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
}
