package utils;

import java.sql.*;

public class DBHandler {

    public static String[][] retrieveHighscores(int size, String difficulty) {
        Connection connection = null;
        String connectionUrl = "jdbc:sqlserver://bingo-db-server.database.windows.net:1433;" +
                "database=BingoDatabase;" +
                "user=syedhuzaif199@bingo-db-server;" +
                "password=BingoSql@199;" +
                "encrypt=true;" +
                "trustServerCertificate=false;" +
                "hostNameInCertificate=*.database.windows.net;" +
                "loginTimeout=30;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String[][] namedScores = new String[2][size];
        for(String[] strings : namedScores)
            for(String string : strings)
                string = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            preparedStatement = connection.prepareStatement("SELECT TOP " + size + " Name, Score FROM playerhighscores_" + difficulty + " ORDER BY Score DESC;");
//            preparedStatement.setInt(1,size);
            resultSet = preparedStatement.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                namedScores[0][i] = resultSet.getString("Name");
                namedScores[1][i] = resultSet.getString("Score");
                i++;
            }


        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return namedScores;
    }

    public static void uploadScore(String name, int score, String difficulty) {
        Connection connection = null;
        String connectionUrl = "jdbc:sqlserver://bingo-db-server.database.windows.net:1433;" +
                "database=BingoDatabase;" +
                "user=syedhuzaif199@bingo-db-server;" +
                "password={BingoSql@199};" +
                "encrypt=true;" +
                "trustServerCertificate=false;" +
                "hostNameInCertificate=*.database.windows.net;" +
                "loginTimeout=30;";
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(connectionUrl);
            preparedStatement = connection.prepareStatement("INSERT INTO playerhighscores_" + difficulty + " (Name, Score) VALUES (?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, score);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
