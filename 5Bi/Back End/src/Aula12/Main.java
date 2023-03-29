package Aula12;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = getConnection();

            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);
            statement.execute(sqlInsert1);
            statement.execute(sqlInsert2);
            statement.execute(sqlInsert3);


            statement.execute(sqlDelete);

            for (String instruction : Arrays.asList(sqlCreateTable, sqlInsert1, sqlInsert2, sqlInsert3)) {
                try {
                    statement.execute(instruction);
                } catch (Exception e) {
                    logger.error(e);
                }
            }

            connection.close();


        } catch (Exception e) {
            logger.error(e);
        }

    }

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Funcionarios; CREATE TABLE Funcionarios"
            + "("
            + "Id INT PRIMARY KEY,"
            + "Nome VARCHAR(100) NOT NULL,"
            + "Sobrenome VARCHAR(100) NOT NULL,"
            + "Cargo VARCHAR(100) NOT NULL,"
            + "Idade INT NOT NULL"
            + ")";

    private static final String sqlInsert1 = "INSERT INTO Funcionarios (Id, Nome, Sobrenome, Cargo, Idade) VALUES (1, 'Carlos', 'Silva', 'Vendedor', 26)";
    private static final String sqlInsert2 = "INSERT INTO Funcionarios (Id, Nome, Sobrenome, Cargo, Idade) VALUES (2, 'Gilmar', 'Miranda', 'Gerente', 25)";
    private static final String sqlInsert3 = "INSERT INTO Funcionarios (Id, Nome, Sobrenome, Cargo, Idade) VALUES (2, 'Luise', 'Miranda', 'Caixa', 23)";
    private static final String sqlDelete = "DELETE FROM Funcionarios WHERE Id=1";

    private static final Logger logger = Logger.getLogger(Main.class);


    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }



}
