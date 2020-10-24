package comentario;

/**
 * Transfer alternativo para poder crear un nuevo comentario. Este transfer, al
 * contrario que el original, recibe el titulo de la plegaria y el email del
 * escritor, en vez de los ids.
 */

public class TComentarioAlt {

	private String email;
	private String titulo;
	private String contenido;

	public TComentarioAlt(String email, String titulo, String contenido) {
		super();
		this.email = email;
		this.titulo = titulo;
		this.contenido = contenido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
