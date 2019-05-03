package application.controller;

import application.view.presentor.WelcomePresentor;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import renault.diversitysimulator.application.controllers.MainController;


public class TextController extends MainController
{
    TextArea text= new TextArea();
    @Override
    public Node execute() throws Exception
    {
        return text;

    }

}
