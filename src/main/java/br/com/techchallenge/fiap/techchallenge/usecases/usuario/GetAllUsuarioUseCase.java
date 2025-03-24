package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetAllUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private GetAllUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static GetAllUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new GetAllUsuarioUseCase(usuarioRepository);
    }

    public List<UsuarioPublicDTO> execute(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepository.findAll(pageable).stream().map(UsuarioMapper::toAPI).toList();
    }
}
