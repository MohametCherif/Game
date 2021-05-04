package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.texteManagement.TraitemenTexte;

import java.io.IOException;

public class ControllerPopup
{
    @FXML
    private Button cancelButton;

    @FXML
    private Button playButton;

    @FXML
    private TextField name;

    public static boolean openMenu=true;

    public static void openPopup() throws IOException
    {
        System.out.println("Open pop up");
        Parent view = FXMLLoader.load(ControllerPopup.class.getResource("FXML/popup.fxml"));
        Scene scene = new Scene(view);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void closePopup() throws IOException
    {
        System.out.println("Close pop up");
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        if (openMenu)
            ControllerMenu.openMenu();
    }

    @FXML
    public void launchNewGame() throws IOException
    {
        System.out.println("Launch new game");
        openMenu=false;
        closePopup();

        TraitemenTexte t = new TraitemenTexte("src/sample/texteManagement/Texte/Marc_A_Bouchaib1.txt");
        ControllerInterfaceJeu.setT(t);
        Parent nextView = FXMLLoader.load(ControllerInterfaceJeu.class.getResource("FXML/interface_jeu.fxml"));
        Scene nextScene = new Scene(nextView);
        Stage window = new Stage();
        window.setScene(nextScene);
        window.show();
    }

}
