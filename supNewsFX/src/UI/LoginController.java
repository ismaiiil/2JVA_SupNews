package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
    public void login_btn_pressed(ActionEvent Event){
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

    }
}
