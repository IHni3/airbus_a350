package base;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logging.LogEngine;
import recorder.FlightRecorder;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.regex.Pattern;

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

    // GUIs of group T15
    private GridPaneBuilder t15Builder;
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
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                cockpit.startup();
                update();
            }
        });

        Button taxiButton = new Button("Taxi");
        taxiButton.setPrefSize(75, 20);

        taxiButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = true;
                cockpit.taxi();
                update();
            }
        });

        Button takeoffButton = new Button("TakeOff");
        takeoffButton.setPrefSize(75, 20);

        takeoffButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.takeoff();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button climbingButton = new Button("Climbing");
        climbingButton.setPrefSize(75, 20);

        climbingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.climbing();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button rightTurnButton = new Button("R-Turn");
        rightTurnButton.setPrefSize(75, 20);

        rightTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.rightTurn();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button leftTurnButton = new Button("L-Turn");
        leftTurnButton.setPrefSize(75, 20);

        leftTurnButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.leftTurn();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button descentButton = new Button("Descent");
        descentButton.setPrefSize(75, 20);

        descentButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.descent();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button landingButton = new Button("Landing");
        landingButton.setPrefSize(75, 20);

        landingButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.landing();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = true;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 777;
                PrimaryFlightDisplay.instance.amountOfFuel = 69.420;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = true;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = true;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = true;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = true;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
                update();
            }
        });

        Button shutdownButton = new Button("Shutdown");
        shutdownButton.setPrefSize(75, 20);

        shutdownButton.setOnAction(new EventHandler<>() {
            public void handle(ActionEvent event) {
                cockpit.shutdown();
                PrimaryFlightDisplay.instance.isWeatherRadarOn = false;
                PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = 0;
                PrimaryFlightDisplay.instance.amountOfFuel = 0;
                PrimaryFlightDisplay.instance.isRightNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isTailNavigationLightOn = false;
                PrimaryFlightDisplay.instance.isNonSmokingSignOn = false;
                PrimaryFlightDisplay.instance.isSeatBeltSignOn = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = false;
                PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = false;
                PrimaryFlightDisplay.instance.isTaxiLightOn = false;
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

        Tab t15Tab = new Tab();
        t15Tab.setText("T15");
        t15Tab.setContent(buildT15View());
        tabPane.getTabs().add(t15Tab);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getChildren().addAll(hBox, tabPane);

        Scene scene = new Scene(vbox, 900, 500);
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

        altitudeTCASLabel = new Label("0");
        gridPane.add(altitudeTCASLabel, 8, tcasRowIndex);

        // turbulent_air_flow_sensor
        int turbulentAirFlowSensorRowIndex = 14;
        Label turbulentAirFlowSensorLabel = new Label("TurbulentAirFlowSensorAlarm : ");
        gridPane.add(turbulentAirFlowSensorLabel, 0, turbulentAirFlowSensorRowIndex);

        ToggleGroup turbulentAirFlowAlarmGroup = new ToggleGroup();

        turbulentAirFlowAlarmOffButton = new RadioButton("Off");
        turbulentAirFlowAlarmOffButton.setToggleGroup(turbulentAirFlowAlarmGroup);
        turbulentAirFlowAlarmOffButton.setSelected(true);
        gridPane.add(turbulentAirFlowAlarmOffButton, 1, turbulentAirFlowSensorRowIndex);

        turbulentAirFlowAlarmOnButton = new RadioButton("On");
        turbulentAirFlowAlarmOnButton.setToggleGroup(turbulentAirFlowAlarmGroup);
        turbulentAirFlowAlarmOnButton.setSelected(false);
        gridPane.add(turbulentAirFlowAlarmOnButton, 2, turbulentAirFlowSensorRowIndex);

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

    // camera
    public void setCameraToggleGroup(boolean isCameraOn) {
        if (isCameraOn) {
            cameraOffButton.setSelected(false);
            cameraOnButton.setSelected(true);
        } else {
            cameraOffButton.setSelected(true);
            cameraOnButton.setSelected(false);
        }
    }

    // gps
    public void setGpsToggleGroup(boolean isGpsOn) {
        if (isGpsOn) {
            gpsOffButton.setSelected(false);
            gpsOnButton.setSelected(true);
        } else {
            gpsOffButton.setSelected(true);
            gpsOnButton.setSelected(false);
        }
    }

    public void setGpsConnectionToggleGroup(boolean isGpsConnected) {
        if (isGpsConnected) {
            gpsDisconnectedButton.setSelected(false);
            gpsConnectedButton.setSelected(true);
        } else {
            gpsDisconnectedButton.setSelected(true);
            gpsConnectedButton.setSelected(false);
        }
    }

    // nitrogenBottle
    public void setNitrogenAmount(int nitrogenAmount) {
        amountOfNitrogenLabel.setText(Integer.toString(nitrogenAmount));
    }

    // oxygenBottle
    public void setOxygenAmount(int oxygenAmount) {
        amountOfOxygenLabel.setText(Integer.toString(oxygenAmount));
    }

    // tcas
    public void setTcasToggleGroup(boolean isTcasOn) {
        if (isTcasOn) {
            tcasOffButton.setSelected(false);
            tcasOnButton.setSelected(true);
        } else {
            tcasOffButton.setSelected(true);
            tcasOnButton.setSelected(false);
        }
    }

    public void setTcasConnectionToggleGroup(boolean isTcasConnected) {
        if (isTcasConnected) {
            tcasDisconnectedButton.setSelected(false);
            tcasConnectedButton.setSelected(true);
        } else {
            tcasDisconnectedButton.setSelected(true);
            tcasConnectedButton.setSelected(false);
        }
    }

    public void setTcasAlarmToggleGroup(boolean isTcasAlarmOn) {
        if (isTcasAlarmOn) {
            tcasAlarmOffButton.setSelected(false);
            tcasAlarmOnButton.setSelected(true);
        } else {
            tcasAlarmOffButton.setSelected(true);
            tcasAlarmOnButton.setSelected(false);
        }
    }

    public void setTcasAltitude(int altitude) {
        altitudeTCASLabel.setText(Integer.toString(altitude));
    }

    // turbulent_air_flow_sensor
    public void setTurbulentAirFlowAlarmToggleGroup(boolean isTurbulentAirFlowAlarm) {
        if (isTurbulentAirFlowAlarm) {
            turbulentAirFlowAlarmOffButton.setSelected(false);
            turbulentAirFlowAlarmOnButton.setSelected(true);
        } else {
            turbulentAirFlowAlarmOffButton.setSelected(true);
            turbulentAirFlowAlarmOnButton.setSelected(false);
        }
    }

    public ScrollPane buildT15View() {
        t15Builder = new GridPaneBuilder();
        t15Builder.setDataList(data);

        ScrollPane scroller = new ScrollPane();
        scroller.setContent(t15Builder.gridPane());
        scroller.setFitToWidth(true);

        t15Builder.addToggle("Right navigation-light", "Off", "On", this::setRightNavigationLight);
        t15Builder.addToggle("Tail navigation-light", "Off", "On", this::setTailNavigationLight);
        t15Builder.addToggle("Taxi light", "Off", "On", this::setTaxiLight);

        t15Builder.addTitle("Seats");
        t15Builder.addToggle("Non smoking sign", "Off", "On", this::setNonSmokingSign);
        t15Builder.addToggle("Seat belt sign", "Off", "On", this::setSeatBeltSign);
        t15Builder.addInteger("Level Seat", Integer.MIN_VALUE, Integer.MAX_VALUE, this::setSeatLevel);

        t15Builder.addTitle("Exhaust-gas sensor");
        t15Builder.addInteger("Temperature", Integer.MIN_VALUE, Integer.MAX_VALUE, this::setExhaustGasTemperature);
        t15Builder.addToggle("Alarm major", "Off", "On", this::setAlarmMajorExhaustGasTemperatureSensor);
        t15Builder.addToggle("Alarm critical", "Off", "On", this::setAlarmCriticalExhaustGasTemperatureSensor);

        t15Builder.addTitle("Fuel");
        t15Builder.addInteger("Fuel flow", 0, Integer.MAX_VALUE, this::setFuelFlow);
        t15Builder.addFloat("Fuel amount", 0, Double.MAX_VALUE, this::setFuelAmount);
        t15Builder.addToggle("Alarm reserve", "Off", "On", this::setAlarmReserveFuelSensor);
        t15Builder.addToggle("Alarm major reserve", "Off", "On", this::setAlarmMajorFuelSensor);
        t15Builder.addToggle("Alarm critical reserve", "Off", "On", this::setAlarmCriticalFuelSensor);

        t15Builder.addTitle("Ice detector probe");
        t15Builder.addToggle("Body-probe", "Off", "On", this::setIceDetectorProbeBodyActivated);
        t15Builder.addToggle("Wind-probe", "Off", "On", this::setIceDetectorProbeWingActivated);
        t15Builder.addToggle("Ice detected", "No", "Yes", this::setIceDetected);

        t15Builder.addTitle("Fire detector");
        t15Builder.addToggle("Body: fire detected", "No", "Yes", this::setFireDetectedBody);
        t15Builder.addToggle("Wing: fire detected", "No", "Yes", this::setFireDetectedWing);

        t15Builder.addTitle("Oxygen sensor");
        t15Builder.addToggle("Alarm", "Off", "On", this::setOxgenSensorAlarm);

        return scroller;
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

    public void setRightNavigationLight(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isRightNavigationLightOn == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(0);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isRightNavigationLightOn = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setTailNavigationLight(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isTailNavigationLightOn == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(1);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isTailNavigationLightOn = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setTaxiLight(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isTaxiLightOn == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(2);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isTaxiLightOn = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setNonSmokingSign(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isNonSmokingSignOn == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(4);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isNonSmokingSignOn = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setSeatBeltSign(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isSeatBeltSignOn == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(5);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isSeatBeltSignOn = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setSeatLevel(int level) {
        if (PrimaryFlightDisplay.instance.levelSeat == level && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(6);
        ((TextField)elements[1]).setText(Integer.toString(level));
        PrimaryFlightDisplay.instance.levelSeat = level;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setExhaustGasTemperature(int temperature) {
        if (PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor == temperature && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(8);
        ((TextField)elements[1]).setText(Integer.toString(temperature));
        PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor = temperature;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setAlarmMajorExhaustGasTemperatureSensor(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(9);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setAlarmCriticalExhaustGasTemperatureSensor(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(10);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setFuelFlow(int fuelFlow) {
        if (PrimaryFlightDisplay.instance.fuelFlow == fuelFlow && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(12);
        ((TextField)elements[1]).setText(Integer.toString(fuelFlow));
        PrimaryFlightDisplay.instance.fuelFlow = fuelFlow;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setFuelAmount(double amountOfFuel) {
        if (PrimaryFlightDisplay.instance.amountOfFuel == amountOfFuel && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(13);
        ((TextField)elements[1]).setText(Double.toString(amountOfFuel));
        PrimaryFlightDisplay.instance.amountOfFuel = amountOfFuel;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setAlarmReserveFuelSensor(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isAlarmReserveFuelSensor == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(14);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isAlarmReserveFuelSensor = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setAlarmMajorFuelSensor(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isAlarmMajorFuelSensor == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(15);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isAlarmMajorFuelSensor = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setAlarmCriticalFuelSensor(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isAlarmCriticalFuelSensor == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(16);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isAlarmCriticalFuelSensor = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setIceDetectorProbeBodyActivated(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(18);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setIceDetectorProbeWingActivated(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(19);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setIceDetected(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isIceDetected == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(20);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isIceDetected = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setFireDetectedBody(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isFireDetectedBody == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(22);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isFireDetectedBody = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setFireDetectedWing(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isFireDetectedWing == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(23);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isFireDetectedWing = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    public void setOxgenSensorAlarm(boolean isOn) {
        if (PrimaryFlightDisplay.instance.isOxgenSensorAlarm == isOn && !updates) { return; }
        Node[] elements = t15Builder.getUiElement(24);
        if (isOn) {
            ((RadioButton)elements[1]).setSelected(false);
            ((RadioButton)elements[2]).setSelected(true);
        } else {
            ((RadioButton)elements[1]).setSelected(true);
            ((RadioButton)elements[2]).setSelected(false);
        }
        PrimaryFlightDisplay.instance.isOxgenSensorAlarm = isOn;
        if (!updates) {
            tableView.refresh();
        }
    }

    private void initData() {
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

    private ObservableList getInitialTableData() {
        initData();
        data = FXCollections.observableList(dataList);
        return data;
    }

    public void update() {
        updates = true;

        // camera
        cameraIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isCameraOn));
        setCameraToggleGroup(PrimaryFlightDisplay.instance.isCameraOn);

        // exhaust_gas_temperature_sensor
        setExhaustGasTemperature(PrimaryFlightDisplay.instance.temperatureExhaustGasTemperatureSensor);
        setAlarmMajorExhaustGasTemperatureSensor(PrimaryFlightDisplay.instance.isAlarmMajorExhaustGasTemperatureSensor);
        setAlarmCriticalExhaustGasTemperatureSensor(PrimaryFlightDisplay.instance.isAlarmCriticalExhaustGasTemperatureSensor);

        // fire_detector
        setFireDetectedBody(PrimaryFlightDisplay.instance.isFireDetectedBody);
        setFireDetectedWing(PrimaryFlightDisplay.instance.isFireDetectedWing);

        // fuel_flow_sensor
        setFuelFlow(PrimaryFlightDisplay.instance.fuelFlow);

        // fuel_sensor
        setFuelAmount(PrimaryFlightDisplay.instance.amountOfFuel);
        setAlarmMajorFuelSensor(PrimaryFlightDisplay.instance.isAlarmMajorFuelSensor);
        setAlarmCriticalFuelSensor(PrimaryFlightDisplay.instance.isAlarmCriticalFuelSensor);

        // gps
        gpsIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGPSOn));
        gpsIsConnectedEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isGPSConnected));
        setGpsToggleGroup(PrimaryFlightDisplay.instance.isGPSOn);
        setGpsConnectionToggleGroup(PrimaryFlightDisplay.instance.isGPSConnected);

        // ice_detector_probe
        setIceDetectorProbeBodyActivated(PrimaryFlightDisplay.instance.isIceDetectorProbeBodyActivated);
        setIceDetectorProbeWingActivated(PrimaryFlightDisplay.instance.isIceDetectorProbeWingActivated);
        setIceDetected(PrimaryFlightDisplay.instance.isIceDetected);

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

        // right_navigation_light
        setRightNavigationLight(PrimaryFlightDisplay.instance.isRightNavigationLightOn);

        // seats
        setNonSmokingSign(PrimaryFlightDisplay.instance.isNonSmokingSignOn);
        setSeatBeltSign(PrimaryFlightDisplay.instance.isSeatBeltSignOn);
        setSeatLevel(PrimaryFlightDisplay.instance.levelSeat);

        // tail_navigation_light
        setTailNavigationLight(PrimaryFlightDisplay.instance.isTailNavigationLightOn);

        // taxi_light
        setTaxiLight(PrimaryFlightDisplay.instance.isTaxiLightOn);

        // turbulent_air_flow_sensor
        isTurbulentAirFlowAlarmEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm));
        setTurbulentAirFlowAlarmToggleGroup(PrimaryFlightDisplay.instance.isTurbulentAirFlowAlarm);

        // weather_radar
        weatherRadarIsOnEntry.setValue(Boolean.toString(PrimaryFlightDisplay.instance.isWeatherRadarOn));
        setWeatherRadarToggleGroup(PrimaryFlightDisplay.instance.isWeatherRadarOn);

        updates = false;
        tableView.refresh();
    }
}
