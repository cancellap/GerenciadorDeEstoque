package br.com.GerenciadorDeEstoque.docker.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProdutoProducer {

    private final RabbitTemplate template;

    public ProdutoProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void enviaCadastroProduto(String mensagem) {
        template.convertAndSend(RabbitMQConection.EXCHANGE_PRODUTO, RabbitMQConection.ROUTING_KEY_CADASTRO_PRODUTO, mensagem);
    }

    public void enviaAumentoEstoque(String mensagem) {
        template.convertAndSend(RabbitMQConection.EXCHANGE_ESTOQUE, RabbitMQConection.ROUTING_KEY_AUMENTO_ESTOQUE, mensagem);
    }

    public void enviaDiminuicaoEstoque(String mensagem) {
        template.convertAndSend(RabbitMQConection.EXCHANGE_ESTOQUE, RabbitMQConection.ROUTING_KEY_DIMINUICAO_ESTOQUE, mensagem);
    }
}
