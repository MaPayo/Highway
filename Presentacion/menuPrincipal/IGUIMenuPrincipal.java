package menuPrincipal;

import javax.swing.JFrame;

import menuPrincipal.GUIMenuPrincipalImp;
import presentacion.IGUI;
import controlador.Evento;

public abstract class IGUIMenuPrincipal extends JFrame implements IGUI {
	
	public IGUIMenuPrincipal(){
		super("Menu");
	}
	
	public static IGUI crearGUIMenuPrincipalImp(){
		return new GUIMenuPrincipalImp();
	}

	
	public void initGUI(){
		
	}

	@Override
	public abstract void actualizar(Evento evento, Object transfer);

}
