package br.com.GerenciadorDeEstoque.model;

import br.com.GerenciadorDeEstoque.dto.ProdutoInsertDto;
import br.com.GerenciadorDeEstoque.model.enumerator.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_produto")
	public Long idProduto;
	
	@Column(name= "nome_produto")
	public String nomeProduto;
	
	@Column(name= "estoque_produto")
	public Long estoqueProduto;
	
	@Enumerated(EnumType.STRING)
	public Categoria categoriaProduto;

	public Produto() {
		super();
	}
	
	public Produto(Long idProduto, String nomeProduto, Long estoqueProduto, Categoria categoriaProduto) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.estoqueProduto = estoqueProduto;
		this.categoriaProduto = categoriaProduto;
	}
	public Produto(ProdutoInsertDto dto) {
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
