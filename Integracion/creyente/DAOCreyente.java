/**
 * 
 */
package creyente;

import java.util.ArrayList;

import creyente.TCreyente;


public interface DAOCreyente {

	public Integer crearCreyente(TCreyente tc);
	
	public Boolean borrarCreyente(int idC);

	public TCreyente modificarCreyente(int idC, String atributo, Object valor );
	
	public TCreyente mostrarCreyente(int idC);
	
	public ArrayList<TCreyente> mostarCreyentes();
	
	public TCreyente buscarCreyentePorEmail(String email);

	//public TCreyente buscarCreyentePorIDC(int idC);
}