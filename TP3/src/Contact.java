/**
 * Cette classe modelise un contact personnel.
 *
 * @author Imad Bouarfa Shaker
 * Courriel: bouarfa.imad@courrier.uqam.ca
 * Cours: INF1120-20
 * @version 2022-12-10
 */
public class Contact {

    //ATTRIBUTS DE CLASSE
    private static int nbrContactsFavoris = 0; //Contient le nombre de contacts créés qui ont été signalés comme étant des contacts favoris.

    //CONSTANTES DE CLASSE
    private static final String NOM_DEFAUT = "Nom"; //Nom par défaut d'un contact
    private static final String PRENOM_DEFAUT = "Prenom"; //Prénom par défaut d'un contact

    //ATTRIBUTS D'INSTANCES
    private String nom; //Le nom de ce contact
    private String prenom; //Le prénom de ce contact
    private Telephone[] telephones; //Les différents téléphones de ce contact
    private Adresse adresse; //L'adresse de ce contact
    private String[] courriels; //Le courriel de ce contact
    private boolean favori; //true si ce contact est un favori, false sinon

    //-----------------------------
    //CONSTRUCTEURS
    //-----------------------------

    /**
     * Ce constructeur construit un objet contact en initialisant ses attributs avec les valeurs passées en paramètre.
     * @param nom
     * @param prenom
     * @param tel
     * @param adresse
     * @param courriel
     * @param favori
     */

    public Contact(String nom, String prenom, Telephone tel, Adresse adresse, String courriel, boolean favori) {
        this.nom = valideNom(nom, NOM_DEFAUT);
        this.prenom = valideNom(prenom, PRENOM_DEFAUT);
        if (tel == null) {
            this.telephones = new Telephone[0];
        } else {
            this.telephones = new Telephone[1];
            this.telephones[0] = tel;
        }

        if (courriel == null || courriel.equals("")) {
            this.courriels = new String[0];
        } else {
            this.courriels = new String[1];
            this.courriels[0] = courriel;
        }
        this.adresse = adresse;
        this.favori = favori;
        if (favori) nbrContactsFavoris++;
    }

    /**
     * Ce constructeur construit un objet contact en initialisant ses attributs avec les valeurs passées en paramètre.
     * @param nom
     * @param prenom
     * @param adresse
     */
    public Contact(String nom, String prenom, Adresse adresse) {
        this(nom, prenom, null, adresse, null, false);
    }

    //GETTERS
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public boolean isFavori() {
        return favori;
    }

    //SETTERS
    public void setNom(String nom) {
        this.nom = valideNom(nom, NOM_DEFAUT);
    }

    public void setPrenom(String prenom) {
        this.prenom = valideNom(prenom, PRENOM_DEFAUT);
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
        if (favori) nbrContactsFavoris++;
    }

    //METHODES D'INSTANCES
    /**
     * Cette methode permet de valider si le nom est null ou vide
     * @param nom
     * @param valeurParDefaut
     * @return le nom s'il n'est pas vide ou null, sinon retourne la valeur par defaut.
     */
    private String valideNom(String nom, String valeurParDefaut) {
        if (nom == null || nom.equals("")) {
            return valeurParDefaut;
        } else {
            return nom;
        }
    }

    /**
     * Cette methode permet l'affichage d'un contact favori ou non favori sous forme de chaine de caractères.
     * @return Retourne une représentation de ce contact sous forme de chaine de caractères dans le format général suivant:
     *
         * //Contact favori avec 4 téléphones, une adresse et 2 courriels
         * LORD, Melanie [FAVORI]
         * TELEPHONE(S) :
         * (514) 122-2223 [domicile]
         * (418) 345-9987 [Domicile]
         * (450) 877-6529, Poste 33 [bureau] (450) 334-2957 [cellulaire]
         * ADRESSE :
         * 233 Lavoie, app. apt. 1, Tracy, QC, CANADA, H7H2G4
         * COURRIEL(S) : toto@gmail.com toto33@yahoo.ca
     */
    @Override
    public String toString() {
        String contact = "";

        if (favori) contact += this.nom.toUpperCase() + ", " + this.prenom + " [FAVORI]\n\n";
        else contact += this.nom.toUpperCase() + ", " + this.prenom + "\n\n";
        contact += "TELEPHONE(S) :\n";
        for (Telephone num : telephones) {
            contact += num.toString() + "\n";
        }
        if (adresse == null) contact += "\nADRESSE :" + "\n\n";
        else contact += "\nADRESSE :\n" + adresse.toString() + "\n\n";

        contact += "COURRIEL(S) :";
        for (String courriel : courriels) {
            contact += "\n" + courriel;
        }

        return contact;
    }

