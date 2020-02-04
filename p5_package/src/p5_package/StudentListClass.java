package p5_package;

/**
 * @author Mario DeCristofaro
 * @project five
 * @date October 7, 2019
 * Description: Class wrapper for a Java array of StudentClass objects,
 *  with management operations
 * <p>
 * Note: Values are accessed by using N which starts at one and goes to array
 *  size; however, the array is still implemented as zero-based
 */
public class StudentListClass 
{
	/**
	 * Member data
	 */
	private int arrayCapacity;
	/**
	 * Member data
	 */
	private int arraySize;
	/**
	 * Default constant capacity
	 */
	private static final int DEFAULT_CAPACITY = 10;
	/**
	 * For N not found in search
	 */
	public static final int NOT_FOUND = -1;
	/**
	 * Member - StudentClass array
	 */
	private StudentClass[] studentArray;
	/**
	 * Default constructor, initializes array to default capacity
	 */
	public StudentListClass()
	{
       arrayCapacity = DEFAULT_CAPACITY;
       arraySize = 0;
       studentArray = new StudentClass[ arrayCapacity ];
	}
	/**
	 * Initializing constructor, initializes array to specified capacity
	 * Note: Does not allow capacity less than default capacity
	 * @param capacity- integer initial capacity specification for the array
	 */
	public StudentListClass(int capacity)
	{
       arrayCapacity = capacity;
       arraySize = 0;
       studentArray = new StudentClass[ arrayCapacity ];
	}
	/**
	 * Copy constructor, initializes array to size of copied array, and
	 *  capacity of copied array, then copies only the elements up
	 *   to the given size
	 * @param copied- StudentListClass object to be copied
	 */
	public StudentListClass( StudentListClass copied )
	{
       arrayCapacity = copied.arrayCapacity;
       arraySize = copied.arraySize;
       int rowIndex;
       
       for( rowIndex = 0; rowIndex <= arrayCapacity; rowIndex++ )
       {
    	   copied.studentArray[ rowIndex ] = new StudentClass(
                                                    studentArray[ rowIndex ] );
       }
	}
	/**
	 * Sets data at end of list
	 * <p>
	 * Note: Uses insertDataAtNthPosition
	 * @param student- StudentClass object to be appended to list
	 */
	public void appendDataAtEnd( StudentClass student )
	{
       insertDataAtNthPosition( arraySize + 1, student );
	}
	/**
	 * Description: Checks for need to resize; if this is necessary,
	 *  creates new array with double the original capacity, loads data
	 *   from original array to new one, then sets studentArray to new array
	 */
	private void checkForResize()
	{
       int index;
       StudentClass tempArr[];
       
       if( arrayCapacity == arraySize )
       {
    	   arrayCapacity = 2 * arrayCapacity;
    	   tempArr = new StudentClass[ arrayCapacity ];
    	   
    	   for( index = 0; index < arrayCapacity; index++ )
    	   {
    		   tempArr[index] = studentArray[index];
    	   }
    	   studentArray = tempArr;
       }
	}
	/**
	 * Clears array of all valid values by setting array size to zero, values
	 *  remain in array but are not accessible
	 */
	public void clear()
	{
       arraySize = 0;
	}
	/**
	 * Displays student list
	 */
	public void displayList()
	{
       int index;
       System.out.println("Student Class List: ");
       for( index = 0; index < arraySize; index++ )
       {
    	   System.out.println( studentArray[ index ] );
       }
       System.out.println();
	}
	/**
	 * Gets number of student if found in list
	 * @param student- StudentClass object for finding N
	 * @return integer N of the StudentClass object,or NOT_FOUND if not in list
	 */
	public int findStudentNumber( StudentClass student )
	{
       int stdIndex;
       
       for( stdIndex = 0; stdIndex < arraySize; stdIndex++ )
       {
    	   if( studentArray[ stdIndex ].compareTo( student ) == 0 )
    	   {
    		   return stdIndex + 1;
    	   }
       }
       return NOT_FOUND;
	}
	/**
	 * Description: Gets current size of array
	 * <p>
	 * Note: size of array indicates number of valid or viable values in
	 *  the array
	 * @return integer size of array
	 */
	public int getCurrentSize()
	{
       return arraySize;
	}
	/**
	 * Acquires the Nth item in the list, starting with N = 1
	 * @param N_value- integer value to identify Nth student to retrieve
	 * @return StudentClass value at element or null if attempt to acquire
	 *  data out of bounds
	 */
	public StudentClass getNthStudent( int N_value )
	{
       if( N_value - 1 <= arraySize && N_value > 0 )
       {
    	   return studentArray[ N_value - 1 ];
       }
       return null;
	}
	/**
	 * Sets data at beginning of list; moves all subsequent data up by
	 *  one element
	 * <p>
	 * Note: No failure mode; data will be set at beginning no matter what
	 *  the size of the array is
	 * @param student- StudentClass object to set at beginning
	 */
	public void insertDataAtBeginning( StudentClass student )
	{
		insertDataAtNthPosition( 1, student );
	}
	/**
	 * Description: Moves data up one element, then sets item in array at
	 *  specified Nth position, where N starts at 1
	 * <p>
	 * Note: Allows item to be appended to end of list
	 * @param NthPos- integer value to indicate which position (N) at which to
	 *  insert student data
	 * @param student- StudentClass object to be inserted at Nth position
	 *  (N - 1 in the array)
	 * @return Boolean success if inserted, or failure if incorrect N was used
	 */
	public boolean insertDataAtNthPosition( int NthPos, StudentClass student )
	{
       int index;
       checkForResize();
       
       if( NthPos - 1 <= arraySize && NthPos > 0 )
       {
    	   for( index = arraySize; index > NthPos - 1; index-- )
    	   {
    		   studentArray[ index ] = studentArray[ index - 1 ];
    	   }
    	   studentArray[ NthPos - 1 ] = student;
    	   arraySize++;
    	   return true;
       }
	   return false;
	}
	/**
	 * Tests for size of array equal to zero, no valid values stored in array
	 * @return Boolean result of test for empty
	 */
	public boolean isEmpty()
	{
       return arraySize == 0;
	}
	/**
	 * Description: Removes Nth item from array if index within array size
	 *  bounds
	 * <p>
	 * Note: Each data item from the element immediately above the remove index
	 *  to the end of the array is moved down by one element
	 * @param numberN- integer number of element value to be removed,
	 *  starts at N = 1
	 * @return removed StudentClass value if successful, null if not
	 */
	public StudentClass removeNthStudent( int numberN )
	{
       int stdIter;
       int numRemoved = numberN - 1;
       StudentClass tempVal = null;
       
       if(  numberN <= arraySize && numberN > 0 )
       {
    	   tempVal = studentArray[ numRemoved ];
    	   for( stdIter = numRemoved; stdIter < arraySize - 1; stdIter++ )
    	   {
    		   studentArray[ stdIter ] = studentArray[ stdIter + 1 ];
    	   }
    	   arraySize--;
       }
       return tempVal;
	}
	/**
	 * Description: Replaces item in array at specified Nth position, where
	 *  N starts at 1
	 * @param NthPos- integer value to indicate Nth position at which to
	 *  replace student data
	 * @param student- StudentClass object to be inserted at Nth position
	 *  (N - 1 in the array)
	 * @return Boolean success if inserted, or failure if incorrect N was used
	 */
	public boolean replaceDataAtNthPosition( int NthPos, StudentClass student )
	{
       if( NthPos > 0 && NthPos <= arraySize )
       {
    	   studentArray[ NthPos - 1 ] = student;
    	   return true;
       }
       return false;
	}
}
