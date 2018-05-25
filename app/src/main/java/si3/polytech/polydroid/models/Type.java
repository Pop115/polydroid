package si3.polytech.polydroid.models;

/**
 * Created by Kienan on 09/04/2018.
 */

public enum Type {

    ELEC("Probl\u00E8me \u00E9lectrique"),
    MAT("Probl\u00E8me mat\u00E9riel"),
    OBJP("Objet perdu"),
    OBJT("Objet trouv\u00E9"),
    INFO("Probl\u00E8me informatique"),
    WC("Probl\u00E8me toilettes"),
    SALE("Salet\u00E9 dans les b\u00E2timents"),
    CAR("Probl\u00E8me parking"),
    SALLE("Conflit salle"),
    AUTRE("Autre");

    private final String type;

    Type(String type) {
        this.type = new String(type);
    }

    @Override
    public String toString() {
        return type;
    }

}
