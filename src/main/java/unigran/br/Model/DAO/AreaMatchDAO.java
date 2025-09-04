/*

package unigran.br.Model.DAO;

import org.springframework.stereotype.Repository;
import unigran.br.Model.Entidades.Postagem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaMatchDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public AreaMatchDAO() {
        // nome da
    public List<Postagem> buscarItensDoUsuario(Long userId) {
        return em.createQuery("SELECT p FROM Postagem p WHERE p.userId = :uid", Postagem.class)
                .setParameter("uid", userId)
                .getResultList();
    }

    public List<Postagem> buscarCompatíveisParaItem(Long userIdOrigem, Postagem itemOrigem) {
        String sql = """
            SELECT p.* FROM postagem p
            WHERE p.userId <> :userIdOrigem
              AND p.categoria = ANY(string_to_array(:interessesOrigem, ','))
              AND :categoriaOrigem = ANY(string_to_array(p.categoriaInteresse, ','))
              AND NOT EXISTS (
                  SELECT 1 FROM item_visualizado v 
                  WHERE v.user_id = :userIdOrigem AND v.item_id = p.id
              )
        """;

        return em.createNativeQuery(sql, Postagem.class)
                .setParameter("userIdOrigem", userIdOrigem)
                .setParameter("interessesOrigem", itemOrigem.getCategoriaInteresse())
                .setParameter("categoriaOrigem", itemOrigem.getCategoria())
                .getResultList();
    }


    public void marcarVisualizado(Long userId, Long itemId) {
        em.getTransaction().begin();
        em.createNativeQuery("""
            INSERT INTO item_visualizado(user_id, item_id)
            VALUES(:userId, :itemId)
            ON CONFLICT DO NOTHING
        """)
                .setParameter("userId", userId)
                .setParameter("itemId", itemId)
                .executeUpdate();
        em.getTransaction().commit();

    public void registrarEscolha(Long userIdOrigem, Long itemIdDestino, String escolha){
        em.getTransaction().begin();
        em.createNativeQuery("""
            INSERT INTO area_match(user_id_origem, item_id_destino, escolha)
            VALUES(:uid, :itemId, :escolha)
            ON CONFLICT (user_id_origem, item_id_destino) 
            DO UPDATE SET escolha = EXCLUDED.escolha
        """)
        .setParameter("uid",userIdOrigem)
        .setParameter("itemId",itemIdDestino)
        .setParameter("escolha",escolha.toUpperCase())
        .executeUpdate();
        em.getTransaction().commit();
        }
    public Long buscarDonoDoItem(Long itemId) {
        return ((Number) em.createQuery(
                        "SELECT p.userId FROM Postagem p WHERE p.id = :pid")
                .setParameter("pid", itemId)
                .getSingleResult()).longValue();
    }

    public boolean verificarMatchReciproco(Long donoDestino, Long userOrigem) {
        List<Object[]> matches = em.createNativeQuery("""
            SELECT 1 FROM area_match am1
            JOIN postagem p1 ON am1.item_id_destino = p1.id
            WHERE am1.user_id_origem = :donoDestino
              AND p1.userId = :userOrigem
              AND am1.escolha = 'SIM'
        """)
                .setParameter("donoDestino", donoDestino)
                .setParameter("userOrigem", userOrigem)
                .getResultList();

        return !matches.isEmpty();
    }

    public void fechar() {
        em.close();
        emf.close();
    }
}*/