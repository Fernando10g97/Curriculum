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

import com.ejb.model.TitulosObtenidos;
import com.ejb.session.TitulosObtenidosSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "titulosobtenidosController")
@SessionScoped

public class TitulosObtenidosController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private TitulosObtenidosSession ejbTitulosObtenidosSession;

	DataModel<TitulosObtenidos> listarC;
	
	// crear un objeto listar
	public TitulosObtenidos titulosobtenidos;
	
	private int estado;
	private String mensaje;
	
	
	public TitulosObtenidosController() {
		if (titulosobtenidos != null);
		titulosobtenidos = new TitulosObtenidos();
	}
	
	
	public TitulosObtenidos getTitulosObtenidos() {
		return titulosobtenidos;
	}

	public void setTitulosObtenidos(TitulosObtenidos titulosobtenidos) {
		this.titulosobtenidos = titulosobtenidos;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<TitulosObtenidos> getListarC() {
		listarC = new ListDataModel<TitulosObtenidos>(ejbTitulosObtenidosSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<TitulosObtenidos> listarC) {
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
		titulosobtenidos = new TitulosObtenidos();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbTitulosObtenidosSession.actualizar(titulosobtenidos);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbTitulosObtenidosSession.grabar(titulosobtenidos);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		titulosobtenidos = new TitulosObtenidos();
		titulosobtenidos.setId(e.getComponent().getAttributes().get("titulosobtenidosbuscar").hashCode());
		titulosobtenidos = ejbTitulosObtenidosSession.buscar(titulosobtenidos);
		
		if (titulosobtenidos != null) {
			titulosobtenidos.getId();
			titulosobtenidos.getCiudad();
			titulosobtenidos.getFechaGrado();
			titulosobtenidos.getDatosInformativos();
			titulosobtenidos.getNivel();
			titulosobtenidos.getTitulo();
			titulosobtenidos.getUniversidad();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			titulosobtenidos = new TitulosObtenidos();
			titulosobtenidos.setId(e.getComponent().getAttributes().get("titulosobtenidoseliminar").hashCode());
			// eliminar
			mensaje = ejbTitulosObtenidosSession.eliminar(titulosobtenidos);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}
	
	
}
