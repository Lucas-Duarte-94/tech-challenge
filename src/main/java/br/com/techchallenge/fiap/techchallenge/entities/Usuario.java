package br.com.techchallenge.fiap.techchallenge.entities;


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

import java.time.OffsetDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_USUARIOS")
@EqualsAndHashCode(callSuper=false)
public class Usuario {

    @Id
    @Column(name="IDE_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name="NOM_COMPLETO")
    private String nomeCompleto;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDE_ENDERECO", nullable = false)
    private Endereco endereco;

    @Column(name="DES_EMAIL")
    private String email;

    @Column(name="NOM_LOGIN")
    private String login;

    @Column(name="DES_SENHA")
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "IDE_TIPO_USUARIO", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name="DTA_ULT_ALTERACAO")
    private OffsetDateTime dataAtualizacao;

    private String criptografarSenha(String senha) {
        return "***" + senha.hashCode();
    }

    public void alterarSenha(String novaSenha) {
        if (novaSenha == null || novaSenha.length() < 6) throw new IllegalArgumentException("Nova senha deve ter no mínimo 6 caracteres");
        this.senha = criptografarSenha(novaSenha);
        this.dataAtualizacao = OffsetDateTime.now();
    }

    private void validarDados(String nome, String email, String senha, String login) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome não pode ser vazio");
        if (email == null || !email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$")) throw new IllegalArgumentException("Email inválido");
        if (senha == null || senha.length() < 6) throw new IllegalArgumentException("Senha deve ter no mínimo 6 caracteres");
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login não pode ser vazio");
    }


}
