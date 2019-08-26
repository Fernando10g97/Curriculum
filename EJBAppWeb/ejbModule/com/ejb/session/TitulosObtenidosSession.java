package com.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.TitulosObtenidos;

@Local
public interface TitulosObtenidosSession {

	// Metodos para el crud
	public TitulosObtenidos buscar(TitulosObtenidos titulosobtenidos);
	public String grabar(TitulosObtenidos titulosobtenidos);
	public String actualizar(TitulosObtenidos titulosobtenidos);
	public String eliminar(TitulosObtenidos titulosobtenidos);
	public List <TitulosObtenidos> listar();
	public TitulosObtenidos buscarporId(int id);
}
