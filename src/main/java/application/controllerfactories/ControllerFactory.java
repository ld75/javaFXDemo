package application.controllerfactories;

import application.Utils;
import application.controller.TextController;
import application.controller.WelcomeController;
import renault.diversitysimulator.application.controllers.IController;
public class ControllerFactory extends AbstractControllerFactory
{
    @Override
    public IController returnController(String controllerName) throws Exception
    {
        if (controllerName.equals(Constants.WELCOME_CONTROLLER))
        {
            return new WelcomeController();
        }
        else if (controllerName.equals(Constants.CLASSIFICATION_NODE_CONTROLLER))
        {
            Utils.openANewPannel("classification heuristique",1000,400,new WelcomeController());
            return null;
        }
        else if (controllerName.equals(Constants.WRITE_SOMETHING_CONTROLLER))
        {
            return new TextController();
        }
        else if (controllerName.equals(Constants.EXCEL_EXPORT_CONTROLLER))
        {
            return new WelcomeController();
        }
        else if (controllerName.equals(Constants.FEATURE_DEEP_TREE_TABLE_CONTROLLER_V0)){
            return new WelcomeController();
        }
        else
        {
            throw new Exception("Controller "+controllerName+" not founded");
        }
    }
}
