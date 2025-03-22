package br.com.techchallenge.fiap.techchallenge.mappers;

import br.com.techchallenge.fiap.techchallenge.dtos.EnderecoPublicDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class EnderecoMapper {
    private UsuarioRepository usuarioRepository;
    private static EnderecoMapper instance;

    private EnderecoMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static EnderecoMapper getInstance(UsuarioRepository usuarioRepository) {
        if (instance == null) {
            return new EnderecoMapper(usuarioRepository);
        }
        return instance;
    }

//    public static EnderecoPublicDTO toAPI(Endereco endereco) {
//        var usuario = usuarioRepository.findById()
//        Usuario usuario = endereco.get();
//        UsuarioPublicDTO usuarioPublicDTO = new UsuarioPublicDTO(
//                usuario.getId(),
//                usuario.getNome(),
//                usuario.getEmail()
//        );
//
//        return new EnderecoPublicDTO(
//
//        );
//    }
}
