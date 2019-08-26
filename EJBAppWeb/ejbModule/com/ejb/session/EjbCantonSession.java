package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CantonDao;
import com.ejb.model.Canton;

@Stateless(name="ejbCantonSession")
public class EjbCantonSession implements CantonSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private CantonDao ejbCantonDao;
	
	@Override
	public Canton buscar(Canton c) {
		return ejbCantonDao.buscar(c);
	}

	@Override
	public String grabar(Canton c) {
		return ejbCantonDao.grabar(c);
	}

	@Override
	public String actualizar(Canton c) {
		return ejbCantonDao.actualizar(c);
	}

	@Override
	public String eliminar(Canton c) {
		return ejbCantonDao.eliminar(c);
	}

	@Override
	public List<Canton> listar(int id) {
		return ejbCantonDao.listar(id);
	}

	@Override
	public Canton buscarporId(int id) {
		return ejbCantonDao.buscarporId(id);
	}

}
