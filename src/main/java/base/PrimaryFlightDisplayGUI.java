package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.util.ArrayList;

public class PrimaryFlightDisplayGUI extends Application {
    private TableView tableView;
    private ArrayList<PrimaryFlightDisplayEntry> dataList;
    private ObservableList data;
    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

    private Spinner slatSpinner;
    private PrimaryFlightDisplayEntry slatEntry;

    private RadioButton costOptimizerOffButton;
    private RadioButton costOptimizerOnButton;
    private PrimaryFlightDisplayEntry costOptimizerIsOnEntry;

    private Spinner costOptimizerSizeSpinner;
    private PrimaryFlightDisplayEntry costOptimizerSizeEntry;

    private Spinner costOptimizerIndexSpinner;
    private PrimaryFlightDisplayEntry costOptimizerIndexEntry;

    private RadioButton cargoCompartmentLightOnButton;
    private RadioButton cargoCompartmentLightOffButton;
    private PrimaryFlightDisplayEntry cargoCompartmentLightIsOnEntry;

    private RadioButton antiCollisionLightOnButton;
    private RadioButton antiCollisionLightOffButton;
    private PrimaryFlightDisplayEntry antiCollisionLightIsOnEntry;

    private RadioButton routeManagementOffButton;
    private RadioButton routeManagementOnButton;
    private PrimaryFlightDisplayEntry routeManagementIsOnEntry;

    private Spinner routeManagementIndexSpinner;
    private PrimaryFlightDisplayEntry routeManagementIndexEntry;

    private Spinner routeManagementSizeSpinner;
    private PrimaryFlightDisplayEntry routeManagementSizeEntry;

    private RadioButton landingLightWingOffButton;
    private RadioButton landingLightWingOnButton;
    private PrimaryFlightDisplayEntry landingLightWingIsOnEntry;

    private RadioButton landingLightBodyOffButton;
    private RadioButton landingLightBodyOnButton;
    private PrimaryFlightDisplayEntry landingLightBodyIsOnEntry;

