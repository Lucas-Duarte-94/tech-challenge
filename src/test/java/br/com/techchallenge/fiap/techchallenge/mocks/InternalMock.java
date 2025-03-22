package br.com.techchallenge.fiap.techchallenge.mocks;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class InternalMock {

    public static Usuario getUsuario(){
        return new Usuario(1L, "Usuário 1", null, "email1@email.com", "login1", "senhaAntiga", getTipoUsuario(), null);
    }

    public static Restaurante getRestaurante(){
        return new Restaurante(1L, "Restaurante Exemplo", getEndereco(), "Tipo Cozinha", getUsuario());
    }

    public static Endereco getEndereco(){
        return new Endereco("logradouro", "cidade", "estado", "12301-350");
    }

    public static TipoUsuario getTipoUsuario(){
        return new TipoUsuario(1L, TipoUsuarioEnum.DONO_RESTAURANTE);
    }

    public static Cardapio getCardapio(){
        return new Cardapio(1L, getRestaurante());
    }

    public static ItemCardapio getItemCardapio() {
        return new ItemCardapio("Item 2", "Descrição do Item", BigDecimal.valueOf(15.0), true, getCardapio());
    }

    public static Funcionamento getFuncionamento() {

        DayOfWeek diaSemana = DayOfWeek.MONDAY;
        LocalTime horaAbertura = LocalTime.of(10, 0);
        LocalTime horaFechamento = LocalTime.of(22, 0);

        return new Funcionamento(diaSemana, horaAbertura, horaFechamento, getRestaurante());

   }
}
