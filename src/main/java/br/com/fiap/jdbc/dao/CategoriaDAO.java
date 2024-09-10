package br.com.fiap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.jdbc.model.Categoria;
import br.com.fiap.jdbc.model.Produto;

public class CategoriaDAO {

	private Connection connection;

	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void adicionar(Categoria categoria) {
		String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.setString(2, categoria.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Categoria categoria) {
		String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.setString(2, categoria.getDescricao());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM categoria WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Categoria> listarTodas() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			String sql = "select * from categoria";
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setIdCategoria(rs.getInt(1));
				categoria.setNome(rs.getString(2));
				categoria.setDescricao(rs.getString(3));
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			return categorias;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Categoria> listarCategoriaComProduto() {
		try {
			Categoria categoriaAtual = null;
			List<Categoria> categorias = new ArrayList<Categoria>();
			String sql = "SELECT C.idCategoria, C.nome, C.descricao, P.idProduto, P.nome,  P.preco, P.descricao, P.idMarca, P.idCategoria "
					+ "FROM CATEGORIA C INNER JOIN PRODUTO P ON C.idCategoria = P.idCategoria order by C.idCategoria";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				if (categoriaAtual == null || !categoriaAtual.getNome().equals(rs.getString(2))) {
					Categoria categoria = new Categoria();
					categoria.setIdCategoria(rs.getInt(1));
					categoria.setNome(rs.getString(2));
					categoria.setDescricao(rs.getString(3));
					categorias.add(categoria);
					categoriaAtual = categoria;
				}
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(4));
				produto.setNome(rs.getString(5));
				produto.setPreco(rs.getDouble(6));
				produto.setDescricao(rs.getString(7));
				produto.setIdMarca(rs.getInt(8));
				produto.setIdCategoria(rs.getInt(9));
				categoriaAtual.adicionaProduto(produto);
			}
			rs.close();
			stmt.close();
			return categorias;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
