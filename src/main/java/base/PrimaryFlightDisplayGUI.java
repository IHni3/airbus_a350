package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    //engine_oil_tank
    private PrimaryFlightDisplayEntry levelEngineOilTankEntry;
    private Label levelEngineOilTankLabel;

    //fuel_tank
    private PrimaryFlightDisplayEntry amountOfFuelEntry;
    private Label amountOfFuelLabel;

    //pitot_tube
    private PrimaryFlightDisplayEntry isPitotTubeCleanedEntry;
    private PrimaryFlightDisplayEntry velocityEntry;

    private RadioButton isPitotTubeCleanedOnButton;
    private RadioButton isPitotTubeCleanedOffButton;
    private Label velocityLabel;

    //radar_altimeter
    private PrimaryFlightDisplayEntry isRadarAltimeterOnEntry;
    private PrimaryFlightDisplayEntry altitudeRadarAltimeterEntry;

    private RadioButton isRadarAltimeterOnOnButton;
    private RadioButton isRadarAltimeterOnOffButton;
    private Label altitudeRadarAltimeterLabel;

    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

    //GUIs of group T16
    private GridPaneBuilder t16Builder;
    private boolean updates;


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
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = PrimaryFlightDisplay.instance.levelEngineOilTank;
//                PrimaryFlightDisplay.instance.amountOfFuel = 1000;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 0;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 0;

                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = PrimaryFlightDisplay.instance.levelEngineOilTank;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9999;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 10;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 0;

                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9800;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 200;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 0;
                cockpit.takeoff();
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9700;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 240;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 1000;
                cockpit.climbing();
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9600;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 250;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 5000;
                cockpit.rightTurn();
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9600;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 250;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 5000;
                cockpit.leftTurn();
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9400;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 240;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 4000;
                cockpit.descent();
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9300;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 200;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = true;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 1000;
                cockpit.landing();
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
//                PrimaryFlightDisplay.instance.isWeatherRadarOn = false;
//                PrimaryFlightDisplay.instance.levelEngineOilTank = 1000;
//                PrimaryFlightDisplay.instance.amountOfFuel = 9200;
//                PrimaryFlightDisplay.instance.isPitotTubeCleaned = true;
                PrimaryFlightDisplay.instance.velocity = 0;
//                PrimaryFlightDisplay.instance.isRadarAltimeterOn = false;
//                PrimaryFlightDisplay.instance.altitudeRadarAltimeter = 0;
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

//        // is needed when other teams are implemented
//        Tab t16Tab = new Tab();
//        t16Tab.setText("T16");
//        t16Tab.setContent(buildT16View());
//        tabPane.getTabs().add(t16Tab);

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
        //engine_oil_tank
        int engineOilTankRowIndex = 3;
        Label engineOilLabel = new Label("Engine Oil Level:");
        gridPane.add(engineOilLabel, 0, engineOilTankRowIndex);

        levelEngineOilTankLabel = new Label("1000");
        gridPane.add(levelEngineOilTankLabel, 1, engineOilTankRowIndex);

        //fuel_tank
        int fuelTankRowIndex = 5;
        Label fuelTankLabel = new Label("Fuel Tank Amount: ");
        gridPane.add(fuelTankLabel, 0, fuelTankRowIndex);

        amountOfFuelLabel = new Label("10000");
        gridPane.add(amountOfFuelLabel, 1, fuelTankRowIndex);

        //pitot_tube
        int pitotTubeRowIndex = 7;
        Label pitotTubeCleanLabel = new Label("Pitottube is clean: ");
        gridPane.add(pitotTubeCleanLabel, 0, pitotTubeRowIndex);


        ToggleGroup pitotTubeIsCleanGroup = new ToggleGroup();
        isPitotTubeCleanedOffButton = new RadioButton("Not clean");
        isPitotTubeCleanedOffButton.setToggleGroup(pitotTubeIsCleanGroup);
        isPitotTubeCleanedOffButton.setSelected(false);
        gridPane.add(isPitotTubeCleanedOffButton, 1, pitotTubeRowIndex);

        isPitotTubeCleanedOnButton = new RadioButton("Clean");
        isPitotTubeCleanedOnButton.setToggleGroup(pitotTubeIsCleanGroup);
        isPitotTubeCleanedOnButton.setSelected(true);
        gridPane.add(isPitotTubeCleanedOnButton, 2, pitotTubeRowIndex);

        Label pitotTubeVelocityLabel = new Label("Pitottube measured velocity: ");
        velocityLabel = new Label("0");

        gridPane.add(pitotTubeVelocityLabel, 3, pitotTubeRowIndex);
        gridPane.add(velocityLabel, 4, pitotTubeRowIndex);

        //radar_altimeter
        int radarAltimeterRowIndex = 9;
        Label radarAltimeterIsOnLabel = new Label("Radar Altimeter is on: ");
        gridPane.add(radarAltimeterIsOnLabel, 0, radarAltimeterRowIndex);

        ToggleGroup isRadarAltimeterOnGroup = new ToggleGroup();
        isRadarAltimeterOnOffButton = new RadioButton("Off");
        isRadarAltimeterOnOffButton.setSelected(true);
        isRadarAltimeterOnOffButton.setToggleGroup(isRadarAltimeterOnGroup);
        gridPane.add(isRadarAltimeterOnOffButton, 1, radarAltimeterRowIndex);

        isRadarAltimeterOnOnButton = new RadioButton("On");
        isRadarAltimeterOnOnButton.setSelected(false);
        isRadarAltimeterOnOnButton.setToggleGroup(isRadarAltimeterOnGroup);
        gridPane.add(isRadarAltimeterOnOnButton, 2, radarAltimeterRowIndex);

        Label radarAltimeterAltitudeLabel = new Label("Radar Altimeter altitude: ");
        gridPane.add(radarAltimeterAltitudeLabel, 3, radarAltimeterRowIndex);

        altitudeRadarAltimeterLabel = new Label("0");
        gridPane.add(altitudeRadarAltimeterLabel, 4, radarAltimeterRowIndex);

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

        return gridPane;
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

