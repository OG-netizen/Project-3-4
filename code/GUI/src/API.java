package API;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;



public class API {
	
	private double data =10.0;
	 private Connection connectie;
	
	public double getData() {
		return data;
		
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	public  void checkIfRegistered(String IBAN) throws IOException{
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
		}
		else if(responseCode == 432){
			System.out.println("JSON wrong");
		}else if (responseCode == 433) {
			System.out.println("Account not registered");
		}else if(responseCode == 434) {
			
		}
	
}
	
	
	public  void Login(String IBAN, String pincode) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/Login" ).openConnection();
		connection.setRequestMethod("POST");     
		String statement = "select * from debitcard where iban = '" + IBAN + "';";       
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(statement);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 208){
			System.out.println("Ok");
		}
		else if(responseCode == 432){
			System.out.println("JSON wrong");
		}else if (responseCode == 433) {
			System.out.println("Account blocked");
		}else if(responseCode == 434) {
			System.out.println("Not registered");
		}
		
	}
		
		public  void checkAttempts(String IBAN) throws IOException{
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
			}
			else if(responseCode == 432){
				System.out.println("JSON wrong");
			}
				
			}
	
			
			public  void checkBalance(String IBAN) throws IOException{
				HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkBalance" ).openConnection();
				connection.setRequestMethod("POST");   
				double amount = data;
				String statement = "select * from debitcard where iban = '" + IBAN + "';";
				
				connection.setDoOutput(true);
			    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
			    wr.write(statement);
			    wr.flush();
				
				int responseCode = connection.getResponseCode();
				if(responseCode == 209){
					System.out.println("Ok");
					System.out.println("Balance is "+ amount);
				}
				else if(responseCode == 432){
					System.out.println("JSON wrong");
				}else if (responseCode == 433) {
					System.out.println("Account blocked");
				}else if(responseCode == 434) {
					
				}
			}
				
				public  void Withdraw(String IBAN, Double amount) throws IOException{
					HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/Withdraw" ).openConnection();
					connection.setRequestMethod("POST");     
					String statement = "UPDATE  Account SET balans"+ amount +" from where iban = '" + IBAN + "';";       
					connection.setDoOutput(true);
				    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
				    wr.write(statement);
				    wr.flush();
					
					int responseCode = connection.getResponseCode();
					if(responseCode == 208){
						System.out.println("Ok");
					}
					else if(responseCode == 432){
						System.out.println("JSON wrong");
					}else if (responseCode == 436) {
						System.out.println("Not logged in ");
					}else if(responseCode == 437) {
						System.out.println("Balance too low");
					}
				}
				
					
					public  void Logout(String IBAN) throws IOException{
						HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/Logout" ).openConnection();
						connection.setRequestMethod("POST");     
						String statement = "select * from debitcard where iban = '" + IBAN + "';";        
						connection.setDoOutput(true);
					    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
					    wr.write(statement);
					    wr.flush();
						
						int responseCode = connection.getResponseCode();
						if(responseCode == 208){
							System.out.println("Ok");
						}
						else if(responseCode == 432){
							System.out.println("JSON wrong");
						}
						
					}
					
					public  void checkLoggedIn(String IBAN) throws IOException{
						HttpURLConnection connection = (HttpURLConnection) new URL("http://145.24.222.156:5000/checkLoggedIn" ).openConnection();
						connection.setRequestMethod("POST");     
						String statement = "select * from debitcard where iban = '" + IBAN + "';";        
						connection.setDoOutput(true);
					    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
					    wr.write(statement);
					    wr.flush();
						
						int responseCode = connection.getResponseCode();
						if(responseCode == 208){
							System.out.println("Ok");
						}
						else if(responseCode == 432){
							System.out.println("JSON wrong");
						}
						
					}


	
}
	

	
	
	

