package twisk.tests;

import org.junit.jupiter.api.Test;
import twisk.monde.Etape;
import twisk.monde.Monde;
import twisk.monde.SasEntree;



class SasEntreeTest extends EtapeTest {
    @Test
    void toC(){
        Monde monde = new Monde();
        Etape sasentree = new SasEntree(monde);
        sasentree.ajouterSuccesseur(vipLodge);
        String res = "entrer("+ sasentree.getNom() +");\n" +
                "delai(3,1);\n" +
                "transfert("+sasentree.getNom()+","+sasentree.iterator().next().getNom()+");";
    }
}