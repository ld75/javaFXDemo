package application.view.presentor;

import application.controllerfactories.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import renault.diversitysimulator.application.controllers.MenuController;

public class MenuPresentor extends APresentor<Node>
{
    public MenuController controller;

    @Override
    public Node convertJavaObjectToNode(Node content) throws Exception
    {
        BorderPane layout = new BorderPane();
        layout.getStyleClass().add("borderpane");
        layout.setTop(getMenu());
        if (content!=null) layout.setCenter(content);
        return layout;
    }
    private Node getMenu()
    {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        MenuItem excelImport = createMenuItem("Import",  Constants.WRITE_SOMETHING_CONTROLLER);
        MenuItem excelExport = createMenuItem("Export", Constants.EXCEL_EXPORT_CONTROLLER);
        MenuItem savescenario = createMenuItem("Store and save this Scenario",Constants.STORE_AND_SAVE_SCENARIO_CONTROLLER);
        MenuItem restorescenario= createMenuItem("Restore Scenarios without loading", Constants.RESTORE_SCENARIOS_CONTROLLER);
        /*MenuItem jsonsavetree = createMenuItem("JsonSave", FontAwesome.SAVE.getDescription(), Constants.SAVE_TREE_TO_JSON_FILE_CONTROLLER);
        MenuItem jsonloadtree = createMenuItem("JsonLoad", FontAwesome.CLOUD_UPLOAD.getDescription(), Constants.LOAD_TREE_FROM_JSON_FILE_CONTROLLER);*/


        menuFile.getItems().addAll(excelImport,excelExport, savescenario,restorescenario);

        Menu menuEdition = new Menu("Edit");

        Menu menuSettings = new Menu("Settings");

        MenuItem viewParameters = createMenuItem("Classification Heuristique",  Constants.CLASSIFICATION_NODE_CONTROLLER);
        MenuItem viewDriverDiversity = createMenuItem("Driver Diversity",  Constants.DIVERSITY_TREE_TABLE_CONTROLLER);

        menuSettings.getItems().addAll(viewParameters,viewDriverDiversity);

        Menu menuView = new Menu("Configuration");


        MenuItem viewFeatureV0 = createMenuItem("Features", Constants.FEATURE_DEEP_TREE_TABLE_CONTROLLER_V0);

        Menu viewComponents = createMenu("Components");
        MenuItem componentsDeepUp = createMenuItem("Up tree", Constants.COMPONENTS_DEEP_UP_TREE_TABLE_CONTROLLER);
        MenuItem componentsDeepDown = createMenuItem("Down tree", Constants.COMPONENTS_DEEP_DOWN_TREE_TABLE_CONTROLLER);
        viewComponents.getItems().add(componentsDeepUp);
        viewComponents.getItems().add(componentsDeepDown);

        MenuItem viewParts = createMenuItem("Parts", Constants.PARTS_DEEP_TREE_TABLE_CONTROLLER);

        menuView.getItems().addAll(viewFeatureV0,viewComponents,viewParts);

        Menu menuCosts = new Menu("Economics");
        MenuItem displayYearlyCosts = createMenuItem("Display Yearly costs", Constants.DISPLAY_YEARLY_COSTS);
        MenuItem displayYearlyRevenues = createMenuItem("Display Yearly revenues", Constants.DISPLAY_YEARLY_REVENUES);
        MenuItem displayYearlyNPV = createMenuItem("Display NPV", Constants.DISPLAY_YEARLY_NPV);
        MenuItem editCostlines = createMenuItem("Edit Costlines", Constants.EDIT_COSTLINES);

        Menu menuScenarios = new Menu("Scenarios");
        MenuItem displayScenarios = createMenuItem("Scenario Tree", Constants.DISPLAY_SCENARIOS_TREE_CONTROLLER);
        MenuItem displayScenarioTable = createMenuItem("Scenario Table", Constants.DISPLAY_SCENARIOS_TABLE_CONTROLLER);
        menuScenarios.getItems().add(displayScenarios);
        menuScenarios.getItems().add(displayScenarioTable);

        MenuItem displayVolumes = createMenuItem("Volumes", Constants.DISPLAY_VOLUMES_CONTROLLER);
        MenuItem displayScenarioOerview = createMenuItem("Overview", Constants.OVERVIEW_CONTROLLER);
        menuCosts.getItems().add(displayYearlyCosts);
        menuCosts.getItems().add(displayYearlyRevenues);
        menuCosts.getItems().add(displayYearlyNPV);
        menuCosts.getItems().add(menuScenarios);
        menuCosts.getItems().add(displayVolumes);
        menuCosts.getItems().add(displayScenarioOerview);
        menuCosts.getItems().add(editCostlines);


        Menu menuHelp = new Menu("Help");
        menuHelp.getItems().add(createMenuItem("Single Boolean expression tester",Constants.SINGLE_BOOLEANEXPRESSION_TESTER_CONTROLLER));
        menuHelp.getItems().add(createMenuItem("Multi Boolean expression tester",Constants.MULTI_BOOLEANEXPRESSION_TESTER_CONTROLLER));
        menuHelp.getItems().add(createMenuItem("Rule Console tester",Constants.CONSOLE_ROLE_TESTER_CONTROLLER));
        menuHelp.getItems().add(createMenuItem("Pilot Engine",Constants.PILOT_ENGINE_CONTROLLER));

        Menu menuCanvasDesign = new Menu("Canvas");
        menuCanvasDesign.getItems().add(createMenuItem("Canvas Design",Constants.CANVAS_DESIGN_CONTROLLER));

        Menu menuEngineRules = new Menu("Engine");
        menuEngineRules.getItems().add(createMenuItem("Engine",Constants.ENGINE_CONTROLLER));

        menuBar.getMenus().addAll(menuFile,menuEdition,menuSettings,menuView,menuCosts,menuEngineRules,menuCanvasDesign,menuHelp);

        return menuBar;
    }



    private Menu createMenu(String label)
    {
        Menu menu = new Menu(label);
        return menu;
    }


    private MenuItem createMenuItem(String label, String controllerConstant)
    {
        MenuItem menu = new MenuItem(label);
        setActionToMenu(controllerConstant, menu);
        return menu;
    }

    private void setActionToMenu(String controllerConstant, MenuItem add)
    {
        add.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent)
            {
                controller.chooseMenuAction(controllerConstant);
            }
        });
    }
}
