package SchiffeVersenken;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private SpielFeld sp = new SpielFeld();

    private Button[][] buttons = new Button[sp.getFelder().length][sp.getFelder()[0].length];

    private Pane root = new Pane();
    private GridPane gp = new GridPane();
    private Text text = new Text();

    private Stage endeStage = new Stage();
    private Button endeButton = new Button();
    private Scene endeScene;
    private TextField[] score = new TextField[2];

    @Override
    public void start(Stage primaryStage) throws Exception {
        init();
        root.getChildren().setAll(gp, text);
        Scene scene = new Scene(root, 800, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void init() {
        sp.init();
        gp.getChildren().clear();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {

                buttons[i][j] = new Button();
                buttons[i][j].setId(i + " " + j);
                buttons[i][j].setMinSize(80, 80);
                buttons[i][j].setOnAction(this);
                gp.add(buttons[i][j], i, j);
            }
        }
        text.setX(150);
        text.setY(850);
        text.setStyle("-fx-font-size: 50");
        synchornisieren();
    }

    public void synchornisieren() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (sp.getFelder()[i][j] instanceof SchiffTeil) {
                    SchiffTeil s = (SchiffTeil) sp.getFelder()[i][j];
                    int[] a = sp.getIds();
                    for (int j2 = 0; j2 < a.length; j2++) {
                        if (sp.getFelder()[i][j].isGetroffen()) {

                            if (a[j2] == s.getId()) {
                                buttons[i][j].setStyle("-fx-background-color: red");
                                break;
                            } else {
                                buttons[i][j].setStyle("-fx-background-color: gray");
                            }
                        } else {
                            buttons[i][j].setStyle("-fx-background-color: lightblue");
                        }
                    }
                } else {
                    if (!sp.getFelder()[i][j].isGetroffen()) {
                        buttons[i][j].setStyle("-fx-background-color: lightblue");
                    } else {
                        buttons[i][j].setStyle("-fx-background-color: blue");
                    }
                }
            }
        }
        text.setText("verbleibende Schiffe: " + sp.getAnzahlSchiffe());
    }

    @Override
    public void handle(ActionEvent event) {
        String[] s = ((Button) event.getSource()).getId().split(" ");
        Position p = new Position(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
        if (!(sp.getFelder()[p.getX()][p.getY()].isGetroffen())) {
            sp.schuss(p);
            synchornisieren();
        }
        if (sp.fertig()) {
            ende();
        }
    }

    public void ende() {
        for (int i = 0; i < score.length; i++) {
            score[i] = new TextField();
            score[i].setEditable(false);
            score[i].setMinSize(300, 50);
            score[i].setStyle(
                    "-fx-background-color: lightgray; -fx-text-fill: black; -fx-font-size: 20; -fx-alignment: center");
        }

        score[0].setText("Wasser getroffen: " + sp.getWasserGetroffen());
        score[1].setText("Schiffe getroffen: " + sp.getSchiffeGetroffen());

        endeButton = new Button("Nochmal spielen");
        endeButton.setOnAction(e -> {
            endeStage.close();
            init();
        });
        endeButton.setStyle(
                "-fx-font-size: 20; -fx-background-color: lightgray; -fx-text-fill: black; -fx-border: solid; -fx-border-width: 1; -fx-border-color: black");
        endeButton.setMinSize(300, 50);
        VBox v = new VBox();
        Pane endeRoot = new Pane();
        v.getChildren().addAll(score[0], score[1], endeButton);
        endeRoot.getChildren().addAll(v);
        endeScene = new Scene(endeRoot, 300, 150);
        endeStage.setScene(endeScene);
        endeStage.show();
        synchornisieren();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

