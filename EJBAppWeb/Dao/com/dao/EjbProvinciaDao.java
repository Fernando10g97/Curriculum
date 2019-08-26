package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.*;

import com.ejb.model.Provincia;

@Stateless(name="ejbProvinciaDao")
public class EjbProvinciaDao implements ProvinciaDao {

	//Unir con mi persistenciaç
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	@Override
	public Provincia buscar(Provincia pr) {
		Provincia obj = null;
		try {
			obj = em.find(Provincia.class, pr.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(Provincia pr) {
		String msg="";
		try {
			em.persist(pr);
			msg="Se grabò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no guardado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String actualizar(Provincia pr) {
		String msg="";
		try {
			em.merge(pr);
			msg="Se actualizò correctamente";
		} catch (Exception e) {		
		msg="Error Dao obj no actualizado"+e.getMessage();
		}
		return msg;
	}

	@Override
	public String eliminar(Provincia pr) {
		String msg="";
		try {
			Provincia buscar = em.find(Provincia.class, pr.getId());
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
	public List<Provincia> listar(int id) {
		List<Provincia> lista= null;
		try {
			Query q = em.createQuery("Select u from Provincia u where u.thPai.id =:id").setParameter("id",id);
			lista = q.getResultList();
			
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
			
		}
		return lista;
	}

	@Override
	public Provincia buscarporId(int id) {
		Provincia pr = null;
		try {
			pr=(Provincia)em.createQuery("Select u from Provincia u where u.thPai"
					+ ""
					+ " = :id").setParameter("id", id)
			.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pr;
	}

}
