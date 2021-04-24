package twisk.ecouteurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import twisk.exceptionstwisk.ExceptionMondeIG;
import twisk.exceptionstwisk.ExceptionObjetNonTrouve;
import twisk.monde.MondeIG;

import java.lang.reflect.InvocationTargetException;

public class EcouteurSimulation implements EventHandler<ActionEvent> {
    private final MondeIG mondeIG;

    public EcouteurSimulation(MondeIG mdeIG){
        mondeIG = mdeIG;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            mondeIG.simuler();
        } catch (ExceptionMondeIG | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException exceptionMondeIG) {
            exceptionMondeIG.printStackTrace();
        } catch (ExceptionObjetNonTrouve exceptionObjetNonTrouve) {
            exceptionObjetNonTrouve.printStackTrace();
        }
    }
}
