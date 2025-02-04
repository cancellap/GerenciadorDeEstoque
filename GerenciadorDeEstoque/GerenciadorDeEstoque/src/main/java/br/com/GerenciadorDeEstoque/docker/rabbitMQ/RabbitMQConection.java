package br.com.GerenciadorDeEstoque.docker.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConection {

	public static final String QUEUE_CADASTRO_PRODUTO = "queue_cadastro_produto";
	public static final String ROUTING_KEY_CADASTRO_PRODUTO = "produto.cadastro";

	public static final String QUEUE_MUDANCA_ESTOQUE = "queue_mudanca_estoque";
	public static final String ROUTING_KEY_AUMENTO_ESTOQUE = "estoque.aumento";
	public static final String ROUTING_KEY_DIMINUICAO_ESTOQUE = "estoque.diminui";

	public static final String EXCHANGE_PRODUTO = "produto.direct";
	public static final String EXCHANGE_ESTOQUE = "estoque.direct";

	@Bean
	Queue queueCadastroProduto() {
		return QueueBuilder.durable(QUEUE_CADASTRO_PRODUTO).build();
	}

	@Bean
	Queue queueMudancaEstoque() {
		return QueueBuilder.durable(QUEUE_MUDANCA_ESTOQUE).build();
	}

	@Bean
	DirectExchange produtoExchange() {
		return new DirectExchange(EXCHANGE_PRODUTO);
	}

	@Bean
	DirectExchange estoqueExchange() {
		return new DirectExchange(EXCHANGE_ESTOQUE);
	}

	@Bean
	Binding cadastroProdutoBinding() {
		return BindingBuilder.bind(queueCadastroProduto()).to(produtoExchange()).with(ROUTING_KEY_CADASTRO_PRODUTO);
	}

	@Bean
	Binding aumentoEstoqueBinding() {
		return BindingBuilder.bind(queueMudancaEstoque()).to(estoqueExchange()).with(ROUTING_KEY_AUMENTO_ESTOQUE);
	}

	@Bean
	Binding diminuicaoEstoqueBinding() {
		return BindingBuilder.bind(queueMudancaEstoque()).to(estoqueExchange()).with(ROUTING_KEY_DIMINUICAO_ESTOQUE);
	}
}
