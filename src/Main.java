import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MathDoku");

        // Creates the action bar at the top.
        HBox optionsHBox = new HBox(5);
        optionsHBox.setPadding(new Insets(5, 5, 5, 5));
        optionsHBox.setAlignment(Pos.CENTER);

        // Creates functionality elements to the action bar.
        Button undoButton = new Button("Undo");
        Button redoButton = new Button("Redo");
        Button loadFileButton = new Button("Load from File");
        Button loadTextButton = new Button("Load from Text");
        Label mistakesLabel = new Label("Click to show mistakes:");
        CheckBox mistakesCheck = new CheckBox();
        optionsHBox.getChildren().addAll(undoButton, redoButton, loadFileButton, loadTextButton, mistakesLabel, mistakesCheck);

        // Sets size parameters.
        int gridSize = 6;
        int tileSize = 100;

        // Creates the main pane.
        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setHgap(1);
        mainPane.setVgap(1);
        mainPane.setAlignment(Pos.CENTER);

        // Creates the grid.
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addEventHandler(KeyEvent.KEY_PRESSED, new KeyPressedHandler(gridSize));

        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                Tile tile = new Tile(x, y);
                tile.setPrefSize(tileSize, tileSize);
                gridPane.add(tile, x, y);
            }
        }

        // Finishes setting up the GUI.
        mainPane.add(optionsHBox, 0, 0);
        mainPane.add(gridPane, 0, 1);
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    class Tile extends Pane {
        private int x;
        private int y;

        public Tile(int x, int y) {
            this.x = x;
            this.y= y;

            this.setStyle("-fx-border-color: black;");
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, new BoxClickHandler(x, y));
        }
    }

    // Event handler code for clicking a grid box.
    class BoxClickHandler implements EventHandler<MouseEvent> {
        private int x;
        private int y;

        // Event handler constructor.
        public BoxClickHandler(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void handle(MouseEvent event) {
            System.out.println("Clicked at: " + x + "," + y);
        }
    }

    // Event handler code for pressing a key.
    class KeyPressedHandler implements EventHandler<KeyEvent> {
        private int gridSize;

        public KeyPressedHandler(int gridSize) {
            this.gridSize = gridSize;
        }

        @Override
        public void handle(KeyEvent event) {
            // Returns the number pressed.
            KeyCode key = event.getCode();
            KeyCode[] codes = {KeyCode.DIGIT2, KeyCode.DIGIT3, KeyCode.DIGIT4, KeyCode.DIGIT5,
                                KeyCode.DIGIT6, KeyCode.DIGIT7, KeyCode.DIGIT8};

            for (int i = 0; i <= gridSize - 2; i++) {
                if (key == codes[i]) {
                    System.out.println("A valid number key was pressed.");
                }
            }
        }
    }

    public static void main(String[] args) {
        // Launches the game.
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
