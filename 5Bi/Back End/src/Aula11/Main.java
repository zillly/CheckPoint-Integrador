package Aula11;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection connection = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);
            statement.execute(circulo1);
            statement.execute(circulo2);
            statement.execute(quadrados3);
            statement.execute(quadrados4);
            statement.execute(quadrados5);

            mostrarFigura(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();

    }

    private static void mostrarFigura(Connection connection) throws SQLException{
        String sqlConsulta = "SELECT * FROM Figura WHERE Cor LIKE 'Vermelho' ";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlConsulta);

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3));
        }

    }


    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Figura; CREATE TABLE Figura"
            + "("
            + " Id INT PRIMARY KEY,"
            + " Nome VARCHAR(100) NOT NULL,"
            + " Cor VARCHAR(100) NOT NULL"
            + ")";

    private static final String circulo1 = "INSERT INTO Figura (Id, Nome, Cor) VALUES (1, 'circulo', 'Vermelho')";
    private static final String circulo2 = "INSERT INTO Figura (Id, Nome, Cor) VALUES (2, 'circulo', 'Azul')";
    private static final String quadrados3 = "INSERT INTO Figura (Id, Nome, Cor) VALUES (3, 'quadrados', 'Verde')";
    private static final String quadrados4 = "INSERT INTO Figura (Id, Nome, Cor) VALUES (4, 'quadrados', 'Vermelho')";
    private static final String quadrados5 = "INSERT INTO Figura (Id, Nome, Cor) VALUES (5, 'quadrados', 'Roxo')";


    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }
}
