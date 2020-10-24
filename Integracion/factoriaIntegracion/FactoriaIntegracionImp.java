package factoriaIntegracion;

import comentario.DAOComentario;
import comentario.DAOComentarioImp;
import creyente.DAOCreyente;
import creyente.DAOCreyenteImp;
import plegaria.DAOPlegaria;
import plegaria.DAOPlegariaImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {
	
	public FactoriaIntegracionImp(){}

	@Override
	public DAOPlegaria generarDAOPlegaria() {
		return new DAOPlegariaImp();	
	}

	@Override
	public DAOCreyente generarDAOCreyente() {
		return new DAOCreyenteImp();	
	}

	@Override
	public DAOComentario generarDAOComentario() {
		return new DAOComentarioImp();
	}
	
}