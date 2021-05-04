package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.texteManagement.TraitemenTexte;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ControllerInterfaceJeu
{

    private final File[] listImage = listImage();
    private int i = listImage.length-1;
    private ArrayList<String> choices = new ArrayList<>();
    private boolean isMenuVisible = false;
    private static TraitemenTexte traitemenTexte;
    private static boolean updateDialog=false;

    @FXML
    private Text dialog;

    @FXML
    private Text characterName;

    @FXML
    private Button menuButton;

    @FXML
    private ImageView background;
    
    @FXML
    private VBox choicePane;

    @FXML
    private AnchorPane textPane;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private HBox menuPane;

    @FXML
    private HBox characterPane;

    @FXML
    private Button quitButton;

    @FXML
    private Button mainMenu;

    @FXML
    private Button settings;

    public static void setT(TraitemenTexte t)
    {
        traitemenTexte = t;
    }

    @FXML
    public void initialize() throws IOException
    {
        menuPane.setVisible(false);
        traitemenTexte.interactionJeu(this);
    }

    public void changeBackground(String path) throws IOException
    {
        setBackground("file:"+path);
        traitemenTexte.interactionJeu(this);
    }

    @FXML
    private void nextText() throws IOException
    {
        traitemenTexte.interactionJeu(this);
        //setBackground("file:"+listImage[i].getAbsolutePath());
        System.out.println("Image = "+listImage[i]);
        i--;
        if (i<0)
        {
            i=listImage.length-1;
        }
        System.out.println("Next text");
    }

    @FXML
    private void openMenu()
    {
        System.out.println("Open menu");
        showMenu();
    }

    @FXML
    private void keyPressed(KeyEvent keyEvent)
    {
        if (keyEvent.getCode() == KeyCode.ESCAPE)
        {
            System.out.println("Escape typed");
            showMenu();
        }
    }

    private void showMenu()
    {
        System.out.println("show menu");
        isMenuVisible = !isMenuVisible;
        menuPane.setVisible(isMenuVisible);
        textPane.setDisable(isMenuVisible);
    }

    private void setBackground(String pathname)
    {
        background.setImage(new Image(pathname, true));
        System.out.println("Set background : pathname = "+pathname);
    }

    private File[] listImage()
    {
        File directory = new File("../Images/Background");

        if (directory.isDirectory())
            return directory.listFiles();
        else
        {
            System.out.println("Not directory");
            System.out.println(directory.isFile());
            System.exit(1);
        }

        return new File[0];
    }

    public void showChoices(ArrayList<String> choices)
    {
        choicePane.setAlignment(Pos.CENTER);
        choicePane.setVisible(true);
        textPane.setVisible(false);
        characterPane.setVisible(false);
        for(String choice : choices)
        {
            Button bChoice = new Button();
            bChoice.setText(choice);
            bChoice.setPrefSize(300,100);
            bChoice.getStyleClass().add("Button");
            bChoice.setWrapText(true);
            bChoice.setTextAlignment(TextAlignment.CENTER);
            bChoice.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    System.out.println("Clicked choice : "+bChoice.getText());
                    try
                    {
                        traitemenTexte.choixClicked(bChoice.getText());
                    }

                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    choicePane.setVisible(false);
                    textPane.setVisible(true);
                    choices.clear();
                    choicePane.getChildren().clear();
                    characterPane.setVisible(true);
                }
            });
            choicePane.getChildren().addAll(bChoice);
        }
    }

    public void afficherPersonnage(String path)
    {
        //Image image = createImage("Images/Bolor.PNG");
        Image image = createImage(path);
        ImageView imageView = new ImageView(image);
        characterPane.getChildren().addAll(imageView);
        System.out.println("Affiche personnage");
    }

    public void supprimePersonnage()
    {
        characterPane.getChildren().clear();
    }

    public Image createImage(String path)
    {
        return new Image(path, 200,250,false, true);
    }

    @FXML
    public void quitGame()
    {
        System.out.println("Close game");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void returnMenu() throws IOException
    {
        System.out.println("Return menu");
        closeWindow();
        ControllerMenu.openMenu();
    }

    public void closeWindow()
    {
        System.out.println("Close window");
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    public void setTexte(String texte)
    {
        dialog.setText(texte);
    }

    public void setCharacterName(String name)
    {
        characterName.setText(name);
    }

}
