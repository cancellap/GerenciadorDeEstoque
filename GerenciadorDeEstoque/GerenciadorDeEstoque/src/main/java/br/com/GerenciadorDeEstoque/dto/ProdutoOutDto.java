package br.com.GerenciadorDeEstoque.dto;

import br.com.GerenciadorDeEstoque.model.Produto;
import br.com.GerenciadorDeEstoque.model.enumerator.Categoria;

public class ProdutoOutDto {

	private Long idProduto;
	private String nomeProduto;
	private Long estoqueProduto;
	private Categoria categoriaProduto;

	public ProdutoOutDto() {
	}

	public ProdutoOutDto(Produto produto) {
		this.idProduto = produto.idProduto;
		this.nomeProduto = produto.nomeProduto;
		this.estoqueProduto = produto.estoqueProduto;
		this.categoriaProduto = produto.categoriaProduto;
	}

	public ProdutoOutDto(ProdutoInsertDto dto) {
		this.idProduto = getIdProduto();
		this.nomeProduto = dto.getNomeProduto();
		this.estoqueProduto = dto.getEstoqueProduto();
		this.categoriaProduto = dto.getCategoriaProduto();
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
