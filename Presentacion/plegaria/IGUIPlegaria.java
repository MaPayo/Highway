/**
 * 
 */
package plegaria;

import javax.swing.JFrame;

import controlador.Evento;
import presentacion.IGUI;


public abstract class IGUIPlegaria extends JFrame implements IGUI {
	
	public IGUIPlegaria(){
		super("Plegaria");
	}
	
	
	
	public void initGUI() {
	
		
	}


	public abstract void actualizar(Evento evento, Object transfer);

}