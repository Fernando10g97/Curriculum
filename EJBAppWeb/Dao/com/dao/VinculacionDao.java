package com.dao;
import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Vinculacion;

@Local
public interface VinculacionDao {
	// Metodos para el CRUD
	
	public Vinculacion buscar(Vinculacion vinculacion);
	public String grabar(Vinculacion vinculacion);
	public String actualizar(Vinculacion vinculacion);
	public String eliminar(Vinculacion vinculacion);
	public List <Vinculacion> listar();
	public Vinculacion buscarporId(int id);
	
}
