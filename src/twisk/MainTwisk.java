package twisk;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
        root.setBottom(new VueOutils(monde));
        root.setTop(new VueMenu(monde));
        root.setCenter(new VueMondeIG(monde));

        Scene scene = new Scene(root, LargeurPanneauPrincipale, HauteurPanneauPrincipale);
        scene.getStylesheets().addAll("twisk/ressources/css/stylesheet.css",
                "twisk/ressources/css/stylesheetEtape.css","twisk/ressources/css/stylesheetArcs.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
