package factoriaIntegracion;

import comentario.DAOComentario;
import creyente.DAOCreyente;
import plegaria.DAOPlegaria;


public abstract class FactoriaIntegracion {
	
	private static FactoriaIntegracion instancia;

	public static FactoriaIntegracion getInstancia() {
		if(instancia == null)
			instancia = new FactoriaIntegracionImp();
		return instancia;	
	}
	
	public abstract DAOPlegaria generarDAOPlegaria();
	
	public abstract DAOCreyente generarDAOCreyente();
	
	public abstract DAOComentario generarDAOComentario();

}