package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the titulos_obtenidos database table.
 * 
 */
@Entity
@Table(name="titulos_obtenidos")
@NamedQuery(name="TitulosObtenidos.findAll", query="SELECT t FROM TitulosObtenidos t")
public class TitulosObtenidos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String ciudad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_grado")
	private Date fechaGrado;

	private String nivel;

	private String titulo;

	private String universidad;

	//bi-directional many-to-one association to DatosInformativos
	@OneToMany(mappedBy="titulosObtenido")
	private List<DatosInformativos> datosInformativos;

	public TitulosObtenidos() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFechaGrado() {
		return this.fechaGrado;
	}

	public void setFechaGrado(Date fechaGrado) {
		this.fechaGrado = fechaGrado;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUniversidad() {
		return this.universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public List<DatosInformativos> getDatosInformativos() {
		return this.datosInformativos;
	}

	public void setDatosInformativos(List<DatosInformativos> datosInformativos) {
		this.datosInformativos = datosInformativos;
	}

	public DatosInformativos addDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().add(datosInformativo);
		datosInformativo.setTitulosObtenido(this);

		return datosInformativo;
	}

	public DatosInformativos removeDatosInformativo(DatosInformativos datosInformativo) {
		getDatosInformativos().remove(datosInformativo);
		datosInformativo.setTitulosObtenido(null);

		return datosInformativo;
	}

}