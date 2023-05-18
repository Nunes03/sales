package io.github.nunes03.repositories;

import io.github.nunes03.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository {

    private static final RowMapper<Cliente> CLIENTE_ROW_MAPPER = (resultSet, i) -> new Cliente(
        resultSet.getInt("id"),
        resultSet.getString("nome")
    );

    private static final String INSERT = "insert into cliente(nome) values (?)";

    private static final String SELECT_ALL = "select * from cliente";

    private static final String UPDATE = "update cliente set nome = ? where id = ?";

    private static final String DELETE_ID = "delete from cliente where id = ?";

    private static final String BUSCAR_POR_NOME = "select * from cliente where cliente.nome like ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(
            UPDATE,
            cliente.getNome(),
            cliente.getId()
        );

        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(
            DELETE_ID,
            id
        );
    }

    public List<Cliente> buscarPorNome(Cliente cliente) {
        return buscarPorNome(cliente.getNome());
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(
            BUSCAR_POR_NOME,
            CLIENTE_ROW_MAPPER,
            "%".concat(nome).concat("%")
        );
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, CLIENTE_ROW_MAPPER);
    }
}
