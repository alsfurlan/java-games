package br.com.esucri.games.jogo;

import br.com.esucri.games.plataforma.Plataforma;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

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
        valida(jogo);
        entityManager.persist(jogo);
        return jogo;
    }

    public Jogo update(Jogo jogo) {
        valida(jogo);
        return entityManager.merge(jogo);
    }

    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    private void valida(Jogo jogo) {
        validaPlataforma(jogo);
        validaNome(jogo);
        validaExistenciaJogo(jogo);
    }

    private void validaPlataforma(Jogo jogo) {
        List<Plataforma> plataformas = jogo.getPlataformas();
        if (plataformas != null && plataformas.size() > 2) {
            throw new WebApplicationException(
                    "Um jogo não pode conter mais que duas plataformas",
                    Response.Status.REQUEST_ENTITY_TOO_LARGE
            );
        }
    }

    private void validaNome(Jogo jogo) {
        if (jogo.getNome().length() < 3) {
            throw new WebApplicationException(
                    "O nome do jogo não pode conter menos que três caracteres", Response.Status.BAD_REQUEST
            );
        }
    }

    private void validaExistenciaJogo(Jogo jogo) {
        List<Jogo> resultList = entityManager
                .createQuery("SELECT j FROM Jogo j WHERE LOWER(j.nome) = :nome", Jogo.class)
                .setParameter("nome", jogo.getNome().toLowerCase())
                .getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            throw new WebApplicationException("O jogo já está cadastrado em nossa base", Response.Status.BAD_REQUEST);
        }
    }

}