    /**
     * Permet d’ajouter le tel donné en paramètre au tableau telephones de ce contact.
     * @param tel
     */
    public void ajouterTelephone(Telephone tel) {
        if (tel != null){
            Telephone[] nouveauTableau = new Telephone[telephones.length + 1];
            for (int i = 0; i < telephones.length; i++) {
                nouveauTableau[i] = telephones[i];
            }
            nouveauTableau[telephones.length] = tel;
            telephones = nouveauTableau;
        }
    }


    /**
     * Permet d’ajouter le courriel donné en paramètre au tableau courriels de ce contact.
     * @param courriel
     */
    public void ajouterCourriel(String courriel) {
        if (courriel != null && !courriel.equals("")){
            String[] nouveauTableau = new String[courriels.length + 1];
            for (int i = 0; i < courriels.length; i++) {
                nouveauTableau[i] = courriels[i];
            }
            nouveauTableau[courriels.length] = courriel;
            courriels = nouveauTableau;
        }
    }


    /**
     * Permet d’obtenir le téléphone du tableau telephones de ce contact qui se trouve à la position donnée.
     * @param position
     * @return null si la position donnée ne correspond pas à un indice valide dans le tableau telephones.
     */
    public Telephone obtenirTelephone(int position) {
        try {
            return telephones[position];
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Permet d’obtenir le courriel du tableau courriels de ce contact qui se trouve à la position donnée.
     * @param position
     * @return null si la position donnée ne correspond pas à un indice valide dans le tableau courriels.
     */
    public String obtenirCourriel(int position) {
        try {
            return courriels[position];
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Permet de supprimer le téléphone se trouvant à la position donnée du tableau de téléphones de ce contact.
     * @param position
     * @return null si la suppression n'a pas eu lieu, sinon, la méthode retourne le téléphone qui a été supprimé du tableau telephones.
     */
    public Telephone supprimerTelephone(int position){

        if (position<0 || position >= telephones.length){
            return null;
        } else {
            Telephone telSupprime=null;
            Telephone[] nouveauTableau = new Telephone[telephones.length - 1];
            int k = 0;
            for (int i=0; i<telephones.length; i++){
                if (i!= position){
                    nouveauTableau[k] = telephones[i];
                    k++;
                } else {
                    telSupprime = telephones[i];
                }
            }
            telephones = nouveauTableau;
            return telSupprime;
        }
    }
    // position = 2 ==> on doit supprimer tel3
    // telephones = [tel1, tel2, tel3, tel4]
    // nouveauTableau = [tel1, tel2, tel4]

    /**
     * Permet de supprimer le courriel se trouvant à la position donnée du tableau de courriels de ce contact.
     * @param position
     * @return Si la suppression a lieu, la méthode retourne le courriel qui a été supprimé du tableau courriels, sinon, la méthode retourne null.
     */
    public String supprimerCourriel(int position){

        if (position<0 || position >= courriels.length){
            return null;
        } else {
            String courrielSupprime=null;
            String[] nouveauTableau = new String[courriels.length - 1];
            int k = 0;
            for (int i=0; i<courriels.length; i++){
                if (i!= position){
                    nouveauTableau[k] = courriels[i];
                    k++;
                } else {
                    courrielSupprime = courriels[i];
                }
            }
            courriels = nouveauTableau;
            return courrielSupprime;
        }
    }

    /**
     * Permet de modifier les attributs du téléphone se trouvant à la position donnée dans le tableau telephones de ce contact.
     * @param position
     * @param indReg
     * @param numero
     * @param poste
     * @param type
     */
    public void modifierTelephone(int position, String indReg, String numero, String poste, String type){
        if (position >= 0 && position < telephones.length){
            if (indReg != null) telephones[position].setIndReg(indReg);
            if (numero != null) telephones[position].setNumero(numero);
            if (poste != null) telephones[position].setPoste(poste);
            if (type != null) telephones[position].setType(type);
        }
    }

    /**
     * Permet de remplacer le courriel se trouvant à la position donnée dans le tableau courriels de ce contact par celui passé en paramètre.
     * @param position
     * @param courriel
     */
    public void modifierCourriel(int position, String courriel){
        if (position >= 0 && position < courriels.length){
            if (courriel != null) courriels[position] = courriel;
        }
    }

    /**
     *
     * @return Retourne le nombre de téléphones associés à ce contact (le nombre de téléphones dans le tableau telephones).
     */
    public int nbrTelephones(){return telephones.length;}

    /**
     *
     * @return Retourne le nombre de courriels associés à ce contact (le nombre de courriels dans le tableau courriels).
     */
    public int nbrCourriels(){return courriels.length;}

    //METHODE DE CLASSE
    /**
     *
     * @return Permet d’obtenir la valeur de l’attribut de classe nbrContactsFavoris.
     */
    public static int obtenirNbrContactsFavoris(){return nbrContactsFavoris;}

}

