import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/*
    na pas inserten -- meteen inloggen
    na inloggen -- saldo, pinnen, snelkeuze 70eu, 

    altijd taal kunnen veranderen en kunnen afbreken


*/

public class GUI extends JFrame implements ActionListener {
    private final String Nederlands = "Nederlands", Engels = "Engels";
    private final String Hoofdscherm = "hoofd", Inlogscherm = "Inlog", Pinscherm = "Pin", Taalscherm = "Taal", KaartVerwijderdScherm = "kaartVerwijderd";
    private String currentLanguage = Nederlands, currentUid;
    private ArrayList<String> lastScreens = new ArrayList<String>();
    private Serial usedSerial;
    private SQLConnection SQLconnection;


    private int breedte = 600, hoogte = 800, aantalPogingen = 0, resterendePogingen = 2;
    private JButton[] knoppen;
    private int logoHoogte = 125, knopBreedte = 300, onderkantHoogte = 100;
    //final Font font = new Font("Arial", Font.BOLD, 20);

    private JPanel venster = new JPanel();
    private JPanel vensterLinks = new JPanel();
    private JPanel vensterRechts = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel textPanel = new JPanel();
    private JPanel onderkant = new JPanel();
    private JLabel insertCardLabel = new JLabel();
    private JLabel logoLabel = new JLabel();
    private ImageIcon insertCardIcon = new ImageIcon("code/GUI/Images/insert card.png");
    private ImageIcon logoIcon = new ImageIcon("code/GUI/Images/logo_white_trans.png");

    private ArrayList<String> code = new ArrayList<String>();

    JLabel text1 = new JLabel("", SwingConstants.CENTER);
    JLabel text2 = new JLabel("", SwingConstants.CENTER);
    JLabel text3 = new JLabel("", SwingConstants.CENTER);
    JLabel text4 = new JLabel("", SwingConstants.CENTER);
    JLabel codeText = new JLabel("", SwingConstants.CENTER);

