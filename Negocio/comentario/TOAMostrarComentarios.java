package comentario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Transfer adaptador de objetos que nos permite almacenar los comentarios
 * mapeados por el titulo de la plegaria o el nombre del creyente, segun la
 * funcion de mostrar en la que sea invocado
 */

public class TOAMostrarComentarios {

	private ArrayList<TComentario> comentarios;
	private ArrayList<String> info;

	public TOAMostrarComentarios() {
		this.comentarios = new ArrayList<TComentario>();
		this.info = new ArrayList<String>();
	}

	public void annadirComentario(TComentario tc) {
		this.comentarios.add(tc);
	}

	public void annadirInfo(String info) {
		this.info.add(info);
	}
	
	public ArrayList<TComentario> getComentarios(){
		return this.comentarios;
	}
	public ArrayList<String> getInformacion(){
		return this.info;
	}
	
	
	
	
	/*
	 * private HashMap<String, TComentario> comentarios;
	 * 
	 * public TOAMostrarComentarios() { this.comentarios = new HashMap<String,
	 * TComentario>(); }
	 * 
	 * public void annadir(String nombre, TComentario tco) {
	 * this.comentarios.put(nombre, tco); } public HashMap<String, TComentario>
	 * getComentarios(){ return (HashMap<String, TComentario>) this.comentarios;
	 * }
	 */

}
