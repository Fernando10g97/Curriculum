package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.PublicacionesDao;
import com.ejb.model.Publicaciones;

@Stateless(name="ejbPublicacionesSession")
public class EjbPublicacionesSession implements PublicacionesSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private PublicacionesDao ejbPublicacionesDao;
	
	@Override
	public Publicaciones buscar(Publicaciones publicaciones) {
		return ejbPublicacionesDao.buscar(publicaciones);
	}

	@Override
	public String grabar(Publicaciones publicaciones) {
		return ejbPublicacionesDao.grabar(publicaciones);
	}

	@Override
	public String actualizar(Publicaciones c) {
		return ejbPublicacionesDao.actualizar(c);
	}

	@Override
	public String eliminar(Publicaciones c) {
		return ejbPublicacionesDao.eliminar(c);
	}

	@Override
	public List<Publicaciones> listar() {
		return ejbPublicacionesDao.listar();
	}

	@Override
	public Publicaciones buscarporId(int id) {
		return ejbPublicacionesDao.buscarporId(id);
	}

}
