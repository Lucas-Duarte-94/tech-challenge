package br.com.techchallenge.fiap.techchallenge.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_CARDAPIO")
@EqualsAndHashCode(callSuper=false)
public class Cardapio {

    @Id
    @Column(name="IDE_CARDAPIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCardapio;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDE_RESTAURANTE", nullable = false)
    private Restaurante restaurante;

}
