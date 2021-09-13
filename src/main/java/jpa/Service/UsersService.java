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
        //For dev
        ret[0][0] = "Professeurs";
        ret[1][0] = "Etudiant";
        RepositoryRequests repreq = new RepositoryRequests();
        List<Etudiant> e = repreq.getAllEtudiants();
        List<Professeur> p = repreq.getAllProfesseurs();
        int i = 0;
        for (Etudiant etudiant : e) {
            ret[1][i] = etudiant.toString() + "<a href=\"http://localhost:8080/user/rm/?id=" + etudiant.getId() + "\"> Delete </a>";
            i++;
        }
        i = 0;
            
        for (Professeur professeur : p) {
            ret[0][i] = professeur.toString()+ "<a href=\"http://localhost:8080/user/rm/?id=" + professeur.getId() + "\"> Delete </a>";
            ;
            i++;
        }
        return ret;
    }

    public void removeUser(String id){
        manager.getTransaction().begin();
        RdvService rdvService = new RdvService();
        Long idlong = Long.parseLong(id);
        Etudiant e = manager.find(Etudiant.class, idlong);
        Professeur p = manager.find(Professeur.class, idlong);
        if(e != null) {
            rdvService.decommandRdvs(e.getRdvs());
            manager.remove(e);
        }
        else if (p != null){
            rdvService.supprimerRdvs(p.getRdvs());
            manager.remove(p);
        }
        System.out.println(id);
        manager.flush();
        manager.getTransaction().commit();
    }
}