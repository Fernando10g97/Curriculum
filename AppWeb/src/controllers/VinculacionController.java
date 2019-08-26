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

import com.ejb.model.Vinculacion;
import com.ejb.session.VinculacionSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "vinculacionController")
@SessionScoped

public class VinculacionController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private VinculacionSession ejbVinculacionSession;

	DataModel<Vinculacion> listarC;
	
	// crear un objeto listar
	public Vinculacion vinculacion;
	
	private int estado;
	private String mensaje;
	
	
	public VinculacionController() {
		if (vinculacion != null);
		vinculacion = new Vinculacion();
	}
	
	
	public Vinculacion getVinculacion() {
		return vinculacion;
	}

	public void setVinculacion(Vinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<Vinculacion> getListarC() {
		listarC = new ListDataModel<Vinculacion>(ejbVinculacionSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<Vinculacion> listarC) {
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
		vinculacion = new Vinculacion();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbVinculacionSession.actualizar(vinculacion);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbVinculacionSession.grabar(vinculacion);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		vinculacion = new Vinculacion();
		vinculacion.setId(e.getComponent().getAttributes().get("vinculacionbuscar").hashCode());
		vinculacion = ejbVinculacionSession.buscar(vinculacion);
		
		if (vinculacion != null) {
			vinculacion.getId();
			vinculacion.getProyecto();
			vinculacion.getInstitucion();
			vinculacion.getAnio();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			vinculacion = new Vinculacion();
			vinculacion.setId(e.getComponent().getAttributes().get("vinculacioneliminar").hashCode());
			// eliminar
			mensaje = ejbVinculacionSession.eliminar(vinculacion);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}
