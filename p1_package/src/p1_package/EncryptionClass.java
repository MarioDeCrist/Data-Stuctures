package p1_package;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Mario DeCristofaro
 * @date   02/19/2019
 * @project one
 */

public class EncryptionClass 
{
    /**
	 * length of one side of the square array
	 */
	private int arrSide;
	/**
	 * array for holding encrypted data
	 */
	private int[][] encryptedArr;
	/**
	 * Class Global FileReader variable so methods can be used
	 */
	private FileReader fileIn;
	/**
	 * Constant for maximum input char limit
	 */
	private final int MAX_INPUT_CHARS = 256;
	/**
	 * Constant for minus sign used in getAnInt
	 */
	private final char MINUS_SIGN = '-';
	/**
	 * Constant for unprintable char value used as message end char
	 */
	private final int UNPRINTABLE_CHAR_VALUE = 127;
	
	/**
	 * Default constructor
	 */
	public EncryptionClass()
	{
	    arrSide = 0;
	    encryptedArr = null;
	    fileIn = null;
	}
	/**
	 * Copy constructor
	 * @param copied - EncryptionClass object to be copied
	 */
	public EncryptionClass( EncryptionClass copied )
	{
	    arrSide = copied.arrSide;
	    fileIn = copied.fileIn;
	    int rowIndex, columnIndex;
	    
	    for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
	    {
	    	for(columnIndex = 0; columnIndex < arrSide; columnIndex++ )
	    	{
	    		encryptedArr[rowIndex][columnIndex] =
	    						copied.encryptedArr[rowIndex][columnIndex];
	    	}
	    }
	}
	/**
	 * tests and reports if a character is found in a given string
	 * <p>
	 * Note: uses .charAt and .length Java utilities for string management
	 * @param testChar - character to be tested against the string
	 * @param testString - string within which the character is tested
	 * @return Boolean result of test
	 */
	private boolean charInString( char testChar, String testString )
	{
	    int index;
	    
	    for( index = 0; index < testString.length(); index++ )
	    {
	    	if( testChar == testString.charAt( index ) )
	    	{
	    		return true;
	    	}
	    }
	    return false;
	}
	/**
	 * Decrypts string from array
	 * @return String result of decryption process
	 */
	public String decryptData()
	{
		int rowStart = arrSide - 1;
	    int columnStart = arrSide - 1;
	    int rowIndex = rowStart;
	    int columnIndex = columnStart;
	    String tempStr = "";
	    
	    while( rowIndex  >= 0 )
	    {
	    	while( rowIndex >= 0 && columnIndex < arrSide )
	    	{
	    		tempStr += (char)encryptedArr[rowIndex][columnIndex];
	    		if(encryptedArr[rowIndex][columnIndex] ==
	    											UNPRINTABLE_CHAR_VALUE)
	    		{
	    			rowStart = 0;
	    			columnStart = 0;
	    		}
	    		rowIndex--;
	    		columnIndex++;
	    	}
	    	if( columnStart > 0 )
	    	{
	    		columnStart--;
	    	}
	    	else
	    	{
	    		rowStart--;
	    	}
	    	columnIndex = columnStart;
	        rowIndex = rowStart;
	    }
	    return tempStr;
	}
	/**
	 * Displays array in character form for diagnostics
	 * <p>
	 * Note: No Java utilities are used in this method
	 */
	public void displayCharArray()
	{
	    int rowIndex, columnIndex;
	    
	    for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
	    {
	    	for( columnIndex = 0; columnIndex < arrSide; columnIndex++ )
	    	{
	    		if( encryptedArr[ rowIndex ][ columnIndex ] !=
	    											   UNPRINTABLE_CHAR_VALUE )
	    		{
	    			char finalArr = (char)encryptedArr[rowIndex][columnIndex];
	    			System.out.print( finalArr );
	    			//System.out.print( " " );
	    		}
	    	}
	    	System.out.print("\n");
	    }
	    
	}
	/**
	 * Downloads encrypted data to file
	 * <p>
	 * Note: No action taken if array is empty
	 * @param fileName - String object holding file name to use
	 */
	public void downloadData( String fileName )
	{
        FileWriter toFile;
       
        int rowIndex, colIndex;
       
        if( arrSide > 0 )
           {
            try
               {
                toFile = new FileWriter( fileName );
            
                toFile.write( "" + arrSide + "\r\n" );
           
                for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
                   {
                    for( colIndex = 0; colIndex < arrSide; colIndex++ )
                       {
                        if( encryptedArr[ rowIndex ][ colIndex ] < 100 )
                           {
                            toFile.write( "0" );
                           }
                        
                        toFile.write("" 
                                + encryptedArr[ rowIndex ][ colIndex ] + " " );
                       }
                 
                    toFile.write( "\r\n" );
                   }
           
                toFile.flush();
                toFile.close();
               }
       
            catch( IOException ioe )
               {
                ioe.printStackTrace();
               }
           }
    }
	/**
	 * Encrypts given string into array
	 * <p>
	 * Note: Uses .charAt and .length Java utilities for String management
	 * @param toEncrypt - String object to be encrypted
	 */
	public void encryptData( String toEncrypt )
	{
		// finds sides
	    arrSide = (int)findSquareRoot( toEncrypt.length() + 1 ) + 1;
	    encryptedArr = new int[arrSide][arrSide];
	    
	    int length = toEncrypt.length();
		int rowStart = arrSide - 1;
	    int columnStart = arrSide - 1;
	    int rowIndex = rowStart;
	    int columnIndex = columnStart;
	    int strIndex = 0;
	    
	    while( rowIndex  >= 0 )
	    {
	    	while( columnIndex < arrSide && rowIndex >= 0)
	    	{
	    		if( strIndex < length )
	    		{
	    			encryptedArr[rowIndex][columnIndex] =
	    									   (int)toEncrypt.charAt(strIndex);
		    		strIndex++;
	    		}
	    		else if( strIndex == length )
	    		{
	    			encryptedArr[rowIndex][columnIndex] =
	    												UNPRINTABLE_CHAR_VALUE;
	    			strIndex++;
	    		}
	    		else
	    		{
	    			encryptedArr[rowIndex][columnIndex] = getRandomCharValue();
	    		}
	    		rowIndex--;
	    		columnIndex++;
	    	}
	    	if( columnStart > 0 )
	    	{
	    		columnStart--;
	    	}
	    	else
	    	{
	    		rowStart--;
	    	}
	        columnIndex = columnStart;
	        rowIndex = rowStart;
	    }
	}
	/**
	 * Finds the square root of an integer value
	 * <p>
	 * Note: Finds square root to precision of 0.000001 without using any Java
	 *  utilities other than abs
	 * @param value - integer value to find square root of
	 * @return double square root value
	 */
	private double findSquareRoot( int value )
	{
	    double lowerBound = 0, upperBound = value;
	    double middleVal = ( lowerBound + upperBound ) / 2.0;
	    double tempResult = middleVal * middleVal;
	    double precision = .000001;
	    
	    // if difference is greater than the specified precision value
	    while( Math.abs( tempResult - value ) > precision )
	    {
	      if( tempResult > value )
	      {
	    	  upperBound = middleVal;
	      }
	      
	      else
	      {
	    	  lowerBound = middleVal;
	      }
	      
	      // recalculate the middle value
	      middleVal = ( lowerBound + upperBound ) / 2.0;
	      tempResult = middleVal * middleVal;
	    }
	    return middleVal;
	}
	/**
	 * gets an integer from the input stream
	 * @param maxLength- maximum length of characters input to
	 *  capture the integer
	 * @return integer captured from file
	 */
	private int getAnInt( int maxLength )
	{
        int inCharInt;
        int index = 0;
        String strBuffer = "";
        int intValue;
        boolean negativeFlag = false;

        try
           {
            inCharInt = fileIn.read();

            // clear space up to number
            while( index < maxLength && !charInString( (char)inCharInt, 
                                                           "0123456789+-" ) )
               {
                inCharInt = fileIn.read();
               
                index++;
               }
           
            if( inCharInt == MINUS_SIGN )
               {
                negativeFlag = true;
               
                inCharInt = fileIn.read();
               }

            while( charInString( (char)inCharInt, "0123456789" ) )
               {   
                strBuffer += (char)( inCharInt );

                index++;
               
                inCharInt = fileIn.read();
               }            
           }
       
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
          
            strBuffer = "";
           }
          
