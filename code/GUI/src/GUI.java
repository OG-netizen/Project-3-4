import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
    na pas inserten -- meteen inloggen
    na inloggen -- saldo, pinScherm, snelkeuze 70eu, 

    altijd veranderTaalScherm kunnen veranderen en kunnen afbreken


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
    private ImageIcon plaatsKaartIcon = new ImageIcon("code/GUI/Images/insert card.png");
    private ImageIcon logoIcon = new ImageIcon("code/GUI/Images/logo_white_trans.png");

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

        hoofdscherm();

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
        tekst4.setForeground(Color.red);
        codeTekst.setFont(new Font("Calibri", Font.BOLD, 40));
        tekstPaneel.add(tekst1);
        tekstPaneel.add(tekst2);
        tekstPaneel.add(tekst3);
        tekstPaneel.add(tekst4);
        tekstPaneel.add(codeTekst);

    }

    private void hoofdscherm() {
        laatsteSchermen.clear();
        plaatsKaart.setVisible(true);
        tekst1.setVisible(false);
        tekst2.setVisible(false);
        tekst3.setVisible(false);
        tekst4.setVisible(false);
        codeTekst.setVisible(false);
        if(huidigeTaal == Variables.Engels) {
            knoppen[0].setText("draw money");
            knoppen[3].setText("change language");
            knoppen[7].setText("exit");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[0].setText("pinScherm");
            knoppen[3].setText("verander taal");
            knoppen[7].setText("afsluiten");
        }
        knoppen[0].setActionCommand(Variables.PinActie);
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[7].setActionCommand(Variables.AfsluitActie);;

        knoppen[0].setVisible(true);
        knoppen[1].setVisible(false);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(false);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
    }

    private void inlogScherm() {
        laatsteSchermen.add(Variables.Inlogscherm);
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }

        plaatsKaart.setVisible(false);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Variables.Engels) {
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
            tekst3.setText("insert pin");
            knoppen[3].setText("change language");
            knoppen[7].setText("cancel");
        } else if(huidigeTaal == Variables.Nederlands) {
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
            tekst3.setText("voer uw pincode in");
            knoppen[3].setText("verander taal");
            knoppen[7].setText("afbreken");
        }
        knoppen[3].setVisible(true);
        knoppen[7].setVisible(true);
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[7].setActionCommand(Variables.TerugActie);

        if(SQLconnectie.isBlocked(huidigeUid)) {
            tekst4.setText("deze kaart is geblokkeerd");
            tekst4.setVisible(true);
        }
        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst3.setVisible(true);
    }

    private void pinScherm() {
        laatsteSchermen.add(Variables.Pinscherm);
        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst3.setVisible(false);
        tekst4.setVisible(true);
        if(huidigeTaal == Variables.Engels){
            knoppen[1].setText("pin 10");
            knoppen[3].setText("change language");
            knoppen[4].setText("pin 20");
            knoppen[5].setText("enter amount");
            knoppen[7].setText("cancel");
        } else if(huidigeTaal == Variables.Nederlands){
            knoppen[1].setText("pin 10");
            knoppen[3].setText("verander taal");
            knoppen[4].setText("pin 20");
            knoppen[5].setText("bedrag invoeren");
            knoppen[7].setText("afbreken");
        }
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[5].setActionCommand(Variables.BedragInvoerActie);
        knoppen[7].setActionCommand(Variables.TerugActie);

        knoppen[0].setVisible(false);
        knoppen[1].setVisible(true);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(true);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
    }

    private void bedragInvulScherm() {
        laatsteSchermen.add(Variables.InvulScherm);
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        knoppen[5].setText("gereed");
        knoppen[3].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);
        knoppen[3].setActionCommand(Variables.TaalActie);
        knoppen[5].setActionCommand(Variables.PinGeldActie);
        knoppen[7].setActionCommand(Variables.TerugActie);
        tekst4.setForeground(Color.black);
        tekst4.setText("voer uw gewenste bedrag in");
        tekst4.setVisible(true);
    }

    private void veranderTaalScherm() {
        laatsteSchermen.add(Variables.Taalscherm);
        tekst1.setVisible(false);
        tekst2.setVisible(false);
        tekst3.setVisible(false);
        tekst4.setVisible(false);

        knoppen[1].setText("Nederlands");
        knoppen[5].setText("English");
        knoppen[1].setActionCommand(Variables.TaalNederlandsActie);
        knoppen[5].setActionCommand(Variables.TaalEngelsActie);

        if(huidigeTaal == Variables.Engels) {
            knoppen[7].setText("back");
        } else if(huidigeTaal == Variables.Nederlands) {
            knoppen[7].setText("terug");
        }
        knoppen[7].setActionCommand(Variables.TerugActie);

        knoppen[0].setVisible(false);
        knoppen[1].setVisible(true);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(false);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(true);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
    }

    private void kaartVerwijderdScherm() {
        laatsteSchermen.add(Variables.KaartVerwijderdScherm);
        tekst1.setVisible(false);
        tekst2.setVisible(false);
        tekst3.setVisible(false);
        tekst4.setVisible(false);

        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        } finally {
            hoofdscherm();
        }
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
                        tekst4.setText("het aantal dat u heeft ingevoerd is te veel");
                        tekst4.setForeground(Color.red);
                        tekst4.setVisible(true);
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
                    tekst4.setText("succesvol ingelogd");
                } else {
                    tekst4.setForeground(Color.red);
                    int resterendePogingen = 3 - SQLconnectie.aantalPogingen(huidigeUid) - 1;
                    SQLconnectie.setAantalPogingen(huidigeUid, 3 - resterendePogingen);
                    System.out.println("Nog " + resterendePogingen + " pogingen");
                    if(resterendePogingen == 2) {
                        tekst4.setText("Nog 2 resterende pogingen"); 
                    }else if(resterendePogingen == 1) {
                        tekst4.setText("nog 1 resterende poging");
                    }else if(resterendePogingen <= 0) {
                        SQLconnectie.blokkeerKaart(huidigeUid, true);
                        tekst4.setText("geen pogingen meer over. Uw kaart is geblokkeerd");
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
        codeTekst.setVisible(false);
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
        int geldAantal = (int) Math.round(Float.parseFloat(codeTekst.getText()) / 10) * 10;
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
        variables.gebruikBiljetten(gebruikAantal);
        SerieleConnectie.werpGeldUit(gebruikAantal);
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
