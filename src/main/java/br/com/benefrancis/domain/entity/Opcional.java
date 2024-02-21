package br.com.benefrancis.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.management.ConstructorParameters;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_OPCIONAL") // se não definirmos o table name, ele irá criar a tabela com o nome Opcional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Opcional {

    @Id // indicando a chave primária depois de colocar o @Entity na entidade
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OPCIONAL") // strategy = sequência dos valores | generator = nome que a sequência vai ter
    @SequenceGenerator(name="SQ_OPCIONAL", sequenceName = "SQ_OPCIONAL")
    private Long id;

    private String nome;

    private Double preco;



}
