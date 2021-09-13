package jpa.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jpa.EntityManagerHelper;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;

public class RepositoryRequests {

    private EntityManager manager;

	public RepositoryRequests(){
		manager = EntityManagerHelper.getEntityManager();
	}

   public List<Etudiant> getAllEtudiants(){
       CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Etudiant> query = criteriaBuilder.createQuery(Etudiant.class);
		Root<Etudiant> from = query.from(Etudiant.class);
		query.select(from);
		List<Etudiant> result = manager.createQuery(query).getResultList();
		return result;

    } 
	  public List<Professeur> getAllProfesseurs(){
       CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Professeur> query = criteriaBuilder.createQuery(Professeur.class);
		Root<Professeur> from = query.from(Professeur.class);
		query.select(from);
		List<Professeur> result = manager.createQuery(query).getResultList();
		return result;

    } 
	  public List<Rdv> getAllRdvs(){
       CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Rdv> query = criteriaBuilder.createQuery(Rdv.class);
		Root<Rdv> from = query.from(Rdv.class);
		query.select(from);
		List<Rdv> result = manager.createQuery(query).getResultList();
		return result;

    }
	  public Professeur findProfByName(String str){
		List<Professeur> results = manager.createQuery("SELECT p FROM Professeur p where p.name = :value1")
		.setParameter("value1", str).getResultList();
		return results.get(0);
    }
	  public Etudiant findEtudiantByName(String str){
		List<Etudiant> results = manager.createQuery("SELECT p FROM Etudiant p where p.name = :value1")
		.setParameter("value1", str).getResultList();
		return results.get(0);
    }

}