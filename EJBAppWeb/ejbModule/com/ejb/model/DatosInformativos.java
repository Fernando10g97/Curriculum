package com.ejb.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the "DATOS INFORMATIVOS" database table.
 * 
 */
@Entity
@Table(name="\"DATOS INFORMATIVOS\"")
@NamedQuery(name="DatosInformativos.findAll", query="SELECT d FROM DatosInformativos d")
public class DatosInformativos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="\"Id_Docente\"")
	private Integer id_Docente;

	@Column(name="\"Apellido\"")
	private String apellido;

	@Column(name="\"Cedula\"")
	private BigDecimal cedula;

	@Column(name="\"Convencional\"")
	private BigDecimal convencional;

	@Column(name="\"Correo_Electronico\"")
	private String correo_Electronico;

	@Column(name="\"Direccion\"")
	private String direccion;

	@Column(name="\"Estado_Civil\"")
	private String estado_Civil;

	@Temporal(TemporalType.DATE)
	@Column(name="\"Fecha_Nacimiento\"")
	private Date fecha_Nacimiento;

	@Column(name="\"Lugar_Nacimiento\"")
	private String lugar_Nacimiento;

	@Column(name="\"Nacionalidad\"")
	private String nacionalidad;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Telefono_Celular\"")
	private BigDecimal telefono_Celular;

	@Column(name="\"Tipo_Sangre\"")
	private String tipo_Sangre;

	//bi-directional many-to-one association to Capacitacion
	@ManyToOne
	private Capacitacion capacitacion;

	//bi-directional many-to-one association to ExperienciaLaboral
	@ManyToOne
	@JoinColumn(name="experiencia_id")
	private ExperienciaLaboral experienciaLaboral;

	//bi-directional many-to-one association to Publicaciones
	@ManyToOne
	@JoinColumn(name="publicaciones_id")
	private Publicaciones publicacione;

	//bi-directional many-to-one association to ReferenciaLaboral
	@ManyToOne
	@JoinColumn(name="institucion_id")
	private ReferenciaLaboral referenciaLaboral;

	//bi-directional many-to-one association to TitulosObtenidos
	@ManyToOne
	@JoinColumn(name="titulos_id")
	private TitulosObtenidos titulosObtenido;

	//bi-directional many-to-one association to Vinculacion
	@ManyToOne
	private Vinculacion vinculacion;

	public DatosInformativos() {
	}

	public Integer getId_Docente() {
		return this.id_Docente;
	}

	public void setId_Docente(Integer id_Docente) {
		this.id_Docente = id_Docente;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public BigDecimal getCedula() {
		return this.cedula;
	}

	public void setCedula(BigDecimal cedula) {
		this.cedula = cedula;
	}

	public BigDecimal getConvencional() {
		return this.convencional;
	}

	public void setConvencional(BigDecimal convencional) {
		this.convencional = convencional;
	}

	public String getCorreo_Electronico() {
		return this.correo_Electronico;
	}

	public void setCorreo_Electronico(String correo_Electronico) {
		this.correo_Electronico = correo_Electronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstado_Civil() {
		return this.estado_Civil;
	}

	public void setEstado_Civil(String estado_Civil) {
		this.estado_Civil = estado_Civil;
	}

	public Date getFecha_Nacimiento() {
		return this.fecha_Nacimiento;
	}

	public void setFecha_Nacimiento(Date fecha_Nacimiento) {
		this.fecha_Nacimiento = fecha_Nacimiento;
	}

	public String getLugar_Nacimiento() {
		return this.lugar_Nacimiento;
	}

	public void setLugar_Nacimiento(String lugar_Nacimiento) {
		this.lugar_Nacimiento = lugar_Nacimiento;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getTelefono_Celular() {
		return this.telefono_Celular;
	}

	public void setTelefono_Celular(BigDecimal telefono_Celular) {
		this.telefono_Celular = telefono_Celular;
	}

	public String getTipo_Sangre() {
		return this.tipo_Sangre;
	}

	public void setTipo_Sangre(String tipo_Sangre) {
		this.tipo_Sangre = tipo_Sangre;
	}

	public Capacitacion getCapacitacion() {
		return this.capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}

	public ExperienciaLaboral getExperienciaLaboral() {
		return this.experienciaLaboral;
	}

	public void setExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public Publicaciones getPublicacione() {
		return this.publicacione;
	}

	public void setPublicacione(Publicaciones publicacione) {
		this.publicacione = publicacione;
	}

	public ReferenciaLaboral getReferenciaLaboral() {
		return this.referenciaLaboral;
	}

	public void setReferenciaLaboral(ReferenciaLaboral referenciaLaboral) {
		this.referenciaLaboral = referenciaLaboral;
	}

	public TitulosObtenidos getTitulosObtenido() {
		return this.titulosObtenido;
	}

	public void setTitulosObtenido(TitulosObtenidos titulosObtenido) {
		this.titulosObtenido = titulosObtenido;
	}

	public Vinculacion getVinculacion() {
		return this.vinculacion;
	}

	public void setVinculacion(Vinculacion vinculacion) {
		this.vinculacion = vinculacion;
	}

}