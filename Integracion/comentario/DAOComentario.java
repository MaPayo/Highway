/**
 * 
 */
package comentario;

import java.util.ArrayList;

import comentario.TComentario;

public interface DAOComentario {
	
	public Integer crearComentario(TComentario tco);

	public Boolean cumplirComentario(int idco);

	public TComentario buscarComentarioPorIDCo(int idco);

	public TComentario modificarComentario(TComentario tco);

	public ArrayList<TComentario> mostrarComentariosPorPlegaria(int idP);

	public ArrayList<TComentario> mostrarComentariosPorEscritor(int idE);
}