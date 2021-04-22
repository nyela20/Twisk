package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import twisk.monde.EtapeIG;
import twisk.monde.MondeIG;

public class EcouteurEtapes implements EventHandler<MouseEvent> {
    private final MondeIG monde;
    private final EtapeIG etape;

    /**
     * Ecouteur de EtapeIG
     * @param mde le monde
     * @param etp l'Etape
     */
    public EcouteurEtapes(MondeIG mde, EtapeIG etp){
        monde = mde;
        etape = etp;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        monde.setEstSelectionne(etape, !etape.estSelectionne());
    }
}
