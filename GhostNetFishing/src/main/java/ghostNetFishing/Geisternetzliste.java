package ghostNetFishing;

import java.io.Serializable;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;

@Named("geisternetzListe")
@ApplicationScoped
public class Geisternetzliste implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");

    private EntityManager entityManager;

    public Geisternetzliste() {
        this.entityManager = emf.createEntityManager();
    }

    public List<Geisternetz> getListe() {
        return entityManager.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    @Transactional
    public void addGeisternetz(Geisternetz geisternetz) {
        entityManager.getTransaction().begin();  
        entityManager.persist(geisternetz);
        entityManager.getTransaction().commit();  
    }


    @Transactional
    public void bergungEintragen(int id, String aktuellerBenutzer) {
        entityManager.getTransaction().begin();  
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null) {
            netz.setStatus(Geisternetzstatus.BERGUNG_BEVORSTEHEND);
            netz.setBergendePerson(aktuellerBenutzer);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit(); 
    }


    public boolean enableBergunganmeldenButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() == Geisternetzstatus.GEMELDET;
    }


    @Transactional
    public void bergungAbgeschlossen(int id) {
        entityManager.getTransaction().begin();  
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null && netz.getStatus() == Geisternetzstatus.BERGUNG_BEVORSTEHEND) {
            netz.setStatus(Geisternetzstatus.GEBORGEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();  
    }

  
    public boolean enableBergungButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() == Geisternetzstatus.BERGUNG_BEVORSTEHEND;
    }


    @Transactional
    public void verschollen(int id) {
        entityManager.getTransaction().begin(); 
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null) {
            netz.setStatus(Geisternetzstatus.VERSCHOLLEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit(); 
    }


    public boolean enableVerschollenButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() != Geisternetzstatus.GEBORGEN;
    }


    public Geisternetzstatus[] getStatusWerte() {
        return Geisternetzstatus.values();
    }


    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
