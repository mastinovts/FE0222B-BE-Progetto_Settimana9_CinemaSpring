package com.cinemaspring.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.cinemaspring.dao.FilmDAO;
import com.cinemaspring.model.Film;
import com.cinemaspring.util.JpaUtil;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class FilmDAOImpl implements FilmDAO {
	
	EntityManager em;

	public void insert(Film f) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();
		
		try {
			String incasso = BCrypt.hashpw(f.getIncasso(), BCrypt.gensalt());
			f.setIncasso(incasso);
			entityTransaction.begin();
			em.persist(f); // col persist salviamo i dati
			entityTransaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			entityTransaction.rollback();

		} finally {
			em.close();
		}
	}

	public void delete(int id) {
		
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		EntityTransaction entityTransaction = em.getTransaction();

		try {
			entityTransaction.begin();
			Film f = em.find(Film.class, id); // cerchiamo l id dalla classe Cittadino
			em.remove(f);
			entityTransaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
			entityTransaction.rollback();

		} finally {
			em.close();
		}
		
	}

	public List <Film> findByRegista(String regista) {
		em = JpaUtil.getEntityManagerFactory().createEntityManager();
		
		Query q = em.createQuery("SELECT f FROM Film f WHERE f.regista = :regista").setParameter("regista", regista);
		List <Film> films = q.getResultList();

		return films;
	}
	

}
