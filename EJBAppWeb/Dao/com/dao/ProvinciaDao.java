package com.dao;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Provincia;

@Local
public interface ProvinciaDao {

	public Provincia buscar(Provincia pr);
	public String grabar(Provincia pr);
	public String actualizar(Provincia pr);
	public String eliminar(Provincia pr);
	public List <Provincia> listar(int id);
	public Provincia buscarporId(int id);
}
