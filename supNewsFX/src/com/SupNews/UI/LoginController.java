package com.SupNews.UI;

import com.SupNews.Core.dao.JDBCCredentialDao;
import com.SupNews.Core.vo.Credential;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML PasswordField password_textfield;
    @FXML Label wrong_pass;


    /**
     * pressing the login button will check if a username matches a password,
     * but first the password input is hashed and compared to the hash on the server
     * the user that has logged in will have his user_id pushed to the dashboard using the {@link DashboardController#init_u_id(int)}
     * method
     * @param Event ActionEvent Event button click
     */
    public void login_btn_pressed(ActionEvent Event){

        JDBCCredentialDao jdbcCredentialDao = new JDBCCredentialDao();
        jdbcCredentialDao.getConnection();


        Credential credential = new Credential();
        credential.setUsername(username_textfield.getText());
        credential.setPassword(stringToMD5Hash(password_textfield.getText()));



        for (Credential cred : jdbcCredentialDao.select()) {
            if(cred.getUsername().equals(credential.getUsername()) && cred.getPassword().equals(credential.getPassword())){
               System.out.println("Right username password combo");
                try{
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root1 = (Parent) fxmlloader.load();
                    DashboardController dashboardController = fxmlloader.getController();
                    dashboardController.init_u_id(cred.getId());
                    dashboardController.initialize();
                    Stage stage = new Stage();
                    stage.setTitle("dashboard");
                    stage.setScene(new Scene(root1));
                    stage.setResizable(false);
                    stage.show();

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                Stage stage = (Stage) username_textfield.getScene().getWindow();
                stage.close();
                break;
            }else{
                wrong_pass.setText("Wrong username or password");
            }


        }


        jdbcCredentialDao.closeConnection();

    }

    /**
     * used to hash a password using MessageDigest class
     * @param password password as string
     * @return hased password as string
     */
    private String stringToMD5Hash(String password){
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(hash);
        return hash;

    }
}
