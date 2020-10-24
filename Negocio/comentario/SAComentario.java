package comentario;

public interface SAComentario {

	public Integer crearComentario(TComentario tco);
	
	public Integer crearComentario(TComentarioAlt tcoa);

	public Boolean cumplirComentario(int idco);

	public TComentario buscarComentarioPorIDCo(int idco);

	public TComentario modificarComentario(int idco, Object valor);
	
	public TOAMostrarComentarios mostrarComentariosPorPlegaria(int idP);

	public TOAMostrarComentarios mostrarComentariosPorEscritor(int idE);

}