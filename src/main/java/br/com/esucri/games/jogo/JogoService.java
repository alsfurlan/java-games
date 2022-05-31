package br.com.esucri.games.jogo;

import br.com.esucri.games.regrasnegocio.RegraNegocioException;
import br.com.esucri.games.plataforma.Plataforma;
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

    public Jogo add(Jogo jogo) throws RegraNegocioException {
        valida(jogo);
        entityManager.persist(jogo);
        return jogo;
    }

    public Jogo update(Jogo jogo) throws RegraNegocioException {
        valida(jogo);
        return entityManager.merge(jogo);
    }

    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    private void valida(Jogo jogo) throws RegraNegocioException {
        validaPlataforma(jogo);
        validaNome(jogo);
        validaExistenciaJogo(jogo);
    }
    
    private void validaPlataforma(Jogo jogo) throws RegraNegocioException {
        List<Plataforma> plataformas = jogo.getPlataformas();
        if (plataformas != null && plataformas.size() > 2) {
            throw new RegraNegocioException("Um jogo não pode conter mais que duas plataformas");
        }
    }

    private void validaNome(Jogo jogo) throws RegraNegocioException {
        if(jogo.getNome().length() < 3) {
            throw new RegraNegocioException("O nome do jogo não pode conter menos que três caracteres");
        }
    }

    private void validaExistenciaJogo(Jogo jogo) throws RegraNegocioException {
        List<Jogo> resultList = entityManager
                .createQuery("SELECT j FROM Jogo j WHERE LOWER(j.nome) = :nome", Jogo.class)
                .setParameter("nome", jogo.getNome().toLowerCase())
                .getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            throw new RegraNegocioException("O jogo já está cadastrado em nossa base");
        }
    }

    
}
