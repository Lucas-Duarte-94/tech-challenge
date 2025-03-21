package br.com.techchallenge.fiap.techchallenge.entities;


import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_TIPO_USUARIOS")
@EqualsAndHashCode(callSuper=false)
public class TipoUsuario {

    @Id
    @Column(name="IDE_TIPO_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name="DES_TIPO_USUARIO")
    private TipoUsuarioEnum descricaoTipoUsuario;


}
