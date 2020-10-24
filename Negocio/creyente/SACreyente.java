package creyente;

import java.util.ArrayList;

public interface SACreyente {

	public Integer crearCreyente(TCreyente tc);

	public Boolean borrarCreyente(Integer idc);

	public TCreyente modificarCreyente(Integer idC, String atributo,
			Object valor);

	public TCreyente mostrarCreyente(Integer idC);

	public ArrayList<TCreyente> mostarCreyentes();

	public TCreyente buscarCreyentePorIDC(Integer idC);

	public TCreyente buscarCreyentePorEmail(String email);

}