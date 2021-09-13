package jpa.Service;

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
    public Rdv createPossibleRdv(Date dateDebut, long duration, Professeur professeur){
        Date dateFin = new Date();
        dateFin.setTime(dateDebut.getTime() + duration); // 1h base
        Rdv ret = new Rdv(professeur, dateDebut, dateFin);
        professeur.addRdv(ret);
        manager.persist(ret);
        manager.persist(professeur);
        return ret;
    }
    public Rdv createPossibleRdv(Date dateDebut, Professeur professeur){
        Rdv ret = new Rdv(professeur, dateDebut);
        professeur.addRdv(ret);
        manager.persist(ret);
        manager.persist(professeur);
        return ret;
    }
    
    //assign un étudiant au rdv
    public void prendreRdv(Rdv rdv, Etudiant etudiant){
        rdv.setEtudiant(etudiant);
        etudiant.addRdv(rdv);
        manager.persist(rdv);
        manager.persist(etudiant);
        manager.flush();
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
        manager.remove(rdv);
        Etudiant etudiant = rdv.getEtudiant();
        Professeur professeur = rdv.getProfesseur();
        etudiant.removeRdv(rdv);
        professeur.removeRdv(rdv);
        manager.persist(etudiant);
        manager.persist(professeur);
        manager.flush();
    }

        //supprimer des rdvs
        public void supprimerRdvs(List<Rdv> rdvs){
            for (Rdv rdv : rdvs) {
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
            }
            manager.flush();
            for (Rdv rdv : rdvs) {
                manager.remove(rdv);
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
}