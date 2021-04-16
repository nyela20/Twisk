package twisk;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk.ecouteurs.EcouteurMenuRenommer;
import twisk.keys.KeyEvents;
import twisk.mondeIG.MondeIG;
import twisk.vues.VueMenu;
import twisk.vues.VueMondeIG;
import twisk.vues.VueOutils;

/**/

public class MainTwisk extends Application {
    int LargeurPanneauPrincipale = 750;
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

        scene.setOnKeyPressed(new KeyEvents(monde));

        scene.getStylesheets().addAll("twisk/ressources/css/stylesheet.css",
                "twisk/ressources/css/stylesheetEtape.css","twisk/ressources/css/stylesheetArcs.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}