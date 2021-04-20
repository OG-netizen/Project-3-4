import java.io.*; // IOException
import java.util.*; // Scanner
import jssc.*;

public class Serial {
    private static SerialPort serialPort;
    private static GUI gui;
    private static SQLConnection SQLconnection;

    public Serial(GUI g, SQLConnection s) throws SerialPortException{
        gui = g;
        SQLconnection = s;
        startSerial();
    }

    private void startSerial() throws SerialPortException{
        String[] portNames = SerialPortList.getPortNames();
        
        if (portNames.length == 0) {
            System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
            System.out.println("Press Enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);;
            return;
        }
        
        
        System.out.println("Available com-ports:");
        for (int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        System.out.println("Type port name, which you want to use, and press Enter...");
        //Scanner in = new Scanner(System.in);
        //String portName = in.next();
        //in.close();
        String portName = portNames[0];
        
        // writing to port
        serialPort = new SerialPort(portName);
        try {
            // opening port
            serialPort.openPort();
            
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
            
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
                                          SerialPort.FLOWCONTROL_RTSCTS_OUT);
            
            serialPort.addEventListener(new PortReader(), SerialPort.MASK_RXCHAR);
            // writing string to port
            serialPort.writeString("led aan");
            
            System.out.println("String wrote to port, waiting for response..");
        }
        catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
            throw ex;
        }
        
    }
    
    // receiving response from port
    public static class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                  
                    String receivedData = serialPort.readString(event.getEventValue());
                    //System.out.println("Received response from port: " + receivedData);
                    String[] dataArray = receivedData.split(" ");
                    if(dataArray[0].equals("key:")) {
                        gui.recievedKey(dataArray[1]);
                    } else if(dataArray[0].equals("uid:")) {
                        String uidString = "";
                        for(int i = 1; i < dataArray.length - 1; i++) {
                            uidString += dataArray[i];
                        }
                        SQLconnection.uidRecieved(uidString);
                    }
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}
