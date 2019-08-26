package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the referencia_laboral database table.
 * 
 */
@Entity
@Table(name="referencia_laboral")
@NamedQuery(name="ReferenciaLaboral.findAll", query="SELECT r FROM ReferenciaLaboral r")
public class ReferenciaLaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String institucion;

	@Column(name="jefe_inmediato")
	private String jefeInmediato;

	private BigDecimal telefono;

	//bi-directional many-to-one association to DatosInformativos
	@OneToMany(mappedBy="referenciaLaboral")
	private List<DatosInformativos> datosInformativos;

	public ReferenciaLaboral() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getJefeInmediato() {
		return this.jefeInmediato;
	}

	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

	public List<DatosInformativos> getDatosInformativos() {
		return this.datosInformativos;
	}

	public void setDatosInformativos(List<DatosInformativos> datosInformativos) {
		this.datosInformativos = datosInformativos;
	}

	public DatosInformativos addDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().add(datosInformativo);
		datosInformativo.setReferenciaLaboral(this);

		return datosInformativo;
	}

	public DatosInformativos removeDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().remove(datosInformativo);
		datosInformativo.setReferenciaLaboral(null);

		return datosInformativo;
	}

}