package com.dao;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.TitulosObtenidos;

@Local
public interface TitulosObtenidosDao {
	public TitulosObtenidos buscar(TitulosObtenidos c);
	public String grabar(TitulosObtenidos c);
	public String actualizar(TitulosObtenidos c);
	public String eliminar(TitulosObtenidos c);
	public List <TitulosObtenidos> listar();
	public TitulosObtenidos buscarporId(int id);
}
