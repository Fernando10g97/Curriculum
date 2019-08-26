package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import com.ejb.model.DatosInformativos;
import com.ejb.session.DatosInformativosSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "datosinformativosController")
@SessionScoped

public class DatosInformativosController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private DatosInformativosSession ejbDatosInformativosSession;

	DataModel<DatosInformativos> listarC;
	
	// crear un objeto listar
	public DatosInformativos datosinformativos;
	
	private int estado;
	private String mensaje;
	
	
	public DatosInformativosController() {
		if (datosinformativos != null);
		datosinformativos = new DatosInformativos();
	}
	
	
	public DatosInformativos getDatosInformativos() {
		return datosinformativos;
	}

	public void setDatosInformativos(DatosInformativos datosinformativos) {
		this.datosinformativos = datosinformativos;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<DatosInformativos> getListarC() {
		listarC = new ListDataModel<DatosInformativos>(ejbDatosInformativosSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<DatosInformativos> listarC) {
		this.listarC = listarC;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	public void limpiar() {
		datosinformativos = new DatosInformativos();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbDatosInformativosSession.actualizar(datosinformativos);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbDatosInformativosSession.grabar(datosinformativos);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		datosinformativos = new DatosInformativos();
		datosinformativos.setId_Docente(e.getComponent().getAttributes().get("datosinformativosbuscar").hashCode());
		datosinformativos = ejbDatosInformativosSession.buscar(datosinformativos);
		
		if (datosinformativos != null) {
			datosinformativos.getId_Docente();
			datosinformativos.getNombre();
			datosinformativos.getApellido();
			datosinformativos.getFecha_Nacimiento();
			datosinformativos.getCapacitacion();
			datosinformativos.getCedula();
			datosinformativos.getTelefono_Celular();
			datosinformativos.getConvencional();
			datosinformativos.getCorreo_Electronico();
			datosinformativos.getLugar_Nacimiento();
			datosinformativos.getNacionalidad();
			datosinformativos.getTipo_Sangre();
			datosinformativos.getDireccion();
			datosinformativos.getEstado_Civil();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			datosinformativos = new DatosInformativos();
			datosinformativos.setId_Docente(e.getComponent().getAttributes().get("datosinformativoseliminar").hashCode());
			// eliminar
			mensaje = ejbDatosInformativosSession.eliminar(datosinformativos);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}
