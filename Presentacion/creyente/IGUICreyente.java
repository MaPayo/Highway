/**
 * 
 */
package creyente;


import javax.swing.JFrame;

import controlador.Evento;
import presentacion.IGUI;

public abstract class IGUICreyente extends JFrame implements IGUI {
	
	public IGUICreyente(){
		super("Creyente");
	}
	
	public void initGUI() {
		
	}

	
	public abstract void actualizar(Evento evento, Object transfer);
}