
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class demo {
	

	public void connect(String portname) {
		
		SerialPort port = new SerialPort(portname);
		
		try {
			port.openPort();
			
			port.setParams(
					
					SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE
					);
			port.addEventListener((SerialPortEvent event)->{
				
				if(event.isRXCHAR()) {
					
					try {
						String s = port.readString();

						System.out.println(s);
						
					
					} catch (SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			});
			
			
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static void main(String[] args) {
		
		String portlist[] = SerialPortList.getPortNames();
		
		for(int i =0; i<portlist.length;i++) { 
			System.out.println(portlist[i]);
		}
		demo obj = new demo();
		obj.connect(portlist[0]);
	

		
		
		
		
		

	}

}

