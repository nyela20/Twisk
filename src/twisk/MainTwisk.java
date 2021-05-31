package twisk;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.keys.KeyEvents;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class MainTwisk extends Application  {
    int LargeurPanneauPrincipale = 850;
    int HauteurPanneauPrincipale = 750;

    @Override
    public void start(Stage primaryStage){
        MondeIG monde = new MondeIG();

        primaryStage.setTitle("twiskIG");

        BorderPane root = new BorderPane();
        VueOutils vueOutils = new VueOutils(monde);
        root.setBottom(vueOutils);

        VueMenu vueMenu = new VueMenu(monde);
        root.setTop(vueMenu);

        VueMondeIG vueMondeIG = new VueMondeIG(monde);
        root.setCenter(vueMondeIG);

        Scene scene = new Scene(root, LargeurPanneauPrincipale, HauteurPanneauPrincipale);

        //évenements
        scene.setOnKeyPressed(new KeyEvents(monde));
        primaryStage.setOnCloseRequest(windowEvent -> {
            try {
                monde.stopSimulation();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        scene.getStylesheets().addAll("twisk/ressources/css/stylesheet.css",
                "twisk/ressources/css/stylesheetEtape.css","twisk/ressources/css/stylesheetArcs.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
