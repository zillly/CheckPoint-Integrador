package Mesa_13;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        Dentista dentista = new Dentista("Gilmar", "Miranda", 2);

        Connection connection = null;

        try {

            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sqlCreateTable);

            PreparedStatement psInsert = connection.prepareStatement(sqlInsert);

            psInsert.setInt(1,1);
            psInsert.setString(2, dentista.getNome());
            psInsert.setString(3, dentista.getSobrenome());
            psInsert.setInt(4, dentista.getMatricula());

            psInsert.execute();

            String sql = "SELECT * FROM Dentista";

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
            }

            connection.setAutoCommit(false);

            PreparedStatement psUpdate = connection.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, dentista.attMatricula(5));
            psUpdate.setInt(2,dentista.getMatricula());
            psUpdate.execute();
            connection.commit();

            connection.setAutoCommit(true);

            stm = connection.createStatement();
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getInt(4));
            }


        }catch (Exception e){
            e.printStackTrace();
            connection.rollback();
        }
        finally {
            connection.close();
        }


    }
    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Dentista; CREATE TABLE Dentista"
            + "("
            + "Id INT PRIMARY KEY, "
            + "Nome VARCHAR(100) NOT NULL, "
            + "Sobrenome VARCHAR(100) NOT NULL, "
            + "Matricula INT NOT NULL "
            + ")";

    private static final String sqlInsert = "INSERT INTO Dentista (Id, Nome, Sobrenome, Matricula) VALUES (?, ?, ?, ?)";
    private static final String sqlUpdate = "UPDATE Dentista SET Matricula=? WHERE Matricula=?";

    private static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }







}
