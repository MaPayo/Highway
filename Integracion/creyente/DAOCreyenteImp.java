package creyente;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import creyente.TCreyente;

public class DAOCreyenteImp implements DAOCreyente {

	private static final String BDURL = "jdbc:mysql://localhost:3306/highway";
	private static final String USUARIO = "root";
	private static final String CONTRASENNA = "";

	public DAOCreyenteImp() {

	}

	@Override
	public Integer crearCreyente(TCreyente tc) {
		int id = -1;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			int r = stmt.executeUpdate("INSERT t_creyentes (IDC, Nombre, Email, Edad, Activo) VALUES (NULL, '"
					+ tc.getNombre() + "', '" + tc.getEmail() + "', " + tc.getEdad() + " , " + tc.isActivo() + ")");

			// Comprobar que solo ha sido afectada una fila
			ResultSet rs = stmt.executeQuery("SELECT IDC FROM t_creyentes WHERE Email = '" + tc.getEmail() + "';");
			if (rs.next()) {
				id = rs.getInt(1);
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

	public Boolean borrarCreyente(int idC) {
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			r = stmt.executeUpdate("UPDATE t_creyentes SET Activo = false WHERE IDC = " + idC + ";");
			if (r == 1) {
				return true;
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

		return false;

	}

	public TCreyente modificarCreyente(int idC, String atributo, Object valor) {
		TCreyente tcn = null;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			if(atributo.equalsIgnoreCase("Nombre")){
				String valorparse = (String)valor;
				r = stmt.executeUpdate(
						"UPDATE t_creyentes SET " + atributo + " = '" + valorparse + "' WHERE IDC = " + idC + ";");
				}
			if(atributo.equalsIgnoreCase("Edad")|| atributo.equalsIgnoreCase("Activo")){
				int valorparse = Integer.parseInt((String)valor);
				r = stmt.executeUpdate(
						"UPDATE t_creyentes SET " + atributo + " = '" + valorparse + "' WHERE IDC = " + idC + ";");
				}
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_creyentes WHERE IDC = " + idC + ";");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDC de la base de datos
				String nombre = rs.getString(2);
				String email = rs.getString(3);
				int edad = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tcn = new TCreyente(id, nombre, email, edad, activo);
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

		return tcn;
	}

	public TCreyente mostrarCreyente(int idC) {
		TCreyente tcn = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_creyentes WHERE IDC = " + idC + ";");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDC de la base de datos
				String nombre = rs.getString(2);
				String email = rs.getString(3);
				int edad = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tcn = new TCreyente(id, nombre, email, edad, activo);
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

		return tcn;
	}

	public ArrayList<TCreyente> mostarCreyentes() {
		ArrayList<TCreyente> tcs = new ArrayList<TCreyente>();
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_creyentes WHERE Activo = 1 ;");
			while (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDC de la base de datos
				String titulo = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				TCreyente tc = new TCreyente(id, titulo, contenido, comincumplidos, activo);
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

	public TCreyente buscarCreyentePorEmail(String email) {
		TCreyente tcn = null;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_creyentes WHERE Email = '" + email + "';");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String nombre = rs.getString(2);
				String emilio = rs.getString(3);
				int edad = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tcn = new TCreyente(id, nombre, emilio, edad, activo);

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

		return tcn;
	}

	/*
	 * public TCreyente buscarCreyentePorIDC(int idC) { TCreyente tcn = null ;
	 * int r; Connection conn = null; Statement stmt = null; try { conn =
	 * DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA); stmt =
	 * conn.createStatement(); ResultSet rs =
	 * stmt.executeQuery("SELECT * FROM t_creyente WHERE IDC = "+ idC + ";"); if
	 * (rs.next()) { int id = rs.getInt(1); // Devuelve el IDC de la base de
	 * datos String nombre = rs.getString(2); String email = rs.getString(3);
	 * int edad = rs.getInt(4); boolean activo = rs.getBoolean(5); tcn = new
	 * TCreyente(id, nombre, email, edad, activo);
	 * 
	 * } } catch (SQLException e) { e.printStackTrace(); } finally { try { if
	 * (stmt != null) stmt.close(); } catch (SQLException e2) { } try { if (conn
	 * != null) conn.close(); } catch (SQLException e3) { e3.printStackTrace();
	 * } }
	 * 
	 * return tcn; }
	 */
}