package creyente;


public class TCreyente {

	private int iDC;
	private String nombre;
	private String email;
	private int edad;
	private Boolean activo;
	
	public TCreyente(int iDC,  String nombre, String email, int edad,
			Boolean activo) {
		super();
		this.iDC = iDC;
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
		this.activo = activo;
	}
	//Añadir nuevas constructoras que seran necesarias a futuro
	
	public TCreyente(String nombre, String email, int edad) {
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
	}

	public Integer getIDC() {
		return iDC;
	}
	public void setIDC(Integer iDC) {
		this.iDC = iDC;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean isActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
}