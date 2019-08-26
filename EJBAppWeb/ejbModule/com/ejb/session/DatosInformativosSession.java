package com.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.ejb.model.DatosInformativos;

@Local
public interface DatosInformativosSession {

	// Metodos para el crud
	public DatosInformativos buscar(DatosInformativos datosinformativos);
	public String grabar(DatosInformativos datosinformativos);
	public String actualizar(DatosInformativos datosinformativos);
	public String eliminar(DatosInformativos datosinformativos);
	public List <DatosInformativos> listar();
	public DatosInformativos buscarporId(int id);
}
