package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.TitulosObtenidos;

@Stateless(name="ejbTitulosObtenidosDao")
public class EjbTitulosObtenidosDao implements TitulosObtenidosDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public TitulosObtenidos buscar(TitulosObtenidos titulosobtenidos) {
		TitulosObtenidos obj = null;
		try {
			obj = em.find(TitulosObtenidos.class, titulosobtenidos.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(TitulosObtenidos titulosobtenidos) {
		String msg = "";
		try {
			em.persist(titulosobtenidos);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(TitulosObtenidos c) {
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
	public String eliminar(TitulosObtenidos c) {
		String msg = "";
		try {
			TitulosObtenidos buscar = em.find(TitulosObtenidos.class, c.getId());
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
	public List<TitulosObtenidos> listar() {
		List<TitulosObtenidos> lista = null;
		try {
			Query q = em.createQuery("Select u from TitulosObtenidos u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public TitulosObtenidos buscarporId(int id) {
		TitulosObtenidos p = null;
		try {
			p = (TitulosObtenidos)em.createQuery("Select u from TitulosObtenidos u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
