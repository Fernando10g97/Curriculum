package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the vinculacion database table.
 * 
 */
@Entity
@NamedQuery(name="Vinculacion.findAll", query="SELECT v FROM Vinculacion v")
public class Vinculacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String anio;

	private String institucion;

	private String proyecto;

	//bi-directional many-to-one association to DatosInformativos
	@OneToMany(mappedBy="vinculacion")
	private List<DatosInformativos> datosInformativos;

	public Vinculacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnio() {
		return this.anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public List<DatosInformativos> getDatosInformativos() {
		return this.datosInformativos;
	}

	public void setDatosInformativos(List<DatosInformativos> datosInformativos) {
		this.datosInformativos = datosInformativos;
	}

	public DatosInformativos addDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().add(datosInformativo);
		datosInformativo.setVinculacion(this);

		return datosInformativo;
	}

	public DatosInformativos removeDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().remove(datosInformativo);
		datosInformativo.setVinculacion(null);

		return datosInformativo;
	}

}