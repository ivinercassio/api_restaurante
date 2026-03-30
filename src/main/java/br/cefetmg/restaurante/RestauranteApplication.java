package br.cefetmg.restaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteApplication.class, args);

		/*
		 * API PENSANDO EM UMA REDE DE RESTAURANTES
		 * COM UM CARDAPIO POR DIA (CADA UM COM VARIOS PRATOS)
		 * ingrediente - várias - receitas
		 * receita - vários - ingredientes
		 * cardápio - várias - receitas
		 * restaurante - vários - cardápios (por dia)
		 * restaurantes - várias - unidades
		 */
	}

}
