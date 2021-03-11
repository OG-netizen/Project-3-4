import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
    na pas inserten -- meteen inloggen
    na inloggen -- saldo, pinnen, snelkeuze 70eu, 

    altijd taal kunnen veranderen en kunnen afbreken


*/

public class GUI extends JFrame implements ActionListener {
    private int breedte = 600, hoogte = 800;
    private JButton[] knoppen;
    private int logoHoogte = 150, knopBreedte = 300, onderkantHoogte = 100;

    private JPanel venster = new JPanel();
    private JPanel vensterLinks = new JPanel();
    private JPanel vensterRechts = new JPanel();
    private JPanel logo = new JPanel();
    private JPanel onderkant = new JPanel();
    private JLabel insertCardLabel = new JLabel();
    private JLabel logoLabel = new JLabel();
    private ImageIcon insertCardIcon = new ImageIcon("Images/insert card.png");
    private ImageIcon logoIcon = new ImageIcon("Images/munt.png");

    GUI() {
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

        venster.setBackground(Color.red);
        venster.setFont(new Font("Calibri", Font.PLAIN, 50));
        venster.setAlignmentX(CENTER_ALIGNMENT);

        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(knopBreedte, MAXIMIZED_VERT));

        logo.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, logoHoogte));
        logo.setBackground(Color.blue);

        onderkant.setBackground(Color.cyan);
        onderkant.setPreferredSize(new Dimension(MAXIMIZED_HORIZ - knopBreedte * 2, onderkantHoogte));

        insertCardLabel.setIcon(insertCardIcon);
        
        logoLabel.setIcon(logoIcon);

        for(int i = 0; i < 8; i++) {
            knoppen[i] = new JButton(String.valueOf(i));
            if(i < 4) {
                vensterLinks.add(knoppen[i]);
            } else {
                vensterRechts.add(knoppen[i]);
            }
            knoppen[i].addActionListener(this);
        }

        hoofdscherm();

        venster.add(insertCardLabel);
        logo.add(logoLabel);

        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
        getContentPane().add(venster, BorderLayout.CENTER);
        getContentPane().add(logo, BorderLayout.NORTH);
        getContentPane().add(onderkant, BorderLayout.SOUTH);
    }

    public void hoofdscherm() {
        knoppen[0].setText("pinnen");
        knoppen[3].setText("verander taal");
        knoppen[7].setText("afbreken");

        knoppen[0].setVisible(true);
        knoppen[1].setVisible(false);
        knoppen[2].setVisible(false);
        knoppen[3].setVisible(true);
        knoppen[4].setVisible(false);
        knoppen[5].setVisible(false);
        knoppen[6].setVisible(false);
        knoppen[7].setVisible(true);
    }

    public void pinnen() {
        knoppen[0].setText("pin 10");
        knoppen[1].setText("pin 20");
        knoppen[2].setText("pin 30");
        knoppen[3].setText("verander taal");
        knoppen[4].setText("pin 40");
        knoppen[5].setText("pin 50");
        knoppen[6].setText("pin 60");
        knoppen[7].setText("afbreken");
        for(int i = 0; i < 8; i++) {
            knoppen[i].setVisible(true);
        }
    }

    public void taal() {
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

    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        String text2 = text.substring(4,6);
        hoofdscherm();
        if(text == "afbreken") {
            hoofdscherm();
        } else if(text == "pinnen") {
            pinnen();
            //venster.getGraphics().drawString("Welkom", venster.getWidth() / 2, venster.getHeight() / 2);
        } else if(text2 == "pin") {
            System.out.println("er word " + text.substring(0, 3) + "euro gepint");
            hoofdscherm();
        } else if(text == "verander taal") {
            taal();
        }
        System.out.println(text2);
        System.out.println("knop: " + text);
    }
}
