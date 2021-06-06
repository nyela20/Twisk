package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class EcouteurSauvegarderMonde implements EventHandler<ActionEvent> {
    private final MondeIG mondeIG;

    public EcouteurSauvegarderMonde(MondeIG mondeIG) {
        this.mondeIG = mondeIG;

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            JFileChooser jFileChooser = new JFileChooser("src/twisk/ressources/savemonde");
            jFileChooser.setMultiSelectionEnabled(false);
            int res = jFileChooser.showSaveDialog(jFileChooser.getParent());
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream objos = new ObjectOutputStream(fos);
                objos.writeObject(mondeIG);
                objos.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
