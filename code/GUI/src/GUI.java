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
    Variables variables = new Variables();
    private String huidigeTaal = Variables.Nederlands, huidigeUid;
    private ArrayList<String> laatsteSchermen = new ArrayList<String>();
    private Serial SerieleConnectie;
    private SQLConnection SQLconnectie;


    private int breedte = 600, hoogte = 800;
    private JButton[] knoppen = new JButton[8];
    private int logoHoogte = 125, knopBreedte = 300, onderkantHoogte = 100;

    private JPanel venster = new JPanel();
    private JPanel vensterLinks = new JPanel();
    private JPanel vensterRechts = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel tekstPaneel = new JPanel();
    private JPanel onderkant = new JPanel();
    private JLabel plaatsKaart = new JLabel();
    private JLabel logoLabel = new JLabel();
    private ImageIcon plaatsKaartIcon = new ImageIcon("Images/insert card.png");
    private ImageIcon logoIcon = new ImageIcon("Images/logo_white_trans.png");

    private ArrayList<String> code = new ArrayList<String>();

    JLabel tekst1 = new JLabel("", SwingConstants.CENTER);
    JLabel tekst2 = new JLabel("", SwingConstants.CENTER);
    JLabel tekst3 = new JLabel("", SwingConstants.CENTER);
    JLabel tekst4 = new JLabel("", SwingConstants.CENTER);
    JLabel codeTekst = new JLabel("", SwingConstants.CENTER);

    public void startGUI(Serial s, SQLConnection c) {
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

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        getContentPane().setLayout(new BorderLayout(50, 50));

        venster.setFont(new Font("Calibri", Font.PLAIN, 50));
        venster.setAlignmentX(CENTER_ALIGNMENT);
        venster.setBackground(variables.achtergrondKleur);

        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));
        vensterLinks.setBackground(variables.achtergrondKleur);

        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));
        vensterRechts.setBackground(variables.achtergrondKleur);

        logo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, logoHoogte));
        logo.setBackground(variables.footerHeaderKleur);

        tekstPaneel.setLayout(new GridLayout(10, 1, venster.getWidth(), venster.getHeight()));
        tekstPaneel.setBackground(variables.achtergrondKleur);

        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte));
        onderkant.setBackground(variables.footerHeaderKleur);

        plaatsKaart.setIcon(plaatsKaartIcon);
        
        logoLabel.setIcon(logoIcon);

        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i] = new JButton(String.valueOf(i));
            knoppen[i].setFont(new Font("Calibri", Font.BOLD, 20));
            knoppen[i].setBackground(variables.knopKleur);
            if(i < 4) {
                vensterLinks.add(knoppen[i]);
            } else {
                vensterRechts.add(knoppen[i]);
            }
            knoppen[i].addActionListener(this);
        }

        venster.add(plaatsKaart);
        venster.add(tekstPaneel);
        logo.add(logoLabel);

        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
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

    public void hoofdscherm() {
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
    }

    private void inlogScherm() {
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
    }

    private void pinScherm() {
        resetKnopEnTekst();
        laatsteSchermen.add(Variables.Pinscherm);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Variables.Engels){
            knoppen[1].setText("Pin 10");
            knoppen[3].setText("Change language");
            knoppen[4].setText("Pin 70");
            knoppen[5].setText("Enter amount");
            knoppen[7].setText("Cancel");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
        } else if(huidigeTaal == Variables.Nederlands){
            knoppen[1].setText("Pin 10");
            knoppen[3].setText("Verander taal");
            knoppen[4].setText("Pin 70");
            knoppen[5].setText("Bedrag invoeren");
            knoppen[7].setText("Afbreken");
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
        }
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[5].setActionCommand(Variables.BedragInvoerActie);
        knoppen[7].setActionCommand(Variables.TerugActie);

        knoppen[1].setVisible(true);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);

        tekst1.setVisible(true);
        tekst2.setVisible(true);
    }

    private void bedragInvulScherm() {
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
    }

    private void veranderTaalScherm() {
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
    }

    private void kaartVerwijderdScherm() {
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
    }

    public void geldUitwerpScherm() {
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
    }

    public void printBonScherm() {
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
    }

    public void ontvangenToets(String data) {
        String huidigeScherm = Variables.Hoofdscherm;
        if(laatsteSchermen.size() > 0) {
            huidigeScherm = laatsteSchermen.get(laatsteSchermen.size() - 1);
        }

        if(huidigeUid == null || SQLconnectie.isBlocked(huidigeUid)) {
            return;
        }
        if(huidigeScherm == Variables.InvulScherm) {
            if(!data.contains("A") && !data.contains("B") && !data.contains("C") && !data.contains("D")) {
                if(!(data.contains("0") && codeTekst.getText().length() == 0)) {
                    String laatsteAantal = codeTekst.getText();
                    codeTekst.setText(codeTekst.getText() + data);
                    int geldAantal = Integer.parseInt(codeTekst.getText());
                    if(geldAantal > variables.getMaxAantalGeld() || geldAantal > Integer.parseInt(SQLconnectie.getDetails(huidigeUid)[0])) {
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
                }
            } else if(data.contains("C")) {
                codeTekst.setText("");
            }
        } else if(huidigeScherm == Variables.Inlogscherm) {
            String gemaskerdeCode = "";
            if(data.contains("D")) {
                if(checkCode()) {
                    SQLconnectie.setAantalPogingen(huidigeUid, 0);
                    pinScherm();
                    code.clear();
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
                    System.out.println("Nog " + resterendePogingen + " pogingen");
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
        }
        codeTekst.setVisible(true);
    }

    private boolean checkCode() {
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

    public void kaartVerwijderd() {
        code.clear();
        huidigeUid = null;
        kaartVerwijderdScherm();
    }

    public void uidInGebruik(String uid) {
        huidigeUid = uid;
        inlogScherm();
    }

    private void laatsteScherm() {
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

    private void pinGeld() {
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
        try {
            geldAantal = (int) Math.round(Float.parseFloat(geldAantalString) / 10) * 10;
        } catch (NumberFormatException e) {
            System.out.println("Er ging iets mis met het verwerken van het aantal" + e);
        }
        System.out.println("bedrag te pinnen: " + geldAantal);
        for(int i = 0; i < waardes.length; i++) {
            gebruikAantal[i] = geldAantal / waardes[i];
            geldAantal %= waardes[i];
            if(gebruikAantal[i] > aantal[i]) {
                geldAantal += (gebruikAantal[i] - aantal[i]) * waardes[i];
                gebruikAantal[i] = aantal[i];
            }
            System.out.println(waardes[i] + "\t" + gebruikAantal[i]);
        }
        geldAantal = (int) Math.round(Float.parseFloat(geldAantalString) / 10) * 10;
        variables.gebruikBiljetten(gebruikAantal);

        SerieleConnectie.werpGeldUit(gebruikAantal);
        SQLconnectie.setSaldo(huidigeUid, Integer.parseInt(SQLconnectie.getDetails(huidigeUid)[0]) - geldAantal);
        geldUitwerpScherm();
    }

    private void resetKnopEnTekst() {
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setText(null);
            knoppen[i].setVisible(false);
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

    public void actionPerformed(ActionEvent e) {
        String uitgevoerdeActie = e.getActionCommand();

        switch (uitgevoerdeActie) {
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
            default:
                System.out.println("weet niet wat te doen! " + uitgevoerdeActie);
                break;
        }

        System.out.println("knop: " + uitgevoerdeActie);
    }
}
