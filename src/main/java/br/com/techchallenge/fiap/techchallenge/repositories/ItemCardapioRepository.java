package br.com.techchallenge.fiap.techchallenge.repositories;


import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long>, JpaSpecificationExecutor<ItemCardapio> {

}
