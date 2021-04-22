package twisk.tests;

import org.junit.jupiter.api.Test;
import twisk.monde.Etape;
import twisk.monde.SasEntree;
import static org.junit.jupiter.api.Assertions.*;



class SasEntreeTest extends EtapeTest {
    @Test
    void toC(){
        Etape sasentree = new SasEntree();
        sasentree.ajouterSuccesseur(vipLodge);
        String res = "entrer("+ sasentree.getNom() +");\n" +
                "delai(3,1);\n" +
                "transfert("+sasentree.getNom()+","+sasentree.iterator().next().getNom()+");";
        assertEquals(sasentree.toC(),res,"La fonction ne retourne pas la syntaxe C attendue");
    }
}