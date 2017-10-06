package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class LiteBrite extends Application {

    int rows = 50;
    int columns = 50;
    private final ColorPicker colorPicker = new ColorPicker();
    private Color color = colorPicker.getValue();
    private GridPane grid;

    public void setColorPickerColor(Color color){
        setColor(color);
        colorPicker.setValue(color);
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public GridPane reset(GridPane grid){
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Pane pane = new Pane();
                pane.setOnMouseReleased(event -> {
                    pane.getChildren().add(Anims.getAtoms(getColor()));
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }
        return grid;
    }

    @Override
    public void start(Stage stage) throws Exception{

        stage.setTitle("Enjoy your game");

        VBox mainBox = new VBox(10);

        HBox buttonBox = new HBox(40);
        buttonBox.setPadding(new Insets(5,0,0,50));
        Button resetButton = new Button("Reset");

        grid = new GridPane();
        grid.getStyleClass().add("game-grid");

        setColorPickerColor(Color.WHITE);
        colorPicker.getStyleClass().add("button");

        colorPicker.setOnAction(event -> {
            setColor(colorPicker.getValue());
        });

        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(10);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(10);
            grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Pane pane = new Pane();
                pane.setOnMouseReleased(event -> {
                    if(pane.getChildren().size()%2 == 1){
                        pane.getChildren().add(Anims.getAtoms(color.BLACK));
                    }
                    else
                        pane.getChildren().add(Anims.getAtoms(getColor()));
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }

        resetButton.setOnAction(event -> {
            setColorPickerColor(getColor());
            grid = reset(grid);
        });

        buttonBox.getChildren().add(colorPicker);
        buttonBox.getChildren().add(resetButton);

        mainBox.getChildren().add(buttonBox);
        mainBox.getChildren().add(grid);

        double width = (columns * 10) + 20;
        double height = (rows * 10) + 70;
        Scene scene = new Scene(mainBox, width, height);
        scene.getStylesheets().add(LiteBrite.class.getResource("resources/game.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static class Anims {

        public static Node getAtoms(Color color) {
            //TODO: Add code to create a colored
            int height =9;
            int width = 9;
            Rectangle rectangle = new Rectangle(0,0, width, height);
            rectangle.setFill(color);
            rectangle.setVisible(true);
            return rectangle;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
