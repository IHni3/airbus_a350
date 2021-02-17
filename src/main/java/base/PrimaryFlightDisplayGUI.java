package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
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

    // camera
    private PrimaryFlightDisplayEntry cameraIsOnEntry;
    private RadioButton cameraOffButton;
    private RadioButton cameraOnButton;

    // gps
    private PrimaryFlightDisplayEntry gpsIsOnEntry;
    private PrimaryFlightDisplayEntry gpsIsConnectedEntry;

    private RadioButton gpsOffButton;
    private RadioButton gpsOnButton;
    private RadioButton gpsDisconnectedButton;
    private RadioButton gpsConnectedButton;

    // nitrogenBottle
    private PrimaryFlightDisplayEntry amountOfNitrogenEntry;

    private Label amountOfNitrogenLabel;

    // oxygenBottle
    private PrimaryFlightDisplayEntry amountOfOxygenEntry;

    private Label amountOfOxygenLabel;

    // tcas
    private PrimaryFlightDisplayEntry isTCASOnEntry;
    private PrimaryFlightDisplayEntry isTCASConnectedEntry;
    private PrimaryFlightDisplayEntry isTCASAlarmEntry;
    private PrimaryFlightDisplayEntry altitudeTCASEntry;

    private RadioButton tcasOffButton;
    private RadioButton tcasOnButton;
    private RadioButton tcasDisconnectedButton;
    private RadioButton tcasConnectedButton;
    private RadioButton tcasAlarmOffButton;
    private RadioButton tcasAlarmOnButton;
    private Label altitudeTCASLabel;

    // turbulent_air_flow_sensor
    private PrimaryFlightDisplayEntry isTurbulentAirFlowAlarmEntry;

    private RadioButton turbulentAirFlowAlarmOffButton;
    private RadioButton turbulentAirFlowAlarmOnButton;

    // weather_radar
    private PrimaryFlightDisplayEntry weatherRadarIsOnEntry;
    private RadioButton weatherRadarOffButton;
    private RadioButton weatherRadarOnButton;

    public static void main (String... args) {
        LogEngine.instance.init();
        FlightRecorder.instance.startup();
        FlightRecorder.instance.init();

        Application.launch(args);

        FlightRecorder.instance.shutdown();
        LogEngine.instance.close();
    }

    public void start (Stage primaryStage) {
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
            public void handle (ActionEvent event) {
                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.takeoff();
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.climbing();
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.rightTurn();
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.leftTurn();
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.descent();
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
                cockpit.landing();
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle (ActionEvent event) {
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

        Scene scene = new Scene(vbox, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane buildVisualView () {
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
        // camera
        int cameraRowIndex = 5;
        Label cameraLabel = new Label("Camera : ");
        gridPane.add(cameraLabel, 0, cameraRowIndex);

        ToggleGroup cameraToggleGroup = new ToggleGroup();

        cameraOffButton = new RadioButton("Off");
        cameraOffButton.setToggleGroup(cameraToggleGroup);
        cameraOffButton.setSelected(true);
        gridPane.add(cameraOffButton, 1, cameraRowIndex);

        cameraOnButton = new RadioButton("On");
        cameraOnButton.setToggleGroup(cameraToggleGroup);
        cameraOnButton.setSelected(false);
        gridPane.add(cameraOnButton, 2, cameraRowIndex);

        // gps
        int gpsRowIndex = 7;
        Label gpsLabel = new Label("GPS : ");
        gridPane.add(gpsLabel, 0, gpsRowIndex);

        ToggleGroup gpsToggleGroup = new ToggleGroup();
        ToggleGroup gpsConnectionToggleGroup = new ToggleGroup();

        gpsOffButton = new RadioButton("Off");
        gpsOffButton.setToggleGroup(gpsToggleGroup);
        gpsOffButton.setSelected(true);
        gridPane.add(gpsOffButton, 1, gpsRowIndex);

        gpsOnButton = new RadioButton("On");
        gpsOnButton.setToggleGroup(gpsToggleGroup);
        gpsOnButton.setSelected(false);
        gridPane.add(gpsOnButton, 2, gpsRowIndex);

        gpsDisconnectedButton = new RadioButton("Disconnected");
        gpsDisconnectedButton.setToggleGroup(gpsConnectionToggleGroup);
        gpsDisconnectedButton.setSelected(true);
        gridPane.add(gpsDisconnectedButton, 3, gpsRowIndex);

        gpsConnectedButton = new RadioButton("Connected");
        gpsConnectedButton.setToggleGroup(gpsConnectionToggleGroup);
        gpsConnectedButton.setSelected(false);
        gridPane.add(gpsConnectedButton, 4, gpsRowIndex);

        // nitrogenBottle
        int nitrogenRowIndex = 9;
        Label nitrogenLabel = new Label("Nitrogen : ");
        gridPane.add(nitrogenLabel, 0, nitrogenRowIndex);

        amountOfNitrogenLabel = new Label("250");
        gridPane.add(amountOfNitrogenLabel, 1, nitrogenRowIndex);

        // oxygenBottle
        int oxygenRowIndex = 10;
        Label oxygenLabel = new Label("Oxygen : ");
        gridPane.add(oxygenLabel, 0, oxygenRowIndex);

        amountOfOxygenLabel = new Label("100");
        gridPane.add(amountOfOxygenLabel, 1, oxygenRowIndex);

        // tcas
        int tcasRowIndex = 12;
        Label tcasLabel = new Label("TCAS : ");
        gridPane.add(tcasLabel, 0, tcasRowIndex);

        ToggleGroup tcasToggleGroup = new ToggleGroup();
        ToggleGroup tcasConnectionToggleGroup = new ToggleGroup();
        ToggleGroup tcasAlarmToggleGroup = new ToggleGroup();

        tcasOffButton = new RadioButton("Off");
        tcasOffButton.setToggleGroup(tcasToggleGroup);
        tcasOffButton.setSelected(true);
        gridPane.add(tcasOffButton, 1, tcasRowIndex);

        tcasOnButton = new RadioButton("On");
        tcasOnButton.setToggleGroup(tcasToggleGroup);
        tcasOnButton.setSelected(false);
        gridPane.add(tcasOnButton, 2, tcasRowIndex);

        tcasDisconnectedButton = new RadioButton("Disconnected");
        tcasDisconnectedButton.setToggleGroup(tcasConnectionToggleGroup);
        tcasDisconnectedButton.setSelected(true);
        gridPane.add(tcasDisconnectedButton, 3, tcasRowIndex);

        tcasConnectedButton = new RadioButton("Connected");
        tcasConnectedButton.setToggleGroup(tcasConnectionToggleGroup);
        tcasConnectedButton.setSelected(false);
        gridPane.add(tcasConnectedButton, 4, tcasRowIndex);

        tcasAlarmOffButton = new RadioButton("AlarmOff");
        tcasAlarmOffButton.setToggleGroup(tcasAlarmToggleGroup);
        tcasAlarmOffButton.setSelected(true);
        gridPane.add(tcasAlarmOffButton, 5, tcasRowIndex);

        tcasAlarmOnButton = new RadioButton("AlarmOn");
        tcasAlarmOnButton.setToggleGroup(tcasAlarmToggleGroup);
        tcasAlarmOnButton.setSelected(false);
        gridPane.add(tcasAlarmOnButton, 6, tcasRowIndex);

        // turbulent_air_flow_sensor
        int turbulentAirFlowSensorRowIndex = 14;
        Label turbulentAirFlowSensorLabel = new Label("TurbulentAirFlowSensorAlarm : ");
        gridPane.add(turbulentAirFlowSensorLabel, 0, turbulentAirFlowSensorRowIndex);

        ToggleGroup turbulentAirFlowAlarmGroup = new ToggleGroup();

        cameraOffButton = new RadioButton("Off");
        cameraOffButton.setToggleGroup(turbulentAirFlowAlarmGroup);
        cameraOffButton.setSelected(true);
        gridPane.add(cameraOffButton, 1, turbulentAirFlowSensorRowIndex);

        cameraOnButton = new RadioButton("On");
        cameraOnButton.setToggleGroup(turbulentAirFlowAlarmGroup);
        cameraOnButton.setSelected(false);
        gridPane.add(cameraOnButton, 2, turbulentAirFlowSensorRowIndex);

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

    public void buildTableView () {
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

    // camera
    public void setCameraToggleGroup (boolean isCameraOn) {
        if (isCameraOn) {
            cameraOffButton.setSelected(false);
            cameraOnButton.setSelected(true);
        } else {
            cameraOffButton.setSelected(true);
            cameraOnButton.setSelected(false);
        }
    }

    // gps
    public void setGpsToggleGroup (boolean isGpsOn) {
        if (isGpsOn) {
            gpsOffButton.setSelected(false);
            gpsOnButton.setSelected(true);
        } else {
            gpsOffButton.setSelected(true);
            gpsOnButton.setSelected(false);
        }
    }

    public void setGpsConnectionToggleGroup (boolean isGpsConnected) {
        if (isGpsConnected) {
            gpsDisconnectedButton.setSelected(false);
            gpsConnectedButton.setSelected(true);
        } else {
            gpsDisconnectedButton.setSelected(true);
            gpsConnectedButton.setSelected(false);
        }
    }

    // nitrogenBottle
    public void setNitrogenAmount (int nitrogenAmount) {
        amountOfNitrogenLabel.setText(Integer.toString(nitrogenAmount));
    }

    // oxygenBottle
    public void setOxygenAmount (int oxygenAmount) {
        amountOfOxygenLabel.setText(Integer.toString(oxygenAmount));
    }

    // tcas
    public void setTcasToggleGroup (boolean isTcasOn) {
        if (isTcasOn) {
            tcasOffButton.setSelected(false);
            tcasOnButton.setSelected(true);
        } else {
            tcasOffButton.setSelected(true);
            tcasOnButton.setSelected(false);
        }
    }

    public void setTcasConnectionToggleGroup (boolean isTcasConnected) {
        if (isTcasConnected) {
            tcasDisconnectedButton.setSelected(false);
            tcasConnectedButton.setSelected(true);
        } else {
            tcasDisconnectedButton.setSelected(true);
            tcasConnectedButton.setSelected(false);
        }
    }

    public void setTcasAlarmToggleGroup (boolean isTcasAlarmOn) {
        if (isTcasAlarmOn) {
            tcasAlarmOffButton.setSelected(false);
            tcasAlarmOnButton.setSelected(true);
        } else {
            tcasAlarmOffButton.setSelected(true);
            tcasAlarmOnButton.setSelected(false);
        }
    }

    public void setTcasAltitude (int altitude) {
        altitudeTCASLabel.setText(Integer.toString(altitude));
    }

    // turbulent_air_flow_sensor
    public void setTurbulentAirFlowAlarmToggleGroup (boolean isTurbulentAirFlowAlarm) {
        if (isTurbulentAirFlowAlarm) {
            turbulentAirFlowAlarmOffButton.setSelected(false);
            turbulentAirFlowAlarmOnButton.setSelected(true);
        } else {
            turbulentAirFlowAlarmOffButton.setSelected(true);
            turbulentAirFlowAlarmOnButton.setSelected(false);
        }
    }

    // weather_radar
    public void setWeatherRadarToggleGroup (boolean isWeatherRadarOn) {
        if (isWeatherRadarOn) {
            weatherRadarOffButton.setSelected(false);
            weatherRadarOnButton.setSelected(true);
        } else {
            weatherRadarOffButton.setSelected(true);
            weatherRadarOnButton.setSelected(false);
        }
    }

    private void initData () {
        dataList = new ArrayList<>();

        // camera
        cameraIsOnEntry = new PrimaryFlightDisplayEntry("Camera (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isCameraOn));
        dataList.add(cameraIsOnEntry);

        // gps
        gpsIsOnEntry = new PrimaryFlightDisplayEntry("GPS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSOn));
        gpsIsConnectedEntry = new PrimaryFlightDisplayEntry("GPS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isGPSConnected));
        dataList.add(gpsIsOnEntry);
        dataList.add(gpsIsConnectedEntry);

        // nitrogen_bottle
        amountOfNitrogenEntry = new PrimaryFlightDisplayEntry("NitrogenBottle (amount)", Integer.toString(PrimaryFlightDisplay.instance.amountOfNitrogen));
        dataList.add(amountOfNitrogenEntry);

        // oxygen_bottle
        amountOfOxygenEntry = new PrimaryFlightDisplayEntry("OxygenBottle (amount)", Integer.toString(PrimaryFlightDisplay.instance.amountOfOxygen));
        dataList.add(amountOfOxygenEntry);

        // tcas
        isTCASOnEntry = new PrimaryFlightDisplayEntry("TCAS (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn));
        isTCASConnectedEntry = new PrimaryFlightDisplayEntry("TCAS (isConnected)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected));
        isTCASAlarmEntry = new PrimaryFlightDisplayEntry("TCAS (isAlarm)", Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm));
        altitudeTCASEntry = new PrimaryFlightDisplayEntry("TCAS (altitude)", Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS));
        dataList.add(isTCASAlarmEntry);
        dataList.add(isTCASConnectedEntry);
        dataList.add(isTCASAlarmEntry);
        dataList.add(altitudeTCASEntry);

        // turbulent_air_flow_sensor
        isTurbulentAirFlowAlarmEntry = new PrimaryFlightDisplayEntry("TurbulentAirFlowAlarm", Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm));
        dataList.add(isTurbulentAirFlowAlarmEntry);

        // weather_radar
        weatherRadarIsOnEntry = new PrimaryFlightDisplayEntry("WeatherRadar (isOn)", Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        dataList.add(weatherRadarIsOnEntry);
    }

    private ObservableList getInitialTableData () {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update () {
        // camera
        cameraIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isCameraOn));
        setCameraToggleGroup(PrimaryFlightDisplay.instance.isCameraOn);

        // gps
        gpsIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGPSOn));
        gpsIsConnectedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGPSConnected));
        setGpsToggleGroup(PrimaryFlightDisplay.instance.isGPSOn);
        setGpsConnectionToggleGroup(PrimaryFlightDisplay.instance.isGPSConnected);

        // nitrogen_bottle
        amountOfNitrogenEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountOfNitrogen));
        setNitrogenAmount(PrimaryFlightDisplay.instance.amountOfNitrogen);

        // oxygen_bottle
        amountOfOxygenEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.amountOfOxygen));
        setOxygenAmount(PrimaryFlightDisplay.instance.amountOfOxygen);

        // tcas
        isTCASOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASOn));
        isTCASConnectedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASConnected));
        isTCASAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTCASAlarm));
        altitudeTCASEntry.setValue(Integer.toString(PrimaryFlightDisplay.instance.altitudeTCAS));
        setTcasToggleGroup(PrimaryFlightDisplay.instance.isTCASOn);
        setTcasConnectionToggleGroup(PrimaryFlightDisplay.instance.isTCASConnected);
        setTcasAlarmToggleGroup(PrimaryFlightDisplay.instance.isTCASAlarm);
        setTcasAltitude(PrimaryFlightDisplay.instance.altitudeTCAS);

        // turbulent_air_flow_sensor
        isTurbulentAirFlowAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm));
        setTurbulentAirFlowAlarmToggleGroup(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        tableView.refresh();
    }
}