package twisk.ecouteurs;

import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

public class EcouteurSetDragOver implements EventHandler<DragEvent> {

    /**
     * Ecouteur qui sert uniquement à autoriser le DragAndDrop
     */
    public EcouteurSetDragOver(){ }

    @Override
    public void handle(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }
}
