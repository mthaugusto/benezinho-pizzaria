package br.com.benefrancis;

import br.com.benefrancis.domain.entity.Opcional;
import br.com.benefrancis.domain.entity.Pizzaria;
import br.com.benefrancis.domain.entity.Produto;
import br.com.benefrancis.domain.entity.Sabor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        // Para usar em casa
        // EntityManagerFactory factory = Persistence.createEntityManagerFactory( "maria-db" );
        // EntityManager manager = factory.createEntityManager();

        // Usar na FIAP
        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "fiap" );
        EntityManager manager = factory.createEntityManager();

        var manjericao = new Sabor( null, "Manjericao", "Deliciosa pizza de manjericão que fora plantado pelos mais renomados agricultores do Brasil" );
        var frangoComCatupiri = new Sabor( null, "Frango com Catupiri", "O verdadeiro sabor do Catupiri Original faz toda a diferença nesta pizza" );

        Pizzaria p1 = new Pizzaria( null, "Dominus" );

        var pizzaDeManjericao = new Produto( null, "Pizza", BigDecimal.valueOf( 59.99 ), manjericao );
        var pizzaDeFrangoComCatupiri = new Produto( null, "Pizza", BigDecimal.valueOf( 79.99 ), frangoComCatupiri );

        var op1 = new Opcional();
        op1.setNome("Borda linda de cheddar");
        op1.setPreco(9.99);

        Opcional paozinhoDeCatupiri = Opcional.builder().preco(19.99).nome("Borda pãozinho de catupiry").build();


        manager.getTransaction().begin();
        manager.persist( manjericao );
        manager.persist( frangoComCatupiri );
        manager.persist( paozinhoDeCatupiri );
        manager.persist( p1 );
        manager.persist( pizzaDeManjericao );
        manager.persist( pizzaDeFrangoComCatupiri );
        manager.getTransaction().commit();

        System.out.println( "PIZZARIA: " + p1 );
        System.out.println( "SABOR: " + manjericao );
        System.out.println( "PIZZA:  " + pizzaDeManjericao );
        System.out.println( "PIZZA:  " + pizzaDeManjericao );

        manager.close();
        factory.close();
    }
}