package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import twisk.vues.VueEtapeIG;

public class EcouterStartDragged implements EventHandler<MouseEvent> {
    private final VueEtapeIG vue;
    private final String identifiant;


    /**
     * Constructeur du "drag" du DragAndDrop sur une étape
     * @param vueetp le node à "drag"
     * @param id identifiant unique de EtapeIG
     */
    public EcouterStartDragged(VueEtapeIG vueetp, String id){
        vue = vueetp;
        identifiant = id;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Dragboard dragboard = vue.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        content.putString(identifiant);
        final WritableImage capture = vue.snapshot(null, null);
        content.putImage(capture);
        dragboard.setContent(content);
        mouseEvent.consume();
    }
}
