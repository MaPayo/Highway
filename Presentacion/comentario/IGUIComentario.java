package comentario;


import javax.swing.JFrame;

import controlador.Evento;
import presentacion.IGUI;

public abstract class IGUIComentario extends JFrame implements IGUI {
	
	
	public IGUIComentario(){
		super("Comentarios");
	}
	
	public static IGUI crearGUIComentarioImp(){
		return new GUIComentarioImp();
	}
	
	private TComentario tComentario;

	public void initGUI() {
		

	}

	public abstract void actualizar(Evento evento, Object transfer);
}