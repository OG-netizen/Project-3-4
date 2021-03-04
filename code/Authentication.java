// Set jou passcode

public class Authentication {
	
	private String correctPIN="1234";
	public boolean authenticate(String pin) {
		return(pin.equals(correctPIN));
	}
	
	private void setCorrectPIN(int pin){
	this.correctPIN = pin;
		
	}

}
