package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.Vinculacion;

@Stateless(name="ejbVinculacionDao")
public class EjbVinculacionDao implements VinculacionDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public Vinculacion buscar(Vinculacion vinculacion) {
		Vinculacion obj = null;
		try {
			obj = em.find(Vinculacion.class, vinculacion.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Vinculacion vinculacion) {
		String msg = "";
		try {
			em.persist(vinculacion);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(Vinculacion c) {
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
	public String eliminar(Vinculacion c) {
		String msg = "";
		try {
			Vinculacion buscar = em.find(Vinculacion.class, c.getId());
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
	public List<Vinculacion> listar() {
		List<Vinculacion> lista = null;
		try {
			Query q = em.createQuery("Select u from Vinculacion u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Vinculacion buscarporId(int id) {
		Vinculacion p = null;
		try {
			p = (Vinculacion)em.createQuery("Select u from Vinculacion u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
