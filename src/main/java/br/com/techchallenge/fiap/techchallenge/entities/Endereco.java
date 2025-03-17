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
@Table(name="TB_ENDERECOS")
@EqualsAndHashCode(callSuper=false)
public class Endereco {

    @Id
    @Column(name="IDE_ENDERECO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @Column(name="DES_LOGRADOURO")
    private String descricaoLogradouro;

    @Column(name="NUM_ENDERECO")
    private String numero;

    @Column(name="DES_COMPLEMENTO")
    private String descricaoComplemento;

    @Column(name="DES_BAIRRO")
    private String descricaoBairro;

    @Column(name="DES_CIDADE")
    private String descricaoCidade;

    @Column(name="DES_ESTADO")
    private String descricaoEstado;

    @Column(name="NUM_CEP")
    private String numeroCEP;

    public Endereco(String logradouro, String cidade, String estado, String cep) {
        validar(logradouro, cidade, estado, cep);
        this.descricaoLogradouro = logradouro;
        this.descricaoCidade = cidade;
        this.descricaoEstado = estado;
        this.numeroCEP = cep;
    }

    private void validar(String logradouro, String cidade, String estado, String cep) {
        if (logradouro == null || logradouro.isBlank()) throw new IllegalArgumentException("Logradouro não pode ser vazio");
        if (cidade == null || cidade.isBlank()) throw new IllegalArgumentException("Cidade não pode ser vazia");
        if (estado == null || estado.isBlank()) throw new IllegalArgumentException("Estado não pode ser vazio");
        if (cep == null || !cep.matches("\\d{5}-\\d{3}")) throw new IllegalArgumentException("CEP inválido");
    }

    @Override
    public String toString() {
        return descricaoLogradouro + ", " + descricaoCidade + " - " + descricaoEstado + " (" + numeroCEP + ")";
    }

}
