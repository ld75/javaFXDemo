package renault.diversitysimulator.application.controllers;

import application.ApplicationContext;
import javafx.scene.Node;

public abstract class MainController implements IController
{
    @Override
    public abstract Node execute() throws Exception;
}
