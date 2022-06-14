package br.com.esucri.games.plataforma;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;

@Stateless
public class PlataformaService {

    @PersistenceContext(unitName = "GamesPU")
    private EntityManager entityManager;

    public Plataforma findById(Long id) {
        return entityManager.find(Plataforma.class, id);
//        return entityManager
//                .createQuery("SELECT p FROM Plataforma p WHERE p.id = :id", Plataforma.class)
//                .setParameter("id", id)
//                .getSingleResult();
    }

    public Plataforma add(Plataforma plataforma) {
        validaExistenciaPlataforma(plataforma);
        entityManager.persist(plataforma);
        return plataforma;
    }

    public void remove(Plataforma plataforma) {
        entityManager.remove(findById(plataforma.getId()));
    }

    public Plataforma update(Plataforma plataformaAtualizada) {
        validaExistenciaPlataforma(plataformaAtualizada);
        entityManager.merge(plataformaAtualizada);
        return plataformaAtualizada;
    }

    public List<Plataforma> findAll() {
        // JPQL         
        return entityManager
                .createQuery("SELECT p FROM Plataforma p", Plataforma.class)
                //                .createNativeQuery("SELECT * FROM plataformas", Plataforma.class)
                .getResultList();
    }

    public List<Plataforma> search(String descricao) {
        return entityManager
                .createQuery("SELECT p FROM Plataforma p WHERE LOWER(p.descricao) LIKE :descricao", Plataforma.class) // JPQL
                .setParameter("descricao", "%" + descricao.toLowerCase() + "%")
                .getResultList();
    }

    private void validaExistenciaPlataforma(Plataforma plataforma) {
        List<Plataforma> resultList = entityManager
                .createQuery("SELECT p FROM Plataforma p WHERE LOWER(p.descricao) = :descricao", Plataforma.class)
                .setParameter("descricao", plataforma.getDescricao().toLowerCase())
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new BadRequestException("O jogo já está cadastrado em nossa base");
        }
    }

}
