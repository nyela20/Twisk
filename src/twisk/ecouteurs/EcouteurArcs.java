package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.monde.ArcIG;
import twisk.monde.MondeIG;

public class EcouteurArcs implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final ArcIG arc;


    /**
     * Constructeur d'un ecouteur pour les ArcsIGs
     * @param mondeIG le monde
     * @param a l'arc
     */
    public EcouteurArcs(MondeIG mondeIG, ArcIG a){
        monde = mondeIG;
        arc = a;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        monde.setEstSelectionne(arc, !arc.estSelectionne());
    }
}
