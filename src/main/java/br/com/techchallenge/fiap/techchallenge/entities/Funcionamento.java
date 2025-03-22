package br.com.techchallenge.fiap.techchallenge.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TB_FUNCIONAMENTO")
@EqualsAndHashCode(callSuper=false)
public class Funcionamento {

    @Id
    @Column(name="IDE_FUNCIONAMENTO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionamento;

    @Enumerated(EnumType.STRING)
    @Column(name="DTA_SEMANA")
    private DayOfWeek diaSemana;

    @Column(name="HORA_ABERTURA")
    private LocalTime horaAbertura;

    @Column(name="HORA_FECHA")
    private LocalTime horaFechamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDE_RESTAURANTE", nullable = false)
    private Restaurante restaurante;


    public Funcionamento(DayOfWeek diaSemana, LocalTime horaAbertura, LocalTime horaFechamento, Restaurante restaurante) {
        validarFuncionamento(horaAbertura, horaFechamento);
        this.diaSemana = diaSemana;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = horaFechamento;
    }

    private void validarFuncionamento(LocalTime abertura, LocalTime fechamento) {
        if (abertura == null || fechamento == null) {
            throw new IllegalArgumentException("Horários de abertura e fechamento não podem ser nulos");
        }
        if (fechamento.isBefore(abertura)) {
            throw new IllegalArgumentException("Horário de fechamento deve ser após o horário de abertura");
        }

    }
}
