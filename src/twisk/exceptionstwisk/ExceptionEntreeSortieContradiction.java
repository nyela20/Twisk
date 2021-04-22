package twisk.exceptionstwisk;

public class ExceptionEntreeSortieContradiction extends TwiskException{

    /**
     * Constructeur ExceptionEntreeSortieContradiction qui est appellé pour toutes les
     * exceptions qui peuvent être lévés, concernent l'assignement d'une Etape à une Entree/Sortie
     * @param message le message de l'exception
     */
    public ExceptionEntreeSortieContradiction(String message){
        super(message);
    }
}
