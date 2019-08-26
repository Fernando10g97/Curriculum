package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CapacitacionDao;
import com.ejb.model.Capacitacion;

@Stateless(name="ejbCapacitacionSession")
public class EjbCapacitacionSession implements CapacitacionSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private CapacitacionDao ejbCapacitacionDao;
	
	@Override
	public Capacitacion buscar(Capacitacion capacitacion) {
		return ejbCapacitacionDao.buscar(capacitacion);
	}

	@Override
	public String grabar(Capacitacion capacitacion) {
		return ejbCapacitacionDao.grabar(capacitacion);
	}

	@Override
	public String actualizar(Capacitacion c) {
		return ejbCapacitacionDao.actualizar(c);
	}

	@Override
	public String eliminar(Capacitacion c) {
		return ejbCapacitacionDao.eliminar(c);
	}

	@Override
	public List<Capacitacion> listar() {
		return ejbCapacitacionDao.listar();
	}

	@Override
	public Capacitacion buscarporId(int id) {
		return ejbCapacitacionDao.buscarporId(id);
	}

}
