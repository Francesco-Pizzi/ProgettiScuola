package it.unicam.mdp_mgc_2026.persistence.db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Hibernate {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("esempioPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Studente studente = new Studente("Marco", "Rossi", 22);
        em.persist(studente);

        em.getTransaction().commit();

        Studente trovato = em.find(Studente.class, studente.getId());
        System.out.println("Studente trovato: " + trovato.getNome() + " " + trovato.getCognome());

        em.close();
        emf.close();
    }
}
