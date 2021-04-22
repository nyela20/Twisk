package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import twisk.monde.EtapeIG;
import twisk.monde.MondeIG;

public class EcouteurDropped implements EventHandler<DragEvent> {
    private final MondeIG monde;

    /**
     * Ecouteur du dropped de Dnd pour une EtapeIG
     * @param m le monde
     */
    public EcouteurDropped(MondeIG m){
        monde = m;
    }


    @Override
    public void handle(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        EtapeIG etape = null;
        try {
            etape = monde.getEtape(dragboard.getString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert etape != null;
        monde.deplacerUneEtape(etape,event.getSceneX() - (double) etape.getLargeur()/2, event.getSceneY()- (double) etape.getHauteur()/2);
        event.setDropCompleted(true);
        event.consume();
    }
}
