import java.io.*; // IOException
import java.util.*; // Scanner
import java.util.concurrent.TimeUnit;
import jssc.*;

public class Serial {
    private static SerialPort serielePoort;
    private static GUI gui;
    public static boolean booted = false;

    public Serial(GUI g) throws SerialPortException{
        gui = g;
        startConnectie();
    }

    private void startConnectie() throws SerialPortException{
        String[] beschikbarePoorten = SerialPortList.getPortNames();
        
        if (beschikbarePoorten.length == 0) {
            System.out.println("Geen com-poorten beschikbaar :( ");
            System.out.println("Druk op enter om af te sluiten...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);;
            return;
        }
        
        System.out.println("Beschikbare com-poorten:");
        for (int i = 0; i < beschikbarePoorten.length; i++){
            System.out.println(beschikbarePoorten[i]);
        }

        String geselecteerdePoort;
        if(beschikbarePoorten.length == 1) {
            geselecteerdePoort = beschikbarePoorten[0];
        } else {
            System.out.println("Type de poort die je wilt gebruiken en druk op enter...");
            Scanner in = new Scanner(System.in);
            geselecteerdePoort = in.next();
            in.close();
        }
        
        // writing to port
        serielePoort = new SerialPort(geselecteerdePoort);
        try {
            // opening port
            serielePoort.openPort();
            
            serielePoort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
            serielePoort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);

            //TimeUnit.SECONDS.sleep(2);
            //int[] biljet = {1,2,3};
            //werpGeldUit(biljet, 100, 10);
            while(!booted) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            System.out.println("Verbinding met de seriele poort " + geselecteerdePoort + " gemaakt.");
            //serielePoort.closePort();
        }
        catch (Exception e) {
            System.out.println("Fout tijdens het verbinden met poort " + geselecteerdePoort + ": " + e);
            //throw e;
        }
    }

    public void werpGeldUit(int[] aantalBiljetten, int geldAantal, int donatieAantal) {
        String geldCommando = "dispense:";
        String bonCommando = "printBon:" + geldAantal + "," + donatieAantal;
        for(int i = 0; i < aantalBiljetten.length; i++) {
            geldCommando += aantalBiljetten[i];
            if(i < aantalBiljetten.length - 1) {
                geldCommando += ",";
            }
        }

        try {
            System.out.println(geldCommando);
            serielePoort.writeString(geldCommando);
            System.out.println(bonCommando);
            serielePoort.writeString(bonCommando);
        } catch (SerialPortException e) {
            System.out.println("Er ging iets mis tijdens het schrijven naar de compoort: " + e);
        }
    }
    
    // receiving response from port
    public static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                  
                    String ontvangenData = serielePoort.readString(event.getEventValue());
                    System.out.println(ontvangenData);
                    booted = true;
                    //System.out.println("Received response from port: " + ontvangenData);
                    String[] ontvangenDataGesplit = ontvangenData.split(" ");
                    for(int i = 0; i < ontvangenDataGesplit.length; i++) {
                        if(ontvangenDataGesplit[i].contains("removed_card")) {
                            gui.kaartVerwijderd();
                        }
                    }
                    if(ontvangenDataGesplit[0].equals("key:")) {
                        gui.ontvangenToets(ontvangenDataGesplit[1]);
                    } else if(ontvangenDataGesplit[0].equals("uid:")) {
                        String uidString = "";
                        for(int i = 1; i < ontvangenDataGesplit.length - 1; i++) {
                            uidString += ontvangenDataGesplit[i];
                        }
                        gui.uidInGebruik(uidString);
                    } else if(ontvangenDataGesplit[0].contains("geldUitgeworpen:")) {
                        gui.printBonScherm();
                    } else if(ontvangenDataGesplit[0].contains("bonGeprint:")) {
                        gui.hoofdscherm();
                    }
                }
                catch (SerialPortException e) {
                    System.out.println("Fout tijdens het ontvangen van data: " + e);
                }
            }
        }
    }
}
