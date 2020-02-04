package p1_package;

public class p1_package_main 
{
	public static void main( String[] args )
	{
		EncryptionClass encryptClass = new EncryptionClass();
		
		encryptClass.encryptData("The quick brown fox jumped over the lazy dog's back.");
		//main.uploadData("encrypted_1.txt");
		encryptClass.displayCharArray();
		//main.downloadData("encrypted_1.txt");
		System.out.print( encryptClass.decryptData() );
		
	}
}
