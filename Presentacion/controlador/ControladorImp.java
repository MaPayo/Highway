package controlador;

import javax.swing.JOptionPane;

import comentario.SAComentario;
import comentario.TComentarioAlt;
import comentario.TComentario;
import comentario.TOAMostrarComentarios;
import creyente.SACreyente;
import creyente.TCreyente;
import plegaria.GUIPlegariaImp;
import plegaria.IGUIPlegaria;
import plegaria.SAPlegaria;
import plegaria.TPlegaria;
import presentacion.IGUI;
import factoriaNegocio.FactoriaNegocio;
import factoriaPresentacion.FactoriaPresentacion;

public class ControladorImp extends Controlador {

	public void controladorImp() {
	}

	public void accion(Evento evento, Object transfer) {
		FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstancia();
		FactoriaPresentacion factoriaPresentacion = FactoriaPresentacion.getInstancia();

		IGUI iGUI;

		SAPlegaria saPlegaria = factoriaNegocio.generarSAPlegaria();
		SACreyente saCreyente = factoriaNegocio.generarSACreyente();
		SAComentario saComentario = factoriaNegocio.generaSAComentario();

		TPlegaria tPlegaria;
		TCreyente tCreyente;
		TComentarioAlt tComentarioAlt;
		TComentario tComentario;
		TOAModificar toam;
		TOAMostrarComentarios toamc;

		int id;
		boolean resultado;
		switch (evento) {
		case CREARPLEGARIA:
			tPlegaria = (TPlegaria) transfer;
			id = saPlegaria.crearPlegaria(tPlegaria);
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			if (id == -1)
				iGUI.actualizar(Evento.CREARPLEGARIAFAIL, null);
			if (id == 0)
				iGUI.actualizar(Evento.CREARPLEGARIAREACTIVACION, tPlegaria);
			if (id > 0)
				iGUI.actualizar(Evento.CREARPLEGARIAOK, null);
			break;
		case BORRARPLEGARIA:
			id = (int) transfer;
			resultado = saPlegaria.borrarPlegaria(id);
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			if (resultado)
				iGUI.actualizar(Evento.BORRARPLEGARIAOK, null);
			else
				iGUI.actualizar(Evento.BORRARPLEGARIAFAIL, null);
			break;
		case MODIFICARPLEGARIA:
			toam = (TOAModificar) transfer;
			tPlegaria = saPlegaria.modificarPlegaria(toam.getId(),
					toam.getCampo(), toam.getValor());
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			if (tPlegaria != null)
				iGUI.actualizar(Evento.MODIFICARPLEGARIAOK, tPlegaria);
			else
				iGUI.actualizar(Evento.MODIFICARPLEGARIAFAIL, null);
			break;
		case MOSTRARPLEGARIA:
			id = (int) transfer;
			tPlegaria = saPlegaria.mostrarPlegaria(id);
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			
			if (tPlegaria != null)
				iGUI.actualizar(Evento.MOSTRARPLEGARIAOK, tPlegaria);
			else
				iGUI.actualizar(Evento.MOSTRARPLEGARIAFAIL, null);
			break;

		case MOSTRARPLEGARIAS:
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			iGUI.actualizar(Evento.MOSTRARPLEGARIASOK,
					saPlegaria.mostrarPlegarias());
			break;
		case CREARCREYENTE:
			tCreyente = (TCreyente) transfer;
			id = saCreyente.crearCreyente(tCreyente);
			iGUI = factoriaPresentacion.generarGUICreyente();
			if (id == -1)
				iGUI.actualizar(Evento.CREARCREYENTEFAIL, null);
			if (id == 0)
				iGUI.actualizar(Evento.CREARCREYENTEREACTIVACION, tCreyente);
			if (id > 0)
				iGUI.actualizar(Evento.CREARCREYENTEOK, tCreyente);
			break;
		case BORRARCREYENTE:
			id = (int) transfer;
			resultado = saCreyente.borrarCreyente(id);
			iGUI = factoriaPresentacion.generarGUICreyente();
			if (resultado)
				iGUI.actualizar(Evento.BORRARCREYENTEOK, null);
			else
				iGUI.actualizar(Evento.BORRARCREYENTEFAIL, null);
			break;
		case MODIFICARCREYENTE:
			toam = (TOAModificar) transfer;
			tCreyente = saCreyente.modificarCreyente(toam.getId(),
					toam.getCampo(), toam.getValor());
			iGUI = factoriaPresentacion.generarGUICreyente();
			if (tCreyente != null)
				iGUI.actualizar(Evento.MODIFICARCREYENTEOK, tCreyente);
			else
				iGUI.actualizar(Evento.MODIFICARCREYENTEFAIL, null);
			break;
		case MOSTRARCREYENTE:
			id = (int) transfer;
			tCreyente = saCreyente.mostrarCreyente(id);
			iGUI = factoriaPresentacion.generarGUICreyente();
			if (tCreyente != null)
				iGUI.actualizar(Evento.MOSTRARCREYENTEOK, tCreyente);
			else
				iGUI.actualizar(Evento.MOSTRARPLEGARIAFAIL, null);
			break;

		case MOSTRARCREYENTES:
			iGUI = factoriaPresentacion.generarGUICreyente();
			iGUI.actualizar(Evento.MOSTRARCREYENTESOK,
					saCreyente.mostarCreyentes());
			break;
		case CREARCOMENTARIO:
			tComentarioAlt = (TComentarioAlt) transfer;
			id = saComentario.crearComentario(tComentarioAlt);
			tComentario = saComentario.buscarComentarioPorIDCo(id);
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (id != -1)
				iGUI.actualizar(Evento.CREARCOMENTARIOOK, tComentario);
			else
				iGUI.actualizar(Evento.CREARCOMENTARIOFAIL, null);
			break;
		case CUMPLIRCOMENTARIO:
			id = (int) transfer;
			resultado = saComentario.cumplirComentario(id);
			tComentario = saComentario.buscarComentarioPorIDCo(id);
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (resultado)
				iGUI.actualizar(Evento.CUMPLIRCOMENTARIOOK, tComentario);
			else
				iGUI.actualizar(Evento.CUMPLIRCOMENTARIOFAIL, null);
			break;
		case MODIFICARCOMENTARIO:
			toam = (TOAModificar) transfer;
			tComentario = saComentario.modificarComentario(toam.getId(),
					toam.getValor());
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (tComentario != null)
				iGUI.actualizar(Evento.MODIFICARCOMENTARIOOK, tComentario);
			else
				iGUI.actualizar(Evento.MODIFICARCOMENTARIOFAIL, null);
			break;
		case MOSTRARCOMENTARIO:
			id = (int) transfer;
			tComentario = saComentario.buscarComentarioPorIDCo(id);
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (tComentario != null)
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOOK, tComentario);
			else
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOFAIL, null);
			break;
		case MOSTRARCOMENTARIOPORPLEGARIA:
			id = (int) transfer;
			toamc = saComentario.mostrarComentariosPorPlegaria(id);
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (toamc != null)
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOPORPLEGARIAOK, toamc);
			else
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOPORPLEGARIAFAIL, null);
			break;
		case MOSTRARCOMENTARIOPORCREYENTE:
			id = (int) transfer;
			toamc = saComentario.mostrarComentariosPorEscritor(id);
			iGUI = factoriaPresentacion.generarGUIComentario();
			if (toamc != null)
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOPORCREYENTEOK, toamc);
			else
				iGUI.actualizar(Evento.MOSTRARCOMENTARIOPORCREYENTEFAIL, null);
			break;

		case INICIALIZARMENU:
			iGUI = factoriaPresentacion.generarGUIMenuPrincipal();
			iGUI.actualizar(Evento.INICIALIZARMENU, null);
			break;
		case ABRIRVISTAPLEGARIA:
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			iGUI.actualizar(Evento.ABRIRVISTAPLEGARIA, null);
			break;
		case ABRIRVISTACREYENTE:
			iGUI = factoriaPresentacion.generarGUICreyente();
			iGUI.actualizar(Evento.ABRIRVISTACREYENTE, null);
			break;
		case ABRIRCREARPLEGARIA:
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			iGUI.actualizar(Evento.ABRIRCREARPLEGARIA, null);
			break;
		case ABRIRCREARCREYENTE:
			iGUI = factoriaPresentacion.generarGUICreyente();
			iGUI.actualizar(Evento.ABRIRCREARCREYENTE, null);
			break;
		case ABRIRCREARCOMENTARIO:
			iGUI = factoriaPresentacion.generarGUIComentario();
			iGUI.actualizar(Evento.ABRIRCREARCOMENTARIO,transfer);
			break;
		case ABRIRMODIFICARPLEGARIA:
			iGUI = factoriaPresentacion.generarGUIPlegaria();
			iGUI.actualizar(Evento.ABRIRMODIFICARPLEGARIA, transfer);
			break;
		case ABRIRMODIFICARCREYENTE:
			iGUI = factoriaPresentacion.generarGUICreyente();
			iGUI.actualizar(Evento.ABRIRMODIFICARCREYENTE, transfer);
			break;
		case ABRIRMODIFICARCOMENTARIO:
			iGUI = factoriaPresentacion.generarGUIComentario();
			iGUI.actualizar(Evento.ABRIRMODIFICARCOMENTARIO, transfer);
			break;

		default:
			JOptionPane.showMessageDialog(null,
					"ERROR DEL CONTROLADOR: Evento no conteplado.");
			break;
		}
	}
}