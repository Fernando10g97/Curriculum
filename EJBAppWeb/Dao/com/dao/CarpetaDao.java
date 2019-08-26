package com.dao;
import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Carpeta;

@Local
public interface CarpetaDao {
	// Metodos para el CRUD
	
	public Carpeta buscar(Carpeta carpeta);
	public String grabar(Carpeta carpeta);
	public String actualizar(Carpeta carpeta);
	public String eliminar(Carpeta carpeta);
	public List <Carpeta> listar();
	public Carpeta buscarporId(int id);
	
}
