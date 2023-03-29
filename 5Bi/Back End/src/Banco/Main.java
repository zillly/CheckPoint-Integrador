package Banco;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {

        Connection connection = null;

        try {

            connection = getConnection();

            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);
            statement.execute(sqlInsert1);
            statement.execute(sqlInsert2);
            statement.execute(sqlInsert3);
            statement.execute(sqlInsert4);
            statement.execute(sqlInsert5);

            mostrarAnimal(connection);

            statement.execute(sqlDelete);

            mostrarAnimal(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        connection.close();

    }

    private static void mostrarAnimal(Connection connection) throws SQLException {
        String sqlConsulta = "SELECT * FROM Animal";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlConsulta);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3));
        }
    }
    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Animal; CREATE TABLE Animal"
            + "("
            + " Id INT PRIMARY KEY,"
            + " Nome VARCHAR(100) NOT NULL,"
            + " Tipo VARCHAR(100) NOT NULL"
            + ")";

    private static final String sqlInsert1 = "INSERT INTO Animal (Id, Nome, Tipo) VALUES (1, 'Dalton', 'Cavalo')";
    private static final String sqlInsert2 = "INSERT INTO Animal (Id, Nome, Tipo) VALUES (2, 'Lua', 'Cachorro')";
    private static final String sqlInsert3 = "INSERT INTO Animal (Id, Nome, Tipo) VALUES (3, 'Sol', 'Gato')";
    private static final String sqlInsert4 = "INSERT INTO Animal (Id, Nome, Tipo) VALUES (4, 'Eclipse', 'Cachorro')";
    private static final String sqlInsert5 = "INSERT INTO Animal (Id, Nome, Tipo) VALUES (5, 'Jupiter', 'Papagaio')";
    private static final String sqlDelete = "DELETE FROM Animal WHERE Id=2";

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }
}
