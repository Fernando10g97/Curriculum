package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.TitulosObtenidosDao;
import com.ejb.model.TitulosObtenidos;

@Stateless(name="ejbTitulosObtenidosSession")
public class EjbTitulosObtenidosSession implements TitulosObtenidosSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private TitulosObtenidosDao ejbTitulosObtenidosDao;
	
	@Override
	public TitulosObtenidos buscar(TitulosObtenidos titulosobtenidos) {
		return ejbTitulosObtenidosDao.buscar(titulosobtenidos);
	}

	@Override
	public String grabar(TitulosObtenidos titulosobtenidos) {
		return ejbTitulosObtenidosDao.grabar(titulosobtenidos);
	}

	@Override
	public String actualizar(TitulosObtenidos c) {
		return ejbTitulosObtenidosDao.actualizar(c);
	}

	@Override
	public String eliminar(TitulosObtenidos c) {
		return ejbTitulosObtenidosDao.eliminar(c);
	}

	@Override
	public List<TitulosObtenidos> listar() {
		return ejbTitulosObtenidosDao.listar();
	}

	@Override
	public TitulosObtenidos buscarporId(int id) {
		return ejbTitulosObtenidosDao.buscarporId(id);
	}

}
