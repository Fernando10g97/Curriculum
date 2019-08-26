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

import com.ejb.model.ExperienciaLaboral;
import com.ejb.session.ExperienciaLaboralSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "experiencialaboralController")
@SessionScoped

public class ExperienciaLaboralController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private ExperienciaLaboralSession ejbExperienciaLaboralSession;

	DataModel<ExperienciaLaboral> listarC;
	
	// crear un objeto listar
	public ExperienciaLaboral experiencialaboral;
	
	private int estado;
	private String mensaje;
	
	
	public ExperienciaLaboralController() {
		if (experiencialaboral != null);
		experiencialaboral = new ExperienciaLaboral();
	}
	
	
	public ExperienciaLaboral getExperienciaLaboral() {
		return experiencialaboral;
	}

	public void setExperienciaLaboral(ExperienciaLaboral experiencialaboral) {
		this.experiencialaboral = experiencialaboral;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<ExperienciaLaboral> getListarC() {
		listarC = new ListDataModel<ExperienciaLaboral>(ejbExperienciaLaboralSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<ExperienciaLaboral> listarC) {
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
		experiencialaboral = new ExperienciaLaboral();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbExperienciaLaboralSession.actualizar(experiencialaboral);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbExperienciaLaboralSession.grabar(experiencialaboral);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		experiencialaboral = new ExperienciaLaboral();
		experiencialaboral.setId(e.getComponent().getAttributes().get("experiencialaboralbuscar").hashCode());
		experiencialaboral = ejbExperienciaLaboralSession.buscar(experiencialaboral);
		
		if (experiencialaboral != null) {
			experiencialaboral.getId();
			experiencialaboral.getExperiencia();
			experiencialaboral.getInstitucion();
			experiencialaboral.getCatedra();
			experiencialaboral.getDesde();
			experiencialaboral.getHsata();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			experiencialaboral = new ExperienciaLaboral();
			experiencialaboral.setId(e.getComponent().getAttributes().get("experiencialaboraleliminar").hashCode());
			// eliminar
			mensaje = ejbExperienciaLaboralSession.eliminar(experiencialaboral);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}
