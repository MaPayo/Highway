package factoriaNegocio;

import plegaria.SAPlegaria;
import plegaria.SAPlegariaImp;
import comentario.SAComentario;
import comentario.SAComentarioImp;
import creyente.SACreyente;
import creyente.SACreyenteImp;


public class FactoriaNegocioImp extends FactoriaNegocio {
	
	public FactoriaNegocioImp(){}

	@Override
	public SAPlegaria generarSAPlegaria() {
		return new SAPlegariaImp();
	}

	@Override
	public SACreyente generarSACreyente() {
		return new SACreyenteImp();
	}

	@Override
	public SAComentario generaSAComentario() {
		return new SAComentarioImp();
	}
}