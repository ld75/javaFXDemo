package application.controller;

import application.view.presentor.WelcomePresentor;
import javafx.scene.Node;
import renault.diversitysimulator.application.controllers.MainController;


public class WelcomeController extends MainController
{
    WelcomePresentor presentor = new WelcomePresentor();
    @Override
    public Node execute() throws Exception
    {
        return presentor.convertJavaObjectToNode(null);

    }

}
