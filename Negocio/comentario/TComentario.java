package comentario;


public class TComentario {

	private int iDCo;
	private int iDEscritor;
	private int iDPlegaria;
	private String comentario;
	private boolean cumplido;
		
	public TComentario(int iDCo, int iDEscritor, int iDPlegaria,
			String comentario, boolean cumplido) {
		super();
		this.iDCo = iDCo;
		this.iDEscritor = iDEscritor;
		this.iDPlegaria = iDPlegaria;
		this.comentario = comentario;
		this.cumplido = cumplido;
	}
	
	public TComentario( int iDEscritor, int iDPlegaria,
			String comentario, boolean cumplido) {
		super();
		this.iDEscritor = iDEscritor;
		this.iDPlegaria = iDPlegaria;
		this.comentario = comentario;
		this.cumplido = cumplido;
	}
	//Añadir nuevas constructoras que seran necesarias a futuro
	public int getIDCo() {
		return iDCo;
	}
	public void setIDCo(int iDCo) {
		this.iDCo = iDCo;
	}
	public int getIDEscritor() {
		return iDEscritor;
	}
	public void setIDEscritor(int iDEscritor) {
		this.iDEscritor = iDEscritor;
	}
	public int getIDPlegaria() {
		return iDPlegaria;
	}
	public void setIDPlegaria(int iDPlegaria) {
		this.iDPlegaria = iDPlegaria;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public boolean isCumplido() {
		return cumplido;
	}
	public void setCumplido(boolean cumplido){
		this.cumplido = cumplido;
		
	}

}