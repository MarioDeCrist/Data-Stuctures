package p10_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Mario DeCristofaro
 * @date November 18, 2019
 * @project ten
 * Simple hash class that uses an array of SimpleBSTClass objects
 */
public class HashClass
{
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char CARRIAGE_RETURN_CHAR = '\r';
    /**
     * Table size default
     */
    private final int DEFAULT_TABLE_SIZE = 10;
    /**
     * Constant for identifying end of file in data load operation
     */
    private static final int END_OF_FILE_MARKER = -1;
    /**
     * File reader object for use in data load operation
     * <p>
     * Note: Used globally within the class to allow supporting input methods
     *  to be used
     */
    private static FileReader fileIn;
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char NEWLINE_CHAR = '\n';
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char SEMICOLON = ';';
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char SPACE = ' ';
    /**
     * Constant for identifying character in file in data load operation
     */
    private static final char TAB_CHAR = '\t';
    /**
     * Array for hash table
     */
    private SimpleBSTClass[] tableArray;
    /**
     * Size of the base table
     */
    private int tableSize;
    
    /**
     * Default constructor
     * Initializes to default table size and number of hash digits
     */
    public HashClass()
    {
        int index;
        tableSize = DEFAULT_TABLE_SIZE;
        tableArray =  new SimpleBSTClass[ tableSize ];
        fileIn = null;
        
        for( index = 0; index < tableSize; index++ )
        {
            tableArray[ index ] =  new SimpleBSTClass();
        }
    }
    
    /**
     * Copy constructor
     * @param copied- HashClass object to be copied
     */
    public HashClass( HashClass copied )
    {
        int index;
        tableSize = copied.tableSize;
        fileIn = null;
        tableArray = new SimpleBSTClass[ tableSize ];
        
        for( index = 0; index < tableSize; index++ )
        {
            if( copied.tableArray[ index ] != null )
            {
                tableArray[ index ] =  new SimpleBSTClass( copied.tableArray[
                                                                     index ] );
            }
        }
    }
    
    /**
     * Initialization constructor
     * @param inTableSize- sets table size
     */
    public HashClass( int inTableSize )
    {
        tableSize = inTableSize;
        int index;
        fileIn = null;
        tableArray = new SimpleBSTClass[ tableSize  ];
        
        for( index = 0; index < tableSize; index++ )
        {
            tableArray[ index ] =  new SimpleBSTClass();
        }
    }
    
    /**
     * Adds item to hash table
     * Overloaded method that accepts SimpleStudentClass object
     * @param newItem- student class object
     * @return Boolean success of operation
     * @throw IndexOutOfBoundsException - if hash calculation failure
     */
    
    public boolean addItem( SimpleStudentClass newItem ) throws
                                                      IndexOutOfBoundsException
    {
        int tempHash = generateHash( newItem.studentID );
        
        if( tableArray[ tempHash ] == null )
        {
            tableArray[ tempHash ] = new SimpleBSTClass();
            
        }
        
        return tableArray[ tempHash ].insert( newItem );
    }
    
    /**
     * Adds item to hash table
     * Uses overloaded addItem with object; optionally may be anonymous
     *  constructor
     * @param inName - name of student
     * @param inStudentID- student ID
     * @param inGender- gender of student
     * @param inGPA- gpa of student
     * @return Boolean success of operation
     */
    public boolean addItem( String inName, int inStudentID, char inGender,
                                                                 double inGPA )
    {
        SimpleStudentClass temp = new SimpleStudentClass( inName, inStudentID,
                                                             inGender, inGPA );
        return addItem( temp );
    }
    