//    is needed in projekt with other teams components
//    public ScrollPane buildT16View() {
//        t16Builder = new GridPaneBuilder();
//        t16Builder.setDataList(data);
//
//        ScrollPane scroller = new ScrollPane();
//        scroller.setContent(t16Builder.gridPane());
//        scroller.setFitToWidth(true);
//
//        t16Builder.addTitle("Engine Oil Tank");
//        t16Builder.addInteger("Engine Oil Tank Level", 0, 1000, this::setEngineOilTankLevel);
//
//        t16Builder.addTitle("Fuel Tank");
//        t16Builder.addInteger("Fuel Amount", 0, 50000, this::setAmountOfFuel);
//
//        t16Builder.addTitle("Pitot Tube");
//        t16Builder.addToggle("Pitot Tube Cleaning", "Off", "On",this::setPitotTubeCleanedToggleGroup);
//        t16Builder.addInteger("Velocity", 0, 1500, this::setVelocity);
//
//        t16Builder.addTitle("Radar Altimeter");
//        t16Builder.addToggle("Radar Altimeter", "Off", "On", this::setRadarAltimeterOnToggleGroup);
//        t16Builder.addInteger("Radar Altimeter Altitude", 0, 5000, this::setRadarAltimeterAltitude);
//
//        return scroller;
//    }

    //engine_oil_tank
    public void setEngineOilTankLevel(int oilTankLevel){
        if(PrimaryFlightDisplay.instance.levelEngineOilTank == oilTankLevel && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(1);
//        ((TextField) elements[1]).setText(Integer.toString(oilTankLevel));
        PrimaryFlightDisplay.instance.levelEngineOilTank = oilTankLevel;

        levelEngineOilTankLabel.setText(Integer.toString(oilTankLevel));

        if(!updates)
            tableView.refresh();
    }

    //fuel_tank
    public void setAmountOfFuel(int amountOfFuel){
        if(PrimaryFlightDisplay.instance.amountOfFuel == amountOfFuel && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(3);
//        ((TextField) elements[1]).setText(Integer.toString(amountOfFuel));
        PrimaryFlightDisplay.instance.amountOfFuel = amountOfFuel;

        amountOfFuelLabel.setText(Integer.toString(amountOfFuel));

        if(!updates)
            tableView.refresh();
    }

    //pitot_tube
    public void setPitotTubeCleanedToggleGroup(boolean isCleaned){
        if(PrimaryFlightDisplay.instance.isPitotTubeCleaned == isCleaned && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(5);
//        ((RadioButton)elements[1]).setSelected(!isCleaned);
//        ((RadioButton)elements[2]).setSelected(isCleaned);
        PrimaryFlightDisplay.instance.isPitotTubeCleaned = isCleaned;

        isPitotTubeCleanedOnButton.setSelected(isCleaned);
        isPitotTubeCleanedOffButton.setSelected(!isCleaned);

        if(!updates)
            tableView.refresh();
    }

    public void setVelocity(int velocity){
        if(PrimaryFlightDisplay.instance.velocity == velocity && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(6);
//        ((TextField) elements[1]).setText(Integer.toString(velocity));
        PrimaryFlightDisplay.instance.velocity = velocity;
        velocityLabel.setText(Integer.toString(velocity));

        if(!updates)
            tableView.refresh();
    }

    //radar_altimeter
    public void setRadarAltimeterOnToggleGroup(boolean isRadarAltimeterOn){
        if(PrimaryFlightDisplay.instance.isRadarAltimeterOn == isRadarAltimeterOn && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(8);
//        ((RadioButton)elements[1]).setSelected(!isRadarAltimeterOn);
//        ((RadioButton)elements[2]).setSelected(isRadarAltimeterOn);
        PrimaryFlightDisplay.instance.isRadarAltimeterOn = isRadarAltimeterOn;

        isRadarAltimeterOnOnButton.setSelected(isRadarAltimeterOn);
        isRadarAltimeterOnOffButton.setSelected(!isRadarAltimeterOn);

        if(!updates)
            tableView.refresh();
    }

    public void setRadarAltimeterAltitude(int altitude){
        if(PrimaryFlightDisplay.instance.altitudeRadarAltimeter == altitude && !updates){return;}
//        Node[] elements = t16Builder.getUiElement(9);
//        ((TextField) elements[1]).setText(Integer.toString(altitude));
        PrimaryFlightDisplay.instance.altitudeRadarAltimeter = altitude;
        altitudeRadarAltimeterLabel.setText(Integer.toString(altitude));

        if(!updates)
            tableView.refresh();
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

    private void initData() {
        dataList = new ArrayList<>();

        //engine_oil_tank
        levelEngineOilTankEntry = new PrimaryFlightDisplayEntry("Engine Oil Tank Level (amount)",
                Integer.toString(PrimaryFlightDisplay.instance.levelEngineOilTank));
        dataList.add(levelEngineOilTankEntry);

        //fuel_tank
        amountOfFuelEntry = new PrimaryFlightDisplayEntry("Fuel Tank Level (amount)", Integer.toString(PrimaryFlightDisplay.instance.amountOfFuel));
        dataList.add(amountOfFuelEntry);

        //pitot_tube
        isPitotTubeCleanedEntry = new PrimaryFlightDisplayEntry("PitotTube (isCleaned)", Boolean.toString(PrimaryFlightDisplay.instance.isPitotTubeCleaned));
        dataList.add(isPitotTubeCleanedEntry);

        velocityEntry = new PrimaryFlightDisplayEntry("Pitot Tube (velocity)", Integer.toString(PrimaryFlightDisplay.instance.velocity));
        dataList.add(velocityEntry);

        //radar_altimeter
        isRadarAltimeterOnEntry = new PrimaryFlightDisplayEntry("Radar Altimeter (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isRadarAltimeterOn));
        dataList.add(isRadarAltimeterOnEntry);

        altitudeRadarAltimeterEntry = new PrimaryFlightDisplayEntry("Radar Altimeter (altitude)", Integer.toString(PrimaryFlightDisplay.instance.altitudeRadarAltimeter));
        dataList.add(altitudeRadarAltimeterEntry);

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
    }

    private ObservableList getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        updates = true;

        //engine_oil_tank
        levelEngineOilTankEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.levelEngineOilTank));
        setEngineOilTankLevel(PrimaryFlightDisplay.instance.levelEngineOilTank);

        //fuel_tank
        amountOfFuelEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountOfFuel));
        setAmountOfFuel(PrimaryFlightDisplay.instance.amountOfFuel);

        //pitot_tube
        isPitotTubeCleanedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isPitotTubeCleaned));
        setPitotTubeCleanedToggleGroup(PrimaryFlightDisplay.instance.isPitotTubeCleaned);

        velocityEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.velocity));
        setVelocity(PrimaryFlightDisplay.instance.velocity);

        //radar_altimeter
        isRadarAltimeterOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isRadarAltimeterOn));
        setRadarAltimeterOnToggleGroup(PrimaryFlightDisplay.instance.isRadarAltimeterOn);

        altitudeRadarAltimeterEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.altitudeRadarAltimeter));
        setRadarAltimeterAltitude(PrimaryFlightDisplay.instance.altitudeRadarAltimeter);

        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        updates = false;
        tableView.refresh();
    }
}