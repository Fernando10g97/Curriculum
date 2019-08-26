package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.ExperienciaLaboral;

@Stateless(name="ejbExperienciaLaboralDao")
public class EjbExperienciaLaboralDao implements ExperienciaLaboralDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public ExperienciaLaboral buscar(ExperienciaLaboral ExperienciaLaboral) {
		ExperienciaLaboral obj = null;
		try {
			obj = em.find(ExperienciaLaboral.class, ExperienciaLaboral.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(ExperienciaLaboral ExperienciaLaboral) {
		String msg = "";
		try {
			em.persist(ExperienciaLaboral);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(ExperienciaLaboral c) {
		String msg = "";
		try {
			em.merge(c);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO ACTUALIZADO"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String eliminar(ExperienciaLaboral c) {
		String msg = "";
		try {
			ExperienciaLaboral buscar = em.find(ExperienciaLaboral.class, c.getId());
			em.remove(buscar);
			em.flush(); // Realiza de manera forzada el comando anterior
			msg = "Se elimino correctamente";
		} catch (Exception e) {
			msg = "DAO ERROR ELIMINAR"+e.getMessage();
		}
		return msg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExperienciaLaboral> listar() {
		List<ExperienciaLaboral> lista = null;
		try {
			Query q = em.createQuery("Select u from ExperienciaLaboral u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public ExperienciaLaboral buscarporId(int id) {
		ExperienciaLaboral p = null;
		try {
			p = (ExperienciaLaboral)em.createQuery("Select u from ExperienciaLaboral u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
