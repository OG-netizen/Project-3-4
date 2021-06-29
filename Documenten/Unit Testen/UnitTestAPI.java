
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;

public class UnitTestAPI {

	public static void main(String[] args) {
		try {
			TestcheckIfRegistered("NITHOT6832");
			TestLogin("NITHOT6832", "1234");
			TestcheckAttempts("NITHOT6832");
			TestcheckBalance("NITHOT6832");
			TestWithdraw("NITHOT6832", 50.43);
			TestcheckLoggedIn("NITHOT6832");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	public static void TestcheckIfRegistered(String IBAN) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkIfRegistered" ).openConnection();
		connection.setRequestMethod("POST");     
		String statement = "select * from debitcard where debitcard_id = '" + IBAN + "';";       
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(statement);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 208){
			System.out.println("POST was successful.");
			Assertions.(null,"ok");
		}
		else if(responseCode == 432){
			System.out.println("JSON wrong");
			Assertions.(null,"false");
		}else if (responseCode == 433) {
			System.out.println("Account not registered");
		}else if(responseCode == 434) {
			
		}
	
}
	
	
	public static void TestLogin(String IBAN, String pincode) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/login" ).openConnection();
		connection.setRequestMethod("POST");     
		String statement = "select * from debitcard where iban = '" + IBAN + "';";       
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(statement);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 208){
			System.out.println("Ok");
			Assertions.(null,"ok");
		}
		else if(responseCode == 432){
			System.out.println("JSON wrong");
			Assertions.(null,"false");
		}else if (responseCode == 433) {
			System.out.println("Account blocked");
		}else if(responseCode == 434) {
			System.out.println("Not registered");
		}
		
	}
		
		public static void TestcheckAttempts(String IBAN) throws IOException{
			HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkAttempts" ).openConnection();
			connection.setRequestMethod("POST");     
			String statement = "select * from debitcard where iban = '" + IBAN + "';";       
			connection.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		    wr.write(statement);
		    wr.flush();
			
			int responseCode = connection.getResponseCode();
			if(responseCode == 208){
				System.out.println("Attempts left "+ responseCode);
				Assertions.(null,"ok");
			}
			else if(responseCode == 432){
				System.out.println("JSON wrong");
				Assertions.(null,"false");
			}
				
			}
	
			
			public static void TestcheckBalance(String IBAN) throws IOException{
				HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkBalance" ).openConnection();
				connection.setRequestMethod("POST");     
				String statement = "select * from debitcard where iban = '" + IBAN + "';";       
				connection.setDoOutput(true);
			    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			    wr.write(statement);
			    wr.flush();
				
				int responseCode = connection.getResponseCode();
				if(responseCode == 208){
					System.out.println("Ok");
					Assertions.(null,"ok");
				}
				else if(responseCode == 432){
					System.out.println("JSON wrong");
					Assertions.(null,"false");
				}else if (responseCode == 433) {
					System.out.println("Account blocked");
				}else if(responseCode == 434) {
					
				}
			}
				
				public static void TestWithdraw(String IBAN, Double amount) throws IOException{
					HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/withdraw" ).openConnection();
					connection.setRequestMethod("POST");     
					String statement = "UPDATE  Account SET balans"+ amount +" from where iban = '" + IBAN + "';";       
					connection.setDoOutput(true);
				    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
				    wr.write(statement);
				    wr.flush();
					
					int responseCode = connection.getResponseCode();
					if(responseCode == 208){
						System.out.println("Ok");
						Assertions.(null,"ok");
						
					}
					else if(responseCode == 432){
						System.out.println("JSON wrong");
						Assertions.(null,"false");
					}else if (responseCode == 436) {
						System.out.println("Not logged in ");
					}else if(responseCode == 437) {
						System.out.println("Balance too low");
					}
				}
				
					
					public static void TestLogout(String IBAN) throws IOException{
						HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/logout" ).openConnection();
						connection.setRequestMethod("POST");     
						String statement = "select * from debitcard where iban = '" + IBAN + "';";        
						connection.setDoOutput(true);
					    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
					    wr.write(statement);
					    wr.flush();
						
						int responseCode = connection.getResponseCode();
						if(responseCode == 208){
							System.out.println("Ok");
							Assertions.(null,"ok");
						}
						else if(responseCode == 432){
							System.out.println("JSON wrong");
							Assertions.(null,"false");
						}
						
					}
					
					public static void TestcheckLoggedIn(String IBAN) throws IOException{
						HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkedLoggeIn" ).openConnection();
						connection.setRequestMethod("POST");     
						String statement = "select * from debitcard where iban = '" + IBAN + "';";        
						connection.setDoOutput(true);
					    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
					    wr.write(statement);
					    wr.flush();
						
						int responseCode = connection.getResponseCode();
						if(responseCode == 208){
							System.out.println("Ok");
							Assertions.(null,"ok");
						}
						else if(responseCode == 432){
							System.out.println("JSON wrong");
							Assertions.(null,"false");
						}
						
					}


}
