package application;

import application.controllerfactories.Constants;
import com.sun.javafx.application.ParametersImpl;
import javafx.scene.Scene;
import javafx.stage.Stage;
import renault.diversitysimulator.application.controllers.MenuController;

public class Application extends javafx.application.Application {

            public static void main(String[] args)
            {
                initialize();
            }

            public static void initialize() {
                launch(new String[] {"argument"});
            }

            @Override
            public void start(Stage primaryStage) throws Exception {
                initializeApplicationArguments();
                MenuController menuController = new MenuController();
                menuController.chooseMenuAction(Constants.WELCOME_CONTROLLER);
                Scene scene = new Scene(ApplicationContext.getInstance().getPane(),500.0,500.0);
                primaryStage.setScene(scene);
                primaryStage.show();

            }

            private void initializeApplicationArguments() throws Exception
            {
                Parameters arguments = ParametersImpl.getParameters(this);
                ApplicationContext.getInstance().setApplicationArguments(arguments);
            }

        }


