package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

public class EcouteurArcs implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final ArcIG arc;


    /**
     * Constructeur d'un ecouteur pour les ArcsIGs
     * @param mde le monde
     * @param a l'arc
     */
    public EcouteurArcs(MondeIG mde, ArcIG a){
        monde = mde;
        arc = a;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        monde.setEstSelectionne(arc, !arc.estSelectionne());
    }
}
