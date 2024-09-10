package br.com.fiap.jdbc.model;

import java.util.ArrayList;
import java.util.List;

public class Marca {

	private int id;
	private String nome, descricao;
	private List<Produto> produtos = new ArrayList<Produto>(); 

	// Construtor

	public Marca() {

	}

	public Marca(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	// Getters e Setters
	public int getIdMarca() {
		return id;
	}

	public void setIdMarca(int id) {
		this.id = id;
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

    // Método para adicionar um produto à lista
    public void adicionaProduto(Produto produto) {
        this.produtos.add(produto);
    }

}
