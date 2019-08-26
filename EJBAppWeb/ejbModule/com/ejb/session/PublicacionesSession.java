package com.ejb.session;
import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Publicaciones;

@Local
public interface PublicacionesSession {
	// Metodos para el CRUD
	
	public Publicaciones buscar(Publicaciones publicaciones);
	public String grabar(Publicaciones publicaciones);
	public String actualizar(Publicaciones publicaciones);
	public String eliminar(Publicaciones publicaciones);
	public List <Publicaciones> listar();
	public Publicaciones buscarporId(int id);
	
}
