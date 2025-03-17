package br.com.techchallenge.fiap.techchallenge.entities;


import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
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
@Table(name="TB_RESTAURANTES")
@EqualsAndHashCode(callSuper=false)
public class Restaurante {

    @Id
    @Column(name="IDE_RESTAURANTE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurante;

    @Column(name="NOM_RESTAURANTE")
    private String nomeRestaurante;

    @Column(name="TIPO_COZINHA")
    private String tipoCozinha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDE_ENDERECO", nullable = false)
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDE_USUARIO", nullable = false)
    private Usuario usuario;


    public Restaurante(Long id, String nome, Endereco endereco, String tipoCozinha, Usuario dono) {
        validar(nome, tipoCozinha, dono);
        this.idRestaurante = id;
        this.nomeRestaurante = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.usuario = dono;

    }

    private void validar(String nome, String tipoCozinha, Usuario dono) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome do restaurante não pode ser vazio");
        if (tipoCozinha == null || tipoCozinha.isBlank()) throw new IllegalArgumentException("Tipo de cozinha não pode ser vazio");
       if (dono == null || dono.getTipoUsuario().getDescricaoTipoUsuario() != TipoUsuarioEnum.DONO_RESTAURANTE) throw new IllegalArgumentException("Somente um dono de restaurante pode criar um restaurante");
    }
}
