package br.com.fiap.test;

import java.sql.SQLException;
import java.util.List;
import br.com.fiap.jdbc.controller.AppController;
import br.com.fiap.jdbc.model.Categoria;
import br.com.fiap.jdbc.model.Produto;

/**
 * @Author Emerson Abraham
 */
public class Teste {

	public static void main(String[] args) throws SQLException {

		AppController appController = AppController.getInstance();

		// Exemplo de listar todos os produtos
		List<Produto> produtos = appController.listarProdutos();
		produtos.forEach(System.out::println);
		

		List<Categoria> listaCategoria = appController.listarCategorias();
		for (Categoria categoria : listaCategoria) {
			System.out.println(categoria.getNome());
		}

		Produto produto1 = new Produto();
		produto1.setNome("God Of War");
		produto1.setDescricao("Jogo de aventura. Recomendação 18+");
		produto1.setPreco(10000);
		produto1.setIdCategoria(2);
		produto1.setIdMarca(1);
		appController.adicionarProduto(produto1);

	/*	Produto produto2 = new Produto();
		produto2.setNome("Smartphone Samsung");
		produto2.setDescricao("Samsung Galaxy S20");
		produto2.setIdCategoria(1L);
		produto2.setIdMarca(1L);
		appController.adicionarProduto(produto2);

		Produto produto3 = new Produto();
		produto3.setNome("iPhone");
		produto3.setDescricao("iPhone 14");
		produto3.setIdCategoria(1L);
		produto3.setIdMarca(3L);
		appController.adicionarProduto(produto2);*/

		listaCategoria = appController.listarCategoriaComProduto();
		List<Produto> listaProdutos = null;
		for (Categoria categoria : listaCategoria) {
			listaProdutos = categoria.getProdutos();
			for (Produto produtoDaCategoria : listaProdutos) {
				System.out.print("Categoria: " + categoria.getNome() + " -- ");
				System.out.println("Nome do produto: " + produtoDaCategoria.getNome());
			}
		}

		List<Produto> listaProduto = appController.listarProdutoPorCategoria(2L);
		for (Produto produto : listaProduto) {
			System.out.println(produto.getNome() + " -- " + produto.getIdCategoria());
		}

		/*
		 * listaCategoria = categoriaController.listarComProduto(); List<Produto>
		 * listaProdutos = null; for (Categoria categoria : listaCategoria) {
		 * listaProdutos = categoria.getProdutos(); for (Produto produtoDaCategoria :
		 * listaProdutos) { System.out.print("Categoria: " + categoria.getNome() +
		 * " -- "); System.out.println("Nome do produto: " +
		 * produtoDaCategoria.getNome()); } }
		 */

	}

}
