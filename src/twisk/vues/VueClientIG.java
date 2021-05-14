package twisk.vues;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import twisk.ecouteurs.EcouteurPointDeControle;
import twisk.mondeIG.MondeIG;
import twisk.simulation.Client;

import java.awt.*;
import java.util.Random;

public class VueClientIG extends Circle implements Observateur{


    public VueClientIG (){
        double taille = 5;
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color couleur = Color.rgb(r,g,b);
        this.setRadius(taille);
        this.setFill(couleur);
    }

    @Override
    public void reagir() { }
}
