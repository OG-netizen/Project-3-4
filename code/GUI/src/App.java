import java.util.concurrent.TimeUnit;

public class App {
    private static Serial serialConnection;
    private static GUI gui;
    private static SQLConnection SQLconnection;

    public static void main(String[] args) {
        gui = new GUI();
        try {
            serialConnection = new Serial(gui);
            SQLconnection = new SQLConnection(gui);
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        gui.startGUI(serialConnection, SQLconnection);
    }
}
