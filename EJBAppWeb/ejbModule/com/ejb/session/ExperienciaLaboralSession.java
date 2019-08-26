package com.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.ExperienciaLaboral;

@Local
public interface ExperienciaLaboralSession {
	
	// Metodos para el crud
	public ExperienciaLaboral buscar(ExperienciaLaboral experienciaLaboral);
	public String grabar(ExperienciaLaboral experiencialaboral);
	public String actualizar(ExperienciaLaboral experiencialaboral);
	public String eliminar(ExperienciaLaboral experiencialaboral);
	public List <ExperienciaLaboral> listar();
	public ExperienciaLaboral buscarporId(int id);
}
