package br.com.esucri.games.jogo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JogoService {

    @PersistenceContext(unitName = "GamesPU")
    private EntityManager entityManager;

    public List<Jogo> findAll() {
        return entityManager
                .createQuery("SELECT j FROM Jogo j")
                .getResultList();
    }

    public Jogo findById(Long id) {
        return entityManager.find(Jogo.class, id);
    }

    public Jogo add(Jogo jogo) {
        entityManager.persist(jogo);
        return jogo;
    }

    public Jogo update(Jogo jogo) {
        return entityManager.merge(jogo);
    }

    public void remove(Long id) {
        entityManager.remove(id);
    }

}
