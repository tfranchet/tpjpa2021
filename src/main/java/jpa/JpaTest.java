package jpa;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import javassist.tools.Dump;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;
import jpa.Service.RdvService;
import jpa.Service.RepositoryRequests;

public class JpaTest {

	private EntityManager manager;

	private RepositoryRequests repoReq;

	private RdvService rdvService;

    public JpaTest(EntityManager manager) {
		this.manager = manager;
		this.repoReq = new RepositoryRequests();
		this.rdvService = new RdvService();
	}

	/**
     * @param args
     */
    public static void main(String[] args) {
		// dev : hsql
		//mysql : mysql
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("mysql");
        EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
		test.resetDb();
		manager.flush();
        try {
			test.test();
			manager.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.result();
		tx.commit();
        manager.close();
        factory.close();
    }

	private void test(){
		/*System.out.println("Debut des tests");
		System.out.println("Creation des etudiants");
		Etudiant e1 = new Etudiant("Harry Potter");
		Etudiant e2 = new Etudiant("Hermione Granger");
		Etudiant e3 = new Etudiant("Ron Weaseley");
		Etudiant e4 = new Etudiant("Dobby");
		manager.persist(e1);
		manager.persist(e2);
		manager.persist(e3);
		manager.persist(e4);
		System.out.println("Creation des professeurs");
		Professeur p1 = new Professeur("Godric Gryffondor");
		Professeur p2 = new Professeur("Helga Poufsouffle");
		Professeur p3 = new Professeur("Rowena Serdaigle");
		Professeur p4 = new Professeur("Salazar Serpentar");
		manager.persist(p1);
		manager.persist(p2);
		manager.persist(p3);
		manager.persist(p4);
		System.out.println("Creation de rdvs sans query la db");
		Date d1 = new Date();
		rdvService.createPossibleRdv(d1, p2);

		manager.flush();
		System.out.println("Creaton de rdv avec query");
		Professeur profFromDb = repoReq.findProfByName("Godric Gryffondor");
		Rdv rdv = rdvService.createPossibleRdv(d1, profFromDb);

		System.out.println("Prise du rdv par un étudiant");
		Etudiant etudiantFromDb = repoReq.findEtudiantByName("Harry Potter");
		rdvService.prendreRdv(rdv, etudiantFromDb);
		manager.flush(); */
	}
	
	private void resetDb(){
		Iterator<Rdv> rdvs = repoReq.getAllRdvs().iterator();
		while(rdvs.hasNext()){
			manager.remove(rdvs.next());
		}
		Iterator<Etudiant> etudiant = repoReq.getAllEtudiants().iterator();
		while(etudiant.hasNext()){
			manager.remove(etudiant.next());
		}
		Iterator<Professeur> professeur = repoReq.getAllProfesseurs().iterator();
		while(professeur.hasNext()){
			manager.remove(professeur.next());
		}
		manager.flush();
	}



	private void result(){
		
		List<Etudiant> result = repoReq.getAllEtudiants();
		Iterator<Etudiant> it = result.iterator();
		System.out.println("Test 1 : Etudiants");
		while(it.hasNext()){
			System.out.println(it.next());
		}
		List<Professeur> listprof = repoReq.getAllProfesseurs();
		Iterator<Professeur> itprof = listprof.iterator();
		System.out.println("Test 2 : Professeurs");
		while(itprof.hasNext()){
			System.out.println(itprof.next());
		}
		List<Rdv> listRdv = repoReq.getAllRdvs();
		Iterator<Rdv> itrdv = listRdv.iterator();
		System.out.println("Test 3 : Liste Rdv");
		while(itrdv.hasNext()){
			System.out.println(itrdv.next());
		}
	}
}
