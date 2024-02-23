package br.com.benefrancis.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_SABOR")
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SABOR")
    @SequenceGenerator(name = "SQ_SABOR", sequenceName = "SQ_SABOR", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_SABOR")
    private Long id;
    private String nome;
    private String descricao;

    public Sabor(){
    }

    public Sabor(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Sabor setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Sabor setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Sabor setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }


    @Override
    public String toString() {
        return "Sabor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
