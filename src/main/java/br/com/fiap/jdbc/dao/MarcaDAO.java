package br.com.fiap.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.jdbc.model.Marca;
import br.com.fiap.jdbc.model.Produto;

public class MarcaDAO {
	private Connection connection;

	public MarcaDAO(Connection connection) {
		this.connection = connection;
	}

	public void adicionar(Marca marca) {
		String sql = "INSERT INTO marca (nome, descricao) VALUES (?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, marca.getNome());
			stmt.setString(2, marca.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Marca marca) {
		String sql = "UPDATE marca SET nome = ?, descricao = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, marca.getNome());
			stmt.setString(2, marca.getDescricao());
			stmt.setLong(3, marca.getIdMarca());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluir(int id) {
		String sql = "DELETE FROM marca WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<Marca> listarTodos() {
		String sql = "SELECT * FROM marca";
		List<Marca> marcas = new ArrayList<>();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Marca marca = new Marca();
				rs.getLong("id");
				rs.getString("nome");
				rs.getString("descricao");
				marcas.add(marca);
			}
			return marcas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Marca> listarMarcaComProduto(){
		
		try {
			Marca marcaAtual = null;
			List<Marca> marcas = new ArrayList<Marca>();
			String sql = "SELECT M.idMarca, M.nome, M.descricao, P.idProduto, P.nome,  P.preco, P.descricao, P.idMarca, P.idCategoria "
					+ "FROM MARCA C INNER JOIN PRODUTO P ON M.idMarca = P.idMarca order by C.idMarca";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				if (marcaAtual == null || !marcaAtual.getNome().equals(rs.getString(2))) {
					Marca marca = new Marca();
					marca.setIdMarca(rs.getInt(1));
					marca.setNome(rs.getString(2));
					marca.setDescricao(rs.getString(3));
					marcas.add(marca);
					marcaAtual = marca;
				}
				Produto produto = new Produto();
				produto.setIdProduto(rs.getInt(4));
				produto.setNome(rs.getString(5));
				produto.setPreco(rs.getDouble(6));
				produto.setDescricao(rs.getString(7));
				produto.setIdMarca(rs.getInt(8));
				produto.setIdCategoria(rs.getInt(9));
				marcaAtual.adicionaProduto(produto);
			}
			rs.close();
			stmt.close();
			return marcas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
