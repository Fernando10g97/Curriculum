package com.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.ExperienciaLaboralDao;
import com.ejb.model.ExperienciaLaboral;

@Stateless(name="ejbExperienciaLaboralSession")
public class EjbExperienciaLaboralSession implements ExperienciaLaboralSession {

	// unir con el dao los distintos metodos
	
	@EJB
	private ExperienciaLaboralDao ejbExperienciaLaboralDao;
	
	@Override
	public ExperienciaLaboral buscar(ExperienciaLaboral experiencialaboral) {
		return ejbExperienciaLaboralDao.buscar(experiencialaboral);
	}

	@Override
	public String grabar(ExperienciaLaboral experiencialaboral) {
		return ejbExperienciaLaboralDao.grabar(experiencialaboral);
	}

	@Override
	public String actualizar(ExperienciaLaboral c) {
		return ejbExperienciaLaboralDao.actualizar(c);
	}

	@Override
	public String eliminar(ExperienciaLaboral c) {
		return ejbExperienciaLaboralDao.eliminar(c);
	}

	@Override
	public List<ExperienciaLaboral> listar() {
		return ejbExperienciaLaboralDao.listar();
	}

	@Override
	public ExperienciaLaboral buscarporId(int id) {
		return ejbExperienciaLaboralDao.buscarporId(id);
	}

}
