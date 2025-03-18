package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private GetAllUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static GetAllUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new GetAllUsuarioUseCase(usuarioRepository);
    }

    public List<UsuarioPublicDTO> execute() {
        var users = usuarioRepository.findAll();
        return users.stream().map(UsuarioMapper::toAPI).collect(Collectors.toList());
    }
}
