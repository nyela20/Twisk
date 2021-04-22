package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.monde.MondeIG;
import twisk.monde.PointDeControleIG;

public class EcouteurPointDeControle implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final PointDeControleIG pointDeControleIG;

    /**
     * Ecouteur sur un PointDeControleIG dans le monde
     * @param mde le monde
     * @param pdc le PointDeControleIG
     */
    public EcouteurPointDeControle(MondeIG mde, PointDeControleIG pdc){
        monde = mde;
        pointDeControleIG = pdc;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        pointDeControleIG.setEstSelectionne(true);
        monde.pointDeControleSelectionne(pointDeControleIG);
    }
}
