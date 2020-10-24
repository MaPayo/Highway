package main;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import controlador.Controlador;
import controlador.Evento;


public class Main {

	public static void main(String[] args) {
		Controlador contrl = Controlador.getInstancia();
		
		try {
			SwingUtilities.invokeAndWait(new Runnable(){
				public void run(){
					contrl.accion(Evento.INICIALIZARMENU, null);
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//contrl.accion(Evento.INICIALIZARMENU, null);

	}

}
