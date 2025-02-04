package br.com.GerenciadorDeEstoque.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.GerenciadorDeEstoque.docker.rabbitMQ.ProdutoProducer;
import br.com.GerenciadorDeEstoque.dto.ProdutoInsertDto;
import br.com.GerenciadorDeEstoque.dto.ProdutoOutDto;
import br.com.GerenciadorDeEstoque.model.Produto;
import br.com.GerenciadorDeEstoque.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final ProdutoProducer produtoProducer;

	public ProdutoService(ProdutoRepository produtoRepository, ProdutoProducer produtoProducer) {
		this.produtoRepository = produtoRepository;
		this.produtoProducer = produtoProducer;
	}

	@Transactional
	public ProdutoOutDto cadastrarProduto(ProdutoInsertDto insertDto) {
		Produto produto = produtoRepository.save(new Produto(insertDto));
		produtoProducer.enviaCadastroProduto("Produto de id " + produto.getIdProduto() + " cadastrado");
		return new ProdutoOutDto(produto);
	}

	public List<ProdutoOutDto> buscarProdutoNome(String busca) {
		return produtoRepository.findByNomeProdutoContainingIgnoreCase(busca).stream().map(ProdutoOutDto::new).toList();
	}

	@Transactional
	public ProdutoOutDto atualizarProduto(Long id, ProdutoInsertDto updates) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado."));

		if (updates.getNomeProduto() != null) {
			produto.setNomeProduto(updates.getNomeProduto());
		}

		if (updates.getEstoqueProduto() != null) {
			produto.setEstoqueProduto(updates.getEstoqueProduto());
		}
		if (updates.getCategoriaProduto() != null) {
			produto.setCategoriaProduto(updates.getCategoriaProduto());
		}

		produtoRepository.save(produto);
		return new ProdutoOutDto(produto);
	}

}
