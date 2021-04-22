public class App {
    private static Serial serieleConnectie;
    private static GUI gui;
    private static SQLConnection SQLconnectie;

    public static void main(String[] args) {
        gui = new GUI();
        try {
            SQLconnectie = new SQLConnection(gui);
            serieleConnectie = new Serial(gui, SQLconnectie);
        } catch(Exception e) {
            System.out.println("Fout tijdens het aanmaken van de klasses: " + e);
            System.exit(0);
        }
        gui.startGUI(serieleConnectie, SQLconnectie);
    }
}
