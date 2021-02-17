package base;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public class GridPaneBuilder {
    protected final int columns = 2;
    protected final GridPane pane;
    protected int row;
    protected int col;

    public GridPaneBuilder() {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.BASELINE_LEFT);
        pane = gridPane;
    }

    public GridPane gridPane() { return pane; }

    public void addToggle(String label, String off, String on, Consumer<Boolean> onChange) {
        addToggle(pane, row, col, label, off, on, onChange);
        moveNext();
    }

    public void addInteger(String label, int min, int max, Consumer<Integer> onChange) {
        addInteger(pane, row, col, label, min, max, onChange);
        moveNext();
    }

    public void addFloat(String label, double min, double max, Consumer<Double> onChange) {
        addFloat(pane, row, col, label, min, max, onChange);
        moveNext();
    }

    public void addTitle(String title) {
        requireClear();
        addTitle(pane, row, columns<<1, title);
        moveNext(true);
    }

    private void moveNext() { moveNext(false); }

    private void moveNext(boolean fullRow) {
        if (fullRow || col + 3 == columns * 3) {
            col = 0;
            row++;
        } else {
            col += 3;
        }
    }

    private void requireClear() {
        if (col != 0) {
            col = 0;
            row++;
        }
    }

    private static void addToggle(GridPane pane, int row, int col, String labelText, String offText, String onText, Consumer<Boolean> onChange) {
        Label label = new Label(labelText + " : ");
        ToggleGroup group = new ToggleGroup();
        RadioButton off = new RadioButton(offText);
        off.setToggleGroup(group);
        RadioButton on = new RadioButton(onText);
        on.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == oldValue){
                return;
            }
            onChange.accept(group.getSelectedToggle() == on);
        });

        pane.add(label, col, row);
        pane.add(off, col + 1, row);
        pane.add(on, col + 2, row);
    }

    private static void addInteger(GridPane pane, int row, int col, String labelText, int min, int max, Consumer<Integer> onChange) {
        Label label = new Label(labelText + " : ");
        TextField num = new TextField(Integer.toString(min));

        num.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == newValue || newValue) {
                return;
            }
            String text = num.getText();
            if (!Pattern.matches("^-?\\d*$", text)) {
                num.setText(Integer.toString(min));
                onChange.accept(min);
                return;
            }
            int value = Integer.parseInt(text);
            if (value < min) {
                num.setText(Integer.toString(min));
                onChange.accept(min);
                return;
            }
            if (value > max) {
                num.setText(Integer.toString(max));
                onChange.accept(max);
                return;
            }
            onChange.accept(value);
        });

        pane.add(label, col, row);
        pane.add(num, col + 1, row, 2, 1);
    }

    private static void addFloat(GridPane pane, int row, int col, String labelText, double min, double max, Consumer<Double> onChange) {
        Label label = new Label(labelText + " : ");
        TextField num = new TextField(Double.toString(min));

        num.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == newValue || newValue) {
                return;
            }
            String text = num.getText();
            if (!Pattern.matches("^-?[0-9]\\d*(\\.\\d+)?$", text)) {
                num.setText(Double.toString(min));
                onChange.accept(min);
                return;
            }
            double value = Double.parseDouble(text);
            if (value < min) {
                num.setText(Double.toString(min));
                onChange.accept(min);
                return;
            }
            if (value > max) {
                num.setText(Double.toString(max));
                onChange.accept(max);
                return;
            }
            onChange.accept(value);
        });

        pane.add(label, col, row);
        pane.add(num, col + 1, row, 2, 1);
    }

    private static void addTitle(GridPane pane, int row, int columns, String title) {
        Font f = Font.font(Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize());
        Label label = new Label(title);
        label.setFont(f);
        Separator after = new Separator(Orientation.HORIZONTAL);
        pane.add(label, 0, row);
        pane.add(after, 1, row, columns-1, 1);
    }

}
