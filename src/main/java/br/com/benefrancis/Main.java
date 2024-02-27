package br.com.benefrancis;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.benefrancis.domain.entity.Opcional;
import br.com.benefrancis.domain.entity.Pizzaria;
import br.com.benefrancis.domain.entity.Produto;
import br.com.benefrancis.domain.entity.Sabor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        var db = LocalDate.now().getDayOfWeek().equals(DayOfWeek.FRIDAY) ? "maria-db" : "fiap";

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(db);
        EntityManager manager = factory.createEntityManager();

        // Para usar em casa
        // EntityManagerFactory factory = Persistence.createEntityManagerFactory( db );
        // EntityManager manager = factory.createEntityManager();

        // Usar na FIAP
        // EntityManagerFactory factory = Persistence.createEntityManagerFactory( "fiap"
        // );
        // EntityManager manager = factory.createEntityManager();

        // Como nós usamos a anotação do CASCADE e tem relacionamento entre todos os
        // objetos da pizza, eu posso ignorar a persistencia de cada item e deixar
        // apenas a das pizzas, pois com a anotação ele salva tudo

//         persistir(manager);

//        Long id = Long.valueOf(JOptionPane.showInputDialog("Informe o id da Pizzaria"));
//        Pizzaria pizzaria = getPizzaria(id, manager);
//
//        System.out.println(pizzaria);

        // Consultando todas as pizzarias usando JPQL

        // var jpql = "From Pizzaria";
        var jpql = "From Pizzaria";

        manager.createQuery(jpql).getResultList().forEach(System.out::println);

        manager.close();
        factory.close();
    }

    /* selecionou o trecho de código abaixo e deu ALT + SHIFT + M e colocou dentro do método persistir */

    private static void persistir(EntityManager manager) {
        var manjericao = new Sabor(null, "Manjericao",
                "Deliciosa pizza de manjericão que fora plantado pelos mais renomados agricultores do Brasil");
        var frangoComCatupiri = new Sabor(null, "Frango com Catupiri",
                "O verdadeiro sabor do Catupiri Original faz toda a diferença nesta pizza");


        var bordaDeCatupiri = Opcional.builder()
                .nome("Borda de Catupiri")
                .preco(9.99)
                .build();

        var bordaPaozinho = Opcional.builder()
                .nome("Borda paozinho de calabresa e catupiri")
                .preco(20.00)
                .build();

        var cocaCola = Opcional.builder()
                .nome("Coca de 2 Litros")
                .preco(19.99)
                .build();

        Set<Opcional> opcionaisDaPrimeiraPizza = new LinkedHashSet<>();
        opcionaisDaPrimeiraPizza.add(bordaDeCatupiri);
        opcionaisDaPrimeiraPizza.add(cocaCola);

        Set<Opcional> opcionaisDaSegundaPizza = new LinkedHashSet<>();
        opcionaisDaSegundaPizza.add(bordaPaozinho);
        opcionaisDaSegundaPizza.add(cocaCola);

        var pizzaDeManjericao = Produto.builder()
                .nome("Pizza")
                .sabor(manjericao)
                .preco(BigDecimal.valueOf(59.99))
                .opcionais(opcionaisDaPrimeiraPizza)
                .build();

        var pizzaDeFrangoComCatupiri = Produto.builder()
                .nome("Pizza")
                .sabor(frangoComCatupiri)
                .preco(BigDecimal.valueOf(19.99))
                .opcionais(opcionaisDaSegundaPizza)
                .build();

        var cardapio = new LinkedHashSet<Produto>();

        cardapio.add(pizzaDeFrangoComCatupiri);
        cardapio.add(pizzaDeManjericao);

        Pizzaria dominus = Pizzaria.builder().nome("Dominus").cardapio(cardapio).build();
        Pizzaria nona = Pizzaria.builder().nome("Nona").cardapio(cardapio).build();

        dominus.setCardapio(cardapio);

        manager.getTransaction().begin();


        manager.persist(dominus);
        manager.persist(pizzaDeManjericao);
        manager.persist(pizzaDeFrangoComCatupiri);
        manager.getTransaction().commit();

        System.out.println("PIZZARIA: " + dominus);
        System.out.println("PIZZARIA: " + nona);
        System.out.println("SABOR: " + manjericao);
        System.out.println("PIZZA:  " + pizzaDeManjericao);
        System.out.println("PIZZA:  " + pizzaDeManjericao);
    }

    private static Pizzaria getPizzaria(Long id, EntityManager manager) {
        Pizzaria pizzaria = manager.find(Pizzaria.class, id);// pesquisa pelo ID da pizzaria
        return pizzaria;
    }
}