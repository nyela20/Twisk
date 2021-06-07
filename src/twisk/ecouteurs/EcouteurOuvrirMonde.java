package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.mondeIG.MondeIG;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class EcouteurOuvrirMonde implements EventHandler<ActionEvent> {
    private final MondeIG mondeIG;

    public EcouteurOuvrirMonde(MondeIG mondeIG){
        this.mondeIG = mondeIG;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try{
            JFileChooser jFileChooser = new JFileChooser("src/twisk/ressources/savemonde");
            jFileChooser.setMultiSelectionEnabled(false);
            int res = jFileChooser.showOpenDialog(jFileChooser.getParent());
            if(res == JFileChooser.APPROVE_OPTION){
                File file = jFileChooser.getSelectedFile();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream objis = new ObjectInputStream(fis);
                MondeIG mn = ((MondeIG) objis.readObject());
                mondeIG.chargerMonde(mn);
                objis.close();
            }
        }catch (Exception e) {
           e.printStackTrace();
        }
    }
}
