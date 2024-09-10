package br.com.fiap.jdbc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.jdbc.dao.CategoriaDAO;
import br.com.fiap.jdbc.dao.MarcaDAO;
import br.com.fiap.jdbc.dao.ProdutoDAO;
import br.com.fiap.jdbc.dao.UsuarioDAO;
import br.com.fiap.jdbc.factory.ConnectionFactory;
import br.com.fiap.jdbc.model.Categoria;
import br.com.fiap.jdbc.model.Marca;
import br.com.fiap.jdbc.model.Produto;

public class AppController {

	private static AppController instance;
	private Connection connection;
	private UsuarioDAO usuarioDAO;
	private ProdutoDAO produtoDAO;
	private MarcaDAO marcaDAO;
	private CategoriaDAO categoriaDAO;

	// Construtor privado para Singleton
	private AppController() throws SQLException {
		this.connection = ConnectionFactory.getConnection(); // Obtém a conexão diretamente
		this.usuarioDAO = new UsuarioDAO(connection);
		this.produtoDAO = new ProdutoDAO(connection);
		this.marcaDAO = new MarcaDAO(connection);
		this.categoriaDAO = new CategoriaDAO(connection);
	}

	// Método para obter a instância única do AppController
	public static AppController getInstance() throws SQLException {
		if (instance == null) {
			instance = new AppController();
		}
		return instance;
	}

	// Método para fechar a conexão quando o AppController não for mais necessário
	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	// Métodos relacionados a Usuario
	/*
	 * public void adicionarUsuario(Usuario usuario) throws SQLException {
	 * usuarioDAO.adicionar(usuario); }
	 * 
	 * public Usuario buscarUsuarioPorId(int id) throws SQLException { return
	 * usuarioDAO.buscarPorId(id); }
	 * 
	 * public void atualizarUsuario(Usuario usuario) throws SQLException {
	 * usuarioDAO.atualizar(usuario); }
	 * 
	 * public void removerUsuario(int id) throws SQLException {
	 * usuarioDAO.remover(id); }
	 * 
	 * public List<Usuario> listarUsuarios() throws SQLException { return
	 * usuarioDAO.listar(); }
	 */

	// Métodos relacionados a Produto
	public void adicionarProduto(Produto produto) throws SQLException {
		produtoDAO.adicionar(produto);
	}

	public void atualizarProduto(Produto produto) throws SQLException {
		produtoDAO.alterar(produto);
	}

	public void removerProduto(int id) throws SQLException {
		produtoDAO.excluir(id);
	}

	public List<Produto> listarProdutos() throws SQLException {
		return produtoDAO.listarTodos();
	}

	public List<Produto> listarProdutoPorCategoria(Long idCategoria) throws SQLException {
		return produtoDAO.listarProdutoPorCategoria(idCategoria);
	}

	public List<Produto> listarProdutoPorMarca(Long idMarca) throws SQLException {
		return produtoDAO.listarProdutoPorMarca(idMarca);
	}

	// Métodos relacionados a Marca
	public void adicionarMarca(Marca marca) throws SQLException {
		marcaDAO.adicionar(marca);
	}

	public void atualizarMarca(Marca marca) throws SQLException {
		marcaDAO.alterar(marca);
	}

	public void removerMarca(int id) throws SQLException {
		marcaDAO.excluir(id);
	}

	public List<Marca> listarMarcas() throws SQLException {
		return marcaDAO.listarTodos();
	}

	public List<Marca> listarMarcaComProduto() throws SQLException {
		return marcaDAO.listarMarcaComProduto();
	}

	// Métodos relacionados a Categoria
	public void adicionarCategoria(Categoria categoria) throws SQLException {
		categoriaDAO.adicionar(categoria);
	}

	public void atualizarCategoria(Categoria categoria) throws SQLException {
		categoriaDAO.alterar(categoria);
	}

	public void removerCategoria(int id) throws SQLException {
		categoriaDAO.excluir(id);
	}

	public List<Categoria> listarCategorias() throws SQLException {
		return categoriaDAO.listarTodas();
	}

	public List<Categoria> listarCategoriaComProduto() throws SQLException {
		return categoriaDAO.listarCategoriaComProduto();
	}

}
