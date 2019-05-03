package renault.diversitysimulator.application.controllers;

import application.ApplicationContext;
import application.Utils;
import application.view.presentor.MenuPresentor;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class MenuController extends MainController
{
        private Node content;
        MenuPresentor menuPresentor = new MenuPresentor();
        private Stage primaryStage;

        @Override
        public Node execute() throws Exception
        {
            try
            {
                menuPresentor.controller = this;
                return menuPresentor.convertJavaObjectToNode(content);
            } catch (Exception e)
            {
                Utils.openErrorDialog(e);
            }
            return null;
        }

        public void chooseMenuAction(String controllerConstant)
        {
            try
            {
                IController controller = ApplicationContext.getInstance().getControllerFactory().returnController(controllerConstant);
                if (controller!=null)
                {
                    content = controller.execute();
                    if (content != null)
                    {
                        ApplicationContext.getInstance().displayContent(this.execute());
                    }
                }
            } catch (Exception e)
            {
                Utils.openErrorDialog(e);
            }
        }

        public Stage getPrimaryStage() {
            return primaryStage;
        }

        public void setPrimaryStage(Stage primaryStage) {
            this.primaryStage = primaryStage;
        }

        // appelé soit par le menu soit par le clic sur la fermeture du primaryStage
        public void closeApplication()
        {
            Optional<ButtonType> result = Utils.openConfirmationDialog("Confirm Close",
                    "Are you sure you want to close Diversity Simulator?",
                    "Close complete application ?");

            // Stage primaryStage = controllerMain.getPrimaryStage() ;
            if (result.get() == ButtonType.OK)
            {
                primaryStage.close();
                return ;
            } else {
                return ;
            }
        }

        public Node getContent()
        {
            return content;
        }

        public void setContent(Node content)
        {
            this.content = content;
        }
    }

