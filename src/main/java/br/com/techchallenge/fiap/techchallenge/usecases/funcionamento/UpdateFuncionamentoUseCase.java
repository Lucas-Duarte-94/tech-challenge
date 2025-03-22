package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.errors.FuncionamentoNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

import java.util.Objects;

public class UpdateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    private UpdateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public static UpdateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        return new UpdateFuncionamentoUseCase(funcionamentoRepository, usuarioRepository, restauranteRepository);
    }

    public Funcionamento execute(Long id, UpdateFuncionamentoDTO request) {
        // Aplicar lógica com token do usuario para validar se o restaurante pertence a quem está fazendo alteração
        var usuario = usuarioRepository.findById(request.usuarioId()).orElseThrow(UsuarioNotFoundException::new);
        var restaurante = restauranteRepository.findById(request.restauranteId()).orElseThrow(RestauranteNotFoundException::new);

        if(!Objects.equals(restaurante.getUsuario().getIdUsuario(), usuario.getIdUsuario())) {
            throw new UserDoesNotHavePermissionException();
        }

        var funcionamento = funcionamentoRepository.findById(id).orElseThrow(FuncionamentoNotFoundException::new);

        funcionamento.setDiaSemana(request.diaSemana());
        funcionamento.setHoraAbertura(request.horaAbertura());
        funcionamento.setHoraFechamento(request.horaFechamento());

        return funcionamentoRepository.save(funcionamento);
    }
}
