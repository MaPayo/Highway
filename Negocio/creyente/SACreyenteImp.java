package creyente;

import java.util.ArrayList;

import factoriaIntegracion.FactoriaIntegracion;

public class SACreyenteImp implements SACreyente {

	public SACreyenteImp() {}

	@Override
	public Integer crearCreyente(TCreyente tc) {
		int id = -1;
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		if(tc != null){
			TCreyente leido = daoc.buscarCreyentePorEmail(tc.getEmail());
			if(leido == null){ //El Creyente no se encuentra en la base de datos
				tc.setActivo(true);
				id = daoc.crearCreyente(tc); 
			}
			else{ //EL creyente se encuentra en la base de datos
				if(!leido.isActivo()){
					daoc.modificarCreyente(leido.getIDC(), "Activo", "1");
					daoc.modificarCreyente(leido.getIDC(), "Nombre", tc.getNombre());
					daoc.modificarCreyente(leido.getIDC(), "Edad", String.valueOf(tc.getEdad()));
					id = 0;
				}
			}
		}
		
		return id;
	}

	@Override
	public Boolean borrarCreyente(Integer idc) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		TCreyente borrada = daoc.mostrarCreyente(idc);
		if(borrada != null && borrada.isActivo()){ //Si el creyente ha sido encontrado y esta activo
			return daoc.borrarCreyente(idc);
		}
		return false;
	}

	@Override
	public TCreyente modificarCreyente(Integer idC, String atributo,
			Object valor) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		TCreyente modificada = daoc.mostrarCreyente(idC);
		if(modificada != null){
			boolean a = modificada.isActivo();
			if(a && !atributo.equalsIgnoreCase("email") && !atributo.equalsIgnoreCase("IDC")){
				return daoc.modificarCreyente(idC, atributo, valor);
			}
		}
		return null;
	}

	@Override
	public TCreyente mostrarCreyente(Integer idC) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		TCreyente mostrada = daoc.mostrarCreyente(idC);
		if(mostrada != null){
			return daoc.mostrarCreyente(idC);
		}
		return null;
	}

	@Override
	public ArrayList<TCreyente> mostarCreyentes() {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		return daoc.mostarCreyentes();
	}

	@Override
	public TCreyente buscarCreyentePorIDC(Integer idC) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		return daoc.mostrarCreyente(idC);
	}

	@Override
	public TCreyente buscarCreyentePorEmail(String email) {
		FactoriaIntegracion instancia = FactoriaIntegracion.getInstancia();
		DAOCreyente daoc = instancia.generarDAOCreyente();
		return daoc.buscarCreyentePorEmail(email);
	}

	
}