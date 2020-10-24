package presentacion;

import javax.swing.JFrame;

import menuPrincipal.IGUIMenuPrincipal;
import comentario.IGUIComentario;
import plegaria.IGUIPlegaria;
import controlador.Evento;
import creyente.IGUICreyente;

public interface IGUI {
	//JFrame frame = new JFrame();

	public abstract void actualizar(Evento evento, Object transfer);

}