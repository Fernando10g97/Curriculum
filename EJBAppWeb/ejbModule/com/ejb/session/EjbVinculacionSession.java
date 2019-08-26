package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.VinculacionDao;
import com.ejb.model.Vinculacion;

@Stateless(name="ejbVinculacionSession")
public class EjbVinculacionSession implements VinculacionSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private VinculacionDao ejbVinculacionDao;
	
	@Override
	public Vinculacion buscar(Vinculacion vinculacion) {
		return ejbVinculacionDao.buscar(vinculacion);
	}

	@Override
	public String grabar(Vinculacion vinculacion) {
		return ejbVinculacionDao.grabar(vinculacion);
	}

	@Override
	public String actualizar(Vinculacion c) {
		return ejbVinculacionDao.actualizar(c);
	}

	@Override
	public String eliminar(Vinculacion c) {
		return ejbVinculacionDao.eliminar(c);
	}

	@Override
	public List<Vinculacion> listar() {
		return ejbVinculacionDao.listar();
	}

	@Override
	public Vinculacion buscarporId(int id) {
		return ejbVinculacionDao.buscarporId(id);
	}

}
