package jpa.Service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpa.EntityManagerHelper;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;

public class UsersService {
    private EntityManager manager;

    public UsersService(){
        manager = EntityManagerHelper.getEntityManager();
    }

    //cré des rdv disponibles pour les etudiants
    public void ajoutUser(String nom, String email, String fonction){
        manager.getTransaction().begin();
        switch(fonction){
            case "Etudiant" : 
            Etudiant ret = new Etudiant(nom);
            ret.setEmail(email);
            manager.persist(ret);
            break;
            case "Professeur" :
            Professeur p = new Professeur(nom);
            p.setEmail(email);
            manager.persist(p);
            break;
            default :
        }
        System.out.println("added to db");
        manager.flush();
        manager.getTransaction().commit();
    }
}