package br.com.GerenciadorDeEstoque.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.GerenciadorDeEstoque.dto.ProdutoInsertDto;
import br.com.GerenciadorDeEstoque.dto.ProdutoOutDto;
import br.com.GerenciadorDeEstoque.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<ProdutoOutDto> cadastrarProduto(@RequestBody ProdutoInsertDto produtoInsertDto) {
		ProdutoOutDto produtoOutDto = produtoService.cadastrarProduto(produtoInsertDto);
		URI location = URI.create("/produto/" + produtoOutDto.getIdProduto());
		return ResponseEntity.created(location).body(produtoOutDto);
	}

	@GetMapping("/{nome}")
	public ResponseEntity<List<ProdutoOutDto>> buscarProdutoNome(@PathVariable(required = false) String nome) {
		if (nome == null || nome.isBlank()) {
			return ResponseEntity.badRequest().body(Collections.emptyList());
		}

		List<ProdutoOutDto> response = produtoService.buscarProdutoNome(nome);
		return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProdutoOutDto> atualizarProduto(@PathVariable Long id,
			@RequestBody ProdutoInsertDto updates) {
		ProdutoOutDto produtoAtualizado = produtoService.atualizarProduto(id, updates);
		return ResponseEntity.ok(produtoAtualizado);
	}
}
