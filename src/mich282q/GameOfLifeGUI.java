package mich282q;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.reactfx.util.FxTimer;
import org.reactfx.util.Timer;

public class GameOfLifeGUI extends Application {

    // Laver et game objekt ved hjælp af Game.createGame metoden
    private Game game = Game.createGame();

    private static Pane pane = new Pane();

    private static Button button = new Button("Start");

    private static Label label = new Label("0");

    @Override
    public void start(Stage primaryStage) {
        game.drawCells();

        button.setLayoutX(25);
        button.setLayoutY(903);
        button.setPrefWidth(100);
        button.setPrefHeight(40);
        button.setOnAction(new EventHandler<ActionEvent>() {

            Timer timer;

            @Override
            public void handle(ActionEvent event) {
                if (button.getText().equals("Stop")) {
                    timer.stop();
                    button.setText("Start");
                } else {
                    timer = FxTimer.runPeriodically(
                            java.time.Duration.ofMillis(300),
                            () -> game.buttonAction());
                    button.setText("Stop");
                }

            }
        });
        pane.getChildren().add(button);

        label.setLayoutX(850);
        label.setLayoutY(895);
        Font font = new Font(30);
        label.setFont(font);
        pane.getChildren().add(label);

        Scene scene = new Scene(pane, 900, 950);
        primaryStage.setTitle("Game Of Life");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    // Gætter til pane
    public static Pane getPane() {
        return pane;
    }

    // Gætter til button
    public static Button getButton() {
        return button;
    }

    // Gætter til label
    public static Label getLabel() {
        return label;
    }
}