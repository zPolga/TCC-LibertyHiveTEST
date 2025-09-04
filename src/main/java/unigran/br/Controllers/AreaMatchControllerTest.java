/*
package unigran.br.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unigran.br.Model.Entidades.AreaMatch;
import unigran.br.Model.Entidades.ItemVisualizado;
import unigran.br.Model.Entidades.Postagem;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AreaMatchControllerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private AreaMatchController areaMatchController;

    private Postagem itemUser1;
    private Postagem itemUser2;

    @BeforeEach
    public void setup() {
        em.createQuery("DELETE FROM AreaMatch").executeUpdate();
        em.createQuery("DELETE FROM ItemVisualizado").executeUpdate();
        em.createQuery("DELETE FROM Postagem").executeUpdate();

        itemUser1 = new Postagem();
        itemUser1.setId(100L);
        itemUser1.setUserID(1L);
        itemUser1.setNomePostagem("Planta Bonita");
        itemUser1.setCategoria("Planta");
        itemUser1.setCategoriaInteresse("Jardim,Decoração");
        em.persist(itemUser1);

        itemUser2 = new Postagem();
        itemUser2.setId(101L);
        itemUser2.setUserID(2L);
        itemUser2.setNomePostagem("Jardim Externo");
        itemUser2.setCategoria("Jardim");
        itemUser2.setCategoriaInteresse("Planta,Ferramenta");
        em.persist(itemUser2);

        em.flush();
    }


    @Test
    public void testBuscarItensCompatíveis() {
        List<Postagem> resultados = areaMatchController.buscarItensCompatíveis(1L);

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
        assertEquals("Jardim Externo", resultados.get(0).getNomePostagem());

        List<ItemVisualizado> visualizados = em.createQuery("SELECT v FROM ItemVisualizado v", ItemVisualizado.class)
                .getResultList();
        assertEquals(1, visualizados.size());
        assertEquals(1L, visualizados.get(0).getUserId());
        assertEquals(itemUser2.getId(), visualizados.get(0).getItemId());
    }

    @Test
    public void testRegistrarEscolhaEDeuMatch() {
        String resposta1 = areaMatchController.registrarEscolha(1L, itemUser2.getId(), "SIM");
        assertEquals("Escolha registrada com sucesso.", resposta1);

        String resposta2 = areaMatchController.registrarEscolha(2L, itemUser1.getId(), "SIM");
        assertEquals("🎉 Deu Match!", resposta2);

        List<AreaMatch> lista = em.createQuery("SELECT a FROM AreaMatch a", AreaMatch.class).getResultList();
        assertEquals(2, lista.size());
    }

    @Test
    public void testRegistrarEscolhaNao() {
        String resposta = areaMatchController.registrarEscolha(1L, itemUser2.getId(), "NAO");
        assertEquals("Escolha registrada com sucesso.", resposta);

        List<AreaMatch> lista = em.createQuery("SELECT a FROM AreaMatch a", AreaMatch.class).getResultList();
        assertEquals(1, lista.size());
        assertEquals("NAO", lista.get(0).getEscolha());
    }
}
*/