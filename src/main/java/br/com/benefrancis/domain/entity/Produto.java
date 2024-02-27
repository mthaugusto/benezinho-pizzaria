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
    @SequenceGenerator(
            name = "SQ_PRODUTO",
            sequenceName = "SQ_PRODUTO",
            initialValue = 1,
            allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NM_PRODUTO")
    private String nome;

    private BigDecimal preco;

    // Gang dos Manys: Nunca andam só. Por este motivo eu tenho que colocar uma
    // outra anaotação
    // Join....
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }) // quer dizer que toda vez
                                                                                              // que eu fizer uma busca
                                                                                              // no banco de dados, se
                                                                                              // ele quer que eu faça o
                                                                                              // join e já traga todos
                                                                                              // os valores de sabor
                                                                                              // também, se eu troco
                                                                                              // Eager por LAZY, eu só
                                                                                              // faria se alguem fosse
                                                                                              // consultar o valor de
                                                                                              // sabor por ex
    @JoinColumn(name = "SABOR", referencedColumnName = "ID_SABOR", foreignKey = @ForeignKey(name = "FK_PRODUTO_SABOR"))
    private Sabor sabor;

    // sempre que existe uma relação many to many, precisamos usar o jointable para criar a tabela associativa
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinTable(name = "TB_OPCIONAL_PRODUTO", joinColumns = {
            @JoinColumn(name = "PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_OPCIONAL")), // o primeiro é o nome da classe que estamos

    }, inverseJoinColumns = {
            @JoinColumn(name = "OPCIONAL", referencedColumnName = "ID_OPCIONAL", foreignKey = @ForeignKey(name = "FK_OPCIONAL_PRODUTO")) // a
                                                                                                                                         // outra
    })
    private Set<Opcional> opcionais = new LinkedHashSet<>(); // professor prefere usar set do que list | linked hashed set não permite repetição (ex, varias coca colas, varias bordas)

}
