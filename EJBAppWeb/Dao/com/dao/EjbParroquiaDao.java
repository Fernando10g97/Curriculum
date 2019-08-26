package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;

import com.ejb.model.Parroquia;

@Stateless(name="ejbParroquiaDao")
public class EjbParroquiaDao implements ParroquiaDao {

	//Unir con mi persistenciaç
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	@Override
	public Parroquia buscar(Parroquia pa) {
		Parroquia obj = null;
		try {
			obj = em.find(Parroquia.class, pa.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Parroquia pa) {
		String msg="";
		try {
			em.persist(pa);
			msg="Se grabò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no guardado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String actualizar(Parroquia pa) {
		String msg="";
		try {
			em.merge(pa);
			msg="Se actualizò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no actualizado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String eliminar(Parroquia pa) {
		String msg="";
		try {
			Parroquia buscar = em.find(Parroquia.class, pa.getId());
			em.remove(buscar);
			em.flush();
			msg="Se eliminò correctamente";
		} catch (Exception e) {
		msg="Error Dao obj no eliminado"+e.getMessage();
		}
		return msg;
	}
	
	
	@Override
	public List<Parroquia> listar(int id) {
		List<Parroquia> lista= null;
		try {
			Query q = em.createQuery("Select u from Parroquia u where u.thCanton.id=:id");
			q.setParameter("id", id);
			lista = q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
			
		}
		return lista;
	}

	@Override
	public Parroquia buscarporId(int id) {
		Parroquia pa = null;
		try {
			pa=(Parroquia)em.createQuery("Select u from Parroquia u"+"where u.user_id = :id").setParameter("id", id)
			.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pa;
	}

}
