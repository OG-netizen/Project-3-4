

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class start extends JFrame implements ActionListener {
    private int breedte = 600, hoogte = 800;
    private JButton[] knoppen;
    private JPanel blad;
    private Graphics g;
    
    start() {
        knoppen = new JButton[8];
        setSize(breedte, hoogte);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setTitle("GUI");
    }
    
    //public static void main(String[] args) {
      //  start frame = new start();
      //  frame.createGUI();
      //  frame.setVisible(true);
     //   System.out.println("done");
   // }

    void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel venster = new JPanel();
        JPanel vensterLinks = new JPanel();
        JPanel vensterRechts = new JPanel();
        getContentPane().setLayout(new BorderLayout(50, 50));
        vensterLinks.setLayout(new GridLayout(3 , 1, 50, 50));
        vensterLinks.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));
        vensterRechts.setLayout(new GridLayout(3, 1, 50, 50));
        vensterRechts.setPreferredSize(new Dimension(300, MAXIMIZED_VERT));

        knoppen[0] = new JButton("Taal");
        vensterLinks.add(knoppen[0]);
        knoppen[0].addActionListener(this);
        knoppen[1] = new JButton("Saldo");
        vensterLinks.add(knoppen[1]);
        knoppen[1].addActionListener(this);
        knoppen[2] = new JButton("Opnemen");
        vensterLinks.add(knoppen[2]);
        knoppen[2].addActionListener(this);
        knoppen[3] = new JButton("Overmaken");
        vensterRechts.add(knoppen[3]);
        knoppen[3].addActionListener(this);

        knoppen[4] = new JButton("Accountgegevens");
        vensterRechts.add(knoppen[4]);
        knoppen[4].addActionListener(this);
        knoppen[5] = new JButton("Recente ransactie");
        vensterRechts.add(knoppen[5]);
        knoppen[5].addActionListener(this);
        // knoppen[6] = new JButton("7");
       //  vensterRechts.add(knoppen[6]);
       // knoppen[6].addActionListener(this);
       // knoppen[7] = new JButton("8");
       // vensterRechts.add(knoppen[7]);
       // knoppen[7].addActionListener(this);
        	


        getContentPane().add(vensterLinks, BorderLayout.WEST);
        getContentPane().add(vensterRechts, BorderLayout.EAST);
        blad = new JPanel();
        venster.add(blad, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        //String text = e.getActionCommand();
      //  g = blad.getGraphics();
      //  g.drawString(text, 500, 500);
       // System.out.print("knop: ");
       // System.out.println(text);
 
        if(e.getActionCommand().equals("Opnemen")) {
        	Opnemen FRAME = new Opnemen();
			FRAME.setVisible(true);
			FRAME.createGUI();
			//setVisible(false);
		} 
        
        
    }
}