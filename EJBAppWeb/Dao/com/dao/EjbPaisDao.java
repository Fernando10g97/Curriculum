package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;

import com.ejb.model.Pais;

@Stateless(name="ejbPaisDao")
public class EjbPaisDao implements PaisDao {

	//Unir con mi persistenciaç
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	@Override
	public Pais buscar(Pais p) {
		Pais obj = null;
		try {
			obj = em.find(Pais.class, p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Pais p) {
		String msg="";
		try {
			em.persist(p);
			msg="Se grabò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no guardado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String actualizar(Pais p) {
		String msg="";
		try {
			em.merge(p);
			msg="Se actualizò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no actualizado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String eliminar(Pais p) {
		String msg="";
		try {
			Pais buscar = em.find(Pais.class, p.getId());
			em.remove(buscar);
			em.flush();
			msg="Se eliminò correctamente";
		} catch (Exception e) {
		msg="Error Dao obj no eliminado"+e.getMessage();
		}
		return msg;
	}
	
	
	@Override
	public List<Pais> listar() {
		List<Pais> lista= null;
		try {
			Query q = em.createQuery("Select u from Pais u");
			lista = q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
			
		}
		return lista;
	}

	@Override
	public Pais buscarporId(int id) {
		Pais p = null;
		try {
			p=(Pais)em.createQuery("Select u from Pais u"+"where u.user_id = :id").setParameter("id", id)
			.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
