/**
 * 
 */
package plegaria;

public class TPlegaria {
	
	private int iDP;
	private String titulo;
	private String contenido;
	private int comIncumplidos;
	private Boolean activo;
	
	public TPlegaria(int iDP, String titulo, String contenido,
			int comIncumplidos, Boolean activo) {
		super();
		this.iDP = iDP;
		this.titulo = titulo;
		this.contenido = contenido;
		this.comIncumplidos = comIncumplidos;
		this.activo = activo;
	}
	
	public TPlegaria(String titulo, String contenido){
		super();
		this.titulo = titulo;
		this.contenido = contenido;
	}
	
	//Añadir nuevas constructoras que seran necesarias a futuro
	
	public int getiDP() {
		return iDP;
	}
	public void setiDP(int iDP) {
		this.iDP = iDP;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getComIncumplidos() {
		return comIncumplidos;
	}
	public void setComIncumplidos(int comIncumplidos) {
		this.comIncumplidos = comIncumplidos;
	}
	public Boolean isActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	

	
}