package UI;

import Core.dao.JDBCCredentialDao;
import Core.vo.Credential;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML TextField password_textfield;

    public void login_btn_pressed(ActionEvent Event){

        JDBCCredentialDao jdbcCredentialDao = new JDBCCredentialDao();
        jdbcCredentialDao.getConnection();
        //System.out.println(jdbcCredentialDao.select());

        Credential credential = new Credential();
        credential.setUsername(username_textfield.getText());
        credential.setPassword(password_textfield.getText());

        for (Credential cred:
             jdbcCredentialDao.select()) {
            if(cred.getUsername().equals(credential.getUsername()) && cred.getPassword().equals(credential.getPassword())){
               System.out.println("Right username password combo");
                try{
                    FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                    Parent root1 = (Parent) fxmlloader.load();
                    Stage stage = new Stage();
                    stage.setTitle("dashboard");
                    stage.setScene(new Scene(root1));
                    stage.show();

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                Stage stage = (Stage) username_textfield.getScene().getWindow();
                stage.close();
                break;
            }else{
                System.out.println("Wrong username or password combo");
            }


        }


        jdbcCredentialDao.closeConnection();

    }
}
