package br.com.GerenciadorDeEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.GerenciadorDeEstoque.model.Produto;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findByNomeProdutoContainingIgnoreCase(String nomeProduto);
}
