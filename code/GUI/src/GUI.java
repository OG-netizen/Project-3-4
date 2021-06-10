import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*

    TO - DO
    - doneerscherm en aantal doorgeven aan seriele poort
    - saldo aanpassen na transactie
    - checken op fouten en bugs

*/

public class GUI extends JFrame implements ActionListener {

    Variables variables = new Variables(); // instance van de klass variables
    private String huidigeTaal = Variables.Nederlands, huidigeUid; // variabel om de huidige taal te opslagen
    private ArrayList<String> laatsteSchermen = new ArrayList<String>();  // een arraylist om de laatse scherm te onthouden
    private ArrayList<String> code = new ArrayList<String>(); // arraylist om van codes
    private Serial SerieleConnectie; // variabel om de serial port te lezen 
    private SQLConnection SQLconnectie; // variabel om connectie te kunnen maken met datase
   
    private int breedte = 600, hoogte = 800; // scherm grootte
    private JButton[] knoppen = new JButton[8]; // 8 knoppen wordt geïnitialiseerd
    private int logoHoogte = 125, knopBreedte = 300, onderkantHoogte = 100; // hoogte van onderkant,logo en breedte van de knop wordt bepaald

    private JPanel venster = new JPanel(); // midden venster
    private JPanel vensterLinks = new JPanel(); // recht venster
    private JPanel vensterRechts = new JPanel(); // links venster
    private JPanel logo = new JPanel(); // logo variabel 
    private JPanel tekstPaneel = new JPanel(); // tekstpaneel variabel 
    private JPanel onderkant = new JPanel();  // onderkant variabel
    private JLabel plaatsKaart = new JLabel(); // plaatskaart variabel 
    private JLabel logoLabel = new JLabel(); // logoLabel variabel
    private ImageIcon plaatsKaartIcon = new ImageIcon("Images/insert card.png"); // foto logo 
    private ImageIcon logoIcon = new ImageIcon("Images/logo_white_trans.png."); // foto logo 

    JLabel tekst1 = new JLabel("", SwingConstants.CENTER); // variabel van tekst gemaakt in het midden van gui
    JLabel tekst2 = new JLabel("", SwingConstants.CENTER);
    JLabel tekst3 = new JLabel("", SwingConstants.CENTER);
    JLabel tekst4 = new JLabel("", SwingConstants.CENTER);
    JLabel codeTekst = new JLabel("", SwingConstants.CENTER); // variabel tekst voor de code die in het midden van de GUI schijnt

    public void startGUI(Serial s, SQLConnection c) { // method de gui te starten 
        SerieleConnectie = s; 
        SQLconnectie = c;
        knoppen = new JButton[8];
        setSize(breedte, hoogte);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("GUI");
        createGUI();
        setVisible(true);
    }

