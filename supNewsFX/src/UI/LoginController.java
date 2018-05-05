package UI;

import Core.dao.JDBCCredentialDao;
import Core.vo.Credential;
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


    public String stringToMD5Hash(String password){
        String passwordToHash = password;
        String generatedPassword = null;
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(passwordToHash.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        return generatedPassword;

    }
}
