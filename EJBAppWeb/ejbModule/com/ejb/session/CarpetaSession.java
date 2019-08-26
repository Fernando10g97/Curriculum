package com.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Carpeta;

@Local
public interface CarpetaSession {

	// Metodos para el crud
	public Carpeta buscar(Carpeta carpeta);
	public String grabar(Carpeta carpeta);
	public String actualizar(Carpeta carpeta);
	public String eliminar(Carpeta carpeta);
	public List <Carpeta> listar();
	public Carpeta buscarporId(int id);
}