        intValue = Integer.parseInt( strBuffer );
       
        if( negativeFlag )
           {
            intValue *= -1;
           }
       
        return intValue;
    }
	/**
	 * Generates the integer value of a random character between the lowest
	 *  possible character value (space) and the highest possible
	 *   character value (tilde)
	 * <p>
	 * Note: Method must be capable of generating a space value, a tilde value,
	 *  and any possible character between;
	 *   may use any appropriate Math utilities
	 * @return integer value of randomly generated character
	 */
	private int getRandomCharValue()
	{
	    int minAscii = 32;
	    int maxAscii = 127;
	    
	    int randVal = (int)(Math.random() * ( maxAscii - minAscii ))+ minAscii;
	    
	    return randVal;
	}
	/**
	 * Uploads data from file holding a square array
	 * @param fileName- String object holding file name
	 */
	public void uploadData( String fileName )
	{ 
        int rowIndex, colIndex;
      
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
        
            // get side length
            arrSide = getAnInt( MAX_INPUT_CHARS );           
      
            encryptedArr = new int[ arrSide ][ arrSide ];
            
            for( rowIndex = 0; rowIndex < arrSide; rowIndex++ )
               {
                for( colIndex = 0; colIndex < arrSide; colIndex++ )
                   {
                    encryptedArr[ rowIndex ][ colIndex ] 
                                                 = getAnInt( MAX_INPUT_CHARS );
                   }
               }
            
            fileIn.close();
           }
      
        // for opening file
        catch( FileNotFoundException fnfe )
           {
            fnfe.printStackTrace();
           }
        
        // for closing file
        catch ( IOException ioe )
           {
            ioe.printStackTrace();
           }
    }
}
