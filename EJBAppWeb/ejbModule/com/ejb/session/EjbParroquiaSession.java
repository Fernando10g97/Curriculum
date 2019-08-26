package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.ParroquiaDao;
import com.ejb.model.Parroquia;

@Stateless(name="ejbParroquiaSession")
public class EjbParroquiaSession implements ParroquiaSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private ParroquiaDao ejbParroquiaDao;
	
	@Override
	public Parroquia buscar(Parroquia pa) {
		return ejbParroquiaDao.buscar(pa);
	}

	@Override
	public String grabar(Parroquia pa) {
		return ejbParroquiaDao.grabar(pa);
	}

	@Override
	public String actualizar(Parroquia c) {
		return ejbParroquiaDao.actualizar(c);
	}

	@Override
	public String eliminar(Parroquia c) {
		return ejbParroquiaDao.eliminar(c);
	}

	@Override
	public List<Parroquia> listar(int id) {
		return ejbParroquiaDao.listar(id);
	}

	@Override
	public Parroquia buscarporId(int id) {
		return ejbParroquiaDao.buscarporId(id);
	}

}
