public class App {
    private static Serial s;
    private static GUI g;

    public static void main(String[] args) {
        try {
            g = new GUI();
            s = new Serial(g);
            g.startGUI(s);
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
