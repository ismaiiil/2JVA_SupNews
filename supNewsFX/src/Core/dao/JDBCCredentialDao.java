package Core.dao;


import Core.vo.Credential;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCCredentialDao implements CredentialDao{

    Connection connection = null;

    public Connection getConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            if(connection == null){
                connection = DriverManager.getConnection("jdbc:mysql://localhost/SupNews?user=root&password=root");

            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;

    }

    @Override
    public List<Credential> select(){
        List<Credential> credentials = new LinkedList<Credential>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SupNews.credentials");
            Credential credential = null;

            while(resultSet.next()){
                credential = new Credential();
                credential.setId(Integer.parseInt(resultSet.getString("id")));
                credential.setUsername(resultSet.getString("username"));
                credential.setPassword(resultSet.getString("password"));
                credentials.add(credential);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return credentials;
    }

    public void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
