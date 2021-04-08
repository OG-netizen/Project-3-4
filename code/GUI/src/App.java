import java.util.concurrent.TimeUnit;

public class App {
    private static Serial serialConnection;
    private static GUI gui;
    private static SQLConnection SQLconnection;

    public static void main(String[] args) {
        try {
            gui = new GUI();
            serialConnection = new Serial(gui);
            SQLconnection = new SQLConnection();
            TimeUnit.SECONDS.sleep(5);
            gui.startGUI(serialConnection);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
