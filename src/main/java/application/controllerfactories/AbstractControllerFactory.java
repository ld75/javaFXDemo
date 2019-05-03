package application.controllerfactories;

import renault.diversitysimulator.application.controllers.IController;

public abstract class AbstractControllerFactory
{
    public IController create(String controllerName) throws Exception
    {
        return returnController(controllerName);
    }

    public abstract IController returnController(String controllerName) throws Exception;

}
