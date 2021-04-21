package twisk.mondeIG;



import twisk.exceptionstwiskig.ExceptionClasseMondeIG;
import twisk.exceptionstwiskig.ExceptionEntreeSortieContradiction;
import twisk.exceptionstwiskig.ExceptionVueMenu;
import twisk.exceptionstwiskig.ExceptionsInvaliditeSurLesArcs;
import twisk.outils.FabriqueIdentifiant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MondeIG extends SujetObserve implements Iterable<EtapeIG> {
    private final HashMap<String,EtapeIG> TableauEtapesIG = new HashMap<>();
    private final ArrayList<ArcIG> TableauArcsIG = new ArrayList<>();
    private final ArrayList<PointDeControleIG> TableauPointsDeControle = new ArrayList<>();
    private int identifiantStyle;

    /**
     * Constructeur d'un MondeIG
     */
    public MondeIG(){
        ajouter("Activite");
        identifiantStyle = 0;
    }
    
    

    /**
     * la fonction ajoute une Activite dans le monde
     * @param type le type de l'Etape
     */
    public void ajouter(String type){
        assert(type.equals("Activite") || type.equals("Guichet")) : "Erreur type inconnu.";
            if (type.equals("Activite")) {
                String identifiant = FabriqueIdentifiant.getInstance().getIdentifiant();
                ActiviteIG activite = new ActiviteIG("Activite", identifiant, 175, 75);
                TableauEtapesIG.put(activite.getIdentifiant(), activite);
                notifierObservateur();
            }
            if (type.equals("Guichet")) {
                String identifiant = FabriqueIdentifiant.getInstance().getIdentifiant();
                GuichetIG guichet = new GuichetIG("Guichet", identifiant, 200, 60);
                TableauEtapesIG.put(guichet.getIdentifiant(), guichet);
                notifierObservateur();
            }
    }

    /**
     * la fonction déplace une EtapeIG d'un point à un autre
     * @param etapeIG l'EtapIG à déplacé
     * @param x l'abscisse d'arrivé
     * @param y l'ordonnée d'arrivé
     */
    public void deplacerUneEtape(EtapeIG etapeIG, double x, double y){
        etapeIG.deplacerEtape(x,y);
        notifierObservateur();
    }

    /**
     * retourne une EtapeIG dans le monde qui correspond  l'identifiant donnée
     * en paramètre.
     * @param idf l'identifiant de l'EtapeIG que l'on cherche
     * @return cet identifiant si trouvé
     * @throws ExceptionClasseMondeIG sinon si non trouvé throw une Exception
     */
    public EtapeIG getEtape(String idf) throws ExceptionClasseMondeIG {
        for(EtapeIG etapeIG: TableauEtapesIG.values()){
            if(etapeIG.getIdentifiant().equals(idf)){
                return etapeIG;
            }
        }
        throw new ExceptionClasseMondeIG("Aucune étape portant cet idenfiant n'est trouvé");
    }

    /**
     * modifie l'etat d'un Arc, si il est séléctionné ou non
     * @param arc l'ArcIG à modifier
     * @param setTo true si on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(ArcIG arc,boolean setTo){
        arc.setEstSelectionne(setTo);
        notifierObservateur();
    }

    /**
     * modifie l'etat d'une EtapeIG, si il est séléctionné ou non
     * @param etp l'EtapeIG à modifier
     * @param setTo true sui on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(EtapeIG etp,boolean setTo){
        etp.setEstSelectionne(setTo);
        notifierObservateur();
    }

    /**
     * Annule la séléction des élements séléctionnées
     */
    public void effacerLaSelection(){
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                etape.setEstSelectionne(false);
            }
        }

        for (ArcIG arc : TableauArcsIG) {
            if (arc.estSelectionne()) {
                arc.setEstSelectionne(false);
            }
        }
        for (PointDeControleIG pdc : TableauPointsDeControle) {
            pdc.setEstSelectionne(false);
        }
        TableauPointsDeControle.clear();
        notifierObservateur();
    }

    /**
     * assigne les Activités sélectionnées en Entrée
     */
    public void assignerEntree(){
        for(EtapeIG etape : this){
            if(etape.estSelectionne()){
                if(etape.estUneEntree()){
                    etape.setEstUneEntree(false);
                }else{
                    try {
                        if(etape.estUneSortie()) {
                            throw new ExceptionEntreeSortieContradiction("Action impossible, cet étape est déjà assigné à être une Sortie.");
                        }else{
                            etape.setEstUneEntree(true);
                        }
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                etape.setEstSelectionne(false);
            }
        }
        notifierObservateur();
    }

    public void renommerEtape(String nom){
        for(EtapeIG e : this){
            if(e.estSelectionne()){
                e.renommer(nom);
                e.setEstSelectionne(false);
            }
        }
        notifierObservateur();
    }


    /**
     * Supprime les EtapeIG et les ArcIG qui sont séléctionnées
     */
    public void supprimerEtapesEtArcsSelectionnes() {
        // suppression des Etapes
        Iterator<EtapeIG> it = iterator();
        while (it.hasNext()) {
            EtapeIG etape = it.next();
            if (etape.estSelectionne()) {
                for (Iterator<ArcIG> iter = iteratorArcIG(); iter.hasNext(); ) {
                    ArcIG arc = iter.next();
                    if (arc.estRelie(etape)) {
                        iter.remove();
                    }
                }
                it.remove();
                viderTableauPointsDeControle();
            }
        }
        // suppression des Arcs
        Iterator<ArcIG> iterarc = iteratorArcIG();
        while (iterarc.hasNext()) {
            ArcIG arc = iterarc.next();
            if (arc.estSelectionne()) {
                iterarc.remove();
            }
        }
        notifierObservateur();
    }


    /**
     * assigne les Activités sélectionnées en Sortie
     */
    public void assignerSortie(){
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                if (etape.estUneSortie()) {
                    etape.setEstUneSortie(false);
                } else {
                    try {
                        if (etape.estUneEntree()) {
                            throw new ExceptionEntreeSortieContradiction("Action impossible, cet étape est déjà assigné à être une Entree.");
                        } else {
                            etape.setEstUneSortie(true);
                        }
                    } catch (ExceptionEntreeSortieContradiction e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            etape.setEstSelectionne(false);
        }
        notifierObservateur();
    }

    /**
     * Previens le monde qu'un PointDeControle à été seléctionné
     * crée un ArcIG lorsque deux points de contrôle sont séléctionnés
     * @param pt1 le pointDeCOntrole qui a été séléctionné
     */
    public void pointDeControleSelectionne(PointDeControleIG pt1){
        TableauPointsDeControle.add(pt1);
        //si deux pointDeControle sont séléctionnés
        if ( TableauPointsDeControle.size() == 2 ){
            //
            PointDeControleIG p = TableauPointsDeControle.get(0);
            PointDeControleIG p2 = TableauPointsDeControle.get(1);
            viderTableauPointsDeControle();
            try {
                ajouter(p,p2);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        notifierObservateur();
    }

    /**
     * retourne le nombre d'activité séléctionnés
     * @return le nombre d'activite sélétionnés
     */
    public int nombreActiviteSelectionne(){
        int res = 0;
        for(EtapeIG etape: this){
            if(etape.estSelectionne()){
                res++;
            }
        }
        return res;
    }

    /**
     * La fonction rajoute un ArcIG dans le nom
     * @param pt1 le point de départ de l'ArcIG
     * @param pt2 le point d'aarivé de l'ArcIG
     * @throws ExceptionsInvaliditeSurLesArcs lève des exceptions si les arcs si suivent
     * pas les règles.
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws ExceptionsInvaliditeSurLesArcs {
        try {
            ArcIG arc = new ArcIG(pt1, pt2);
            if (pt1.estSurLaMemeEtapeQue(pt2)) {
                throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer cet arc, les deux points sont sur la même étape");
            }
            if (arc.getEtapeDebut().estUneSortie() && arc.getEtapeArrive().estUneEntree()) {
                throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer et arc, une Entrée ne peut pas être successeur d'une Sortie");
            }
            if (arc.getEtapeDebut().estUnGuichet() && arc.getEtapeArrive().estUnGuichet()){
                throw new ExceptionsInvaliditeSurLesArcs("Impossible de relier un Guichet à un autre Guichet.");
            }
            for (ArcIG arcIG : TableauArcsIG) {
                if (arcIG.aCommeDebut(arc.getEtapeDebut()) && arcIG.aCommeArrive(arc.getEtapeArrive())) {
                    throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer cet arc, il existe déjà un arc qui relie ces deux étapes");
                } else {
                    if (arcIG.aCommeDebut(arc.getEtapeArrive()) && arcIG.aCommeArrive(arc.getEtapeDebut())) {
                        throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer cet arc, deux arcs sont symétriques");
                    }
                }
            }
            TableauArcsIG.add(arc);
            initDFS();
            if (DFS(this.iterator().next())){
                TableauArcsIG.remove(arc);
                throw new ExceptionsInvaliditeSurLesArcs("Circuit détécté, les circuits ne sont pas gérés par Twisk.");
            }
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        pt1.setEstSelectionne(false);
        pt2.setEstSelectionne(false);
    }



    /**
     * assigne un délai à une EtapeIG séléctionnée
     * @param delai le nouveau délai
     */
    public void assignerDelaiAEtape(int delai) throws ExceptionVueMenu {
            for (EtapeIG etapeIG : this) {
                if (etapeIG.estSelectionne()) {
                    etapeIG.setEstSelectionne(false);
                    if (etapeIG.estUneActivite()) {
                        ActiviteIG activiteIG = (ActiviteIG) etapeIG;
                        activiteIG.setDelai(delai);
                    }
                    else{
                        if(etapeIG.estUnGuichet()) {
                            notifierObservateur();
                            throw new ExceptionVueMenu("vous essayez d'assigner un Délai à un Guichet...c'est impossible");
                        }
                    }
                }
            }
            notifierObservateur();
    }

    /**
     * assigne un Ecart Temps à une EtapeIG séléctionnée
     * @param ecarttemps le nouveau Ecart-temps
     */
    public void assignerEcartTempsAEtape(int ecarttemps) throws ExceptionVueMenu {
            for(EtapeIG etapeIG : this){
                if(etapeIG.estSelectionne()) {
                    etapeIG.setEstSelectionne(false);
                    if (etapeIG.estUneActivite()) {
                        ActiviteIG activiteIG = (ActiviteIG) etapeIG;
                        activiteIG.setEcarttemps(ecarttemps);
                    } else{
                        if(etapeIG.estUnGuichet()) {
                            notifierObservateur();
                            throw new ExceptionVueMenu("vous essayez d'assigner un Ecart-Temps à un Guichet...c'est impossible");
                        }
                    }
                }
            }
            notifierObservateur();
    }

    /**
     * assigne un nombre de jeton(s) à un Guichet
     * @param nombredeJetons le nombre de jeton(s)
     */
    public void assignerNombreDeJetonsAEtape(int nombredeJetons) throws ExceptionVueMenu {
        for(EtapeIG etapeIG : this){
            if(etapeIG.estSelectionne()) {
                etapeIG.setEstSelectionne(false);
                if (etapeIG.estUnGuichet()) {
                    GuichetIG guichetIG = (GuichetIG) etapeIG;
                    guichetIG.setNombreDeJetons(nombredeJetons);
                }else{
                    if(etapeIG.estUneActivite()) {
                        notifierObservateur();
                        throw new ExceptionVueMenu("vous essayez d'assigner un nombre de jetons à une Activité... c'est impossible");
                    }
                }
            }
        }
        notifierObservateur();
    }



    /**
     * retourne un Tableau d'EtapeIG qui sont les successeurs
     * de l'Etape donnée en paramètre
     * @param etapeIG l'Etape
     * @return les successeurs de l'Etape
     */
    private ArrayList<EtapeIG> getSuccesseur(EtapeIG etapeIG){
        ArrayList<EtapeIG> etapeIGArrayList = new ArrayList<>();
        for(ArcIG arc : TableauArcsIG){
            if(arc.aCommeDebut(etapeIG)){
                etapeIGArrayList.add(arc.getEtapeArrive());
            }
        }
        return etapeIGArrayList;
    }

    /**
     * initialise le parcours en DFS
     */
    private void initDFS(){
        for(EtapeIG etapeIG : this){
            etapeIG.setCouleur("blanc");
        }
    }

    /**
     * PARCOURS EN PROFONDEUR POUR DETECTÉ LES CIRCUITS
     * DFS récursif
     * @param sommet sommet de départ
     * @return vrai si il existe un circuit, sinon faux
     */
    private boolean DFS(EtapeIG sommet){
        sommet.setCouleur("gris");
        for(EtapeIG succ : getSuccesseur(sommet)){
            if(succ.getCouleur() == "blanc"){
                DFS(succ);
            }
            if(succ.getCouleur() == "gris"){
                    return true;
            }
        }
        sommet.setCouleur("noir");
        return false;
    }


    /**
     * la fonction vide le tableau de PointDeControle
     */
    public void viderTableauPointsDeControle(){
        TableauPointsDeControle.clear();
    }

    /**
     * retourne un itérateur sur les Etapes du Monde
     * @return un itérateur EtapeIG
     */
    @Override
    public Iterator<EtapeIG> iterator() {
        return TableauEtapesIG.values().iterator();
    }

    /**
     * retourne un itérateur du les ArcIG du monde
     * @return un itérateur ArcIG
     */
    public Iterator<ArcIG> iteratorArcIG(){
        return TableauArcsIG.iterator();
    }

    /**
     * retourne le nombre d'Etape dans le monde
     * @return la taille d'un tableau d'Etape
     */
    public int sizeTableauEtapeIG(){
        return TableauEtapesIG.size();
    }

    /**
     * retourne le nombre de PoinDeControle séléctionnée dans le monde
     * @return la taille du tableau de PointDeControle
     */
    public int sizeTableauPointIG() {
        return TableauPointsDeControle.size();
    }

    /**
     * retourne le nombre d'arc dans le monde
     * @return la taille du tableau d'ArcIG
     */
    public int sizeTableauArcIG() {
        return TableauArcsIG.size();
    }

    /**
     * retourne la valeur de l'idenfiant de style du monde
     * par exemple le style 1 : identifiant 1
     * pour un style unique d'affichage
     * @return identifiantStyle
     */
    public int getIdentifiantStyle() {
        return identifiantStyle;
    }

    /**
     * modifie l'identifiant de style dans le monde
     * @param newstyle le nouvelle identifiant du style d'affichage
     */
    public void setIdentifiantStyle(int newstyle){
        identifiantStyle = newstyle;
        notifierObservateur();
    }
}
