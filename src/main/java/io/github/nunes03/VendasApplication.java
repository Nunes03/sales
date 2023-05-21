package io.github.nunes03;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.entities.Pedido;
import io.github.nunes03.repositories.ClienteRepository;
import io.github.nunes03.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean(name = "iniciar")
    public CommandLineRunner init(
        @Autowired ClienteRepository clienteRepository,
        @Autowired PedidoRepository pedidoRepository
    ) {
        return args -> {
            var lucas = new Cliente();
            lucas.setNome("Lucas");

            lucas = clienteRepository.save(lucas);

            var pedido = new Pedido();
            pedido.setData(LocalDate.now());
            pedido.setCliente(lucas);
            pedido.setTotal(BigDecimal.valueOf(100));

            pedidoRepository.save(pedido);

           var pedidos = pedidoRepository.findByCliente(lucas);

           System.err.println(pedidos);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    private void printarTudo(List<Cliente> clientes) {
        clientes.forEach(System.err::println);
    }
}
