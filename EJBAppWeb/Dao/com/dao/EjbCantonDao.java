package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;

import com.ejb.model.Canton;

@Stateless(name="ejbCantonDao")
public class EjbCantonDao implements CantonDao {

	//Unir con mi persistenciaç
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	@Override
	public Canton buscar(Canton c) {
		Canton obj = null;
		try {
			obj = em.find(Canton.class, c.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Canton c) {
		String msg="";
		try {
			em.persist(c);
			msg="Se grabo correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no guardado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String actualizar(Canton c) {
		String msg="";
		try {
			em.merge(c);
			msg="Se actualizo correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no actualizado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String eliminar(Canton c) {
		String msg="";
		try {
			Canton buscar = em.find(Canton.class, c.getId());
			em.remove(buscar);
			em.flush();
			msg="Se eliminò correctamente";
		} catch (Exception e) {
		msg="Error Dao obj no eliminado"+e.getMessage();
		}
		return msg;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Canton> listar(int id) {
		List<Canton> lista= null;
		try {
			Query q = em.createQuery("Select u from Canton u where u.thProvincia.id =:id");
			q.setParameter("id",id);
			lista = q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
			
		}
		return lista;
	}

	@Override
	public Canton buscarporId(int id) {
		Canton c = null;
		try {
			c=(Canton)em.createQuery("Select u from Canton u" + "where u.user_id= :id").setParameter("id", id)
			.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

}
