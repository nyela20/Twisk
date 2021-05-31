package twisk.vues;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import twisk.simulation.Client;


public class VueClientIG extends StackPane implements Observateur{
    private final int rang;


    public VueClientIG (Client client){
        Text text = new Text(String.valueOf(client.getRang()));
        text.setFont(Font.font(4));
        if(client.getRang() < 9) {
            rang = client.getRang();
        }else{
            rang = 1;
        }
        Circle cercle = new Circle();
        cercle.setRadius(6);
        cercle.setFill(Color.LIME);
        this.getChildren().addAll(cercle, text);
    }


    public int getRang() {
        return rang;
    }

    @Override
    public void reagir(){

    }
}
