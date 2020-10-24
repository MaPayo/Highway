package factoriaPresentacion;

import presentacion.IGUI;


public abstract class FactoriaPresentacion {
	private static FactoriaPresentacion instancia;

	public static FactoriaPresentacion getInstancia() {
		if (instancia == null)
			instancia = new FactoriaPresentacionImp();
			return instancia;
	}
	
	public abstract IGUI generarGUIPlegaria();
	
	public abstract IGUI generarGUIComentario();

	public abstract IGUI generarGUICreyente();
	
	public abstract IGUI generarGUIMenuPrincipal();
}