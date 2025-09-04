/*
        package unigran.br.Controllers;

import org.springframework.web.bind.annotation.*;
import unigran.br.Model.DAO.AreaMatchDAO;
import unigran.br.Model.Entidades.AreaMatch;
import unigran.br.Model.Entidades.ItemVisualizado;
import unigran.br.Model.Entidades.Postagem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/areamatch")
public class AreaMatchController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/buscar")
    @Transactional
    public List<Postagem> buscarItensCompatíveis(@RequestParam Long userIdOrigem) {

        List<Postagem> itensOrigem = em.createQuery(
                        "SELECT p FROM Postagem p WHERE p.userID = :uid", Postagem.class)
                .setParameter("uid", userIdOrigem)
                .getResultList();

        List<Postagem> resultadosFinais = new ArrayList<>();

        for (Postagem itemOrigem : itensOrigem) {
            String sql = """
                SELECT p.* FROM postagem p
                WHERE p.userid <> :userIdOrigem
                  AND p.categoria = ANY(string_to_array(:interessesOrigem, ','))
                  AND :categoriaOrigem = ANY(string_to_array(p.categoria_interesse, ','))
                  AND NOT EXISTS (
                      SELECT 1 FROM item_visualizado v 
                      WHERE v.user_id = :userIdOrigem AND v.item_id = p.id
                  )
            """;

            List<Postagem> resultados = em.createNativeQuery(sql, Postagem.class)
                    .setParameter("userIdOrigem", userIdOrigem)
                    .setParameter("interessesOrigem", itemOrigem.getCategoriaInteresse())
                    .setParameter("categoriaOrigem", itemOrigem.getCategoria())
                    .getResultList();

            for (Postagem p : resultados) {
                List<ItemVisualizado> viz = em.createQuery(
                                "SELECT v FROM ItemVisualizado v WHERE v.userId = :uid AND v.itemId = :itemId",
                                ItemVisualizado.class)
                        .setParameter("uid", userIdOrigem)
                        .setParameter("itemId", p.getId())
                        .getResultList();

                if (viz.isEmpty()) {
                    ItemVisualizado v = new ItemVisualizado();
                    v.setUserId(userIdOrigem);
                    v.setItemId(p.getId());
                    em.persist(v);
                }
            }

            resultadosFinais.addAll(resultados);
        }

        return resultadosFinais;
    }

    @PostMapping("/escolher")
    @Transactional
    public String registrarEscolha(@RequestParam Long userIdOrigem,
                                   @RequestParam Long itemIdDestino,
                                   @RequestParam String escolha) {

        List<AreaMatch> lista = em.createQuery(
                        "SELECT am FROM AreaMatch am WHERE am.userIdOrigem = :userIdOrigem AND am.itemIdDestino = :itemIdDestino",
                        AreaMatch.class)
                .setParameter("userIdOrigem", userIdOrigem)
                .setParameter("itemIdDestino", itemIdDestino)
                .getResultList();

        AreaMatch am;
        if (lista.isEmpty()) {
            am = new AreaMatch();
            am.setUserIdOrigem(userIdOrigem);
            am.setItemIdDestino(itemIdDestino);
        } else {
            am = lista.get(0);
        }

        am.setEscolha(escolha.toUpperCase());
        em.merge(am);

        if ("SIM".equalsIgnoreCase(escolha)) {
            Long donoDoItemDestino = ((Number) em.createQuery(
                            "SELECT p.userID FROM Postagem p WHERE p.id = :pid")
                    .setParameter("pid", itemIdDestino)
                    .getSingleResult()).longValue();

            List<AreaMatch> matchesReciproco = em.createQuery(
                            "SELECT am1 FROM AreaMatch am1 JOIN Postagem p1 ON am1.itemIdDestino = p1.id " +
                                    "WHERE am1.userIdOrigem = :donoDestino " +
                                    "AND p1.userID = :userOrigem " +
                                    "AND am1.escolha = 'SIM'", AreaMatch.class)
                    .setParameter("donoDestino", donoDoItemDestino)
                    .setParameter("userOrigem", userIdOrigem)
                    .getResultList();

            if (!matchesReciproco.isEmpty()) {
                return "🎉 Deu Match!";
            }
        }

        return "Escolha registrada com sucesso.";
    }
}*/