package application.view.presentor;

import application.ControllerActionBoundry;
import application.Utils;
import javafx.scene.Node;
import renault.diversitysimulator.application.controllers.IController;


public abstract class APresentor<G> implements IPresentor<G>
{
    protected IController controller;
    Utils.ThrowingFunction<ControllerActionBoundry, Object, Exception> parentAction;
    public APresentor parentPresentor;
    @Override
    public void setController(IController controller)
    {
        this.controller = controller;
    }
    @Override
    public IController getController()
    {
        return this.controller;
    }

    @Override
    public void setParentPresentor(APresentor parentPresentor)
    {
        this.parentPresentor = parentPresentor;
    }

    @Override
    public Node returnContent()
    {
        return null;
    }

    @Override
    public void setNode(Node node)
    {
    }
    public void setParentAction(Utils.ThrowingFunction<ControllerActionBoundry, Object, Exception> parentAction)
    {
        this.parentAction = parentAction;
    }
    public Utils.ThrowingFunction<ControllerActionBoundry, Object, Exception> getParentAction()
    {
        return parentAction;
    }
}
