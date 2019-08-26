package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.Capacitacion;

@Stateless(name="ejbCapacitacionDao")
public class EjbCapacitacionDao implements CapacitacionDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public Capacitacion buscar(Capacitacion capacitacion) {
		Capacitacion obj = null;
		try {
			obj = em.find(Capacitacion.class, capacitacion.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Capacitacion capacitacion) {
		String msg = "";
		try {
			em.persist(capacitacion);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(Capacitacion c) {
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
	public String eliminar(Capacitacion c) {
		String msg = "";
		try {
			Capacitacion buscar = em.find(Capacitacion.class, c.getId());
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
	public List<Capacitacion> listar() {
		List<Capacitacion> lista = null;
		try {
			Query q = em.createQuery("Select u from Capacitacion u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Capacitacion buscarporId(int id) {
		Capacitacion p = null;
		try {
			p = (Capacitacion)em.createQuery("Select u from Capacitacion u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
