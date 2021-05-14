package twisk.mondeIG;

import javafx.concurrent.Task;
import twisk.exceptionstwiskIG.*;
import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.GestionnaireThreads;
import twisk.simulation.GestionnaireClients;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur {
    private final HashMap<String, EtapeIG> TableauEtapesIG = new HashMap<>();
    private final ArrayList<ArcIG> TableauArcsIG = new ArrayList<>();
    private final ArrayList<PointDeControleIG> TableauPointsDeControle = new ArrayList<>();
    private final CorrespondanceEtapes correspondancesEtapes = new CorrespondanceEtapes();
    private int identifiantStyle;

    /**
     * Constructeur d'un MondeIG
     */
    public MondeIG() {
        ajouter("Activite");
        identifiantStyle = 0;
    }

    /**
     * modifie l'etat d'un Arc, si il est séléctionné ou non
     * @param arc   l'ArcIG à modifier
     * @param setTo true si on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(ArcIG arc, boolean setTo){
        arc.setEstSelectionne(setTo);
        notifierObservateur();
    }

    /**
     * modifie l'etat d'une EtapeIG, si il est séléctionné ou non
     * @param etp   l'EtapeIG à modifier
     * @param setTo true sui on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(EtapeIG etp, boolean setTo) {
        etp.setEstSelectionne(setTo);
        notifierObservateur();
    }

    /**
     * retourne le nombre de PoinDeControle séléctionnée dans le monde
     *
     * @return la taille du tableau de PointDeControle
     */
    public int sizeTableauPointIG() {
        return TableauPointsDeControle.size();
    }


    /**
     * retourne le nombre d'arc dans le monde
     * @return la taille du tableau d'ArcIG en mémoire
     */
    public int sizeTableauArcIG() {
        return TableauArcsIG.size();
    }

    /**
     * retourne le nombre d'Etape dans le monde
     * @return la taille du tableau d'Etape en mémoire
     */
    public int sizeTableauEtapeIG() {
        return TableauEtapesIG.size();
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
     * retourne une EtapeIG dans le monde
     * @param idf l'identifiant de l'EtapeIG que l'on cherche
     * @return cet identifiant si trouvé
     * @throws ExceptionMondeIG sinon si non trouvé throw une Exception
     */
    public EtapeIG getEtape(String idf) throws ExceptionMondeIG {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.getIdentifiant().equals(idf)) {
                return etapeIG;
            }
        }
        throw new ExceptionMondeIG("Aucune étape portant cet idenfiant n'est trouvé");
    }

    /**
     * modifie l'identifiant de style dans le monde
     * @param newstyle le nouvelle identifiant du style d'affichage
     */
    public void setIdentifiantStyle(int newstyle) {
        identifiantStyle = newstyle;
        notifierObservateur();
    }

    /**
     * la fonction vide le tableau de PointDeControle
     */
    public void viderTableauPointsDeControle() {
        TableauPointsDeControle.clear();
    }

    /**
     * la fonction modifie les coordonnées d'une EtapeIG
     * @param etapeIG l'EtapIG à déplacé
     * @param x       l'abscisse d'arrivé
     * @param y       l'ordonnée d'arrivé
     */
    public void deplacerUneEtape(EtapeIG etapeIG, double x, double y) {
        etapeIG.deplacerEtape(x, y);
        notifierObservateur();
    }

    /**
     * La fonction créer une ActiviteRestreinteIG si elle est précédé par un GuichetIG
     */
    private void creerActiviteRestreinte() {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estUnGuichet()) {
                ((ActiviteIG) etapeIG.iterator().next()).setEstUneActiviteRestreinte(true);
            }
        }
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
     * fonction réagir()
     */
    @Override
    public void reagir() {
        correspondancesEtapes.mettreaJour();
        notifierObservateur();
    }

    /**
     * retourne un itérateur du les ArcIG du monde
     *
     * @return un itérateur ArcIG
     */
    public Iterator<ArcIG> iteratorArcIG() {
        return TableauArcsIG.iterator();
    }

    public void simuler() {
        try {
            int nbclient = 3;
            verifierMondeIG();
            Monde monde = creerMonde();
            start(monde,10);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }



    /**
     * vérifie que la composition du monIg est correcte
     */
    private void verifierMondeIG() throws ExceptionMondeIG {
        creerActiviteRestreinte();
        if (!verifierExisteEntree()) {
            throw new ExceptionMondeIG("Monde invalide : Le monde ne possède pas d'Entrée");
        }
        if (!verifierExisteSortie()) {
            throw new ExceptionMondeIG("Monde invalide : Le monde ne possède pas de Sortie");
        }
        if (!verifierNomDesEtapes()) {
            throw new ExceptionMondeIG("Monde invalide : Deux Etapes ont le même nom OU une Etape a un nom invalide");
        }
        if (!verifierSuccesseursDesActivites()) {
            throw new ExceptionMondeIG("Monde invalide : Une Activite est suivi d'une activité restreinte");
        }
        if (!verifierEntreeRelieASortie()) {
            throw new ExceptionMondeIG("Monde invalide : Entrée non relié à la Sortie");
        }
        if (!verifierSortiesSontDesActivites()) {
            throw new ExceptionMondeIG("Monde invalide : Un guichet ne peut pas être une sortie");
        }
        if (!verifierEntreesSontDesActivites()) {
            throw new ExceptionMondeIG("Monde invalide : Un guichet ne peut pas être une entrée");
        }
    }


    /**
     * verifie la validité des arcs avant de les créer
     *
     * @param pt1 le point de départ de l' ArcIG
     * @param pt2 le point d'arrivée de l'ArcIG
     * @throws ExceptionsInvaliditeSurLesArcs sur les arcs
     */
    private void verifierArcIG(PointDeControleIG pt1, PointDeControleIG pt2) throws ExceptionsInvaliditeSurLesArcs {
        ArcIG arc = new ArcIG(pt1, pt2);
        if (pt1.estSurLaMemeEtapeQue(pt2)) {
            throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer cet arc, les deux points sont sur la même étape");
        }
        if (arc.getEtapeDebut().estUneSortie() && arc.getEtapeArrive().estUneEntree()) {
            throw new ExceptionsInvaliditeSurLesArcs("Impossible de créer et arc, une Entrée ne peut pas être successeur d'une Sortie");
        }
        if (arc.getEtapeDebut().estUnGuichet()) {
            if (arc.getEtapeArrive().estUnGuichet()) {
                throw new ExceptionsInvaliditeSurLesArcs("Impossible de relier un Guichet à un autre Guichet.");
            }
            if (arc.getEtapeDebut().nombreDeSuccesseur() == 1) {
                throw new ExceptionsInvaliditeSurLesArcs("Impossible un guichet ne peut avoir qu'un seul successeur");
            }
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
    }


    /**
     * La fonction créer un Monde et le retourne
     *
     * @return le mojde crée
     */
    private Monde creerMonde() {
        Monde monde = new Monde();
        for (EtapeIG etapeIG : this) {
            Etape etape = null;
            if (etapeIG.estUneActiviteRestreinte()) {
                etape = new ActiviteRestreinte(etapeIG.getNom(), ((ActiviteIG) etapeIG).getDelai(), ((ActiviteIG) etapeIG).getEcarttemps());
            } else if (etapeIG.estUneActivite()) {
                etape = new Activite(etapeIG.getNom(), ((ActiviteIG) etapeIG).getDelai(), ((ActiviteIG) etapeIG).getEcarttemps());
            } else if (etapeIG.estUnGuichet()) {
                etape = new Guichet(etapeIG.getNom(), ((GuichetIG) etapeIG).getNombreDeJetons());
            }
            if (etapeIG.estUneEntree()) {
                monde.aCommeEntree(etape);
            } else if (etapeIG.estUneSortie()) {
                monde.aCommeSortie(etape);
            }
            correspondancesEtapes.ajouter(etapeIG, etape);
        }
        for (EtapeIG etapeIG : this) {
            monde.ajouter(correspondancesEtapes.get(etapeIG));
            for (EtapeIG successeur : etapeIG) {
                correspondancesEtapes.get(etapeIG).ajouterSuccesseur(correspondancesEtapes.get(successeur));
            }
        }
        return monde;
    }


    /**
     * La fonction rajoute un ArcIG dans le nom
     *
     * @param pt1 le point de départ de l'ArcIG
     * @param pt2 le point d'arrivé de l'ArcIG
     * @throws ExceptionsInvaliditeSurLesArcs lève des exceptions si les arcs si suivent
     *                                        pas les règles.
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws ExceptionsInvaliditeSurLesArcs {
        try {
            ArcIG arc = new ArcIG(pt1, pt2);
            verifierArcIG(pt1, pt2);
            TableauArcsIG.add(arc);
            arc.getEtapeDebut().ajouterSuccesseur(arc.getEtapeArrive());
        } catch (ExceptionsInvaliditeSurLesArcs invaliditeSurLesArcs) {
            System.out.println(invaliditeSurLesArcs.getMessage());
        }
        pt1.setEstSelectionne(false);
        pt2.setEstSelectionne(false);
    }

    /**
     * la fonction ajoute une Activite dans le monde
     *
     * @param type le type de l'Etape
     */
    public void ajouter(String type) {
        assert (type.equals("Activite") || type.equals("Guichet")) : "Erreur type inconnu.";
        String identifiant = FabriqueIdentifiant.getInstance().getIdentifiant();
        if (type.equals("Activite")) {
            ActiviteIG activite = new ActiviteIG(identifiant, identifiant, 175, 75);
            TableauEtapesIG.put(activite.getIdentifiant(), activite);
            notifierObservateur();
        }
        if (type.equals("Guichet")) {
            GuichetIG guichet = new GuichetIG(identifiant, identifiant, 200, 60);
            TableauEtapesIG.put(guichet.getIdentifiant(), guichet);
            notifierObservateur();
        }
    }

    /**
     * Lance le simulation
     *
     * @param monde     le Monde à simuler
     * @param nbclients le nombre de clients
     * @throws ClassNotFoundException    si pas de classe trouvé
     * @throws NoSuchMethodException     si aucun méthode trouvé
     * @throws InvocationTargetException si erreur de cible
     * @throws InstantiationException    si erreur instanciation
     * @throws IllegalAccessException    si erreur d'accées
     */
    private void start(Monde monde, int nbclients) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(this.getClass().getClassLoader());
        Class<?> loadClass = classLoaderPerso.loadClass("twisk.simulation.Simulation");
        Constructor<?> co = loadClass.getConstructor();
        Object simulation = co.newInstance();
        assert (simulation.getClass().equals(Simulation.class)) : "erreur newInstance() Simulation";
        Method ajo = simulation.getClass().getMethod("ajouterObs", MondeIG.class);
        ajo.invoke(simulation,this);
        Method m1 = simulation.getClass().getMethod("setNbClients", int.class);
        m1.invoke(simulation, nbclients);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Method sim = simulation.getClass().getMethod("simuler", Monde.class);
                    sim.invoke(simulation, monde);
                    System.out.println("....................................................Fin Simulation\n");
                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                return null;
            }
        };
        GestionnaireThreads.getInstance().lancer(task);
    }


    /**
     * Annule la séléction des élements séléctionnées
     */
    public void effacerLaSelection() {
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
    public void assignerEntree() {
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                etape.setEstUneEntree(!etape.estUneEntree());
                etape.setEstSelectionne(false);
            }
        }
        notifierObservateur();
    }


    /**
     * assigne les Activités sélectionnées en Sortie
     */
    public void assignerSortie() {
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                etape.setEstUneSortie(!etape.estUneSortie());
            }
            etape.setEstSelectionne(false);
        }
        notifierObservateur();
    }

    /**
     * renomme une Etape
     *
     * @param nom l'Etape à renommer
     */
    public void renommerEtape(String nom) {
        for (EtapeIG e : this) {
            if (e.estSelectionne()) {
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
                etape.supprimerSuccesseur();
                viderTableauPointsDeControle();
            }
        }
        // suppression des Arcs
        Iterator<ArcIG> iterarc = iteratorArcIG();
        while (iterarc.hasNext()) {
            ArcIG arc = iterarc.next();
            if (arc.estSelectionne()) {
                iterarc.remove();
                arc.getEtapeDebut().supprimerSuccesseur();
            }
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
        if (TableauPointsDeControle.size() == 2) {
            //
            PointDeControleIG p = TableauPointsDeControle.get(0);
            PointDeControleIG p2 = TableauPointsDeControle.get(1);
            viderTableauPointsDeControle();
            try {
                ajouter(p, p2);
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
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                res++;
            }
        }
        return res;
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
            }
        }
        notifierObservateur();
    }

    /**
     * assigne un Ecart Temps à une EtapeIG séléctionnée
     *
     * @param ecarttemps le nouveau Ecart-temps
     */
    public void assignerEcartTempsAEtape(int ecarttemps) throws ExceptionVueMenu {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estSelectionne()) {
                etapeIG.setEstSelectionne(false);
                if (etapeIG.estUneActivite()) {
                    ActiviteIG activiteIG = (ActiviteIG) etapeIG;
                    activiteIG.setEcarttemps(ecarttemps);
                }
            }
        }
        notifierObservateur();
    }

    public void assignerNombreDeJetonsAEtape(int nbjetons) {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estSelectionne()) {
                etapeIG.setEstSelectionne(false);
                if (etapeIG.estUnGuichet()) {
                    GuichetIG guichetIG = (GuichetIG) etapeIG;
                    guichetIG.setNombreDeJetons(nbjetons);
                }
            }
        }
        notifierObservateur();
    }

    /**
     * la fonction vérifie que les successeurs des entrées ne sont pas des entrées
     * @return vrai si la condition est respécté, sinon faux
     */
    public boolean verifierSuccesseursEntree(){
        for(EtapeIG etapeIG : this){
            if(etapeIG.estUneEntree()){
                for(EtapeIG succ : etapeIG){
                    if(succ.estUneEntree()){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * retourne vrai si il le monde possède une Entrée,sinon faux
     * @return vrai si le monde possède une Entrée,sinon faux
     */
    private boolean verifierExisteEntree() {
        for (EtapeIG etape : this) {
            if (etape.estUneEntree()) {
                return true;
            }
        }
        return false;
    }

    /**
     * retourne vrai si il existe une Sortie dans le monde,sinon faux
     * @return retourne vrai si il existe une Sortie dans le monde,sinon faux
     */
    private boolean verifierExisteSortie() {
        for (EtapeIG etape : this) {
            if (etape.estUneSortie()) {
                return true;
            }
        }
        return false;
    }

    /**
     * retourne faux si une Etape à le même nom qu'une Autre Etape, sinon vrai
     * @return faux si une Etape à le même nom qu'une Autre Etape, sinon vrai
     */
    private boolean verifierNomDesEtapes() {
        for (EtapeIG etapeIGref : this) {
            if (etapeIGref.getNom().equals("")) {
                return false;
            }
            for (EtapeIG etapeIGtest : this) {
                if (!etapeIGref.equals(etapeIGtest)) {
                    if (etapeIGref.getNom().equals(etapeIGtest.getNom())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * La fonction vérifie que l'Entrée en bien relié à la sortie
     * @param etapeIGsommet l'Etape de départ
     * @return vrai si est connexe
     */
    private boolean entreeRelieASortie(EtapeIG etapeIGsommet) {
        if (etapeIGsommet.estUneSortie()) {
            return true;
        }
        for (EtapeIG succ : etapeIGsommet) {
            return entreeRelieASortie(succ);
        }
        return false;
    }


    /**
     * verifie si les activite sont correctes
     * Les acitvites ne sont pas suivie pas des Activites Restreintes
     * @return vrai si la condition est respectée sinon faux
     */
    private boolean verifierSuccesseursDesActivites() {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estUneActivite()) {
                for (EtapeIG succ : etapeIG) {
                    if (succ.estUneActiviteRestreinte()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Verifie que les Entrées sont correctes
     * Une entrée doit être réliéà la sortie
     * @return vrai si les conditions sont vérifiées
     */
    private boolean verifierEntreeRelieASortie() {
        boolean res = true;
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estUneEntree()) {
                res = entreeRelieASortie(etapeIG);
            }
        }
        return res;
    }

    /**
     * Verifie que les Sorties sont correctes
     * Une sortie doit être une activité
     * @return vrai si les conditions sont vérifiées
     */
    private boolean verifierSortiesSontDesActivites() {
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estUneSortie() && etapeIG.estUnGuichet()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifie que les Sorties sont correctes
     * Une Entrée doit être une activité
     * @return vrai si les conditions sont vérifiées
     */
    public boolean verifierEntreesSontDesActivites(){
        for(EtapeIG etapeIG : this){
            if (etapeIG.estUneEntree() && etapeIG.estUnGuichet()){
                return false;
            }
        }
        return true;
    }


}
