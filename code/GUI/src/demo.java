import java.io.*; // IOException
import java.util.*; // Scanner
import jssc.*;

public class demo {

    private static SerialPort serialPort;
    public String data;
    
    /**
     * @param args the command line arguments
     */
    public demo() {
        String[] portNames = SerialPortList.getPortNames();
        
        if (portNames.length == 0) {
            System.out.println("There are no serial-ports :( You can use an emulator, such ad VSPE, to create a virtual serial port.");
            System.out.println("Press Enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return;
        }
        
        
        System.out.println("Available com-ports:");
        for (int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }
        System.out.println("Type port name, which you want to use, and press Enter...");
        Scanner in = new Scanner(System.in);
        String portName = in.next();
        in.close();
        
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
            serialPort.writeString("Hurrah!!!");
            
            System.out.println("String wrote to port, waiting for response..");
        }
        catch (SerialPortException ex) {
            System.out.println("Error in writing data to port: " + ex);
        }
        
    }

    protected void sendData() {
        
    }
    
    // receiving response from port
    public class PortReader implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                  
                    data = serialPort.readString(event.getEventValue());
                    sendData();
                    //System.out.println("Received response from port: " + receivedData);
                }
                catch (SerialPortException ex) {
                    System.out.println("Error in receiving response from port: " + ex);
                }
            }
        }
    }
}
