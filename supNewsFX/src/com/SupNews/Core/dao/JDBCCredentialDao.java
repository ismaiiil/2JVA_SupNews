package com.SupNews.Core.dao;


import com.SupNews.Core.vo.Credential;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JDBCCredentialDao implements CredentialDao{

    Connection connection = null;

    /**
     * creates a connection with database using a {@link JDBCCredentialDao} instance
     * @return returns a connection to the supnews database
     */
    public Connection getConnection() {
        try {
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

    /**
     * select all from database
     * @return returns a list of {@link Credential} objects from the database
     */
    @Override
    public List<Credential> select(){
        List<Credential> credentials = new LinkedList<Credential>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM SupNews.credentials");
            Credential credential = null;

            while(resultSet.next()){
                credential = new Credential();
                credential.setId(Integer.parseInt(resultSet.getString("user_id")));
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
    /**
     * stops the connection of a {@link JDBCCredentialDao} context to the database
     */
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