    /**
     * Indicates whether a given character is found in a given string
     * <p>
     * Note: Uses .length and .charAt
     * @param testChar- character to be tested in the string
     * @param str- string given for the character search
     * @return Boolean indication that the character was found in the string
     */
    private boolean charInString( char testChar, String str )
    {
        int index;
        
        for( index = 0; index < str.length(); index++ )
        {
            if( testChar == str.charAt( index ) )
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Clears hash table by clearing all trees
     */
    public void clearHashTable()
    {
        int index;
        
        for( index = 0; index < tableSize; index++ )
        {
            tableArray[ index ].clearTree();
        }
    }
    
    /**
     * Searches for item in hash table
     * @param studentID- used for requesting data
     * @return SimpleStudentClass object found, or null if not found
     */
    public SimpleStudentClass findItem( int studentID )
    {
        int temp = generateHash( studentID );
        SimpleStudentClass locNode = null;
        
        if( tableArray[ temp ].search( studentID ) != null )
        {
            locNode = tableArray[ temp ].search( studentID );
        }
        
        return locNode;
    }
    
    /**
     * Method converts student ID within data value to hash value for use as
     *  index in hash table
     * <p>
     * Note: Method is overloaded,this one can be used with a student ID number
     * Note: digits are individually multiplied by position (1 - 6, left to
     *  right) and added to sum before being set to hash value. E.g., for
     *   654987, last (LSD) digit (7) is multiplied by 6, then fifth digit (8)
     *    is multiplied by 5, then fourth digit (9) is multiplied by 4,etc.
     * @param studentID- contains student ID number to be converted to hash
     *  value
     * @return integer hash index value
     */
    public int generateHash( int studentID )
    {
        int tempID = studentID;
        int ten = 10;
        int total = 0;
        int idMultiplier = 6;
        int holder;
        
        while( tempID > 0 )
        {
            holder = tempID % ten;
            total += holder * idMultiplier;
            tempID = tempID / ten;
            idMultiplier--;
        }
        
        total = total % tableSize;
        return total;
    }
    
    /**
     * Method converts student ID within data value to hash value for use as
     *  index in hash table
     * <p>
     * Note: Method is overloaded, this one can be used with a student class
     *  string value that holds the student ID
     * Note: calls overloaded generateHash with string converted to integer
     * Note: digits are individually multiplied by position (1 - 6, left to
     *  right) and added to sum before being set to hash value. E.g., for
     *   654987, last (LSD) digit (7) is multiplied by 6, then fifth digit (8)
     *    is multiplied by 5, then fourth digit (9) is multiplied by 4, etc.
     * @param dataString- String value contains student data within which the
     *  student ID will be converted to hash value
     * @return integer hash index value
     */
    public int generateHash( String dataString )
    {
        int tempStr = getStudentID( dataString );
        int ten = 10;
        int total = 0;
        int idMultiplier = 6;
        int holder;
        
        while( tempStr > 0 )
        {
            holder = tempStr % ten;
            total += holder * idMultiplier;
            tempStr = tempStr / ten;
            idMultiplier--;
        }
        
        total = total % tableSize;
        return total;
    }
    
    /**
     * Local method for getting a string with specified end characters,ignoring
     *  most white space
     * @param endChar- flag character to end input
     * @return integer character for use in input process
     */
    private String getStringFromFile( char endChar )
    {
        int nextCharInt = 0;
        String outString = "";
        
        try
           {
            // skip leading white space
            do
               {
                nextCharInt = fileIn.read();                  
               }
            while( nextCharInt != END_OF_FILE_MARKER 
                     && ( (char)nextCharInt == SPACE 
                         || (char)nextCharInt == TAB_CHAR ) 
                             || (char)nextCharInt == NEWLINE_CHAR 
                                 || (char)nextCharInt == CARRIAGE_RETURN_CHAR );
            
            while( nextCharInt != END_OF_FILE_MARKER 
                             && (char)nextCharInt != endChar
                               && (char)nextCharInt != NEWLINE_CHAR 
                                  && (char)nextCharInt != CARRIAGE_RETURN_CHAR )
               {
                outString += (char)nextCharInt;
                
                nextCharInt = fileIn.read();
               }            
           }
        
        catch( IOException ioe )
           {
            System.out.println( "INPUT ERROR: Failure to capture character" );
            
            outString = "";
           }
        
        if( nextCharInt == END_OF_FILE_MARKER )
           {
            outString = "";
           }
        
        return outString;
    }
    
    /**
     * Traverses through string, finds student ID, returns
     * @param dataString - String object through which process traverses
     * @return extracted student ID
     */
    private int getStudentID( String dataString )
    {
        int index = 0;
        int strLen = dataString.length();
        char testChar;
        String numString = "";
        
        while( index < strLen && 
                    !charInString( dataString.charAt( index ), "0123456789" ) )
           {
            index++;
           }
        
        testChar = dataString.charAt( index );
        
        while( index < strLen && charInString( testChar, "0123456789" ) )
           {
            numString += testChar;
            
            index++;

            testChar = dataString.charAt( index );
           }
        
        return Integer.parseInt( numString );
    }
    
    /**
     * Local method uploads data character by character, parses characters, and
     *  loads into hash data structure
     * Exception: If there is a file failure such as file not found, method
     *  will return false
     * @param fileName - name of file in local directory required for upload
     * @return returns Boolean evidence of success
     */
    public boolean loadDataFromFile( String fileName )
    {
        String name, idStr, genderStr, gpaStr;
        int idVal;
        char genderVal;
        double gpaVal;
        boolean failedAccess = false, endInput = false;

        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
           }
        
        catch( FileNotFoundException fnfe )
           {
            failedAccess = true;
            
            return false;
           }

        do
           {
            // get name
            name = getStringFromFile( SEMICOLON );
             
            if( name != "" )
               {
                // get student ID
                idStr = getStringFromFile( SEMICOLON );
                idVal = Integer.parseInt( idStr );
             
                // get gender
                genderStr = getStringFromFile( SEMICOLON );
                genderVal = genderStr.charAt( 0 );
             
                // get gpa
                gpaStr = getStringFromFile( CARRIAGE_RETURN_CHAR );
                gpaVal = Double.parseDouble( gpaStr );
             
                // load data into StudentClass object
                failedAccess = !addItem( name, idVal, genderVal, gpaVal );
               }
             
            else
               {
                endInput = true;
               }
           }
        while( !failedAccess && !endInput);
        
        try
           {
            if( fileIn != null )
               {
                fileIn.close();
               }
           }
        
        catch( IOException ioe )
           {
            System.out.println( "DATA ACCESS ERROR: Failure to close file" );
           }
            
        return !failedAccess;
    }
    
    /**
     * Removes item from hash table
     * @param studentID- used for requesting data
     * @return SimpleStudentClass object removed, or null if not found
     * @throw IndexOutOfBoundsException - if hash calculation failure
     */
    public SimpleStudentClass removeItem( int studentID ) throws
                                                      IndexOutOfBoundsException
    {
        int tempHash = generateHash( studentID );
        return tableArray[ tempHash ].removeNode( studentID );
    }
    
    /**
     * traverses through all array bins, finds heights of each tree, then
     *  displays as table
     * Shows table of tree heights, then shows table size and number of digits
     *  of the student ID used for hashing, then shows the number of empty bins
     *   and the tallest tree height of the set
     */
    public void showHashTableStatus()
    {
        int index, heightValue = 0, nilCount = 0, maxHeight = -1;
        
        System.out.println( "Tree height report: " );
        System.out.print( " Index: ");
        
        for( index = 0; index < tableSize; index++ )
           {
            System.out.format( "%6d ", index );
           }
        
        System.out.println();
        System.out.print("         ");

        for( index = 0; index < tableSize; index++ )
           {
            System.out.print( "  -----");
           }
        
        System.out.println();
        System.out.print( "         ");
        
        for( index = 0; index < tableSize; index++ )
           {
            if( !tableArray[ index ].isEmpty() )
               {
                heightValue = tableArray[ index ].getTreeHeight();
                
                if( heightValue > maxHeight )
                   {
                    maxHeight = heightValue;
                   }
                
                System.out.format("%6d ", heightValue );                 
               }
            
            else
               {
                nilCount++;
                
                System.out.print("     * " );                 
               }
           }
        
        // final test for max after loop
        if( heightValue > maxHeight )
           {
            maxHeight = heightValue;
           }
        
        System.out.println( "\n\nWith a table size of " + tableSize );
        System.out.println( "The number of empty bins was "
              + nilCount + ", and the tallest tree height was "
              + maxHeight + '\n' );
    }
}
