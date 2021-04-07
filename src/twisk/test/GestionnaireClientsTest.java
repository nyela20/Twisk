package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    GestionnaireClients gestionnaireClients;
    Etape etape;

    @BeforeEach
    void setUp() {
        gestionnaireClients = new GestionnaireClients();
        assertEquals(gestionnaireClients.getNombreDeClients(), 0, "Constructeur vide assigne 0 clients");
        gestionnaireClients = new GestionnaireClients(5);
        assertEquals(gestionnaireClients.getNombreDeClients(), 0, "Le Construteur n'assigne pas  les clients");
        etape = new Activite("jeu",5,1);
    }

    @Test
    void setClients() {
        gestionnaireClients.setClients(11111);
        assertEquals(gestionnaireClients.getNombreDeClients(), 1, "La fonction n'instancie pas correctement les clients");
        assertEquals(gestionnaireClients.iterator().next().getNumeroClient(), 11111, "Le numéro donnée en paramètre n'a pas été attribué à la nouvelle instance1 de clients");
        gestionnaireClients.setClients(11111,45686,245687,65486,542);
        assertEquals(gestionnaireClients.getNombreDeClients(), 6, "La fonction n'instancie pas correctement les clients");
    }

    @Test
    void allerA(){
        gestionnaireClients.setClients(11111);
        gestionnaireClients.allerA(11111,etape,0);
        assertEquals(gestionnaireClients.iterator().next().getNumeroClient(),11111,"Erreur la fonction ne manipule pas le processus donnée en paramètre.");
        assertEquals(gestionnaireClients.iterator().next().getNumeroClient(),11111,"Erreur la fonction ne manipule pas le processus donnée en paramètre.");
        assertEquals(gestionnaireClients.iterator().next().getEtapeClient(),etape,"erreur l'étape donnée en paramètre n'a pas été assigné au client");
        assertEquals(gestionnaireClients.iterator().next().getRangClient(),0,"erreur le rang donéne en paramètre n'apas été assigné au client");
    }


}