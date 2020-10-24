package factoriaPresentacion;

import menuPrincipal.GUIMenuPrincipalImp;
import comentario.GUIComentarioImp;
import creyente.GUICreyenteImp;
import plegaria.GUIPlegariaImp;
import presentacion.IGUI;


public class FactoriaPresentacionImp extends FactoriaPresentacion {

	public FactoriaPresentacionImp() {	}

	@Override
	public IGUI generarGUIPlegaria() {
		return new GUIPlegariaImp();
	}

	@Override
	public IGUI generarGUIComentario() {
		return new GUIComentarioImp();
	}

	@Override
	public IGUI generarGUICreyente() {
		return new GUICreyenteImp();
	}
	
	@Override
	public IGUI generarGUIMenuPrincipal(){
		return new GUIMenuPrincipalImp();
	}
}