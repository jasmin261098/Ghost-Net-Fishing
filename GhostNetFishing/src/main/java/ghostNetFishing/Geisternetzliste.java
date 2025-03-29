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
    
    // Manuell initialisierte EntityManagerFactory
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysql");

    // Manuell initialisierter EntityManager
    private EntityManager entityManager;

    public Geisternetzliste() {
        // Initialisiere den EntityManager manuell
        this.entityManager = emf.createEntityManager();
    }

    // Methode, um die Liste der Geisternetze zu erhalten
    public List<Geisternetz> getListe() {
        return entityManager.createQuery("SELECT g FROM Geisternetz g", Geisternetz.class).getResultList();
    }

    // Methode zum Hinzufügen eines neuen Geisternetzes
    @Transactional
    public void addGeisternetz(Geisternetz geisternetz) {
        entityManager.getTransaction().begin();  // Beginne die Transaktion
        entityManager.persist(geisternetz);
        entityManager.getTransaction().commit();  // Commit der Transaktion
    }

    // Methode zum Eintragen der Bergung
    @Transactional
    public void bergungEintragen(int id, String aktuellerBenutzer) {
        entityManager.getTransaction().begin();  // Beginne die Transaktion
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null) {
            netz.setStatus(Geisternetzstatus.BERGUNG_BEVORSTEHEND);
            netz.setBergendePerson(aktuellerBenutzer);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();  // Commit der Transaktion
    }

    // Überprüft, ob der Bergungsanmelden-Button aktiviert ist
    public boolean enableBergunganmeldenButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() == Geisternetzstatus.GEMELDET;
    }

    // Methode zum Abschluss der Bergung
    @Transactional
    public void bergungAbgeschlossen(int id) {
        entityManager.getTransaction().begin();  // Beginne die Transaktion
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null && netz.getStatus() == Geisternetzstatus.BERGUNG_BEVORSTEHEND) {
            netz.setStatus(Geisternetzstatus.GEBORGEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();  // Commit der Transaktion
    }

    // Überprüft, ob der Bergungs-Button aktiviert ist
    public boolean enableBergungButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() == Geisternetzstatus.BERGUNG_BEVORSTEHEND;
    }

    // Methode zum Markieren eines Geisternetzes als verschollen
    @Transactional
    public void verschollen(int id) {
        entityManager.getTransaction().begin();  // Beginne die Transaktion
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        if (netz != null) {
            netz.setStatus(Geisternetzstatus.VERSCHOLLEN);
            entityManager.merge(netz);
        }
        entityManager.getTransaction().commit();  // Commit der Transaktion
    }

    // Überprüft, ob der Verschollen-Button aktiviert ist
    public boolean enableVerschollenButton(int id) {
        Geisternetz netz = entityManager.find(Geisternetz.class, id);
        return netz != null && netz.getStatus() != Geisternetzstatus.GEBORGEN;
    }

    // Gibt alle Statuswerte zurück
    public Geisternetzstatus[] getStatusWerte() {
        return Geisternetzstatus.values();
    }

    // Schließt den EntityManager und EntityManagerFactory
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
