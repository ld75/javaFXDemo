package application.view.presentor;

import application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.UnsupportedEncodingException;
import java.net.URL;

import static application.Utils.openMessageDialog;

public class WelcomePresentor extends APresentor
{
    @Override
    public Node convertJavaObjectToNode(Object graphNode) throws Exception
    {
        final BorderPane welcomePane = new BorderPane();
        welcomePane.getStyleClass().add("region");
        final String path = WelcomePresentor.class.getClassLoader().getResource("demo.css").toExternalForm();
        welcomePane.getStylesheets().add(path);
        Button welcomeButton = new Button("welcome");
        welcomeButton.getStyleClass().add("button");
        welcomeButton.getStylesheets().add(path);
        welcomeButton.setOnAction(c->{
            try
            {
                openMessageDialog("aaaaaaa");
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            /*Stage primaryStage = new Stage();
            primaryStage.setTitle("aaaaaaa");
            BorderPane root = new BorderPane() ;
            root.setCenter(new Label("boutoncliqué")) ;
            Scene scene = new Scene(root,500,500) ;
            primaryStage.setScene(scene) ;
            primaryStage.show() ;*/
        });
        welcomePane.setCenter(welcomeButton);
        return welcomePane;
    }
}
