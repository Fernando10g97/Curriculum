package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.Publicaciones;

@Stateless(name="ejbPublicacionesDao")
public class EjbPublicacionesDao implements PublicacionesDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public Publicaciones buscar(Publicaciones publicaciones) {
		Publicaciones obj = null;
		try {
			obj = em.find(Publicaciones.class, publicaciones.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Publicaciones publicaciones) {
		String msg = "";
		try {
			em.persist(publicaciones);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(Publicaciones c) {
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
	public String eliminar(Publicaciones c) {
		String msg = "";
		try {
			Publicaciones buscar = em.find(Publicaciones.class, c.getId());
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
	public List<Publicaciones> listar() {
		List<Publicaciones> lista = null;
		try {
			Query q = em.createQuery("Select u from Publicaciones u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Publicaciones buscarporId(int id) {
		Publicaciones p = null;
		try {
			p = (Publicaciones)em.createQuery("Select u from Publicaciones u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
