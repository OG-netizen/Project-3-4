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
    private final String Nederlands = "Nederlands", Engels = "Engels";
    private final String Hoofdscherm = "hoofd", Inlogscherm = "Inlog", Pinscherm = "Pin", Taalscherm = "Taal", KaartVerwijderdScherm = "kaartVerwijderd", InvulScherm = "invulScherm";
    private final String hoofdschermActie = "hoofdschermActie", InlogActie = "inlogActie", PinActie = "pinActie", TaalActie = "taalActie", KaartVerwijderdActie = "kaartVerwijderdActie", TerugActie = "terugActie", AfsluitActie = "afsluitActie", TaalEngelsActie = "taalEngelsActie", TaalNederlandsActie = "taalNederlandsActie", BedragInvoerActie = "bedragInvoerActie";
    private String huidigeTaal = Nederlands, huidigeUid;
    private ArrayList<String> laatsteSchermen = new ArrayList<String>();
    private Serial SerieleConnectie;
    private SQLConnection SQLconnectie;


    private int breedte = 600, hoogte = 800, aantalPogingen = 0, resterendePogingen = 2;
    private JButton[] knoppen;
    private int logoHoogte = 125, knopBreedte = 300, onderkantHoogte = 100;
    //final Font font = new Font("Arial", Font.BOLD, 20);

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

        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        logo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, logoHoogte));
        logo.setBackground(Color.black);

        tekstPaneel.setLayout(new GridLayout(10, 1, venster.getWidth(), venster.getHeight()));
        venster.add(tekstPaneel);

        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte));
        onderkant.setBackground(Color.black);

        plaatsKaart.setIcon(plaatsKaartIcon);
        
        logoLabel.setIcon(logoIcon);

        for(int i = 0; i < 8; i++) {
            knoppen[i] = new JButton(String.valueOf(i));
            knoppen[i].setFont(new Font("Calibri", Font.BOLD, 20));
            knoppen[i].setBackground(Color.LIGHT_GRAY);
            if(i < 4) {
                vensterLinks.add(knoppen[i]);
            } else {
                vensterRechts.add(knoppen[i]);
            }
            knoppen[i].addActionListener(this);
        }

        hoofdscherm();

        venster.add(plaatsKaart);
        logo.add(logoLabel);

        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
        getContentPane().add(venster, BorderLayout.CENTER);
        getContentPane().add(logo, BorderLayout.NORTH);
        getContentPane().add(onderkant, BorderLayout.SOUTH);

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
        if(huidigeTaal == Engels) {
            knoppen[0].setText("draw money");
            knoppen[3].setText("change language");
            knoppen[7].setText("exit");
        } else if(huidigeTaal == Nederlands) {
            knoppen[0].setText("pinScherm");
            knoppen[3].setText("verander taal");
            knoppen[7].setText("afsluiten");
        }
        knoppen[0].setActionCommand(PinActie);
        knoppen[3].setActionCommand(TaalActie);
        knoppen[7].setActionCommand(AfsluitActie);;

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
        laatsteSchermen.add(Inlogscherm);
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        knoppen[3].setVisible(true);
        knoppen[7].setVisible(true);
        knoppen[3].setActionCommand(TaalActie);
        knoppen[7].setActionCommand(TerugActie);

        plaatsKaart.setVisible(false);
        String[] details = SQLconnectie.getDetails(huidigeUid);
        if(huidigeTaal == Engels) {
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Balance: " + details[0]);
            tekst3.setText("insert pin");
        } else if(huidigeTaal == Nederlands) {
            tekst1.setText("Iban: " + huidigeUid);
            tekst2.setText("Saldo: " + details[0]);
            tekst3.setText("voer uw pincode in");
        }
        if(SQLconnectie.isBlocked(huidigeUid)) {
            tekst4.setText("deze kaart is geblokkeerd");
            tekst4.setVisible(true);
        }
        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst3.setVisible(true);
    }

    private void pinScherm() {
        laatsteSchermen.add(Pinscherm);
        tekst1.setVisible(true);
        tekst2.setVisible(true);
        tekst3.setVisible(false);
        tekst4.setVisible(true);
        if(huidigeTaal == Engels){
            knoppen[1].setText("pin 10");
            knoppen[3].setText("change language");
            knoppen[4].setText("pin 20");
            knoppen[5].setText("enter amount");
            knoppen[7].setText("cancel");
        } else if(huidigeTaal == Nederlands){
            knoppen[1].setText("pin 10");
            knoppen[3].setText("verander taal");
            knoppen[4].setText("pin 20");
            knoppen[5].setText("bedrag invoeren");
            knoppen[7].setText("afbreken");
        }
        knoppen[3].setActionCommand(TaalActie);
        knoppen[5].setActionCommand(BedragInvoerActie);
        knoppen[7].setActionCommand(TerugActie);

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
        laatsteSchermen.add(InvulScherm);
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        knoppen[5].setText("gereed");
        knoppen[3].setVisible(true);
        knoppen[5].setVisible(true);
        knoppen[7].setVisible(true);
        knoppen[3].setActionCommand(TaalActie);
        knoppen[7].setActionCommand(TerugActie);
        tekst4.setForeground(Color.black);
        tekst4.setText("voer uw gewenste bedrag in");
        tekst4.setVisible(true);
    }

    private void veranderTaalScherm() {
        laatsteSchermen.add(Taalscherm);
        tekst1.setVisible(false);
        tekst2.setVisible(false);
        tekst3.setVisible(false);
        tekst4.setVisible(false);

        knoppen[1].setText("Nederlands");
        knoppen[5].setText("English");
        knoppen[1].setActionCommand(TaalNederlandsActie);
        knoppen[5].setActionCommand(TaalEngelsActie);

        if(huidigeTaal == Engels) {
            knoppen[7].setText("back");
        } else if(huidigeTaal == Nederlands) {
            knoppen[7].setText("terug");
        }
        knoppen[7].setActionCommand(TerugActie);

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
        laatsteSchermen.add(KaartVerwijderdScherm);
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
        resterendePogingen = 2 - aantalPogingen;
        String huidigeScherm = Hoofdscherm;
        if(laatsteSchermen.size() > 0) {
            huidigeScherm = laatsteSchermen.get(laatsteSchermen.size() - 1);
        }

        boolean invulScherm = huidigeScherm == Inlogscherm;
        if(huidigeUid == null || SQLconnectie.isBlocked(huidigeUid) || !invulScherm) {
            return;
        }
        if(huidigeScherm == InvulScherm) {
            if(!data.contains("A") && !data.contains("B") && !data.contains("C") && !data.contains("D")) {
                if(!(data.contains("0") && codeTekst.getText().length() < 1)) {
                    codeTekst.setText(codeTekst.getText() + data);
                }
            } else if(data.contains("C")) {
                codeTekst.setText("");
            }
        }

    	String gemaskerdeCode = "";
        if(data.contains("D")) {
            if(checkCode()) {
                SQLconnectie.setAantalPogingen(huidigeUid, 0);
                pinScherm();
                code.clear();
                tekst4.setForeground(Color.green);
                tekst4.setText("succesvol ingelogd");
            } else {
                plaatsKaart.setVisible(false);
                tekst4.setForeground(Color.red);
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

    private boolean checkCode() {
        String checkCode = SQLconnectie.getCode(huidigeUid);
        String newCode = String.join("", code);
        if(newCode.equals(checkCode)) {
            System.out.println("code goedgekeurd");
            aantalPogingen = 0;
            return true;
        } else {
            System.out.println("code foutgekeurd");
            aantalPogingen++;
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
        String laatsteScherm;
        if(laatsteSchermen.size() > 1) {
            laatsteScherm = laatsteSchermen.get(laatsteSchermen.size() - 2);
            if(laatsteSchermen.get(laatsteSchermen.size() - 1) == Taalscherm) {
                laatsteSchermen.remove(laatsteSchermen.size() - 1);
            }
            laatsteSchermen.remove(laatsteSchermen.size() - 1);
        } else {
            laatsteScherm = Hoofdscherm;
        }
        switch(laatsteScherm) {
            case Hoofdscherm:
                hoofdscherm();
                break;
            case Inlogscherm:
                hoofdscherm();
                break;
            case Pinscherm:
                pinScherm();
                break;
            case Taalscherm:
                veranderTaalScherm();
                break;
            default:
                kaartVerwijderdScherm();
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        String uitgevoerdeActie = e.getActionCommand();

        switch (uitgevoerdeActie) {
            case AfsluitActie:
                System.exit(0);
                break;
            case PinActie:
                pinScherm();
                break;
            case TaalActie:
                veranderTaalScherm();
                break;
            case TaalEngelsActie:
                huidigeTaal = Engels;
                laatsteScherm();
                break;
            case TaalNederlandsActie:
                huidigeTaal = Nederlands;
                laatsteScherm();
                break;
            case BedragInvoerActie:
                bedragInvulScherm();
                break;
            case TerugActie:
                laatsteScherm();
                break;
            default:
                System.out.println("weet niet wat te doen! " + uitgevoerdeActie);
                break;
        }

        System.out.println("knop: " + uitgevoerdeActie);
    }
}
