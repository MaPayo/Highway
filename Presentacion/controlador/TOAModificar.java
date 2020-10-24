package controlador;

/**
 * Transfer adaptador de objetos que sirve recibir los campos introducidos por
 * la interfaz grafica de modificar y que el controlador pueda acceder y usarlos
 */

public class TOAModificar {
	private int id;
	private String campo;
	private Object valor;

	public TOAModificar(int id, String campo, Object valor) {
		this.id = id;
		this.campo = campo;
		this.valor = valor;
	}

	public TOAModificar(int id, Object valor) {
		super();
		this.id = id;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
