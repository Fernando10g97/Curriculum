package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.DatosInformativosDao;
import com.ejb.model.DatosInformativos;

@Stateless(name="ejbDatosInformativosSession")
public class EjbDatosInformativosSession implements DatosInformativosSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private DatosInformativosDao ejbDatosInformativosDao;
	
	@Override
	public DatosInformativos buscar(DatosInformativos datosinformativos) {
		return ejbDatosInformativosDao.buscar(datosinformativos);
	}

	@Override
	public String grabar(DatosInformativos datosinformativos) {
		return ejbDatosInformativosDao.grabar(datosinformativos);
	}

	@Override
	public String actualizar(DatosInformativos c) {
		return ejbDatosInformativosDao.actualizar(c);
	}

	@Override
	public String eliminar(DatosInformativos c) {
		return ejbDatosInformativosDao.eliminar(c);
	}

	@Override
	public List<DatosInformativos> listar() {
		return ejbDatosInformativosDao.listar();
	}

	@Override
	public DatosInformativos buscarporId(int id) {
		return ejbDatosInformativosDao.buscarporId(id);
	}

}
