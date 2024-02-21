package br.com.benefrancis.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_PIZZARIA")
public class Pizzaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PIZZARIA")
    @SequenceGenerator(name = "SQ_PIZZARIA")
    private Long id;
    private String nome;

    public Pizzaria() {
    }

    public Pizzaria(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public Pizzaria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Pizzaria setNome(String nome) {
        this.nome = nome;
        return this;
    }


    @Override
    public String toString() {
        return "Pizzaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
