package br.com.benefrancis.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO", initialValue = 1, allocationSize = 1)
    private Long id;
    private String nome;
    private BigDecimal preco;


    //Gang dos Manys: Nunca andam só. Por este motivo eu tenho que colocar uma outra anaotação
    //Join....
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "SABOR",
            referencedColumnName = "id"
            , foreignKey = @ForeignKey(name = "FK_PRODUTO_SABOR")
    )
    private Sabor sabor;

    @ManyToMany
    private Set<Opcional> opcionais = new LinkedHashSet<>(); // professor prefere usar set do que list | linked hashed set não permite repetição (ex, varias coca colas, varias bordas)
}