    private void createGUI() { //method om de layout van de gui te maken 
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        getContentPane().setLayout(new BorderLayout(50, 50));

        venster.setFont(new Font("Calibri", Font.PLAIN, 50)); // de font wordt bepaald
        venster.setAlignmentX(CENTER_ALIGNMENT); // de uitlijning van de venster            
        venster.setBackground(variables.achtergrondKleur); // kleur van de venster, in de variables klass kun je zien welke kleur dit is

        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));
        vensterLinks.setBackground(variables.achtergrondKleur);

        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));
        vensterRechts.setBackground(variables.achtergrondKleur);

        logo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, logoHoogte)); // size van de logo wordt bepaald
        logo.setBackground(variables.footerHeaderKleur); // achtergond kleur van de logo 

        tekstPaneel.setLayout(new GridLayout(10, 1, venster.getWidth(), venster.getHeight())); // tekst paneel layout wordt bepaa,d
        tekstPaneel.setBackground(variables.achtergrondKleur); // kleur van de tekspaneel achtergrond

        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte)); 
        onderkant.setBackground(variables.footerHeaderKleur);

        plaatsKaart.setIcon(plaatsKaartIcon); // icon foto 
        
        logoLabel.setIcon(logoIcon); 

        for(int i = 0; i < knoppen.length; i++) { // for loop om 8 knoppen en hun kenmerken te maken 
            knoppen[i] = new JButton(String.valueOf(i));
            knoppen[i].setFont(new Font("Calibri", Font.BOLD, 20));
            knoppen[i].setBackground(variables.knopKleur);
            if(i < 4) {
                vensterLinks.add(knoppen[i]); // 4 knoppen naar de links en 4 knoppen naar recht
            } else {
                vensterRechts.add(knoppen[i]);
            }
            knoppen[i].addActionListener(this);
        }

        venster.add(plaatsKaart); 
        venster.add(tekstPaneel);
        logo.add(logoLabel);

        getContentPane().add(vensterLinks, BorderLayout.WEST); // venster links gaat naar west
        getContentPane().add(vensterRechts, BorderLayout.EAST);// venster recht gaat naar east
        getContentPane().add(venster, BorderLayout.CENTER); 
        getContentPane().add(logo, BorderLayout.NORTH);
        getContentPane().add(onderkant, BorderLayout.SOUTH);
        getContentPane().setBackground(variables.achterAchtergrondKleur);

        tekst1.setFont(new Font("Calibri", Font.PLAIN, 40));
        tekst2.setFont(new Font("Calibri", Font.PLAIN, 40));
        tekst3.setFont(new Font("Calibri", Font.PLAIN, 40));
        tekst4.setFont(new Font("Calibri", Font.BOLD, 35));
        codeTekst.setFont(new Font("Calibri", Font.BOLD, 40));
        tekstPaneel.add(tekst1);
        tekstPaneel.add(tekst2);
        tekstPaneel.add(tekst3);
        tekstPaneel.add(tekst4);
        tekstPaneel.add(codeTekst);

        hoofdscherm();
    }

    public void hoofdscherm() { // hoofdscherm functie bevat de layout van de hoofdscherm
        laatsteSchermen.clear();
        resetKnopEnTekst();
        plaatsKaart.setVisible(true);
        if(huidigeTaal == Variables.Engels) {
            knoppen[3].setText("Change language");
            knoppen[7].setText("Exit");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[3].setText("Verander taal");
            knoppen[7].setText("Afsluiten");
        }
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[7].setActionCommand(Variables.AfsluitActie);;
        knoppen[3].setVisible(true);
        knoppen[7].setVisible(true);

        keypadOpKnoppen();
    }

    private void inlogScherm() { // inlog scherm functie bevat de layout van de inlog scherm
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.Inlogscherm);
        plaatsKaart.setVisible(false);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Variables.Engels) {
            knoppen[3].setText("Change language");
            knoppen[7].setText("Cancel");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
            tekst3.setText("insert pin");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[3].setText("Verander taal");
            knoppen[7].setText("Afbreken");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
            tekst3.setText("voer uw pincode in");
        }
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[7].setActionCommand(Variables.TerugActie);
        knoppen[3].setVisible(true);
        knoppen[7].setVisible(true);

        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst3.setVisible(true);
        if(SQLconnectie.isBlocked(huidigeUid)) {
            tekst4.setForeground(Color.red);
            tekst4.setVisible(true);
            if(huidigeTaal == Variables.Engels) {
                tekst4.setText("This card is blocked");
            } else if(huidigeTaal == Variables.Nederlands) {
                tekst4.setText("Deze kaart is geblokkeerd");
            }
        }

        keypadOpKnoppen();
    }

    private void pinScherm() { // pinscherm functie bevat de layout van de pinscherm
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.Pinscherm);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Variables.Engels){
            knoppen[0].setText("Pin 10");
            knoppen[3].setText("Change language");
            knoppen[4].setText("Pin 70");
            knoppen[5].setText("Enter amount");
            knoppen[7].setText("Cancel");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
        } else if(huidigeTaal == Variables.Nederlands){
            knoppen[0].setText("Pin 10");
            knoppen[3].setText("Verander taal");
            knoppen[4].setText("Pin 70");
            knoppen[5].setText("Bedrag invoeren");
            knoppen[7].setText("Afbreken");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
        }
        knoppen[0].setActionCommand(Variables.SnelPin10Actie);
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[4].setActionCommand(Variables.SnelPin70Actie);
        knoppen[5].setActionCommand(Variables.BedragInvoerActie);
        knoppen[7].setActionCommand(Variables.TerugActie);

        knoppen[0].setVisible(true);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);

        tekst1.setVisible(true);
        tekst2.setVisible(true);

        keypadOpKnoppen();
    }

    private void bedragInvulScherm() { // bedraginvulscherm fucntie bevat de layout van de bedraginvulscherm
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.InvulScherm);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Variables.Engels) {
            knoppen[3].setText("Change language");
            knoppen[5].setText("Done");
            knoppen[7].setText("Cancel");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
            tekst4.setText("Enter the desired amount");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[3].setText("Verander taal");
            knoppen[5].setText("Gereed");
            knoppen[7].setText("Afbreken");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
            tekst4.setText("Voer uw gewenste bedrag in");
        }
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[5].setActionCommand(Variables.PinGeldActie);
        knoppen[7].setActionCommand(Variables.TerugActie);
        knoppen[3].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);
        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst4.setForeground(Color.black);
        tekst4.setVisible(true);

        keypadOpKnoppen();
    }

    private void veranderTaalScherm() { // veranderTaalScherm fucntie bevat de layout van de veranderTaalScherm
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.Taalscherm);

        knoppen[1].setText("Nederlands");
        knoppen[5].setText("English");
        if(huidigeTaal == Variables.Engels) {
            knoppen[7].setText("Back");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[7].setText("Terug");
        }
        knoppen[1].setActionCommand(Variables.TaalNederlandsActie);
        knoppen[5].setActionCommand(Variables.TaalEngelsActie);
        knoppen[7].setActionCommand(Variables.TerugActie);

        knoppen[1].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);

        keypadOpKnoppen();
    }

    private void kaartVerwijderdScherm() { // veranderTaalScherm fucntie bevat de layout van de veranderTaalScherm
        if(laatsteSchermen.size() < 1) {
            if(huidigeTaal == Variables.Engels) {
                tekst1.setText("U are logged out");
            } else if(huidigeTaal == Variables.Nederlands) {
                tekst1.setText("U bent uitgelogd");
            }
            tekst1.setVisible(true);
            try {
                TimeUnit.SECONDS.sleep(3);
                tekst1.setText(null);
                tekst1.setVisible(false);
            } catch (InterruptedException e) {
                System.out.println("Er ging iets mis tijdens het wachten in het hoofdscherm: " + e);
            }
            return;
        }
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.KaartVerwijderdScherm);
        if(huidigeTaal == Variables.Engels) {
            tekst3.setText("U are logged out");
            tekst4.setText("U are being redirected to the main screen");
        } else if(huidigeTaal == Variables.Nederlands) {
            tekst3.setText("U bent uitgelogd");
            tekst4.setText("U wordt doorgestuurd naar het startscherm");
        }
        tekst3.setVisible(true);
        tekst4.setVisible(true);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("er ging iets mis tijdens het wachten op het hoofdscherm: "  + e);
        } finally {
            hoofdscherm();
        }

        keypadOpKnoppen();
    }

    public void geldUitwerpScherm() { // geldUitwerpScherm fucntie bevat de layout van de geldUitwerpScherm
        String codeTekstString = codeTekst.getText();
        resetKnopEnTekst();

        if(huidigeTaal == Variables.Engels) {
            tekst1.setText("Ur money is being dispensed");
            tekst4.setText("Remaining balance: " + SQLconnectie.getDetails(huidigeUid)[0]);
        } else if(huidigeTaal == Variables.Nederlands) {
            tekst1.setText("Uw geld wordt uitgeworpen");
            tekst4.setText("Overgebleven saldo: " + SQLconnectie.getDetails(huidigeUid)[0]);
        }
        codeTekst.setText(codeTekstString);
        tekst1.setVisible(true);
        tekst4.setVisible(true);

        keypadOpKnoppen();
    }

    public void printBonScherm() { // printBonScherm fucntie bevat de layout van de printBonScherm
        try {
            int geldAantal = (int) Math.round(Float.parseFloat(codeTekst.getText()) / 10) * 10;
            SerieleConnectie.printBon(geldAantal, 0);
        } catch (NumberFormatException e) {
            System.out.println("Er ging iets mis met het verwerken van het aantal: " + e);
        }
        resetKnopEnTekst();

        if(huidigeTaal == Variables.Engels) {
            tekst1.setText("Ur ticket is being dispensed");
            tekst4.setText("Remaining balance: " + SQLconnectie.getDetails(huidigeUid)[0]);
        } else if(huidigeTaal == Variables.Nederlands) {
            tekst1.setText("Uw bon wordt geprint");
            tekst4.setText("Overgebleven saldo: " + SQLconnectie.getDetails(huidigeUid)[0]);
        }

        tekst1.setVisible(true);
        tekst4.setVisible(true);

        keypadOpKnoppen();
    }

    public void ontvangenToets(String data) { // functie om de data uit de keypad te onthouden 
        String huidigScherm = Variables.Hoofdscherm;
        if(laatsteSchermen.size() > 0) {
            huidigScherm = laatsteSchermen.get(laatsteSchermen.size() - 1);
        }

        if(huidigScherm == Variables.InvulScherm) {
            invulschermKeypad(data);
        } else if(huidigScherm == Variables.Inlogscherm) {
            inlogschermKeypad(data);
        } else {
            keypadKnoppen(data);
        }
    }

    public void invulschermKeypad(String data) { // functie voor het invullen en behandelen van de pincode
        if(!(data.contains("A") || // als je nummer heeft getoets dan wordt het gepakt anders niet
            data.contains("B") || 
            data.contains("C") || 
            data.contains("D") || 
            data.contains("*") || 
            data.contains("#"))
            ) {
            if(!(codeTekst.getText().length() == 0 && data.contains("0"))) {
                String laatsteAantal = codeTekst.getText();
                codeTekst.setText(laatsteAantal + data);
                int nieuwAantal = Integer.parseInt(codeTekst.getText());
                if( nieuwAantal > variables.getMaxAantalGeld() || 
                    nieuwAantal > Integer.parseInt(SQLconnectie.getDetails(huidigeUid)[0])
                    ) {
                    codeTekst.setText(laatsteAantal);
                    if(huidigeTaal == Variables.Engels) {
                        tekst4.setText("The entered amount is too much");
                    } else if(huidigeTaal == Variables.Nederlands) {
                        tekst4.setText("Het aantal dat u heeft ingevoerd is te veel");
                    }
                    tekst4.setForeground(Color.red);
                    tekst4.setVisible(true);
                } else {
                    tekst4.setForeground(Color.black);
                    tekst4.setVisible(false);
                }
            } else if(data.contains("C")) {
                codeTekst.setText("");
            }
        }
        codeTekst.setVisible(true);
        keypadKnoppen(data);
    }

    public void inlogschermKeypad(String data) { // functie voor het inlogschermkeypad
        String gemaskerdeCode = "";
        if(data.contains("D")) {
            if(checkCode()) {
                SQLconnectie.setAantalPogingen(huidigeUid, 0);
                code.clear();
                pinScherm();
                tekst4.setForeground(Color.green);
                if(huidigeTaal == Variables.Engels) {
                    tekst4.setText("Succesfully logged in");
                } else if(huidigeTaal == Variables.Nederlands) {
                    tekst4.setText("Succesvol ingelogd");
                }
            } else {
                tekst4.setForeground(Color.red);
                int resterendePogingen = 3 - SQLconnectie.aantalPogingen(huidigeUid) - 1;
                SQLconnectie.setAantalPogingen(huidigeUid, 3 - resterendePogingen);
                //System.out.println("Nog " + resterendePogingen + " pogingen");
                if(huidigeTaal == Variables.Engels) {
                    if(resterendePogingen == 2) {
                        tekst4.setText("2 Remaining attempts"); 
                    }else if(resterendePogingen == 1) {
                        tekst4.setText("1 Remaining attempt");
                    }else if(resterendePogingen <= 0) {
                        SQLconnectie.blokkeerKaart(huidigeUid, true);
                        tekst4.setText("No more attempts left. Ur card has been blocked");
                    }
                } else if(huidigeTaal == Variables.Nederlands) {
                    if(resterendePogingen == 2) {
                        tekst4.setText("Nog 2 resterende pogingen"); 
                    }else if(resterendePogingen == 1) {
                        tekst4.setText("Nog 1 resterende poging");
                    }else if(resterendePogingen <= 0) {
                        SQLconnectie.blokkeerKaart(huidigeUid, true);
                        tekst4.setText("Geen pogingen meer over. Uw kaart is geblokkeerd");
                    }
                }
                code.clear();
            }
            tekst4.setVisible(true);
        } else if(data.contains("C")) {
            code.clear();
        } else if(code.size() < 6) {
            code.add(data);
        }

        for(int i = 0; i < code.size(); i++) {
            gemaskerdeCode += "*";
        }
        codeTekst.setText(gemaskerdeCode);
        codeTekst.setVisible(true);
        keypadKnoppen(data);
    }

    public void keypadKnoppen(String data) { // functie voor het keypad knoppen 
        if(data.contains("1")){
            knopGedrukt(knoppen[0].getActionCommand());
        } else if(data.contains("4")){
            knopGedrukt(knoppen[1].getActionCommand());
        } else if(data.contains("7")){
            knopGedrukt(knoppen[2].getActionCommand());
        } else if(data.contains("*")){
            knopGedrukt(knoppen[3].getActionCommand());
        } else if(data.contains("A")){
            knopGedrukt(knoppen[4].getActionCommand());
        } else if(data.contains("B")){
            knopGedrukt(knoppen[5].getActionCommand());
        } else if(data.contains("C")){
            knopGedrukt(knoppen[6].getActionCommand());
        } else if(data.contains("#")){
            knopGedrukt(knoppen[7].getActionCommand());
        } else {
            System.out.println("ongeldige knop.");
        }

    }

    private boolean checkCode() { // functie om te bepalen of de code correct is
        String checkCode = SQLconnectie.getCode(huidigeUid);
        String newCode = String.join("", code);
        if(newCode.equals(checkCode)) {
            System.out.println("code goedgekeurd");
            return true;
        } else {
            System.out.println("code foutgekeurd");
            return false;
        }
    }

    public void kaartVerwijderd() { //funcite als het kaart is verwijderd
        code.clear();
        huidigeUid = null;
        kaartVerwijderdScherm();
    }

    public void uidInGebruik(String uid) { // funcite als het RFID een uid detecteert
        huidigeUid = uid;
        inlogScherm();
    }

    private void laatsteScherm() { // functie om te bepalen welke scherm is het laatst
        System.out.println(laatsteSchermen);
        String laatsteScherm;
        if(laatsteSchermen.size() > 1) {
            laatsteScherm = laatsteSchermen.get(laatsteSchermen.size() - 2);
            laatsteSchermen.remove(laatsteSchermen.size() - 1);
            laatsteSchermen.remove(laatsteSchermen.size() - 1);
        } else {
            laatsteScherm = Variables.Hoofdscherm;
        }
        switch(laatsteScherm) {
            case Variables.Hoofdscherm:
                hoofdscherm();
                break;
            case Variables.Inlogscherm:
                hoofdscherm();
                break;
            case Variables.Pinscherm:
                pinScherm();
                break;
            case Variables.Taalscherm:
                veranderTaalScherm();
                break;
            default:
                kaartVerwijderdScherm();
                break;
        }
    }

    private void pinGeld() { // funcite om geld te pinnen 
        int[] waardes = variables.getBiljetWaardes();
        int[] aantal = variables.getAantalBiljetten();
        int[] gebruikAantal = new int[waardes.length];
        String geldAantalString = codeTekst.getText();
        if(geldAantalString == null || geldAantalString == "") {
            try {
            tekst4.setForeground(Color.red);
            TimeUnit.MILLISECONDS.sleep(250);
            tekst4.setForeground(Color.black);
            TimeUnit.MILLISECONDS.sleep(250);
            tekst4.setForeground(Color.red);
            } catch(InterruptedException e) {
                System.out.println("er is iets misgegaan: " + e);
            }
            return;
        }
        int geldAantal = 0;
        int geldConstant = 0;
        try {
            geldAantal = (int) Math.round(Float.parseFloat(geldAantalString) / 10) * 10;
            geldConstant = geldAantal;
        } catch (NumberFormatException e) {
            System.out.println("Er ging iets mis met het verwerken van het aantal" + e);
        }
        System.out.println("bedrag te pinnen: " + geldAantal);
        for(int i = 0; i < waardes.length; i++) { // for loop te bepalen van groot naar klein de biljet waardes
            gebruikAantal[i] = geldAantal / waardes[i];
            geldAantal %= waardes[i];
            if(gebruikAantal[i] > aantal[i]) {
                geldAantal += (gebruikAantal[i] - aantal[i]) * waardes[i];
                gebruikAantal[i] = aantal[i];
            }
            System.out.println(waardes[i] + "\t" + gebruikAantal[i]);
        }
        variables.gebruikBiljetten(gebruikAantal);
        SerieleConnectie.werpGeldUit(gebruikAantal);
        System.out.println("bedrag gepint: " + geldConstant);
        SQLconnectie.setSaldo(huidigeUid, Integer.parseInt(SQLconnectie.getDetails(huidigeUid)[0]) - geldConstant);
        geldUitwerpScherm();
    }

    private void resetKnopEnTekst() { // functie voor het maken reset knop en tekst
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setText(null);
            knoppen[i].setVisible(false);
            knoppen[i].setActionCommand(Variables.GeenActie);
        }

        tekst1.setText(null);
        tekst1.setVisible(false);
        tekst1.setForeground(Color.black);
        tekst2.setText(null);
        tekst2.setVisible(false);
        tekst2.setForeground(Color.black);
        tekst3.setText(null);
        tekst3.setVisible(false);
        tekst3.setForeground(Color.black);
        tekst4.setText(null);
        tekst4.setVisible(false);
        tekst4.setForeground(Color.black);
        codeTekst.setText("");
        codeTekst.setVisible(false);
        codeTekst.setForeground(Color.black);
    }

    private void keypadOpKnoppen() { 
        String[] keypadKnoppen = {"1", "4", "7", "*", "A", "B", "C", "#"};
        for(int i = 0; i < knoppen.length; i++) {
            if(knoppen[i].isVisible()) {
                knoppen[i].setText(knoppen[i].getText() + " (" + keypadKnoppen[i] + ")");
            }
        }
    }

    public void actionPerformed(ActionEvent e) { //functie "action listener" het luister naar de keypad die wordt gedrukt
        String uitgevoerdeActie = e.getActionCommand();
        knopGedrukt(uitgevoerdeActie);
        // switch (uitgevoerdeActie) {
        //     case Variables.AfsluitActie:
        //         System.exit(0);
        //         break;
        //     case Variables.PinActie:
        //         pinScherm();
        //         break;
        //     case Variables.TaalActie:
        //         veranderTaalScherm();
        //         break;
        //     case Variables.TaalEngelsActie:
        //         huidigeTaal = Variables.Engels;
        //         laatsteScherm();
        //         break;
        //     case Variables.TaalNederlandsActie:
        //         huidigeTaal = Variables.Nederlands;
        //         laatsteScherm();
        //         break;
        //     case Variables.BedragInvoerActie:
        //         bedragInvulScherm();
        //         break;
        //     case Variables.TerugActie:
        //         laatsteScherm();
        //         break;
        //     case Variables.PinGeldActie:
        //         pinGeld();
        //         break;
        //     default:
        //         System.out.println("weet niet wat te doen! " + uitgevoerdeActie);
        //         break;
        //}

        System.out.println("knop: " + uitgevoerdeActie);
    }

    private void knopGedrukt(String action) { // functie om te bepalen wat te doen als keypad knop werd gedrukt
        switch (action) {
            case Variables.AfsluitActie:
                System.exit(0);
                break;
            case Variables.PinActie:
                pinScherm();
                break;
            case Variables.TaalActie:
                veranderTaalScherm();
                break;
            case Variables.TaalEngelsActie:
                huidigeTaal = Variables.Engels;
                laatsteScherm();
                break;
            case Variables.TaalNederlandsActie:
                huidigeTaal = Variables.Nederlands;
                laatsteScherm();
                break;
            case Variables.BedragInvoerActie:
                bedragInvulScherm();
                break;
            case Variables.TerugActie:
                laatsteScherm();
                break;
            case Variables.PinGeldActie:
                pinGeld();
                break;
            case Variables.SnelPin10Actie:
                codeTekst.setText("10");
                pinGeld();
                break;
            case Variables.SnelPin70Actie:
                codeTekst.setText("70");
                pinGeld();
                break;
            default:
                System.out.println("weet niet wat te doen! " + action);
                break;
        }
    }
}
