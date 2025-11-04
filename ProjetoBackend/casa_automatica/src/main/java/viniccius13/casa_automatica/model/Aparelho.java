package viniccius13.casa_automatica.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aparelho {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;


    private String descricao;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


    @OneToMany(mappedBy = "aparelho", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;
}
