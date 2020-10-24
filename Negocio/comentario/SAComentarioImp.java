/**
 * 
 */
package comentario;

import java.util.ArrayList;

import creyente.DAOCreyente;
import creyente.TCreyente;
import plegaria.DAOPlegaria;
import plegaria.TPlegaria;
import factoriaIntegracion.FactoriaIntegracion;

public class SAComentarioImp implements SAComentario {

	public SAComentarioImp() {
	}

	// Darle otra vuelta a lo de comentarios incumplidos, demasiado sueño
	@Override
	public Integer crearComentario(TComentario tco) {
		int id = -1;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoc = instancia.generarDAOComentario();
		if (tco != null) {
			id = daoc.crearComentario(tco);
		}
		return id;
	}

	@Override
	public Integer crearComentario(TComentarioAlt tcoa) {
		int id = -1;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		DAOComentario daoco = instancia.generarDAOComentario();
		TPlegaria pleg = daop.buscarPlegariaPorTitulo(tcoa.getTitulo());
		TCreyente crey = daoc.buscarCreyentePorEmail(tcoa.getEmail());
		if (pleg != null && pleg.isActivo() && crey != null && crey.isActivo()) {
			TComentario com = new TComentario(crey.getIDC(), pleg.getiDP(),
					tcoa.getContenido(), false);
			pleg.setComIncumplidos(pleg.getComIncumplidos() + 1);
			id = daoco.crearComentario(com);
			daop.modificarPlegaria(pleg.getiDP(), "ComIncumplidos",
					pleg.getComIncumplidos());
		
		}

		return id;
	}

	@Override
	public Boolean cumplirComentario(int idco) {
		boolean resultado = false;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoc = instancia.generarDAOComentario();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		TComentario borrado = daoc.buscarComentarioPorIDCo(idco);
		TPlegaria pleg = daop.mostrarPlegaria(borrado.getIDPlegaria());
		if (borrado != null && !borrado.isCumplido()) { //El comentario existe y esta sin cumplir 
			resultado = daoc.cumplirComentario(idco);
			pleg.setComIncumplidos(pleg.getComIncumplidos() - 1);
			daop.modificarPlegaria(pleg.getiDP(), "ComIncumplidos",
					pleg.getComIncumplidos());
		}
		return resultado;
	}

	@Override
	public TComentario buscarComentarioPorIDCo(int idco) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoc = instancia.generarDAOComentario();
		return daoc.buscarComentarioPorIDCo(idco);
		
	}

	@Override
	public TComentario modificarComentario(int idco, Object valor) {
		TComentario com = null;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoc = instancia.generarDAOComentario();
		TComentario modificado = daoc.buscarComentarioPorIDCo(idco);
		String cuerpo = (String) valor;
		if (modificado != null) {
			modificado.setComentario(cuerpo);
			com = daoc.modificarComentario(modificado);
		}
		return com;
	}

	@Override
	public TOAMostrarComentarios mostrarComentariosPorPlegaria(int idP) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoco = instancia.generarDAOComentario();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		ArrayList<TComentario> tcos = daoco.mostrarComentariosPorPlegaria(idP);
		TOAMostrarComentarios toa = new TOAMostrarComentarios();
		for (TComentario tCs : tcos) {
			toa.annadirComentario(tCs);
		}
		for(int i = 0; i < tcos.size(); i++){
			toa.annadirInfo(daoc.mostrarCreyente(tcos.get(i).getIDEscritor()).getNombre());
		}
		
		return toa;
	}

	@Override
	public TOAMostrarComentarios mostrarComentariosPorEscritor(int idE) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOComentario daoco = instancia.generarDAOComentario();
		DAOPlegaria daop = instancia.generarDAOPlegaria();
		ArrayList<TComentario> tcos = daoco.mostrarComentariosPorEscritor(idE);
		TOAMostrarComentarios toa = new TOAMostrarComentarios();
		for (TComentario tCs : tcos) {
			toa.annadirComentario(tCs);
		}
		for(int i = 0; i < tcos.size(); i++){
			toa.annadirInfo(daop.mostrarPlegaria(tcos.get(i).getIDPlegaria()).getTitulo());
		}

		return toa;
	}
}
