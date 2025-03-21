package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    @Query("SELECT u.endereco FROM Usuario u WHERE u.idUsuario = :id")
    Endereco findEnderecoByUsuarioId(@Param("id") Long id);

    Page<Usuario> findAll(Pageable pageable);
}
