package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControllerMenu
{
    @FXML
    private Button continueButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button quitButton;

    @FXML
    private Pane mainWindow;

    @FXML
    private Label titre;

    @FXML
    private Button cancelButton;

    @FXML
    private Button playButton;

    @FXML
    private TextField name;


    @FXML
    public void initialize()
    {
        File file = new File("src\\Images\\Extra\\menuBackground.jpg");
        System.out.println("Initialize");
        Image image = new Image("file:"+file.getAbsolutePath());
        BackgroundSize backgroundSize = new BackgroundSize(100,100, true, true, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        mainWindow.setBackground(background);
    }

    /*@FXML
    public void launchGame() throws IOException
    {
        System.out.println("New game launching");

        Parent nextView = FXMLLoader.load(ControllerMenu.class.getResource("FXML/interface_jeu.fxml"));
        Scene nextScene = new Scene(nextView);

        mainWindow.getChildren().setAll(nextView);

    }*/

    @FXML
    public void continueGame()
    {
        System.out.println("Continue game");
    }

    @FXML
    public void settingGame()
    {
        System.out.println("Open setting menu");
    }

    @FXML
    public void quitGame()
    {
        System.out.println("Close game");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openPopup() throws IOException
    {
        System.out.println("Open pop up");
        ControllerPopup.openPopup();
        quitGame();
    }

    public static void openMenu() throws IOException
    {
        System.out.println("Open menu");
        Parent view = FXMLLoader.load(ControllerMenu.class.getResource("FXML/Menu.fxml"));
        Scene scene = new Scene(view);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
