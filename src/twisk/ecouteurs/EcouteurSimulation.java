package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.exceptionstwiskIG.ExceptionMondeIG;
import twisk.exceptionstwiskIG.ExceptionObjetNonTrouve;
import twisk.mondeIG.MondeIG;

import java.lang.reflect.InvocationTargetException;

public class EcouteurSimulation implements EventHandler<ActionEvent> {
    private final MondeIG mondeIG;

    public EcouteurSimulation(MondeIG mdeIG){ mondeIG = mdeIG; }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            mondeIG.simuler();
        } catch (Exception exceptionMondeIG) {
            exceptionMondeIG.printStackTrace();
        }
    }
}
