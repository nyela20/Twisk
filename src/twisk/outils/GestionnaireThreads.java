package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class GestionnaireThreads {
    private static final GestionnaireThreads instance = new GestionnaireThreads();
    private final ArrayList<Thread> LesThreads = new ArrayList<>();

    private GestionnaireThreads(){ }

    public static GestionnaireThreads getInstance(){
        return instance;
    }

    /**
     * destruction des threads
     */
    public void detruireTout(){
        for(Thread thread: LesThreads){
            thread.interrupt();
        }
    }

    /**
     * execution des threads
     * @param task la tâche du thread
     */
    public void lancer(Task task){
        Thread thread = new Thread(task);
        LesThreads.add(thread);
        thread.start();
    }
}
