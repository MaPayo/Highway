package factoriaNegocio;

import comentario.SAComentario;
import creyente.SACreyente;
import plegaria.SAPlegaria;

public abstract class FactoriaNegocio {
	private static FactoriaNegocio instancia;

	public static FactoriaNegocio getInstancia() {
		if (instancia == null)
			instancia = new FactoriaNegocioImp();
			return instancia;
	}
	

	public abstract SAPlegaria generarSAPlegaria();

	public abstract SACreyente generarSACreyente();

	public abstract SAComentario generaSAComentario();
}