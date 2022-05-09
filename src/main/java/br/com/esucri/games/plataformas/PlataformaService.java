/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.esucri.games.plataformas;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlataformaService {

    @PersistenceContext(unitName = "GamesPU")
    private EntityManager entityManager;

    public Plataforma findById(Long id) {
        return entityManager.find(Plataforma.class, id);
    }

    public Plataforma add(Plataforma plataforma) {
        entityManager.persist(plataforma);
        return plataforma;
    }

    public void remove(Plataforma plataforma) {
        entityManager.remove(findById(plataforma.getId()));
    }

    public Plataforma update(Plataforma plataformaAtualizada) {
        entityManager.merge(plataformaAtualizada);
        return plataformaAtualizada;
    }

    public List<Plataforma> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Plataforma p", Plataforma.class) // JPQL
                .getResultList();
    }

    public List<Plataforma> search(String descricao) {
         return entityManager
                .createQuery("SELECT p FROM Plataforma p WHERE LOWER(p.descricao) LIKE :descricao", Plataforma.class) // JPQL
                .setParameter("nome", "%" + descricao.toLowerCase() + "%")
                .getResultList();
    }

}
