package jpa.Service;

import java.util.List;

import javax.persistence.EntityManager;
import jpa.EntityManagerHelper;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;


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

    public String[][] getAllUsers(){
        String[][] ret = new String[2][50];
        ret[0][0] = "Professeurs";
        ret[1][0] = "Etudiant";
        RepositoryRequests repreq = new RepositoryRequests();
        List<Etudiant> e = repreq.getAllEtudiants();
        List<Professeur> p = repreq.getAllProfesseurs();
        int i = 1;
        for (Etudiant etudiant : e) {
            ret[1][i] = etudiant.toString();
            i++;
        }
        i = 1;
            
        for (Professeur professeur : p) {
            ret[0][i] = professeur.toString();
            i++;
        }
        return ret;
    }
}