package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.ProvinciaDao;
import com.ejb.model.Provincia;

@Stateless(name="ejbProvinciaSession")
public class EjbProvinciaSession implements ProvinciaSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private ProvinciaDao ejbProvinciaDao;
	
	@Override
	public Provincia buscar(Provincia pr) {
		return ejbProvinciaDao.buscar(pr);
	}

	@Override
	public String grabar(Provincia pr) {
		return ejbProvinciaDao.grabar(pr);
	}

	@Override
	public String actualizar(Provincia c) {
		return ejbProvinciaDao.actualizar(c);
	}

	@Override
	public String eliminar(Provincia c) {
		return ejbProvinciaDao.eliminar(c);
	}

	@Override
	public List<Provincia> listar(int id) {
		return ejbProvinciaDao.listar(id);
	}

	@Override
	public Provincia buscarporId(int id) {
		return ejbProvinciaDao.buscarporId(id);
	}

}
