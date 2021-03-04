import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    private int breedte = 600, hoogte = 800;
    static private int gridH = 4, gridW = 3;
    private JButton[] knoppen;
    private JPanel blad;
    private Graphics g;

    GUI() {
        knoppen = new JButton[gridH * gridW];
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
        Container venster = this.getContentPane();
        //venster.setLayout(new GridLayout(gridH, gridW));
        // for(int i = 0; i < gridW; i++) {
        //     for(int j = 0; j < gridH - 1; j++) {
        //         knoppen[i] = new JButton(String.valueOf(i * (gridH - 1) + j + 1));
        //         venster.add(knoppen[i]);
        //         knoppen[i].addActionListener(this);
        //     }
        // }
        knoppen[0] = new JButton("1");
        knoppen[1] = new JButton("2");
        venster.add(knoppen[0], BorderLayout.WEST);
        knoppen[0].addActionListener(this);
        venster.add(knoppen[1], BorderLayout.EAST);
        knoppen[1].addActionListener(this);
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