    public void startGUI(Serial s, SQLConnection c) {
        usedSerial = s;
        SQLconnection = c;
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

        //venster.setBackground(Color.red);
        venster.setFont(new Font("Calibri", Font.PLAIN, 50));
        venster.setAlignmentX(CENTER_ALIGNMENT);

        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        logo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, logoHoogte));
        logo.setBackground(Color.black);

        textPanel.setLayout(new GridLayout(10, 1, venster.getWidth(), venster.getHeight()));
        venster.add(textPanel);

        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte));
        onderkant.setBackground(Color.black);

        insertCardLabel.setIcon(insertCardIcon);
        
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

        hoofdscherm(Nederlands);

        venster.add(insertCardLabel);
        logo.add(logoLabel);

        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
        getContentPane().add(venster, BorderLayout.CENTER);
        getContentPane().add(logo, BorderLayout.NORTH);
        getContentPane().add(onderkant, BorderLayout.SOUTH);

        text1.setFont(new Font("Calibri", Font.PLAIN, 40));
        text2.setFont(new Font("Calibri", Font.PLAIN, 40));
        text3.setFont(new Font("Calibri", Font.PLAIN, 40));
        text4.setFont(new Font("Calibri", Font.BOLD, 35));
        text4.setForeground(Color.red);
        codeText.setFont(new Font("Calibri", Font.BOLD, 40));
        textPanel.add(text1);
        textPanel.add(text2);
        textPanel.add(text3);
        textPanel.add(text4);
        textPanel.add(codeText);

    }

    public void hoofdscherm(String taal) {
        lastScreens.clear();
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        if(taal == Engels) {
            knoppen[0].setText("draw money");
            knoppen[3].setText("change language");
            knoppen[7].setText("close");
        } else if(taal == Nederlands) {
            knoppen[0].setText("pinnen");
            knoppen[3].setText("verander taal");
            knoppen[7].setText("afsluiten");
        }

        knoppen[0].setVisible(true);
        knoppen[1].setVisible(false);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(false);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
    }

    public void inlogScherm(String taal) {
        lastScreens.add(Inlogscherm);
        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        knoppen[3].setVisible(true);
        insertCardLabel.setVisible(false);
        String[] details = SQLconnection.getDetails(currentUid);
        if(taal == Engels) {
            text1.setText("Iban: " + currentUid);
            text2.setText("Balance: " + details[0]);
            text3.setText("insert pin");
        } else if(taal == Nederlands) {
            text1.setText("Iban: " + currentUid);
            text2.setText("Saldo: " + details[0]);
            text3.setText("voer uw pincode in");
        }
        if(SQLconnection.isBlocked(currentUid)) {
            text4.setText("deze kaart is geblokkeerd");
            text4.setVisible(true);
        }
        text1.setVisible(true);
        text2.setVisible(true);
        text3.setVisible(true);
    }

    public void pinnen(String taal) {
        lastScreens.add(Pinscherm);
        text3.setVisible(false);
        if(taal == Engels){

            knoppen[1].setText("pin 10");
            knoppen[5].setText("pin 20");
            knoppen[7].setText("cancel");
        } else if(taal == Nederlands){
    
            knoppen[1].setText("pin 10");
            knoppen[5].setText("pin 20");
            knoppen[7].setText("afbreken");
        }
            
            knoppen[0].setVisible(false);
            knoppen[1].setVisible(true);
            knoppen[2].setVisible(false);
            knoppen[3].setVisible(false);
            knoppen[4].setVisible(false);
            knoppen[5].setVisible(true);
            knoppen[6].setVisible(false);
            knoppen[7].setVisible(true);

    }

    public void taal(String taal) {
        lastScreens.add(Taalscherm);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);

        if(taal == Engels){

        knoppen[1].setText("Nederlands");
        knoppen[5].setText("English");
        knoppen[7].setText("cancel");

        knoppen[0].setVisible(false);
        knoppen[1].setVisible(true);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(false);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(true);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);

        } else if(taal == Nederlands){

        knoppen[1].setText("Nederlands");
        knoppen[5].setText("English");
        knoppen[7].setText("afbreken");

        knoppen[0].setVisible(false);
        knoppen[1].setVisible(true);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(false);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(true);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
        }
    }

    private void kaartVerwijderdScherm(String taal) {
        lastScreens.add(KaartVerwijderdScherm);
        text1.setVisible(false);
        text2.setVisible(false);
        text3.setVisible(false);
        text4.setVisible(false);

        for(int i = 0; i < knoppen.length; i++) {
            knoppen[i].setVisible(false);
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {

        } finally {
            hoofdscherm(taal);
        }
    }

    public void recievedKey(String data) {
        resterendePogingen = 2 - aantalPogingen;
        if(currentUid == null || SQLconnection.isBlocked(currentUid)) {
            return;
        }

    	String maskedCode = "";
        if(data.contains("D")) {
            if(checkCode()) {
                pinnen(Nederlands);
                code.clear();
                text4.setForeground(Color.green);
                text4.setText("succesvol ingelogd");
            } else {
                insertCardLabel.setVisible(false);
                text4.setForeground(Color.red);
                System.out.println("Nog " + resterendePogingen + " pogingen");
                if(resterendePogingen == 2) {
                    text4.setText("Nog 2 resterende pogingen"); 
                }else if(resterendePogingen == 1) {
                    text4.setText("nog 1 resterende poging");
                }else if(resterendePogingen <= 0) {
                    SQLconnection.blokkeerKaart(currentUid, true);
                    text4.setText("geen pogingen meer over. Uw kaart is geblokkeerd");
                }
                code.clear();
            }
            text4.setVisible(true);
        } else if(data.contains("C")) {
            code.clear();
        } else if(code.size() < 6) {
            code.add(data);
        }

        for(int i = 0; i < code.size(); i++) {
            maskedCode += "*";
        }
        codeText.setText(maskedCode);
    }

    private boolean checkCode() {
        String checkCode = SQLconnection.getCode(currentUid);
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

    public void cardRemoved() {
        recievedKey("C");
        currentUid = null;
        kaartVerwijderdScherm(currentLanguage);
    }

    public void uidInUse(String uid) {
        currentUid = uid;
        inlogScherm(currentLanguage);
    }

    private void lastScreen() {
        System.out.println(lastScreens);
        String lastScreen;
        if(lastScreens.size() > 1) {
            lastScreen = lastScreens.get(lastScreens.size() - 2);
            lastScreens.remove(lastScreens.size() - 1);
        } else {
            lastScreen = Hoofdscherm;
            lastScreens.clear();
        }
        switch(lastScreen) {
            case Hoofdscherm:
                hoofdscherm(currentLanguage);
                break;
            case Inlogscherm:
                inlogScherm(currentLanguage);
                break;
            case Pinscherm:
                pinnen(currentLanguage);
                break;
            case Taalscherm:
                taal(currentLanguage);
                break;
            default:
                kaartVerwijderdScherm(currentLanguage);
                break;
        }
    }

    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();

        switch (text) {
            case "afsluiten":
                System.exit(0);
                break;
            case "close":
                System.exit(0);
                break;
            case "pinnen":
                pinnen(currentLanguage);
                break;
            case "draw money":
                pinnen(currentLanguage);
                break;
            case "verander taal":
                taal(currentLanguage);
                break;
            case "change language":
                taal(currentLanguage);
                break;
            case "English":
                currentLanguage = Engels;
                lastScreen();
                break;
            case "Nederlands":
                currentLanguage = Nederlands;
                lastScreen();
                break;
            case "afbreken":
                lastScreen();
                break;
            case "cancel":
                lastScreen();
                break;
            default:
                System.out.println("weet niet wat te doen! " + text);
                break;
        }


        System.out.println("knop: " + text);
    }
}
