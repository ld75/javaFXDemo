package application;

import application.controllerfactories.AbstractControllerFactory;
import application.controllerfactories.ControllerFactory;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import renault.diversitysimulator.application.controllers.MenuController;


public class ApplicationContext
{

    private BorderPane pane = new BorderPane();
    private MenuController menuController = new MenuController();
    private AbstractControllerFactory controllerfactory;
    private Application.Parameters applicationArguments;

    public AbstractControllerFactory getControllerFactory()
    {
        if (controllerfactory==null)
        {
            return new ControllerFactory();
        }
        return controllerfactory;
    }

    public BorderPane getPane()
    {
        return pane;
    }

    public void setPane(BorderPane pane)
    {
        this.pane = pane;
    }

    public void displayContent(Node todisplay)
    {
        getPane().setCenter(todisplay);
    }
    public void setApplicationArguments(Application.Parameters arguments)
    {
        applicationArguments = arguments;
    }
    private static class SingletonHolder
        {
            private final static ApplicationContext instance = new ApplicationContext();
        }

        public static ApplicationContext getInstance()
        {
            return SingletonHolder.instance;
        }
}
