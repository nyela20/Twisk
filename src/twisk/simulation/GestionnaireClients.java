package twisk.simulation;

import twisk.exceptionstwiskIG.ExceptionMondeIG;
import twisk.monde.Etape;
import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client> {

    private final ArrayList<Client> gestionnaireClients;
    private int nombreDeClients;

    public GestionnaireClients(){
        gestionnaireClients = new ArrayList<>();
    }

    public GestionnaireClients(int nbClients){
        gestionnaireClients = new ArrayList<>(nbClients);
    }

    public void setClients(int ... tabClients){
        for(int pid : tabClients){
            Client client = new Client(pid);
            gestionnaireClients.add(client);
        }
    }

    public void setNbClients(int nbClients){
        nombreDeClients = nbClients;
    }

    public void allerA(int numeroClient,Etape etape,int rang){
        for(Client client : gestionnaireClients){
            if(client.getNumeroClient() == numeroClient){
                client.allerA(etape,rang);
            }
        }
    }

    /**
     * retourne un client ayant le mếmé numéro
     * que celui donné en paramètre
     * @param num numéro du client
     * @return le client
     */
    public Client getClient(int num){
        for(Client client : gestionnaireClients){
            if(client.getNumeroClient() == num){
                return client;
            }
        }
        return null;
      //  throw new ExceptionObjetNonTrouve("Aucun clients trouvé portant ce numéro");
    }

    public void reset(){
        gestionnaireClients.clear();
    }

    public int getNombreDeClients(){
        return gestionnaireClients.size();
    }

    @Override
    public Iterator<Client> iterator() {
        return gestionnaireClients.iterator();
    }
}
