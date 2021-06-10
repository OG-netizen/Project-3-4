import java.awt.Color;

public class Variables { // variabel klass om alle waardevol variabel te opslaan voor de gui 
    public static final String  // taal variabels
        Nederlands = "Nederlands", 
        Engels = "Engels";
    public static final String  // variabels van schermen
        Hoofdscherm = "hoofd", 
        Inlogscherm = "Inlog", 
        Pinscherm = "Pin", 
        Taalscherm = "Taal", 
        KaartVerwijderdScherm = "kaartVerwijderd", 
        InvulScherm = "invulScherm";
    public static final String // variabels van actie van de knoppen
        hoofdschermActie = "hoofdschermActie", 
        InlogActie = "inlogActie", 
        PinActie = "pinActie", 
        TaalActie = "taalActie", 
        KaartVerwijderdActie = "kaartVerwijderdActie", 
        TerugActie = "terugActie", 
        AfsluitActie = "afsluitActie", 
        TaalEngelsActie = "taalEngelsActie", 
        TaalNederlandsActie = "taalNederlandsActie", 
        BedragInvoerActie = "bedragInvoerActie", 
        PinGeldActie = "pinGeldActie", 
        GereedActie = "gereedActie", 
        GeenActie = "geenActie", 
        SnelPin10Actie = "pin10", 
        SnelPin70Actie = "pin70";
    private int[] aantalBiljetten = {5, 4, 5}; // array van aantalbiljetten
    private int[] biljetWaardes = {50, 20, 10}; // array van biljetwaardes, er zijn drie biljetwaardes


    //kleur van verschillende aspecten van de GUI
    public Color footerHeaderKleur = Color.BLACK; 
    public Color achtergrondKleur = Color.WHITE; 
    public Color knopKleur = Color.GRAY;
    public Color achterAchtergrondKleur = Color.WHITE;

    public Variables() {

    }

    public Variables(int[] biljetwaardes, int[] aantalBiljetten) { // functie om te checken of waarde van de biljetten overeenkomen met de aantal biljetten
        if(biljetwaardes.length != aantalBiljetten.length) {
            System.out.println("De waardes van de biljetten komen niet overeen met de aantallen");
            System.exit(0);
        } else {
            this.biljetWaardes = biljetwaardes;
            this.aantalBiljetten = aantalBiljetten;
        }
    }

    public int[] getAantalBiljetten() { // return de aantal biljetten 
        return aantalBiljetten;
    }

    public int[] getBiljetWaardes() { // return de biljet waardes
        return biljetWaardes;
    }

    public int getMaxAantalGeld() { // return de maximaal geld die de atm kan werpen 
        int output = 0;
        for(int i = 0; i < aantalBiljetten.length; i++) {
            output += aantalBiljetten[i] * biljetWaardes[i];
        }
        return output;
    }

    public void gebruikBiljetten(int[] aantalBiljetten) { //functie om welke biljetten te gebruiken 
        for(int i = 0; i < this.aantalBiljetten.length; i++) {
            this.aantalBiljetten[i] -= aantalBiljetten[i];
            if(this.aantalBiljetten[i] < 0) {
                this.aantalBiljetten[i] = 0;
            }
        }
    }

}
