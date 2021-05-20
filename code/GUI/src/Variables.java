import java.awt.Color;

public class Variables {
    public static final String Nederlands = "Nederlands", Engels = "Engels";
    public static final String Hoofdscherm = "hoofd", Inlogscherm = "Inlog", Pinscherm = "Pin", Taalscherm = "Taal", KaartVerwijderdScherm = "kaartVerwijderd", InvulScherm = "invulScherm";
    public static final String hoofdschermActie = "hoofdschermActie", InlogActie = "inlogActie", PinActie = "pinActie", TaalActie = "taalActie", KaartVerwijderdActie = "kaartVerwijderdActie", TerugActie = "terugActie", AfsluitActie = "afsluitActie", TaalEngelsActie = "taalEngelsActie", TaalNederlandsActie = "taalNederlandsActie", BedragInvoerActie = "bedragInvoerActie", PinGeldActie = "pinGeldActie",GereedActie = "gereedActie";
    private int[] aantalBiljetten = {50, 50, 50};
    private int[] biljetWaardes = {50, 20, 10};

    public Color footerHeaderKleur = Color.BLACK;
    public Color achtergrondKleur = Color.WHITE;
    public Color knopKleur = Color.GRAY;
    public Color achterAchtergrondKleur = Color.WHITE;

    public Variables() {

    }

    public Variables(int[] biljetwaardes, int[] aantalBiljetten) {
        if(biljetwaardes.length != aantalBiljetten.length) {
            System.out.println("De waardes van de biljetten komen niet overeen met de aantallen");
            System.exit(0);
        } else {
            this.biljetWaardes = biljetwaardes;
            this.aantalBiljetten = aantalBiljetten;
        }
    }

    public int[] getAantalBiljetten() {
        return aantalBiljetten;
    }

    public int[] getBiljetWaardes() {
        return biljetWaardes;
    }

    public int getMaxAantalGeld() {
        int output = 0;
        for(int i = 0; i < aantalBiljetten.length; i++) {
            output += aantalBiljetten[i] * biljetWaardes[i];
        }
        return output;
    }

    public void gebruikBiljetten(int[] aantalBiljetten) {
        for(int i = 0; i < this.aantalBiljetten.length; i++) {
            this.aantalBiljetten[i] -= aantalBiljetten[i];
            if(this.aantalBiljetten[i] < 0) {
                this.aantalBiljetten[i] = 0;
            }
        }
    }

}
