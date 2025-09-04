package unigran.br.Model.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "area_match",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id_origem", "item_id_destino"}))
public class AreaMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id_origem", nullable = false)
    private Long userIdOrigem;

    @Column(name = "item_id_destino", nullable = false)
    private Long itemIdDestino;

    @Column(nullable = false)
    private String escolha;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserIdOrigem() { return userIdOrigem; }
    public void setUserIdOrigem(Long userIdOrigem) { this.userIdOrigem = userIdOrigem; }

    public Long getItemIdDestino() { return itemIdDestino; }
    public void setItemIdDestino(Long itemIdDestino) { this.itemIdDestino = itemIdDestino; }

    public String getEscolha() { return escolha; }
    public void setEscolha(String escolha) { this.escolha = escolha; }
}
