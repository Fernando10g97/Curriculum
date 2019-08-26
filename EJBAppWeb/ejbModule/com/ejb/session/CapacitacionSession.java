package com.ejb.session;
import java.util.List;

import javax.ejb.Local;

import com.ejb.model.Capacitacion;

@Local
public interface CapacitacionSession {
	// Metodos para el CRUD
	
	public Capacitacion buscar(Capacitacion capacitacion);
	public String grabar(Capacitacion capacitacion);
	public String actualizar(Capacitacion capacitacion);
	public String eliminar(Capacitacion capacitacion);
	public List <Capacitacion> listar();
	public Capacitacion buscarporId(int id);
	
}
