package plegaria;

import java.util.ArrayList;

import plegaria.TPlegaria;

public interface DAOPlegaria {
	
	public int crearPlegaria(TPlegaria tp);

	public Boolean borrarPlegaria(int idP);

	public TPlegaria modificarPlegaria(int idp, String atributo, Object valor);

	public TPlegaria mostrarPlegaria(int idP);

	public ArrayList<TPlegaria> mostrarPlegarias();

	//public TPlegaria buscarPlegariaPorIDP(int idP);

	public TPlegaria buscarPlegariaPorTitulo(String titulo);
}