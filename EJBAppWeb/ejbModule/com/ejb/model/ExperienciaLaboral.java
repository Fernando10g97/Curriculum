package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the experiencia_laboral database table.
 * 
 */
@Entity
@Table(name="experiencia_laboral")
@NamedQuery(name="ExperienciaLaboral.findAll", query="SELECT e FROM ExperienciaLaboral e")
public class ExperienciaLaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String catedra;

	@Temporal(TemporalType.DATE)
	private Date desde;

	private String experiencia;

	@Temporal(TemporalType.DATE)
	private Date hsata;

	private String institucion;

	//bi-directional many-to-one association to DatosInformativos
	@OneToMany(mappedBy="experienciaLaboral")
	private List<DatosInformativos> datosInformativos;

	public ExperienciaLaboral() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatedra() {
		return this.catedra;
	}

	public void setCatedra(String catedra) {
		this.catedra = catedra;
	}

	public Date getDesde() {
		return this.desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public String getExperiencia() {
		return this.experiencia;
	}

	public void setExperiencia(String experiencia) {
		this.experiencia = experiencia;
	}

	public Date getHsata() {
		return this.hsata;
	}

	public void setHsata(Date hsata) {
		this.hsata = hsata;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public List<DatosInformativos> getDatosInformativos() {
		return this.datosInformativos;
	}

	public void setDatosInformativos(List<DatosInformativos> datosInformativos) {
		this.datosInformativos = datosInformativos;
	}

	public DatosInformativos addDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().add(datosInformativo);
		datosInformativo.setExperienciaLaboral(this);

		return datosInformativo;
	}

	public DatosInformativos removeDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().remove(datosInformativo);
		datosInformativo.setExperienciaLaboral(null);

		return datosInformativo;
	}

}