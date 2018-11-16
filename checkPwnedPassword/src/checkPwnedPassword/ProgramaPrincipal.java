package checkPwnedPassword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProgramaPrincipal {

	public static void main(String[] args) {

		String password = "";
		System.out.println("Enter the password that you want to check: ");
		
		BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
		try {
			password = ob.readLine();
			ob.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println("\nThe program is searching for your password in the pwned passwords file. Please don't abort...");
			checkPass("passwords.txt", password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void checkPass(String pFile, String pPassword) throws IOException {
		
		boolean coincide = false;
		String hashedPass = Sha1.aplicarSha1(pPassword);
		String sCurrentLine;
		
		try(FileReader fr = new FileReader(pFile);
	    	    BufferedReader br = new BufferedReader(fr))
	    	{
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.equals(hashedPass))
					coincide = true;
			}
			
			br.close();
			
	    	} catch (IOException e) {
	    		 e.printStackTrace();
	    	}
		
		if(coincide == true)
			System.out.println("\nCongratulations! Your password wasn't found in the list!");
		else
			System.out.println("\nYour password WAS FOUND in the list! You should never use this password!");
	}

}
