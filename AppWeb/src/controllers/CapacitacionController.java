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

import com.ejb.model.Capacitacion;
import com.ejb.session.CapacitacionSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "capacitacionController")
@SessionScoped

public class CapacitacionController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private CapacitacionSession ejbCapacitacionSession;

	DataModel<Capacitacion> listarC;
	
	// crear un objeto listar
	public Capacitacion capacitacion;
	
	private int estado;
	private String mensaje;
	
	
	public CapacitacionController() {
		if (capacitacion != null);
		capacitacion = new Capacitacion();
	}
	
	
	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<Capacitacion> getListarC() {
		listarC = new ListDataModel<Capacitacion>(ejbCapacitacionSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<Capacitacion> listarC) {
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
		capacitacion = new Capacitacion();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbCapacitacionSession.actualizar(capacitacion);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbCapacitacionSession.grabar(capacitacion);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		capacitacion = new Capacitacion();
		capacitacion.setId(e.getComponent().getAttributes().get("capacitacionbuscar").hashCode());
		capacitacion = ejbCapacitacionSession.buscar(capacitacion);
		
		if (capacitacion != null) {
			capacitacion.getId();
			capacitacion.getCapacitacion();
			capacitacion.getTipoEvento();
			capacitacion.getInstitucion();
			capacitacion.getNumeroHora();
			capacitacion.getFechaInicial();
			capacitacion.getFechaFinal();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			capacitacion = new Capacitacion();
			capacitacion.setId(e.getComponent().getAttributes().get("capacitacioneliminar").hashCode());
			// eliminar
			mensaje = ejbCapacitacionSession.eliminar(capacitacion);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}

