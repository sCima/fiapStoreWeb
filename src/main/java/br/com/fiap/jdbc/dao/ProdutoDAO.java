package br.com.fiap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.jdbc.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void adicionar(Produto produto) {
		try {
			String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO, idMarca, idCategoria) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getIdMarca());
			stmt.setInt(5, produto.getIdCategoria());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void alterar(Produto produto) {
		try {
			String sql = "UPDATE PRODUTO P SET P.nome = ?, P.descricao = ?, P.preco=?, P.idMarca=?, P.idCategoria=? WHERE idProduto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getIdMarca());
			stmt.setInt(5, produto.getIdCategoria());
			stmt.setInt(6, produto.getIdProduto());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(int id) {
		try {
			String sql = "DELETE FROM PRODUTO WHERE idProduto = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String sql = "SELECT * FROM PRODUTO";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				produto.setPreco(rs.getDouble(4));
				produto.setIdMarca(rs.getInt(5));
				produto.setIdCategoria(rs.getInt(6));
				produtos.add(produto);
			}
			stmt.close();
			rs.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> listarProdutoPorCategoria(Long idCategoria) {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String sql = "SELECT * FROM PRODUTO WHERE idCategoria = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, idCategoria);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				produto.setPreco(rs.getDouble(4));
				produto.setIdMarca(rs.getInt(5));
				produto.setIdCategoria(rs.getInt(6));
				produtos.add(produto);
			}
			stmt.close();
			rs.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> listarProdutoPorMarca(Long idMarca) {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String sql = "SELECT * FROM PRODUTO WHERE idMarca = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, idMarca);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(1));
				produto.setNome(rs.getString(2));
				produto.setDescricao(rs.getString(3));
				produto.setPreco(rs.getDouble(4));
				produto.setIdMarca(rs.getInt(5));
				produto.setIdCategoria(rs.getInt(6));
				produtos.add(produto);
			}
			stmt.close();
			rs.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
