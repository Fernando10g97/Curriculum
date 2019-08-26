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

import com.ejb.model.Publicaciones;
import com.ejb.session.PublicacionesSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "publicacionesController")
@SessionScoped

public class PublicacionesController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private PublicacionesSession ejbPublicacionesSession;

	DataModel<Publicaciones> listarC;
	
	// crear un objeto listar
	public Publicaciones publicaciones;
	
	private int estado;
	private String mensaje;
	
	
	public PublicacionesController() {
		if (publicaciones != null);
		publicaciones = new Publicaciones();
	}
	
	
	public Publicaciones getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(Publicaciones publicaciones) {
		this.publicaciones = publicaciones;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<Publicaciones> getListarC() {
		listarC = new ListDataModel<Publicaciones>(ejbPublicacionesSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<Publicaciones> listarC) {
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
		publicaciones = new Publicaciones();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbPublicacionesSession.actualizar(publicaciones);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbPublicacionesSession.grabar(publicaciones);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		publicaciones = new Publicaciones();
		publicaciones.setId(e.getComponent().getAttributes().get("publicacionesbuscar").hashCode());
		publicaciones = ejbPublicacionesSession.buscar(publicaciones);
		
		if (publicaciones != null) {
			publicaciones.getId();
			publicaciones.getTipoPublicacion();
			publicaciones.getEditorial();
			publicaciones.getEditorial();
			publicaciones.getAnio();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			publicaciones = new Publicaciones();
			publicaciones.setId(e.getComponent().getAttributes().get("publicacioneseliminar").hashCode());
			// eliminar
			mensaje = ejbPublicacionesSession.eliminar(publicaciones);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}
