package br.com.GerenciadorDeEstoque.dto;

import br.com.GerenciadorDeEstoque.model.Produto;
import br.com.GerenciadorDeEstoque.model.enumerator.Categoria;

public class ProdutoInsertDto {

	private String nomeProduto;
	private Long estoqueProduto;
	private Categoria categoriaProduto;

	public ProdutoInsertDto() {
	}

	public ProdutoInsertDto(Produto produto) {
		this.nomeProduto = produto.nomeProduto;
		this.estoqueProduto = produto.estoqueProduto;
		this.categoriaProduto = produto.categoriaProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Long getEstoqueProduto() {
		return estoqueProduto;
	}

	public void setEstoqueProduto(Long estoqueProduto) {
		this.estoqueProduto = estoqueProduto;
	}

	public Categoria getCategoriaProduto() {
		return categoriaProduto;
	}

	public void setCategoriaProduto(Categoria categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}

}
