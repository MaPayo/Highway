package plegaria;

import java.util.ArrayList;

import factoriaIntegracion.FactoriaIntegracion;

public class SAPlegariaImp implements SAPlegaria {

	public SAPlegariaImp() {
	}

	// -1 creada y activa. 0 reactivacion. id creado
	@Override
	public int crearPlegaria(TPlegaria tp) {
		int id = -1;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		if (tp != null) {
			TPlegaria leido = daop.buscarPlegariaPorTitulo(tp.getTitulo());
			if (leido == null) { // La plegaria no se encuentra en la base de
									// datos
				tp.setActivo(true);
				tp.setComIncumplidos(0);
				id = daop.crearPlegaria(tp);
			} else { // La plegaria se encuentra en la base de datos
				if (!leido.isActivo()) {
					daop.modificarPlegaria(leido.getiDP(), "Activo", 1);
					// daop.modificarPlegaria(leido.getiDP(), "Titulo",
					// tp.getTitulo());
					daop.modificarPlegaria(leido.getiDP(), "Cuerpo", tp.getContenido());
					id = 0;
				}
			}
		}

		return id;
	}

	@Override
	public Boolean borrarPlegaria(int idP) {
		boolean resultado = false;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		TPlegaria borrada = daop.mostrarPlegaria(idP);
		int comIncumplidos = borrada.getComIncumplidos();
		if (borrada != null && borrada.isActivo() && comIncumplidos == 0) {
			daop.borrarPlegaria(idP);
			resultado = true;
		}
		return resultado;
	}

	@Override
	public TPlegaria modificarPlegaria(int idP, String atributo, Object valor) {
		TPlegaria resultado = null;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		TPlegaria modificada = daop.mostrarPlegaria(idP);
		TPlegaria busqueda = daop.buscarPlegariaPorTitulo((String) valor);
		if (modificada != null) {			
			boolean a = modificada.isActivo();
			if (a && !atributo.equalsIgnoreCase("IDP")) {
				if (atributo.equalsIgnoreCase("titulo")) {
					System.out.println("Estoy dentro del if del titulo");
					System.out.println(busqueda.getComIncumplidos());
				}
				if (busqueda == null)
					resultado = daop.modificarPlegaria(idP, atributo, valor);
			}
		}
		return resultado;
	}

	@Override
	public TPlegaria mostrarPlegaria(int idP) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		return daop.mostrarPlegaria(idP);
	}

	@Override
	public ArrayList<TPlegaria> mostrarPlegarias() {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		return daop.mostrarPlegarias();
	}

	@Override
	public TPlegaria buscarPlegariaPorIDP(int idP) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		return daop.mostrarPlegaria(idP);
	}

	@Override
	public TPlegaria buscarPlegariaPorTitulo(String titulo) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		return daop.buscarPlegariaPorTitulo(titulo);
	}

}