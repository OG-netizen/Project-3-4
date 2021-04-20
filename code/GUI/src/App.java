public class App {
    private static Serial serialConnection;
    private static GUI gui;
    private static SQLConnection SQLconnection;

    public static void main(String[] args) {
        gui = new GUI();
        try {
            SQLconnection = new SQLConnection(gui);
            serialConnection = new Serial(gui, SQLconnection);
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        gui.startGUI(serialConnection, SQLconnection);
    }
}
