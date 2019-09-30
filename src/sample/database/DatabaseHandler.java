package sample.database;

import sample.model.Task;
import sample.model.User;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

    String connectionString = "jdbc:mysql://" + dbHost + ":"
            + dbPort + "/"
            + dbName + dbUtf;
    Class.forName("com.mysql.jdbc.Driver");

    dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

    return dbConnection;

    }
    public void signUpUsers(User user){
        String insert = "INSERT INTO " + Const.USERS_TABLE + "("+ Const.USERS_FIRSTNAME
                + "," + Const.USERS_LASTNAME + "," +Const.USERS_USERNAME + ","
                + Const.USERS_PASSWORD + "," + Const.USERS_LOCATION + ","
                + Const.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?)"; // Метод для реестрації/запису до бази данних;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        if (!user.getUserName().equals("") || !user.getPassword().equals("")){
            String query = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                    +Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";  //Вибрати всіх з таблиці юзерів Де UserName= "userName" та Password="passWord";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Please enter your credentials");
        }


        return resultSet;

    }

}