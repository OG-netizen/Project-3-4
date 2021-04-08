import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
    na pas inserten -- meteen inloggen
    na inloggen -- saldo, pinnen, snelkeuze 70eu, 

    altijd taal kunnen veranderen en kunnen afbreken


*/

public class GUI extends JFrame implements ActionListener {
    private final String Nederlands = "Nederlands";
    private final String Engels = "Engels";
    private Serial usedSerial;
    private int breedte = 600, hoogte = 800;
    private JButton[] knoppen;
    private int logoHoogte = 125, knopBreedte = 300, onderkantHoogte = 100;

    private JPanel venster = new JPanel();
    private JPanel vensterLinks = new JPanel();
    private JPanel vensterRechts = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel onderkant = new JPanel();
    private JLabel insertCardLabel = new JLabel();
    private JLabel logoLabel = new JLabel();
    private ImageIcon insertCardIcon = new ImageIcon("code/GUI/Images/insert card.png");
    private ImageIcon logoIcon = new ImageIcon("code/GUI/Images/logo_white_trans.png");

    private String code = "";

    public void startGUI(Serial s) {
        usedSerial = s;
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

        onderkant.setBackground(Color.black);
        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte));

        insertCardLabel.setIcon(insertCardIcon);
        
        logoLabel.setIcon(logoIcon);

        for(int i = 0; i < 8; i++) {
            knoppen[i] = new JButton(String.valueOf(i));
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
    }

    public void hoofdscherm(String taal) {
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

  
   

    public void pinnen(String taal) {
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

    public void dataReceived(String data) {
        String maskedCode = "";
        code += data;
        char checkLetter = 'l';
        if(code.length() > 0) {
            checkLetter = code.charAt(code.length() - 1);
            if(checkLetter == 'D') {
                code = code.substring(0, 4);
                System.out.println("code die word gecheckt: " + code);
                if(checkCode()) {
                    pinnen("Nederlands");
                }
                code = "";
            } else if(checkLetter == 'C') {
                code = "";
            }
        }
        if(code.length() > 0) System.out.println(code.charAt(code.length() - 1));

        for(int i = 0; i < code.length(); i++) {
            maskedCode += "*";
        }
        venster.getGraphics().clearRect(0, 0, venster.getWidth(), venster.getHeight());
        venster.getGraphics().drawString(maskedCode, venster.getWidth() / 2, venster.getHeight() / 2);
        System.out.println(code);

    }

    public boolean checkCode() {
        String checkCode = "1234";
        if(code.substring(0,1) == checkCode.substring(0,1)) {
            System.out.println("code goedgekeurd! doorgestuurd om te pinnen");
            return true;
        } else {
            System.out.println("code foutgekeurd!");
            return false;
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
                pinnen(Nederlands);
                break;
            case "draw money":
                pinnen(Engels);
                break;
            case "verander taal":
                taal(Nederlands);
                break;
            case "change language":
                taal(Engels);
                break;
            case "English":
                hoofdscherm(Engels);
                break;
            case "Nederlands":
                hoofdscherm(Nederlands);
                break;
            case "afbreken":
                hoofdscherm(Nederlands);
                break;
            case "cancel":
                hoofdscherm(Engels);
                break;
            default:
                System.out.println("weet niet wat te doen! " + text);
                break;
        }


        System.out.println("knop: " + text);
    }
}
