package jpa.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import jpa.EntityManagerHelper;
import jpa.Entity.Etudiant;
import jpa.Entity.Professeur;
import jpa.Entity.Rdv;

public class RdvService {

    private EntityManager manager;

    public RdvService(){
        manager = EntityManagerHelper.getEntityManager();
    }

    //cré des rdv disponibles pour les etudiants
    public Rdv createPossibleRdv(String date, String heureDebut, String heureFin, String professeurId) {
        String dated = date + "-" +heureDebut;
        String datef = date + "-" +heureFin;
        Long pId = Long.parseLong(professeurId);
        Professeur professeur = manager.find(Professeur.class, pId);
        Date date1;
        Date date2;
        try {
            date1 = new SimpleDateFormat("yyyy-mm-dd-HH:mm").parse(dated);
            date2=new SimpleDateFormat("yyyy-mm-dd-HH:mm").parse(datef); 
            Rdv ret = new Rdv(professeur, date1, date2);
            professeur.addRdv(ret);
            manager.getTransaction().begin();
            manager.persist(ret);
            manager.persist(professeur);
            manager.flush();
            manager.getTransaction().commit();
            return ret;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }  
        
    }
    public Rdv createPossibleRdv(Date dateDebut, Professeur professeur){
        Rdv ret = new Rdv(professeur, dateDebut);
        professeur.addRdv(ret);
        manager.persist(ret);
        manager.persist(professeur);
        return ret;
    }
    
    //assign un étudiant au rdv
    public void prendreRdv(String rdvid, String etudiantid){
        Long rId = Long.parseLong(rdvid);
        Rdv rdv = manager.find(Rdv.class, rId);
        Long eId = Long.parseLong(etudiantid);
        Etudiant etudiant = manager.find(Etudiant.class, eId);
        rdv.setEtudiant(etudiant);
        etudiant.addRdv(rdv);
        manager.getTransaction().begin();
        manager.persist(rdv);
        manager.persist(etudiant);
        manager.flush();
        manager.getTransaction().commit();
    }
        //decaler un rdv
    public void decalerRdv(Rdv rdv, Date heureDebut){
        //Récupère la dff entre début et fin pour avoir la durée
        Date debut = rdv.getheureDebut();
        Date fin = rdv.getHeureFin();
        int diff = debut.compareTo(fin);
        rdv.setHeureDebut(heureDebut);
        //réplique la durée pour générer l'heure de fin
        Date dateFin = new Date();
        dateFin.setTime(heureDebut.getTime() + diff); 
        rdv.setHeureFin(dateFin);
        manager.persist(rdv);
        manager.flush();
    }
    //supprimer un rdv
    public void supprimerRdv(Rdv rdv){
        Etudiant etudiant = rdv.getEtudiant();
        Professeur professeur = rdv.getProfesseur();
        if(etudiant != null){
            etudiant.removeRdv(rdv);
            manager.persist(etudiant);
        }
        if(professeur != null){
            professeur.removeRdv(rdv);
            manager.persist(professeur);
        }
        manager.remove(rdv);
        manager.flush();
    }

        //supprimer des rdvs
        public void supprimerRdvs(List<Rdv> rdvs){
            Object[] it = rdvs.toArray();
            for ( int i = 0; i < it.length; i++) {
                supprimerRdv((Rdv)it[i]);
            }
        }
    //décommander un rdv
    public void decommandRdv(Rdv rdv){
        Etudiant etudiant = rdv.getEtudiant();
        rdv.setEtudiant(null);
        etudiant.removeRdv(rdv);
        manager.persist(rdv);
        manager.persist(etudiant);
        manager.flush();
    } 
       //décommander tout rdvs
    public void decommandRdvs(List<Rdv> rdvs){
        for (Rdv rdv : rdvs) {
        Etudiant etudiant = rdv.getEtudiant();
        rdv.setEtudiant(null);
        etudiant.removeRdv(rdv);
        manager.persist(rdv);
        manager.persist(etudiant);
        }
        manager.flush();
    }

    public List<Rdv> getAllRdvs(){
        String[] ret = new String[50];
        RepositoryRequests repreq = new RepositoryRequests();
        List<Rdv> e = repreq.getAllRdvs();
        return e;
    }
}