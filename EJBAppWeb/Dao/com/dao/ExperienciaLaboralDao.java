package com.dao;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.ExperienciaLaboral;

@Local
public interface ExperienciaLaboralDao {
	public ExperienciaLaboral buscar(ExperienciaLaboral c);
	public String grabar(ExperienciaLaboral c);
	public String actualizar(ExperienciaLaboral c);
	public String eliminar(ExperienciaLaboral c);
	public List <ExperienciaLaboral> listar();
	public ExperienciaLaboral buscarporId(int id);
}
