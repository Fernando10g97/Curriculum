package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.PaisDao;
import com.ejb.model.Pais;

@Stateless(name="ejbPaisSession")
public class EjbPaisSession implements PaisSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private PaisDao ejbPaisDao;
	
	@Override
	public Pais buscar(Pais p) {
		return ejbPaisDao.buscar(p);
	}

	@Override
	public String grabar(Pais p) {
		return ejbPaisDao.grabar(p);
	}

	@Override
	public String actualizar(Pais c) {
		return ejbPaisDao.actualizar(c);
	}

	@Override
	public String eliminar(Pais c) {
		return ejbPaisDao.eliminar(c);
	}

	@Override
	public List<Pais> listar() {
		return ejbPaisDao.listar();
	}

	@Override
	public Pais buscarporId(int id) {
		return ejbPaisDao.buscarporId(id);
	}

}

