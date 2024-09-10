package br.com.fiap.jdbc.factory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
	private static HikariDataSource dataSource;

	static {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL");
		config.setUsername("");
		config.setPassword("");
		config.setMaximumPoolSize(10); // Tamanho máximo do pool
		config.setMinimumIdle(5); // Número mínimo de conexões ociosas
		config.setIdleTimeout(60000); // Tempo em milissegundos para uma conexão ociosa ser considerada para remoção
		config.setConnectionTimeout(30000); // Tempo máximo para obter uma conexão do pool
		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close() {
		if (dataSource != null) {
			dataSource.close();
		}
	}
}
