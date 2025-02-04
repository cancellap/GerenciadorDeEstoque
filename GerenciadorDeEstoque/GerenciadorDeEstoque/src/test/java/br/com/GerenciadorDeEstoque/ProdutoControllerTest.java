package br.com.GerenciadorDeEstoque;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import br.com.GerenciadorDeEstoque.controller.ProdutoController;
import br.com.GerenciadorDeEstoque.dto.ProdutoInsertDto;
import br.com.GerenciadorDeEstoque.dto.ProdutoOutDto;
import br.com.GerenciadorDeEstoque.service.ProdutoService;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProdutoService produtoService;

	@InjectMocks
	private ProdutoController produtoController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
	}

	@Test
	public void testCadastrarProduto() throws Exception {
		ProdutoInsertDto produtoInsertDto = new ProdutoInsertDto();
		produtoInsertDto.setNomeProduto("Produto Teste");
		produtoInsertDto.setEstoqueProduto(10L);

		ProdutoOutDto produtoOutDto = new ProdutoOutDto();
		produtoOutDto.setIdProduto(1L);
		produtoOutDto.setNomeProduto("Produto Teste");
		produtoOutDto.setEstoqueProduto(10L);

		when(produtoService.cadastrarProduto(any(ProdutoInsertDto.class))).thenReturn(produtoOutDto);

		mockMvc.perform(post("/produto/cadastrar").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeProduto\":\"Produto Teste\",\"estoqueProduto\":10}")).andExpect(status().isCreated())
				.andExpect(jsonPath("$.idProduto").value(1L))
				.andExpect(jsonPath("$.nomeProduto").value("Produto Teste"))
				.andExpect(jsonPath("$.estoqueProduto").value(10));

		verify(produtoService, times(1)).cadastrarProduto(any(ProdutoInsertDto.class));
	}

	@Test
	public void testBuscarProdutoNome() throws Exception {
		ProdutoOutDto produtoOutDto = new ProdutoOutDto();
		produtoOutDto.setIdProduto(1L);
		produtoOutDto.setNomeProduto("Produto Teste");
		produtoOutDto.setEstoqueProduto(10L);

		List<ProdutoOutDto> produtos = Collections.singletonList(produtoOutDto);

		when(produtoService.buscarProdutoNome("Produto Teste")).thenReturn(produtos);

		mockMvc.perform(get("/produto/Produto Teste").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].idProduto").value(1L))
				.andExpect(jsonPath("$[0].nomeProduto").value("Produto Teste"))
				.andExpect(jsonPath("$[0].estoqueProduto").value(10));

		verify(produtoService, times(1)).buscarProdutoNome("Produto Teste");
	}

	@Test
	public void testBuscarProdutoNomeVazio() throws Exception {
		mockMvc.perform(get("/produto/ ").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());

		verify(produtoService, never()).buscarProdutoNome(anyString());
	}

	@Test
	public void testAtualizarProduto() throws Exception {
		ProdutoInsertDto produtoInsertDto = new ProdutoInsertDto();
		produtoInsertDto.setNomeProduto("Produto Atualizado");
		produtoInsertDto.setEstoqueProduto(20L);

		ProdutoOutDto produtoOutDto = new ProdutoOutDto();
		produtoOutDto.setIdProduto(1L);
		produtoOutDto.setNomeProduto("Produto Atualizado");
		produtoOutDto.setEstoqueProduto(20L);

		when(produtoService.atualizarProduto(eq(1L), any(ProdutoInsertDto.class))).thenReturn(produtoOutDto);

		mockMvc.perform(put("/produto/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nomeProduto\":\"Produto Atualizado\",\"estoqueProduto\":20}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.idProduto").value(1L))
				.andExpect(jsonPath("$.nomeProduto").value("Produto Atualizado"))
				.andExpect(jsonPath("$.estoqueProduto").value(20));

		verify(produtoService, times(1)).atualizarProduto(eq(1L), any(ProdutoInsertDto.class));
	}
}