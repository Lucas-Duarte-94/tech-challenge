package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

import java.util.Objects;

public class CreateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    private CreateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository,RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public static CreateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository, RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        return new CreateFuncionamentoUseCase(funcionamentoRepository, restauranteRepository, usuarioRepository);
    }

    public Funcionamento execute(CreateFuncionamentoDTO request) {
        var usuario = usuarioRepository.findById(request.usuarioId()).orElseThrow(UsuarioNotFoundException::new);
        var restaurante = restauranteRepository.findById(request.restauranteId()).orElseThrow(RestauranteNotFoundException::new);

        if(!Objects.equals(restaurante.getUsuario().getIdUsuario(), usuario.getIdUsuario())) {
            throw new UserDoesNotHavePermissionException();
        }

        Restaurante existingRestaurante = restauranteRepository.findById(request.restauranteId()).orElseThrow(RestauranteNotFoundException::new);

        Funcionamento newFuncionamento = Funcionamento.builder()
                .diaSemana(request.diaSemana())
                .horaAbertura(request.horaAbertura())
                .horaFechamento(request.horaFechamento())
                .restaurante(existingRestaurante)
                .build();

        return funcionamentoRepository.save(newFuncionamento);
    }
}
