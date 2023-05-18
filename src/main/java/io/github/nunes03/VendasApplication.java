package io.github.nunes03;

import io.github.nunes03.entities.Cliente;
import io.github.nunes03.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean(name = "iniciar")
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository) {
        return args -> {
            var lucas = new Cliente();
            lucas.setNome("Lucas");

            var pedro = new Cliente();
            pedro.setNome("Pedro");

            clienteRepository.salvar(lucas);
            clienteRepository.salvar(pedro);

            printarTudo(clienteRepository.obterTodos());

            lucas.setId(1);
            lucas.setNome("Lucas alterado");

            pedro.setId(2);
            pedro.setNome("pedro alterado");

            clienteRepository.atualizar(lucas);
            clienteRepository.atualizar(pedro);

            printarTudo(clienteRepository.obterTodos());

            var encontradosComNome = clienteRepository.buscarPorNome("ucas");

            printarTudo(encontradosComNome);

            clienteRepository.deletar(1);

            printarTudo(clienteRepository.obterTodos());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    private void printarTudo(List<Cliente> clientes) {
        clientes.forEach(System.err::println);
    }
}