    public static void main(String... args) {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        Application.launch(args);

        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("A350 - Primary Flight Display");

        Airplane airplane = new Airplane();
        airplane.build();

        Cockpit cockpit = new Cockpit(airplane);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 12, 15, 12));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");

        Button startupButton = new Button("Startup");
        startupButton.setPrefSize(75, 20);

        startupButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.takeoff();
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.climbing();
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.rightTurn();
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.leftTurn();
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.descent();
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.landing();
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.shutdown();
                update();
            }
        });

        hBox.getChildren().addAll(startupButton, taxiButton, takeoffButton, climbingButton, rightTurnButton,
                leftTurnButton, descentButton, landingButton, shutdownButton);

        TabPane tabPane = new TabPane();

        Tab visualTab = new Tab();
        visualTab.setText("Visual");
        visualTab.setContent(buildVisualView());
        tabPane.getTabs().add(visualTab);

        Tab tableTab = new Tab();
        tableTab.setText("Table");
        buildTableView();
        tableTab.setContent(tableView);
        tabPane.getTabs().add(tableTab);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(hBox, tabPane);

        Scene scene = new Scene(vbox, 850, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane buildVisualView() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.BASELINE_LEFT);

        Label frontGearLabel = new Label("Front Gear : ");
        gridPane.add(frontGearLabel, 0, 0);

        ComboBox<String> frontGearComboBox = new ComboBox<>();
        frontGearComboBox.getItems().addAll("down", "up");
        frontGearComboBox.setValue("down");
        frontGearComboBox.setEditable(false);
        gridPane.add(frontGearComboBox, 1, 0);

        Label rearGearLabel = new Label("Rear Gear : ");
        gridPane.add(rearGearLabel, 2, 0);

        ComboBox<String> rearGearComboBox = new ComboBox<>();
        rearGearComboBox.getItems().addAll("down", "up");
        rearGearComboBox.setValue("down");
        rearGearComboBox.setEditable(false);
        gridPane.add(rearGearComboBox, 3, 0);

        Label flapLabel = new Label("Flap : ");
        gridPane.add(flapLabel, 4, 0);

        ComboBox<String> flapComboBox = new ComboBox<>();
        flapComboBox.getItems().addAll("neutral", "one", "two", "three");
        flapComboBox.setValue("neutral");
        flapComboBox.setEditable(false);
        gridPane.add(flapComboBox, 5, 0);

        // --- insert section: begin

        // weather_radar
        Label weatherRadarLabel = new Label("WeatherRadar : ");
        gridPane.add(weatherRadarLabel, 6, 0);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        weatherRadarOffButton = new RadioButton("Off");
        weatherRadarOffButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOffButton.setSelected(true);
        gridPane.add(weatherRadarOffButton, 7, 0);

        weatherRadarOnButton = new RadioButton("On");
        weatherRadarOnButton.setToggleGroup(weatherRadarToggleGroup);
        weatherRadarOnButton.setSelected(false);
        gridPane.add(weatherRadarOnButton, 8, 0);

        // --- insert section: end

        Label frequencyLabel = new Label("Frequency : ");
        gridPane.add(frequencyLabel, 0, 2);

        Spinner<Integer> vcfSpinner = new Spinner<>();
        vcfSpinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> vcfSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(200, 300, 250);
        vcfSpinner.setValueFactory(vcfSpinnerValueFactory);
        gridPane.add(vcfSpinner, 1, 2);

        addSlatElements(gridPane, 3);

        addAntiCollisionLightElements(gridPane, 4);

        addCargoCompartmentLightElements(gridPane, 5);

        addLandingLightWingElements(gridPane, 6);
        addLandingLightBodyElements(gridPane, 7);

        addRouteManagementEnabledElements(gridPane, 8);
        addRouteManagementIndexElements(gridPane, 9);
        addRouteManagementSizeElements(gridPane, 10);

        addCostOptimizerEnabledElements(gridPane, 11);
        addCostOptimizerIndexElements(gridPane, 12);
        addCostOptimizerSizeElements(gridPane, 13);

        return gridPane;
    }

    private void addCostOptimizerEnabledElements(GridPane gridPane, int rowIndex) {
        costOptimizerOffButton = new RadioButton();
        costOptimizerOnButton = new RadioButton();
        onOffGroupElementFactory("Cost optimizer enabled: ",
                costOptimizerOnButton,
                costOptimizerOffButton,
                gridPane,
                rowIndex);
    }

    private void addCostOptimizerSizeElements(GridPane gridPane, int rowIndex) {
        costOptimizerSizeSpinner = new Spinner();
        numberElementFactory("Cost optimizer size: ",
                costOptimizerSizeSpinner,
                0,
                1000,
                0,
                gridPane,
                rowIndex);
    }

    private void addCostOptimizerIndexElements(GridPane gridPane, int rowIndex) {
        costOptimizerIndexSpinner = new Spinner();
        numberElementFactory("Cost optimizer index: ",
                costOptimizerIndexSpinner,
                0,
                1000,
                0,
                gridPane,
                rowIndex);
    }


    private void addRouteManagementSizeElements(GridPane gridPane, int rowIndex) {
        routeManagementSizeSpinner = new Spinner();
        numberElementFactory("Route management size: ",
                routeManagementSizeSpinner,
                0,
                1000,
                0,
                gridPane,
                rowIndex);
    }

    private void addRouteManagementIndexElements(GridPane gridPane, int rowIndex) {
        routeManagementIndexSpinner = new Spinner();
        numberElementFactory("Route management index: ",
                routeManagementIndexSpinner,
                0,
                1000,
                0,
                gridPane,
                rowIndex);
    }

    private void addRouteManagementEnabledElements(GridPane gridPane, int rowIndex) {
        routeManagementOnButton = new RadioButton();
        routeManagementOffButton = new RadioButton();
        onOffGroupElementFactory("Route management enabled: ",
                routeManagementOnButton,
                routeManagementOffButton,
                gridPane,
                rowIndex);
    }

    private void addLandingLightWingElements(GridPane gridPane, int rowIndex) {
        landingLightWingOnButton = new RadioButton();
        landingLightWingOffButton = new RadioButton();
        onOffGroupElementFactory("Landing light wing: ",
                landingLightWingOnButton,
                landingLightWingOffButton,
                gridPane,
                rowIndex);
    }

    private void addLandingLightBodyElements(GridPane gridPane, int rowIndex) {
        landingLightBodyOnButton = new RadioButton();
        landingLightBodyOffButton = new RadioButton();
        onOffGroupElementFactory("Landing light body: ",
                landingLightBodyOnButton,
                landingLightBodyOffButton,
                gridPane,
                rowIndex);
    }

    private void addCargoCompartmentLightElements(GridPane gridPane, int rowIndex) {
        cargoCompartmentLightOnButton = new RadioButton();
        cargoCompartmentLightOffButton = new RadioButton();
        onOffGroupElementFactory("Cargo compartment light: ",
                cargoCompartmentLightOnButton,
                cargoCompartmentLightOffButton,
                gridPane,
                rowIndex);
    }

    private void addAntiCollisionLightElements(GridPane gridPane, int rowIndex) {
        antiCollisionLightOnButton = new RadioButton();
        antiCollisionLightOffButton = new RadioButton();
        onOffGroupElementFactory("Anti collision light: ",
                antiCollisionLightOnButton,
                antiCollisionLightOffButton,
                gridPane,
                rowIndex);
    }

    private void addSlatElements(GridPane gridPane, int rowIndex) {
        slatSpinner = new Spinner();
        numberElementFactory("Slat: ",
                slatSpinner,
                -90,
                0,
                0,
                gridPane,
                rowIndex);
    }

    private static void onOffGroupElementFactory(String title, RadioButton onBtn, RadioButton offBtn, GridPane parent, int rowIndex) {
        Label antiCollisionLightLabel = new Label(title);
        parent.add(antiCollisionLightLabel, 0, rowIndex);

        ToggleGroup weatherRadarToggleGroup = new ToggleGroup();

        offBtn.setText("Off");
        offBtn.setToggleGroup(weatherRadarToggleGroup);
        offBtn.setSelected(true);
        parent.add(offBtn, 1, rowIndex);

        onBtn.setText("On");
        onBtn.setToggleGroup(weatherRadarToggleGroup);
        onBtn.setSelected(false);
        parent.add(onBtn, 2, rowIndex);
    }

    private static void numberElementFactory(String title, Spinner spinner, int min, int max, int initial, GridPane parent, int rowIndex) {
        Label label = new Label(title);
        parent.add(label, 0, rowIndex);

        spinner.setMaxWidth(60);
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(min, max, initial);
        spinner.setValueFactory(factory);
        parent.add(spinner, 1, rowIndex);
    }

    public void buildTableView() {
        tableView = new TableView();
        data = getInitialTableData();
        tableView.setItems(data);

        TableColumn attributeColumn = new TableColumn("attribute");
        attributeColumn.setCellValueFactory(new PropertyValueFactory("attribute"));

        TableColumn valueColumn = new TableColumn("value");
        valueColumn.setCellValueFactory(new PropertyValueFactory("value"));

        tableView.getColumns().setAll(attributeColumn, valueColumn);
        tableView.setPrefWidth(450);
        tableView.setPrefHeight(450);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // weather_radar
    public void setWeatherRadarToggleGroup(boolean isWeatherRadarOn) {
        if (isWeatherRadarOn) {
            weatherRadarOffButton.setSelected(false);
            weatherRadarOnButton.setSelected(true);
        } else {
            weatherRadarOffButton.setSelected(true);
            weatherRadarOnButton.setSelected(false);
        }
    }

    public void setCostOptimizerEnabled(boolean enabled) {
        if (enabled) {
            costOptimizerOffButton.setSelected(false);
            costOptimizerOnButton.setSelected(true);
        } else {
            costOptimizerOffButton.setSelected(true);
            costOptimizerOnButton.setSelected(false);
        }
    }


    public void setCargoCompartmentLightEnabled(boolean enabled) {
        if (enabled) {
            cargoCompartmentLightOffButton.setSelected(false);
            cargoCompartmentLightOnButton.setSelected(true);
        } else {
            cargoCompartmentLightOffButton.setSelected(true);
            cargoCompartmentLightOnButton.setSelected(false);
        }
    }

    public void setAntiCollisionLightEnabled(boolean enabled) {
        if (enabled) {
            antiCollisionLightOffButton.setSelected(false);
            antiCollisionLightOnButton.setSelected(true);
        } else {
            antiCollisionLightOffButton.setSelected(true);
            antiCollisionLightOnButton.setSelected(false);
        }
    }

    public void setRouteManagementEnabled(boolean enabled) {
        if (enabled) {
            routeManagementOffButton.setSelected(false);
            routeManagementOnButton.setSelected(true);
        } else {
            routeManagementOffButton.setSelected(true);
            routeManagementOnButton.setSelected(false);
        }
    }

    public void setLandingLightWingEnabled(boolean enabled) {
        if (enabled) {
            landingLightWingOffButton.setSelected(false);
            landingLightWingOnButton.setSelected(true);
        } else {
            landingLightWingOffButton.setSelected(true);
            landingLightWingOnButton.setSelected(false);
        }
    }

    public void setLandingLightBodyEnabled(boolean enabled) {
        if (enabled) {
            landingLightBodyOffButton.setSelected(false);
            landingLightBodyOnButton.setSelected(true);
        } else {
            landingLightBodyOffButton.setSelected(true);
            landingLightBodyOnButton.setSelected(false);
        }
    }

    private void initData() {
        dataList = new ArrayList<>();

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);


        slatEntry = new PrimaryFlightDisplayEntry("Slat (isOn)", String.valueOf(PrimaryFlightDisplay.instance.getSlagDegree()));
        dataList.add(slatEntry);

        costOptimizerIsOnEntry = new PrimaryFlightDisplayEntry("CostOptimizer (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isCostOptimizerEnabled()));
        dataList.add(costOptimizerIsOnEntry);

        costOptimizerSizeEntry = new PrimaryFlightDisplayEntry("CostOptimizer (size)", String.valueOf(PrimaryFlightDisplay.instance.getSizeCostOptimizer()));
        dataList.add(costOptimizerSizeEntry);

        costOptimizerIndexEntry = new PrimaryFlightDisplayEntry("CostOptimizer (index)", String.valueOf(PrimaryFlightDisplay.instance.getIndexCostOptimizer()));
        dataList.add(costOptimizerIndexEntry);

        cargoCompartmentLightIsOnEntry = new PrimaryFlightDisplayEntry("CargoCompartmentLight (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isCargoCompartmentLightEnabled()));
        dataList.add(cargoCompartmentLightIsOnEntry);

        antiCollisionLightIsOnEntry = new PrimaryFlightDisplayEntry("AntiCollisionLight (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isAntiCollisionLightEnabled()));
        dataList.add(antiCollisionLightIsOnEntry);

        routeManagementIsOnEntry = new PrimaryFlightDisplayEntry("RouteManagement (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isRouteManagementEnabled()));
        dataList.add(routeManagementIsOnEntry);

        routeManagementIndexEntry = new PrimaryFlightDisplayEntry("RouteManagement (index)", String.valueOf(PrimaryFlightDisplay.instance.getIndexRouteManagement()));
        dataList.add(routeManagementIndexEntry);

        routeManagementSizeEntry = new PrimaryFlightDisplayEntry("RouteManagement (Size)", String.valueOf(PrimaryFlightDisplay.instance.getSizeRouteManagement()));
        dataList.add(routeManagementSizeEntry);

        landingLightWingIsOnEntry = new PrimaryFlightDisplayEntry("LandingLightWing (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isLandingLightWingEnabled()));
        dataList.add(landingLightWingIsOnEntry);

        landingLightBodyIsOnEntry = new PrimaryFlightDisplayEntry("LandingLightBody (isOn)", String.valueOf(PrimaryFlightDisplay.instance.isLandingLightBodyEnabled()));
        dataList.add(landingLightBodyIsOnEntry);

    }

    private ObservableList getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        setCostOptimizerEnabled(PrimaryFlightDisplay.instance.isCostOptimizerEnabled());
        setCargoCompartmentLightEnabled(PrimaryFlightDisplay.instance.isCargoCompartmentLightEnabled());
        setAntiCollisionLightEnabled(PrimaryFlightDisplay.instance.isAntiCollisionLightEnabled());
        setRouteManagementEnabled(PrimaryFlightDisplay.instance.isRouteManagementEnabled());
        setLandingLightWingEnabled(PrimaryFlightDisplay.instance.isLandingLightWingEnabled());
        setLandingLightBodyEnabled(PrimaryFlightDisplay.instance.isLandingLightBodyEnabled());

        slatSpinner.getValueFactory().setValue(PrimaryFlightDisplay.instance.getSlagDegree());
        costOptimizerSizeSpinner.getValueFactory().setValue(PrimaryFlightDisplay.instance.getSizeCostOptimizer());
        costOptimizerIndexSpinner.getValueFactory().setValue(PrimaryFlightDisplay.instance.getIndexCostOptimizer());
        routeManagementIndexSpinner.getValueFactory().setValue(PrimaryFlightDisplay.instance.getIndexRouteManagement());
        routeManagementSizeSpinner.getValueFactory().setValue(PrimaryFlightDisplay.instance.getSizeRouteManagement());


        slatEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.getSlagDegree()));
        costOptimizerIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isCostOptimizerEnabled()));
        costOptimizerSizeEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.getSizeCostOptimizer()));
        costOptimizerIndexEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.getIndexCostOptimizer()));
        cargoCompartmentLightIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isCargoCompartmentLightEnabled()));
        antiCollisionLightIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isAntiCollisionLightEnabled()));
        routeManagementIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isRouteManagementEnabled()));
        routeManagementIndexEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.getIndexRouteManagement()));
        routeManagementSizeEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.getSizeRouteManagement()));
        landingLightWingIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isLandingLightWingEnabled()));
        landingLightBodyIsOnEntry.setValue(String.valueOf(PrimaryFlightDisplay.instance.isLandingLightBodyEnabled()));


        tableView.refresh();
    }
}