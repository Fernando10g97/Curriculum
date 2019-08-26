package com.dao;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.DatosInformativos;

@Local
public interface DatosInformativosDao {
	public DatosInformativos buscar(DatosInformativos c);
	public String grabar(DatosInformativos c);
	public String actualizar(DatosInformativos c);
	public String eliminar(DatosInformativos c);
	public List <DatosInformativos> listar();
	public DatosInformativos buscarporId(int id);
}
