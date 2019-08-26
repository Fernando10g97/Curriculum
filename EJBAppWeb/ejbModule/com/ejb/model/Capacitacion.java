package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the capacitacion database table.
 * 
 */
@Entity
@NamedQuery(name="Capacitacion.findAll", query="SELECT c FROM Capacitacion c")
public class Capacitacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String capacitacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String institucion;

	@Column(name="numero_hora")
	private BigDecimal numeroHora;

	@Column(name="tipo_evento")
	private String tipoEvento;

	//bi-directional many-to-one association to DatosInformativos
	@OneToMany(mappedBy="capacitacion")
	private List<DatosInformativos> datosInformativos;

	public Capacitacion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCapacitacion() {
		return this.capacitacion;
	}

	public void setCapacitacion(String capacitacion) {
		this.capacitacion = capacitacion;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public BigDecimal getNumeroHora() {
		return this.numeroHora;
	}

	public void setNumeroHora(BigDecimal numeroHora) {
		this.numeroHora = numeroHora;
	}

	public String getTipoEvento() {
		return this.tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public List<DatosInformativos> getDatosInformativos() {
		return this.datosInformativos;
	}

	public void setDatosInformativos(List<DatosInformativos> datosInformativos) {
		this.datosInformativos = datosInformativos;
	}

	public DatosInformativos addDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().add(datosInformativo);
		datosInformativo.setCapacitacion(this);

		return datosInformativo;
	}

	public DatosInformativos removeDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().remove(datosInformativo);
		datosInformativo.setCapacitacion(null);

		return datosInformativo;
	}

}