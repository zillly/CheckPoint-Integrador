
package main.dao.impl;

import main.config.ConfiguracaoJDBC;
import main.dao.IDao;
import main.model.Endereco;
import main.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class PacienteDao implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;

    private EnderecoDao enderecoDao;

    private final static Logger logger = Logger.getLogger(PacienteDao.class);

    public PacienteDao(EnderecoDao enderecoDao) {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
        this.enderecoDao = enderecoDao;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        logger.debug("Salvando novo paciente: " + paciente.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        paciente.setEndereco(enderecoDao.salvar(paciente.getEndereco()));
        String query = String.format("INSERT INTO paciente (nome, sobrenome, rg, data_nascimento, endereco_id)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s')", paciente.getNome(), paciente.getSobrenome(), paciente.getRg(),
                paciente.getDataNascimento(), paciente.getEndereco().getId());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
                paciente.setId(generatedKeys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.debug("Buscando Paciente cadastrados...");
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = "SELECT * FROM paciente";
        List<Paciente> pacientes = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                pacientes.add(criarPaciente(resultSet));

            }
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public void excluir(int id) {
        logger.debug("Excluindo paciente : " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("DELETE FROM paciente WHERE id = '%s'", id);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Paciente buscarPorId(int id) {
        logger.debug("Buscando paciente com id: " + id);
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM paciente WHERE id = '%s'", id);
        Paciente paciente = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                paciente = criarPaciente(resultSet);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;


    }

    public Paciente criarPaciente(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String sobrenome = resultSet.getString("sobrenome");
        String rg = resultSet.getString("rg");
        String data_nascimento = resultSet.getString("data_nascimento");
        Endereco endereco = enderecoDao.buscarPorId(resultSet.getInt("endereco_id"));
        Paciente paciente = new Paciente (id, nome, sobrenome, rg, data_nascimento, endereco);
        return paciente;
    }
}
