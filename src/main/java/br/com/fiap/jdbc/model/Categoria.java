package br.com.fiap.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int idCategoria;
	private String nome, descricao;
	private List<Produto> produtos = new ArrayList<Produto>();

	public Categoria() {

	}

	public Categoria(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void adicionaProduto(Produto produto) {
		produtos.add(produto);
	}

}
