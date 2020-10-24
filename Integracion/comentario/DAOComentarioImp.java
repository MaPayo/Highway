/**
 * 
 */
package comentario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*Preguntar informacion a devolver de Creyentes y Plegarias en TComentario*/

import comentario.TComentario;
import creyente.TCreyente;

public class DAOComentarioImp implements DAOComentario {
	private static final String BDURL = "jdbc:mysql://localhost:3306/highway";
	private static final String USUARIO = "root";
	private static final String CONTRASENNA = "";

	public DAOComentarioImp() {
	}

	@Override
	public Integer crearComentario(TComentario tco) {
		int id = 0;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
				r = stmt.executeUpdate(
						"INSERT t_comentarios (IDCo, IDEscritor, IDPlegaria, Comentario, Cumplido) VALUES (NULL, "
								+ tco.getIDEscritor() + ", " + tco.getIDPlegaria() + ", '" + tco.getComentario()
								+ "' , " + tco.isCumplido() + ")");
				ResultSet rs = stmt.executeQuery("SELECT IDCo FROM t_comentarios WHERE IDEscritor = " + tco.getIDEscritor()
						+ " AND IDPlegaria = " + tco.getIDPlegaria() + " AND Comentario = '" + tco.getComentario()
						+ "';");
				if (rs.next()) {
					id = rs.getInt(1); // Devuelve el IDP de la base de
										// datos
				}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return id;
	}

	@Override
	public Boolean cumplirComentario(int idco) {
		boolean resultado = false;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			r = stmt.executeUpdate("UPDATE t_comentarios SET Cumplido = true WHERE IDCo = " + idco + ";");
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return resultado;

	}

	@Override
	public TComentario modificarComentario(TComentario tco) {
		TComentario tcon = null;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			r = stmt.executeUpdate("UPDATE t_comentarios SET Comentario = '" + tco.getComentario() + "' WHERE IDCo = "
					+ tco.getIDCo() + ";");
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_comentarios WHERE IDCo = " + tco.getIDCo() + ";");
			if (rs.next()) {
				int idco = rs.getInt(1); // Devuelve el IDC de la base de datos
				int ide = rs.getInt(2);
				int idp = rs.getInt(3);
				String comentario = rs.getString(4);
				boolean cumplido = rs.getBoolean(5);
				tcon = new TComentario(idco, ide, idp, comentario, cumplido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return tcon;
	}

	@Override
	public ArrayList<TComentario> mostrarComentariosPorPlegaria(int idP) {
		ArrayList<TComentario> tcs = new ArrayList<TComentario>();
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM t_comentarios WHERE IDPlegaria = " + idP + " ORDER BY IDCo;");
			while (rs.next()) {
				int idco = rs.getInt(1); // Devuelve el IDC de la base de datos
				int ide = rs.getInt(2);
				int idp = rs.getInt(3);
				String comentario = rs.getString(4);
				boolean cumplido = rs.getBoolean(5);
				TComentario tc = new TComentario(idco, ide, idp, comentario, cumplido);
				tcs.add(tc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return tcs;
		// end-user-code
	}

	@Override
	public ArrayList<TComentario> mostrarComentariosPorEscritor(int idE) {
		ArrayList<TComentario> tcs = new ArrayList<TComentario>();
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT * FROM t_comentarios WHERE IDEscritor = " + idE + " ORDER BY IDCo ;");
			while (rs.next()) {
				int idco = rs.getInt(1); // Devuelve el IDC de la base de datos
				int ide = rs.getInt(2);
				int idp = rs.getInt(3);
				String comentario = rs.getString(4);
				boolean cumplido = rs.getBoolean(5);
				TComentario tc = new TComentario(idco, ide, idp, comentario, cumplido);
				tcs.add(tc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return tcs;
	}

	@Override
	/*
	 * To do: Ver si esta query consigue la informacion del nombre y la plegaria
	 */
	public TComentario buscarComentarioPorIDCo(int idco) {
		TComentario tcon = null;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_comentarios WHERE IDCo = " + idco + ";");
			if (rs.next()) {
				int idc = rs.getInt(1); // Devuelve el IDC de la base de datos
				int ide = rs.getInt(2);
				int idp = rs.getInt(3);
				String comentario = rs.getString(4);
				boolean cumplido = rs.getBoolean(5);
				tcon = new TComentario(idc, ide, idp, comentario, cumplido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		return tcon;
	}

}