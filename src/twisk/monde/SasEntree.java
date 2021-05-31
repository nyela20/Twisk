package twisk.monde;

import java.util.Iterator;

public class SasEntree extends Activite {

    /**
     * Constructeur des SasEntree
     */
    public SasEntree() {
        super("Sas_Entree", 3, 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String toC() {
        StringBuilder affichage = new StringBuilder();
        if (nbSuccesseurs() == 1) {
            Etape succ = iterator().next();
            affichage.append("entrer(").append(getNom()).append(");\n").append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC());
        }
        if (nbSuccesseurs() > 1) {
            Iterator<Etape> iterator = this.iterator();
            affichage.append("\nnb = (int)((rand()/(float) RAND_MAX) *").append(nbSuccesseurs()).append(");\n");
            affichage.append("switch(nb){\n");
            for (int i = 0; i < nbSuccesseurs(); i++) {
                Etape succ = iterator.next();
                affichage.append("case ").append(i).append(":\n");
                affichage.append("entrer(").append(getNom()).append(");\n").append("delai(").append(getTemps()).append(",").append(getEcartTemps()).append(");\n").append("transfert(").append(getNom()).append(",").append(succ.getNom()).append(");").append(succ.toC()).append("break;\n");
            }
            affichage.append("}\n");
        }
        return affichage.toString();
    }

    @Override
    public String getNom(){
        return super.getNom();
    }


}
