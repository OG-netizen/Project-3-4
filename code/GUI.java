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
    private JPanel blad;
    private Graphics g;

    GUI() {
        knoppen = new JButton[8];
        setSize(breedte, hoogte);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("GUI");
    }
    
    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.createGUI();
        frame.setVisible(true);
        System.out.println("done");
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel venster = new JPanel();
        JPanel vensterLinks = new JPanel();
        JPanel vensterRechts = new JPanel();
        getContentPane().setLayout(new BorderLayout(50, 50));
        vensterLinks.setLayout(new GridLayout(4 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));
        vensterRechts.setLayout(new GridLayout(4, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));

        knoppen[0] = new JButton("1");
        vensterLinks.add(knoppen[0]);
        knoppen[0].addActionListener(this);
        knoppen[1] = new JButton("2");
        vensterLinks.add(knoppen[1]);
        knoppen[1].addActionListener(this);
        knoppen[2] = new JButton("3");
        vensterLinks.add(knoppen[2]);
        knoppen[2].addActionListener(this);
        knoppen[3] = new JButton("4");
        vensterLinks.add(knoppen[3]);
        knoppen[3].addActionListener(this);

        knoppen[4] = new JButton("5");
        vensterRechts.add(knoppen[4]);
        knoppen[4].addActionListener(this);
        knoppen[5] = new JButton("5");
        vensterRechts.add(knoppen[5]);
        knoppen[5].addActionListener(this);
        knoppen[6] = new JButton("5");
        vensterRechts.add(knoppen[6]);
        knoppen[6].addActionListener(this);
        knoppen[7] = new JButton("5");
        vensterRechts.add(knoppen[7]);
        knoppen[7].addActionListener(this);



        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
        blad = new JPanel();
        venster.add(blad, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String text = e.getActionCommand();
        g = blad.getGraphics();
        g.drawString(text, 500, 500);
        System.out.print("knop: ");
        System.out.println(text);
    }
}
