package br.com.benefrancis.domain.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TB_PIZZARIA")
public class Pizzaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PIZZARIA")
    @SequenceGenerator(name = "SQ_PIZZARIA")
    @Column(name = "ID_PIZZARIA")
    private Long id;
    private String nome;

    // no joincolumn nós definimos o nome da coluna, qual a referencia (se a gente
    // não informa com o @column, ele pega o nome do atributo, por ex: id direto)
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "TB_CARDAPIO", joinColumns = {
            @JoinColumn(name = "PIZZARIA", referencedColumnName = "ID_PIZZARIA", foreignKey = @ForeignKey(name = "FK_PIZZARIA_PRODUTO"))
    }, inverseJoinColumns = {
            @JoinColumn(name = "PRODUTO", referencedColumnName = "ID_PRODUTO", foreignKey = @ForeignKey(name = "FK_PRODUTO_PIZZARIA"))
    })
    // Se estamos trabalhando com JPA, é melhor usar SET do que LIST e o linked hash
    // set porque não permite duplicidade de produto
    private Set<Produto> cardapio = new LinkedHashSet<>();
}
