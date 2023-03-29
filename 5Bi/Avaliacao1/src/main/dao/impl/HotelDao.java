package main.dao.impl;

import main.config.ConfiguracaoJDBC;
import main.dao.IDao;
import main.model.Hotel;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HotelDao implements IDao <Hotel> {

    private ConfiguracaoJDBC configuracaoJDBC;
    private final static Logger logger = Logger.getLogger(HotelDao.class);

    public HotelDao () {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }


    @Override
    public Hotel salvar(Hotel hotel) {
        logger.debug("Salvando: " + hotel.toString());
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO hotel (nome, rua, numero, cidade, estado, estrela) VALUES " +
                "('%s', '%s', '%s', '%s','%s','%s')", hotel.getNome() , hotel.getRua(), hotel.getNumero(), hotel.getCidade(), hotel.getEstado(), hotel.getAvaliarEstrelas());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
               hotel.setId(generatedKeys.getInt(1));
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }
}
