package br.com.techchallenge.fiap.techchallenge.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_ITENS_CARDAPIOS")
@EqualsAndHashCode(callSuper=false)
public class ItemCardapio {

    @Id
    @Column(name="IDE_ITEM")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCardapio;
    @Column(name = "NOM_ITEM", nullable = false)
    private String nome;

    @Column(name = "DES_ITEM", nullable = false)
    private String descricao;

    @Column(name = "VLR_ITEM", nullable = false)
    private BigDecimal valor;

    @Column(name = "DES_FOTO")
    private String foto;

    @Column(name = "STA_LOCAL", nullable = false)
    private Boolean somenteLocal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "IDE_CARDAPIO", nullable = false)
    private Cardapio cardapio;

    public ItemCardapio(String nome, String descricao, BigDecimal valor, Boolean somenteLocal, Cardapio cardapio) {
        validar(nome, descricao, valor, somenteLocal, cardapio);
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.somenteLocal = somenteLocal;
        this.cardapio = cardapio;
    }

    private void validar(String nome, String descricao, BigDecimal valor, Boolean somenteLocal, Cardapio cardapio) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do item não pode ser vazio.");
        if (descricao == null || descricao.isBlank()) throw new IllegalArgumentException("Descrição do item não pode ser vazia.");
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("O valor do item deve ser maior que zero.");
        if (somenteLocal == null) throw new IllegalArgumentException("O campo 'somenteLocal' deve ser definido.");
        if (cardapio == null) throw new IllegalArgumentException("O item deve estar associado a um cardápio.");
    }

    public void atualizarDados(String nome, String descricao, BigDecimal valor, Boolean somenteLocal, String foto) {
        validar(nome, descricao, valor, somenteLocal, this.cardapio);
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.somenteLocal = somenteLocal;
        this.foto = foto;
    }

}
