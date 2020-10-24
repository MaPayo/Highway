package controlador;

import presentacion.IGUI;

public abstract class Controlador {

	private static Controlador instancia;
	
	
	public static Controlador getInstancia() {
		if (instancia == null)
			instancia = new ControladorImp();
			return instancia;
	}

	public abstract void accion(Evento evento, Object transfer);
}