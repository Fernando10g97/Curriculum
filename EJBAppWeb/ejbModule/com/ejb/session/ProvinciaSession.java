package com.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Provincia;

@Local
public interface ProvinciaSession {

	public Provincia buscar(Provincia pr);
	public String grabar(Provincia pr);
	public String actualizar(Provincia pr);
	public String eliminar(Provincia pr);
	public List <Provincia> listar(int id);
	public Provincia buscarporId(int id);
}
