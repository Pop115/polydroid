package si3.polytech.polydroid;


public enum PosteAnnee {

    TECHNICIEN_DE_SURFACE("Technicien de surface"),
    PROFESSEUR("Professeur"),
    STAGIAIRE("Stagiaire"),
    DOCTORANT("Doctorant"),
    PERSONNEL_ADMINISTRATIF("Personnel administratif"),
    TROISIEME_ANNEE("3ème Année"),
    QUATRIEME_ANNEE("4ème Année"),
    CINQUIEME_ANNEE("5ème Année"),
    VISITEUR("Visiteur"),
    AUTRE("Autre");
    //@formatter:on

    private final String posteannee;

    PosteAnnee(String posteannee) {
        this.posteannee = new String(posteannee);
    }

    @Override
    public String toString() {
        return posteannee;
    }

}
