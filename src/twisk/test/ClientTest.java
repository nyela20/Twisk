package twisk.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.ActiviteRestreinte;
import twisk.monde.Etape;
import twisk.monde.Guichet;
import twisk.simulation.Client;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client client;
    Etape luge;
    Etape ski;
    Etape lodge;

    @BeforeEach
    void setUp() {
        client = new Client(1595);
        luge = new Activite("luge");
        ski = new ActiviteRestreinte("ski");
        lodge = new Guichet("lodge",5);
    }

    @Test
    void allerA() {
        client.allerA(luge,0);
        assert(client.getEtapeClient().equals(luge)) : "erreur le client n'est pas à la luge";
        client.allerA(lodge,1);
        assert(client.getEtapeClient().equals(lodge)) : "erreur le client n'est pas au lodge";
        client.allerA(ski,2);
        assert(client.getEtapeClient().equals(ski)) : "erreur le client n'est pas au ski";
    }
}