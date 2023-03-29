
package main.dao.impl;

import main.config.ConfiguracaoJDBC;
import main.dao.IDao;
import main.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public Paciente buscarPorId(int id) {
        return null;
    }

    public Paciente criarEndereco(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String rua = resultSet.getString("nome");
        String numero = resultSet.getString("sobrenome");
        String cidade = resultSet.getString("rg");
        String bairro = resultSet.getString("data_nascimento");
        Endereco endereco = enderecoDao.buscarPorId(resultSet.getInt("endereco_id"))
        Paciente paciente = new Paciente (id, nome, sobrenome, rg, data_nascimento,endereco);
        return paciente;
    }
}
