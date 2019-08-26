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

import com.ejb.model.Canton;
import com.ejb.model.Carpeta;
import com.ejb.model.Pais;
import com.ejb.model.Parroquia;
import com.ejb.model.Provincia;
import com.ejb.session.CantonSession;
import com.ejb.session.CarpetaSession;
import com.ejb.session.EjbParroquiaSession;
import com.ejb.session.PaisSession;
import com.ejb.session.ParroquiaSession;
import com.ejb.session.ProvinciaSession;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.media.jfxmedia.events.NewFrameEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "carpetaController")
@SessionScoped

public class CarpetaController implements Serializable {

	private static final long serialVersionUID = 1L;

	// llame o conecte con 
	@EJB
	private CarpetaSession ejbCarpetaSession;

	
	@EJB
	private PaisSession ejbPaisSession;
	@EJB
	private ProvinciaSession ejbProvinciaSession;
	@EJB
	private CantonSession ejbCantonSession;
	@EJB
	private ParroquiaSession ejbParroquiaSession;
	
	
	// crear un objeto listar
	public Carpeta carpeta;
	public Pais p; // no va
	public Provincia pr; // no va
	public int varPais=1, varProvincia=0, varCanton=0, varParroquia=0;
	
	
	// objeto para cargar la lista de carpeta
	DataModel<Carpeta> listarC;
	private ArrayList<SelectItem> PaisArray;
	private ArrayList<SelectItem> ProvinciaArray;
	private ArrayList<SelectItem> CantonArray;
	private ArrayList<SelectItem> ParroquiaArray;
	
	// areas de estudio
	List<String> areaEstudioLista = new ArrayList<String>();
	
	private int estado;
	private String mensaje;
	private String pais, provincia, canton, parroquia;
	
	
	public CarpetaController() {
		if (carpeta != null);
		carpeta = new Carpeta();
		p = new Pais(); // no va
		ProvinciaArray = new ArrayList<SelectItem>(); // no va
		pr = new Provincia(); //  no va
		CantonArray = new ArrayList<SelectItem>(); // no va
		//varPais=0;
	}

	// objetos para cargarñps en tiempo de ejecucion
	
	@PostConstruct // carga datos antes que se ejecute el controller o el form
	public void init() {
		areaEstudioLista = new ArrayList<String>();
		areaEstudioLista.add("administracion");
		areaEstudioLista.add("investigacion");
		areaEstudioLista.add("educacion");
		areaEstudioLista.add("sistemas");
	}
	
	
	
	public ArrayList<SelectItem> getPaisArray() {
		PaisArray = new ArrayList<SelectItem>();
		for (Pais obj:ejbPaisSession.listar()) {
			PaisArray.add(new SelectItem(obj.getId().toString(),obj.getDescPais()));
		}
		return PaisArray;
	}

	public void setPaisArray(ArrayList<SelectItem> paisArray) {
		PaisArray = paisArray;
	}

	public ArrayList<SelectItem> getProvinciaArray() {
		ProvinciaArray = new ArrayList<SelectItem>();
		for (Provincia obj:ejbProvinciaSession.listar(getVarPais())) {
			ProvinciaArray.add(new SelectItem(obj.getId().toString(),obj.getDescProvincia()));
		}
		return ProvinciaArray;
	}

	public void setProvinciaArray(ArrayList<SelectItem> provinciaArray) {
		ProvinciaArray = provinciaArray;
	}

	public ArrayList<SelectItem> getCantonArray() {
		CantonArray = new ArrayList<SelectItem>();
		for (Canton obj:ejbCantonSession.listar(getVarProvincia())) {
			CantonArray.add(new SelectItem(obj.getId().toString(),obj.getDescCanton()));
		}
		return CantonArray;
	}

	public void setCantonArray(ArrayList<SelectItem> cantonArray) {
		CantonArray = cantonArray;
	}

	public ArrayList<SelectItem> getParroquiaArray() {
		ParroquiaArray = new ArrayList<SelectItem>();
		for (Parroquia obj:ejbParroquiaSession.listar(getVarCanton())) {
			ParroquiaArray.add(new SelectItem(obj.getId().toString(),obj.getDescParroquia()));
		}
		return ParroquiaArray;
	}

	public void setParroquiaArray(ArrayList<SelectItem> parroquiaArray) {
		ParroquiaArray = parroquiaArray;
	}

	public List<String> getAreaEstudioLista() {
		return areaEstudioLista;
	}

	public void setAreaEstudioLista(List<String> areaEstudioLista) {
		this.areaEstudioLista = areaEstudioLista;
	}

	public Carpeta getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(Carpeta carpeta) {
		this.carpeta = carpeta;
	}

	// modificar el listar con el metodo listar del EJB
	public DataModel<Carpeta> getListarC() {
		listarC = new ListDataModel<Carpeta>(ejbCarpetaSession.listar());
		return listarC;
	}

	public void setListarC(DataModel<Carpeta> listarC) {
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
		carpeta = new Carpeta();
		estado =0;
	}
	
	public String grabar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (getEstado()==1) {
				mensaje = ejbCarpetaSession.actualizar(carpeta);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
			else {
				mensaje = ejbCarpetaSession.grabar(carpeta);
				context.addMessage(null, new FacesMessage(mensaje.toString()));
			}
		} catch (Exception e) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", e.toString()).toString();
		}
		return null;
	}
	
	public void buscar(javax.faces.event.ActionEvent e) throws Exception {
		carpeta = new Carpeta();
		carpeta.setId(e.getComponent().getAttributes().get("carpetabuscar").hashCode());
		carpeta = ejbCarpetaSession.buscar(carpeta);
		
		if (carpeta != null) {
			carpeta.getId();
			carpeta.getNombre();
			setEstado(1);
		}
	}
	
	public void eliminar(javax.faces.event.ActionEvent e)  throws Exception{ // ActionEvent = me jala los datos, atributos
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			carpeta = new Carpeta();
			carpeta.setId(e.getComponent().getAttributes().get("carpetaeliminar").hashCode());
			// eliminar
			mensaje = ejbCarpetaSession.eliminar(carpeta);
			context.addMessage(null, new FacesMessage(mensaje.toString()));
					} catch (Exception e2) {
			mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error", e2.toString()).toString();
			e2.printStackTrace();
			context.addMessage(null, new FacesMessage(mensaje.toString()));
		}
		
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public int getVarPais() {
		return varPais;
	}

	public void setVarPais(int varPais) {
		this.varPais = varPais;
	}

	public int getVarProvincia() {
		return varProvincia;
	}

	public void setVarProvincia(int varProvincia) {
		this.varProvincia = varProvincia;
	}

	public int getVarCanton() {
		return varCanton;
	}

	public void setVarCanton(int varCanton) {
		this.varCanton = varCanton;
	}

	public int getVarParroquia() {
		return varParroquia;
	}

	public void setVarParroquia(int varParroquia) {
		this.varParroquia = varParroquia;
	}
	
	
}
