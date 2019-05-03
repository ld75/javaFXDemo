package application.view.presentor;

import javafx.scene.Node;
import renault.diversitysimulator.application.controllers.IController;

public interface IPresentor<G>
{
    Node convertJavaObjectToNode(G graphNode) throws Exception;
    void setController(IController controller);
    IController getController();
    void setParentPresentor(APresentor parentPresentor);
    void setNode(Node node);
    Node returnContent();

}
