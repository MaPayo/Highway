/**
 * 
 */
package plegaria;

import java.util.ArrayList;
import java.sql.*;

import plegaria.TPlegaria;

public class DAOPlegariaImp implements DAOPlegaria {

	private static final String BDURL = "jdbc:mysql://localhost:3306/highway";//añadir nombre base de datos // ruta de la base de datos
	private static final String USUARIO = "root";// usuario para la conexión con la base
										// de datos
	private static final String CONTRASENNA = "";// contraseña de la base de datos


	public DAOPlegariaImp() {// CONSTRUCTORA
	
	}
	@Override
	// Devuelve el id de la nueva plegaria
	public int crearPlegaria(TPlegaria tp) {
		int id = 0;
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			r = stmt.executeUpdate("INSERT t_plegarias (IDP, Titulo, Cuerpo, ComIncumplidos, Activo) VALUES (NULL, '"
					+ tp.getTitulo() + "', '" + tp.getContenido() + "', "+ tp.getComIncumplidos()+ " , "+ tp.isActivo()+")");
			// Comprobar que solo ha sido afectada una fila
			ResultSet rs = stmt
					.executeQuery("SELECT IDP FROM t_plegarias WHERE Titulo = '"
							+ tp.getTitulo() + "';");
			if (rs.next()) {
				id = rs.getInt(1); // Devuelve el IDP de la base de datos
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
	public Boolean borrarPlegaria(int idP) {
		int r;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			r = stmt.executeUpdate("UPDATE t_plegarias SET Activo = FALSE WHERE IDP = "
					+ idP + ";");
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

	@Override
	public TPlegaria modificarPlegaria(int idP, String atributo, Object valor) {
		TPlegaria tpn = null;
		int r;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			if(atributo.equalsIgnoreCase("titulo") || atributo.equalsIgnoreCase("cuerpo")){
				String valorcast = (String) valor;
				r = stmt.executeUpdate("UPDATE t_plegarias SET "+ atributo +" = '"+valorcast+"' WHERE IDP = "
					+ idP + ";");
			}
			if (atributo.equalsIgnoreCase("activo")|| atributo.equalsIgnoreCase("ComIncumplidos")){
				int valorcast = (int) valor;
				r = stmt.executeUpdate("UPDATE t_plegarias SET "+ atributo +" = '"+valorcast+"' WHERE IDP = "
						+ idP + ";");
			}
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_plegarias WHERE IDP = " + idP + ";");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String titulo = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tpn = new TPlegaria(id, titulo, contenido,comincumplidos, activo);
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

		return tpn;
	}

	@Override
	public TPlegaria mostrarPlegaria(int idP) {
		TPlegaria tpn = null ;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_plegarias WHERE IDP = "+ idP+ ";");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String titulo = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tpn = new TPlegaria(id, titulo, contenido,comincumplidos, activo);
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

		return tpn;
	}
	
	@Override
	public ArrayList<TPlegaria> mostrarPlegarias() {// añadir tipo al arraylist
													// (?) ArrayList<tpnlegaria>
		ArrayList<TPlegaria> tpns = new ArrayList<TPlegaria>();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_plegarias WHERE Activo = 1;");
			while (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String titulo = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				TPlegaria tpn = new TPlegaria(id, titulo, contenido,comincumplidos, activo);
				tpns.add(tpn);
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

		return tpns;
	}

	/*public TPlegaria buscarPlegariaPorIDP(int idP) {
		TPlegaria tpn = null ;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_plegarias WHERE IDP = "+ idP + ";");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String titulo = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tpn = new TPlegaria(id, titulo, contenido,comincumplidos, activo);
			
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

		return tpn;
	}
*/
	@Override
	public TPlegaria buscarPlegariaPorTitulo(String titulo) {
		TPlegaria tpn = null ;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(BDURL, USUARIO, CONTRASENNA);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_plegarias WHERE Titulo = '"+ titulo + "';");
			if (rs.next()) {
				int id = rs.getInt(1); // Devuelve el IDP de la base de datos
				String tit = rs.getString(2);
				String contenido = rs.getString(3);
				int comincumplidos = rs.getInt(4);
				boolean activo = rs.getBoolean(5);
				tpn = new TPlegaria(id, tit, contenido,comincumplidos, activo);
			
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

		return tpn;
	}


}