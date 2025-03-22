package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateUsuarioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioAlreadyExistsException;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

public class CreateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final EnderecoRepository enderecoRepository;

    private CreateUsuarioUseCase(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public static CreateUsuarioUseCase create(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository, EnderecoRepository enderecoRepository) {
        return new CreateUsuarioUseCase(usuarioRepository, tipoUsuarioRepository, enderecoRepository);
    }

    @Transactional
    public UsuarioPublicDTO execute(CreateUsuarioDTO usuario) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.findByDescricaoTipoUsuario(usuario.tipoUsuario()).orElseThrow(TipoUsuarioNotFoundException::new);

        var userExists = usuarioRepository.findByLogin(usuario.login());

        if(userExists.isPresent()) {
            throw new UsuarioAlreadyExistsException();
        }

        Endereco endereco = Endereco.builder()
                .descricaoLogradouro(usuario.endereco().descricaoLogradouro())
                .descricaoBairro(usuario.endereco().descricaoBairro())
                .descricaoCidade(usuario.endereco().descricaoCidade())
                .descricaoComplemento(usuario.endereco().descricaoComplemento())
                .descricaoEstado(usuario.endereco().descricaoEstado())
                .numero(usuario.endereco().numero())
                .numeroCEP(usuario.endereco().numeroCep())
                .build();

        Usuario user = Usuario.builder()
                .nomeCompleto(usuario.nomeCompleto())
                .endereco(endereco)
                .email(usuario.email())
                .login(usuario.login())
                .senha(usuario.senha())
                .tipoUsuario(tipoUsuario)
                .build();

        enderecoRepository.save(endereco);
        var storedUser = usuarioRepository.save(user);
        return UsuarioMapper.toAPI(storedUser);
    }
}
