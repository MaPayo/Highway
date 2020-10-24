package plegaria;

import java.util.ArrayList;

public interface SAPlegaria {
	public int crearPlegaria(TPlegaria tp);

	public Boolean borrarPlegaria(int idP);

	public TPlegaria modificarPlegaria(int idP, String atributo, Object valor);

	public TPlegaria mostrarPlegaria(int idP);

	public ArrayList<TPlegaria> mostrarPlegarias();

	public TPlegaria buscarPlegariaPorIDP(int idP);

	public TPlegaria buscarPlegariaPorTitulo(String titulo);
}