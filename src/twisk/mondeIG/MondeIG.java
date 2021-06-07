package twisk.mondeIG;

import javafx.concurrent.Task;
import twisk.exceptionstwiskIG.*;
import twisk.monde.*;
import twisk.mondepredefinis.Monde3;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueIdentifiant;
import twisk.outils.GestionnaireThreads;
import twisk.mondepredefinis.Monde1;
import twisk.mondepredefinis.Monde2;
import twisk.simulation.Client;
import twisk.simulation.Simulation;
import twisk.vues.Observateur;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;


public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur, Serializable {
    private static final long serialVersionUID =  -6535851685176719879L;
    private final HashMap<String, EtapeIG> TableauEtapesIG = new HashMap<>();
    private final ArrayList<ArcIG> TableauArcsIG = new ArrayList<>();
    private final ArrayList<PointDeControleIG> TableauPointsDeControle = new ArrayList<>();
    private int identifiantStyle;
    private boolean modeCreation;
    private int nombreDeClients;
    private boolean suitLoiUniforme;
    private boolean suitLoiPoisson;
    private boolean suitLoiNormale;
    private Object simulation = new Simulation();

    /**
     * Constructeur d'un MondeIG
     */
    public MondeIG(){
        ajouter("Activite");
        identifiantStyle = 0;
        nombreDeClients = 10;
        modeCreation = true;
        initLoi();
        setSuitLoiUniforme(true);
    }

    /**
     * modifie l'etat d'un Arc, si il est séléctionné ou non
     * @param arc   l'ArcIG à modifier
     * @param setTo true si on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(ArcIG arc, boolean setTo){
        if(estModeCreation()) {
            arc.setEstSelectionne(setTo);
            notifierObservateur();
        }
    }

    /**
     * modifie l'etat d'une EtapeIG, si il est séléctionné ou non
     * @param etp   l'EtapeIG à modifier
     * @param setTo true sui on veut qu'il soit séléctionné sinon false
     */
    public void setEstSelectionne(EtapeIG etp, boolean setTo) {
        if(estModeCreation()) {
            etp.setEstSelectionne(setTo);
            notifierObservateur();
        }
    }

    /**
     * assigne un nombre de clients à la simulation
     * @param nombreDeClients le nombre de clients à assigner
     */
    public void setNombreDeClients(int nombreDeClients){
        this.nombreDeClients = nombreDeClients;
        notifierObservateur();
    }

    /**
     * Selectionne toutes les etapes du monde
     */
    public void nouveauMonde(){
        for(EtapeIG etapeIG : this){
            etapeIG.setEstSelectionne(true);
        }
        supprimerEtapesEtArcsSelectionnes();
    }

    /**
     * retourne le mode actuel
     * @return le mode
     */
    public boolean estModeCreation() {
        return modeCreation;
    }

    /**
     * assigne une loi d'entrée qui suit la loi uniforme
     */
    public void setSuitLoiNormale(boolean suitLoiNormale) {
        initLoi();
        this.suitLoiNormale = suitLoiNormale;
    }

    /**
     * assigne une loi d'entrée qui suit la loi de Poisson au Monde
     */
    public void setSuitLoiPoisson(boolean suitLoiPoisson) {
        initLoi();
        this.suitLoiPoisson = suitLoiPoisson;
    }

    /**
     * assigne une loi d'entrée qui suit la loi de Poisson au Monde
     */
    public void setSuitLoiUniforme(boolean suitLoiUniforme) {
        initLoi();
        this.suitLoiUniforme = suitLoiUniforme;
    }


    /**
     * Les lois d'entrées sont réinitialisée le monde ne connaît
     * donc aucun loi d'entrée.
     */
    public void initLoi(){
        this.suitLoiPoisson = false;
        this.suitLoiUniforme = false;
        this.suitLoiNormale = false;
    }


    /**
     * assigne un mode
     * @param modeCreation le mode
     */
    public void setModeCreation(boolean modeCreation) {
        this.modeCreation = modeCreation;
    }

    /**
     * retourne le nombre de PoinDeControle séléctionnée dans le monde
     * @return la taille du tableau de PointDeControle
     */
    public int sizeTableauPointIG() {
        return TableauPointsDeControle.size();
    }

    /**
     * retourne le nombre de clients à simuler du monde
     * @return le nombre de clietns à simuler du monde
     */
    public int getNombreDeClients(){
        return nombreDeClients;
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
    public void viderTableauPointsDeControle(){
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
    private void creerActiviteRestreinte(){
        for (EtapeIG etapeIG : this) {
            if (etapeIG.estUnGuichet()) {
                ((ActiviteIG) etapeIG.iterator().next()).setEstUneActiviteRestreinte(true);
            }
        }
    }

    /**
     * La fonction arrete la simulation
     *
     */
    public void stopSimulation(){
        GestionnaireThreads.getInstance().detruireTout();
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
     * retourne un objet de type Iterator<Client>
     * @return un itérateur sur les clients
     */
    @SuppressWarnings("unchecked")
    public Iterator<Client> iteratorClient() throws Exception {
       return (Iterator<Client>) simulation.getClass().getMethod("iterator").invoke(simulation);
    }

    /**
     * Charge un mondeIG sauvegarder
     * @param mondeIG Le monde à charger
     */
    public void chargerMonde(MondeIG mondeIG){
        nouveauMonde();
        for(EtapeIG etapeIG : mondeIG){
            this.TableauEtapesIG.put(etapeIG.getIdentifiant(),etapeIG);
        }
        this.TableauArcsIG.addAll(mondeIG.TableauArcsIG);
        notifierObservateur();
    }

    /**
     * ajouter un monde déjà construit dans le mondeIG
     */
    public void ajouterMondePredefini1(){
        new Monde1(this);
    }

    /**
     * ajouter un monde déjà construit dans le mondeIG un
     */
    public void ajouterMondePredefini2(){
        new Monde2(this);
    }

    /**
     * ajouter un monde déjà construit dans le mondeIG un
     */
    public void ajouterMondePredefini3(){
        new Monde3(this);
    }


    /**
     * fonction réagir()
     */
    @Override
    public void reagir(){
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


    public void simuler() throws ExceptionMondeIG {
        verifierMondeIG();
        Monde monde = creerMonde();
        setModeCreation(false);
        start(monde);
    }


    /**
     * vérifie que la composition du monIg est correcte
     */
    private void verifierMondeIG() throws ExceptionMondeIG {
        creerActiviteRestreinte();
        if (!verifierNomDesEtapes()) {
            throw new ExceptionMondeIG("Monde invalide : Deux Etapes ont le même nom OU une Etape n'a pas de nom");
        }
        if( !verifierActiviteRestreintePasUneEntree()){
            throw new ExceptionMondeIG("Monde Invalide : Une Activite Restreinte ne peut pas être une entrée");
        }
        if(!verifierUneGuichetAUnSuccesseur()) {
            throw new ExceptionMondeIG("Monde invalide : Un guichet n'a pas de successeur");
        }
        if (!verifierSuccesseursDesActivites()) {
            throw new ExceptionMondeIG("Monde invalide : Une ActiviteRestreinte ne peut être précedée que par un guichet");
        }
        if(!verifierAucunCircuits()){
            throw new ExceptionMondeIG("Monde invalide : Circuit détecté");
        }
        if (!verifierExisteEntree()) {
            throw new ExceptionMondeIG("Monde invalide : Le monde ne possède pas d'Entrée");
        }
        if (!verifierExisteSortie()) {
            throw new ExceptionMondeIG("Monde invalide : Le monde ne possède pas de Sortie");
        }
    }


    /**
     * verifie la validité des arcs avant de les créer
     * @param pt1 le point de départ de l' ArcIG
     * @param pt2 le point d'arrivée de l'ArcIG
     * @throws ExceptionArcIG sur les arcs
     */
    private void verifierArcIG(PointDeControleIG pt1, PointDeControleIG pt2) throws ExceptionArcIG {
        ArcIG arc = new ArcIG(pt1, pt2);
        if (pt1.estSurLaMemeEtapeQue(pt2)) {
            throw new ExceptionArcIG("Impossible de créer cet arc, les deux points sont sur la même étape");
        }
        if (arc.getEtapeDebut().estUneSortie() && arc.getEtapeArrive().estUneEntree()) {
            throw new ExceptionArcIG("Impossible de créer et arc, une Entrée ne peut pas être successeur d'une Sortie");
        }
        if (arc.getEtapeDebut().estUnGuichet()) {
            if (arc.getEtapeArrive().estUnGuichet()) {
                throw new ExceptionArcIG("Impossible de relier un Guichet à un autre Guichet.");
            }
            if (arc.getEtapeDebut().nombreDeSuccesseur() == 1) {
                throw new ExceptionArcIG("Impossible de créer cet arc, un guichet ne peut avoir qu'un seul successeur");
            }
        }
        for (ArcIG arcIG : TableauArcsIG) {
            if (arcIG.aCommeDebut(arc.getEtapeDebut()) && arcIG.aCommeArrive(arc.getEtapeArrive())) {
                throw new ExceptionArcIG("Impossible de créer cet arc, il existe déjà un arc qui relie ces deux étapes");
            }
            if (arcIG.aCommeDebut(arc.getEtapeArrive()) && arcIG.aCommeArrive(arc.getEtapeDebut())) {
                throw new ExceptionArcIG("Impossible de créer cet arc, deux arcs sont symétriques");
            }
            if (arcIG.getEtapeArrive().equals(arc.getEtapeArrive()) && arc.getEtapeArrive().estUnGuichet()) {
                throw new ExceptionArcIG("Impossible de créer cet arc, un guichet ne peut pas avoir deux prédecesseurs");
            }
        }
    }



    /**
     * ajouter un Successeur à une EtapeIG
     * @param type le type sur successeurs
     * @param etapeIG l'EtapeIG qui attend un nouveau successeur
     */
    public void ajouterSuccesseur(String type, EtapeIG etapeIG) {
        if (estModeCreation()) {
            Random random = new Random();
            try {
                EtapeIG etapeIGsucc = null;
                if (type.equals("Activite") || type.equals("ActiviteRestreinte")) {
                    if (!(etapeIG.estUnGuichet() && etapeIG.nombreDeSuccesseur() >= 1)) {
                        etapeIGsucc = ajouter("Activite");
                    }
                }
                if (type.equals("Guichet")) {
                    etapeIGsucc = ajouter("Guichet");
                }
                assert etapeIGsucc != null;
                etapeIGsucc.deplacerEtape(etapeIG.getPosX() + 250, random.nextInt(250) + etapeIG.getPosY() - 125);
                String choixEtapeIG;
                if (etapeIGsucc.getPosY() + etapeIGsucc.getHauteur() / 2 >= etapeIG.getPosY() && etapeIGsucc.getPosY() + etapeIGsucc.getHauteur() / 2 <= etapeIG.getPosY() + etapeIG.getHauteur()) {
                    choixEtapeIG = "droite";
                } else {
                    if (etapeIG.getPosY() + etapeIG.getHauteur() / 2 <= etapeIGsucc.getPosY()) {
                        choixEtapeIG = "bas";
                    } else {
                        choixEtapeIG = "haut";
                    }
                }
                if (etapeIG.estUneActivite()) {
                    ajouter(etapeIG.getPointDeControle(choixEtapeIG), etapeIGsucc.getPointDeControle("gauche"));
                }
                if (etapeIG.estUnGuichet()) {
                    ajouter(etapeIG.getPointDeControle("droite"), etapeIGsucc.getPointDeControle("gauche"));
                }
            } catch (Exception exception) {
                System.out.print(exception.getMessage());
            }
            notifierObservateur();
        }
    }

    /**
     * La fonction créer un Monde et le retourne
     * @return le monde crée
     */
    private Monde creerMonde() {
        Monde monde = new Monde();
        CorrespondanceEtapes correspondancesEtapes = new CorrespondanceEtapes();
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
        //ajout des successeurs ici
        for (EtapeIG etapeIG : this) {
            monde.ajouter(correspondancesEtapes.get(etapeIG));
            for (EtapeIG successeur : etapeIG) {
                correspondancesEtapes.get(etapeIG).ajouterSuccesseur(correspondancesEtapes.get(successeur));
            }
        }
        /* assigne les lois d'entréés gaussienne ou uniforme ou exponentielle*/
        if (suitLoiUniforme) {
            monde.setSuitLoiUniforme();
        }
        if (suitLoiPoisson) {
            monde.setSuitLoiPoisson();
        }
        if (suitLoiNormale) {
            monde.setSuitLoiNormale();
        }
        return monde;
    }


    /**
     * La fonction rajoute un ArcIG dans le nom
     * @param pt1 le point de départ de l'ArcIG
     * @param pt2 le point d'arrivé de l'ArcIG
     * @throws ExceptionArcIG lève des exceptions si les arcs si suivent pas les règles.
     */
    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2) throws ExceptionArcIG {
        try {
            ArcIG arc = new ArcIG(pt1, pt2);
            verifierArcIG(pt1, pt2);
            TableauArcsIG.add(arc);
            arc.getEtapeDebut().ajouterSuccesseur(arc.getEtapeArrive());
            if(arc.getEtapeArrive().estUnGuichet()) {
                assignerSensDeCirculationGuichetIG(arc);
            }
        } catch (ExceptionArcIG invaliditeSurLesArcs) {
            System.out.println(invaliditeSurLesArcs.getMessage());
        }
        pt1.setEstSelectionne(false);
        pt2.setEstSelectionne(false);
    }

    /**
     * assigne un sens de circulation à un Guichet
     * @param arcEntree l'arc qui défini l'entrée du guichet
     */
    private void assignerSensDeCirculationGuichetIG(ArcIG arcEntree) {
        if (arcEntree.getPointDarrive().getPosition().equals("gauche")) {
            ((GuichetIG) arcEntree.getEtapeArrive()).assignerSensDeCirculation("gauche", "droite");
        }
        if (arcEntree.getPointDarrive().getPosition().equals("droite")) {
            ((GuichetIG) arcEntree.getEtapeArrive()).assignerSensDeCirculation("droite", "gauche");
        }
    }


    /**
     * la fonction ajoute une Activite dans le monde
     * @param type le type de l'Etape
     */
    public EtapeIG ajouter(String type) {
        String identifiant;
        if (type.equals("Activite")) {
            identifiant = FabriqueIdentifiant.getInstance().getIdentifiantActivite();
            ActiviteIG activite = new ActiviteIG(identifiant, identifiant, 175, 100);
            TableauEtapesIG.put(activite.getIdentifiant(), activite);
            notifierObservateur();
            return activite;
        }
        if (type.equals("Guichet")) {
            identifiant = FabriqueIdentifiant.getInstance().getIdentifiantGuichet();
            GuichetIG guichet = new GuichetIG(identifiant, identifiant, 200, 60);
            TableauEtapesIG.put(guichet.getIdentifiant(), guichet);
            notifierObservateur();
            return guichet;
        }
        return null;
    }

    /**
     * Lance la simulation
     * @param monde le Monde à simuler
     */
    private void start(Monde monde){
        MondeIG mde = this;
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {

                    ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(this.getClass().getClassLoader());
                    Class<?> loadClass = classLoaderPerso.loadClass("twisk.simulation.Simulation");
                    Constructor<?> co = loadClass.getConstructor();
                    Object simulation = co.newInstance();
                    assert (simulation.getClass().equals(Simulation.class)) : "erreur newInstance() Simulation";
                    mde.simulation = simulation;

                    Method ajo = simulation.getClass().getMethod("ajouterObservateur", MondeIG.class);
                    ajo.invoke(simulation, mde);

                    Method m1 = simulation.getClass().getMethod("setNbClients", int.class);
                    m1.invoke(simulation, nombreDeClients);

                    Method sim = simulation.getClass().getMethod("simuler", Monde.class);
                    sim.invoke(simulation, monde);

                    System.out.println(".............................................................................................Fin Simulation");
                    mde.setModeCreation(true);
                    mde.notifierObservateur();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
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
                if(etape.estUneActivite() && !(etape.estUneSortie())) {
                    etape.setEstUneEntree(!etape.estUneEntree());
                }
            }
            etape.setEstSelectionne(false);
        }
        notifierObservateur();
    }


    /**
     * assigne les Activités sélectionnées en Sortie
     */
    public void assignerSortie() {
        for (EtapeIG etape : this) {
            if (etape.estSelectionne()) {
                if(etape.estUneActivite() && !(etape.estUneEntree())) {
                    etape.setEstUneSortie(!etape.estUneSortie());
                }
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

        // suppression des Arcs
        Iterator<ArcIG> iterarc = iteratorArcIG();
        while (iterarc.hasNext()) {
            ArcIG arcIG = iterarc.next();
            if(arcIG.estSelectionne()){
                arcIG.getEtapeDebut().supprimerSuccesseur(arcIG.getEtapeArrive());
                if(arcIG.getEtapeArrive().estUneActiviteRestreinte()){
                    ((ActiviteIG) arcIG.getEtapeArrive()).setEstUneActiviteRestreinte(false);
                }
                iterarc.remove();
            }
        }
        // suppression des Etapes
        Iterator<EtapeIG> it = iterator();
        while (it.hasNext()) {
            EtapeIG etapeIG = it.next();
            if (etapeIG.estSelectionne()) {
                for (Iterator<ArcIG> iter = iteratorArcIG(); iter.hasNext(); ) {
                    ArcIG arc = iter.next();
                    if(arc.aCommeArrive(etapeIG)) {
                        arc.getEtapeDebut().supprimerSuccesseur(arc.getEtapeArrive());
                    }
                    if (arc.estRelie(etapeIG)) {
                        iter.remove();
                    }
                }
                it.remove();
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
    private boolean verifierAucunCircuits() {
        boolean res = true;
        for (EtapeIG etapeIG : this) {
            for (EtapeIG succ : etapeIG) {
                res = etapeIG.estAccessibleDepuis(succ);
                //Dès qu'il détecte un circuit on retourne faux
                //quitter la boucle
                if(res){
                    return false;
                }
            }
        }
        return !res;
    }


    /**
     * on vérifie qu'un guichet a toujours un successeur
     * @return vrai si la condition vérifié, sinon faux
     */
    public boolean verifierUneGuichetAUnSuccesseur(){
        for(EtapeIG etapeIG : this){
            if(etapeIG.estUnGuichet() && etapeIG.nombreDeSuccesseur() == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * La fonction vérifie qu'un activité restreinte n'est pas une Entrée
     * @return vrai si la condition est vérifiée sinon faux
     */
    public boolean verifierActiviteRestreintePasUneEntree(){
        for(EtapeIG etapeIG : this){
            if(etapeIG.estUneActiviteRestreinte() && etapeIG.estUneEntree()){
                return false;
            }
        }
        return true;
    }
}
