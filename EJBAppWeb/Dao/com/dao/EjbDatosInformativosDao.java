package com.dao;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ejb.model.DatosInformativos;

@Stateless(name="ejbDatosInformativosDao")
public class EjbDatosInformativosDao implements DatosInformativosDao {

	
	// Unir con mi Persistencia
	@PersistenceContext(name="persistencia")
	private EntityManager em;
	
	@Override
	public DatosInformativos buscar(DatosInformativos DatosInformativos) {
		DatosInformativos obj = null;
		try {
			obj = em.find(DatosInformativos.class, DatosInformativos.getId_Docente());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public String grabar(DatosInformativos DatosInformativos) {
		String msg = "";
		try {
			em.persist(DatosInformativos);
			msg = "Se grabo correctamente";
		} catch (Exception e) {
			//System.out.println("ERROR DAO OBJ NO GUARDADO");
			msg = "ERROR DAO OBJ NO GUARDADO";
		}
		return msg;
	}

	@Override
	public String actualizar(DatosInformativos c) {
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
	public String eliminar(DatosInformativos c) {
		String msg = "";
		try {
			DatosInformativos buscar = em.find(DatosInformativos.class, c.getId_Docente());
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
	public List<DatosInformativos> listar() {
		List<DatosInformativos> lista = null;
		try {
			Query q = em.createQuery("Select u from DatosInformativos u");
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Error ejbdao listar");
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public DatosInformativos buscarporId(int id) {
		DatosInformativos p = null;
		try {
			p = (DatosInformativos)em.createQuery("Select u from DatosInformativos u" + "where u.user_id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
