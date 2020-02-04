package p2_package;
/**
 * @author Mario DeCristofaro
 * @date 09/16/2019
 * @project two
 */

public class RegistrarClass 
{
    /**
     * Private capacity and size data
     */
	private int capacity;
	/**
	 * Constant default capacity
	 */
	private final int DEFAULT_CAPACITY = 	10;
	/**
	 * private final int NOT_FOUND
	 */
	private final int NOT_FOUND = -1;
	/**
	 * Private capacity and size data
	 */
	private int size;
	/**
	 * Private array holding student data
	 */
	private StudentClass[] studentArr;
	
	/**
	 * Default constructor
	 */
	public RegistrarClass()
	{
	   capacity = DEFAULT_CAPACITY;
	   size = 0;
	   studentArr = new StudentClass[ capacity ];
	}
	/**
	 * Initialization constructor
	 * @param initialCapacity- integer value to set class initial capacity
	 */
	public RegistrarClass( int initialCapacity )
	{
	   capacity = initialCapacity;
	   size = 0;
	   studentArr = new StudentClass[ initialCapacity ];
	}
	/**
	 * Copy constructor
	 * @param copied- RegistrarClass object to be copied
	 */
	public RegistrarClass( RegistrarClass copied )
	{
	   capacity = copied.capacity;
	   size = copied.size;
	   studentArr = new StudentClass[ capacity ];
	   int index;
	   for( index = 0; index < size; index++ )
	   {
		   studentArr[ index ] = new StudentClass(copied.studentArr[ index ] );
	   }
	}
	/**
	 * Creates a StudentClass item, then adds to list using other method
	 * <p>
	 * Note: Uses anonymous StudentClass instantiation in call to other method;
	 *  one line of code
	 * Note: Overloaded method
	 * @param stdName- String name of student
	 * @param stdID- integer student ID of student
	 * @param stdGender- character gender of student
	 * @param stdGPA- double GPA of student
	 * @return Boolean result of adding student
	 */
	public boolean addStudent( String stdName,int stdID, char stdGender,
			                                                    double stdGPA )
	{
	   StudentClass newStudent = new StudentClass( stdName, stdID, stdGender,
			                                                          stdGPA );
	   return addStudent( newStudent );
	}
	/**
	 * Adds a StudentClass item to list
	 * <p>
	 * Note: Overloaded method
	 * @param newStudent- StudentClass object to be added to array
	 * @return Boolean result of item addition to array
	 */
	public boolean addStudent( StudentClass newStudent )
	{
	   if( size < capacity )
	   {
		   studentArr[ size ] = newStudent;
		   size++;
		   return true;
	   }
	   return false;
	}
	/**
	 * Copies student list from one array to other
	 * <p>
	 * Note: Must create new StudentClass object to assign to each element to
	 *  destination array to eliminate aliasing
	 * @param dest- StudentClass array to which data is copied
	 * @param source- StudentClass array from which data is copied
	 */
	private void copyArrayData( StudentClass[] dest, StudentClass[] source )
	{
	   int index;
	   
	   for( index = 0; index < size; index++ )
	   {
		   dest[ index ] = new StudentClass( source[ index ] );
	   }
	}
	/**
	 * Optional method, local array dump for diagnostics
	 */
	public void diagnosticArrayDump()
	{
	   
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr );
	   int index;
	   
	   for( index = 0; index < size; index++ )
	   {
		   System.out.println( tempArr[ index ] );
	   }
	}
	/**
	 * Finds student in array, returns data
	 * <p>
	 * Note: Uses findStudentIndex
	 * @param student- StudentClass object to be found
	 * @return StudentClass object found, or null if not found
	 */
	public StudentClass findStudent( StudentClass student )
	{
	   if( findStudentIndex( student ) != NOT_FOUND )
	   {
		   return studentArr[ findStudentIndex( student ) ];
	   }
	   return null;
	}
	/**
	 * Finds student's index in array, returns index, or returns NOT_FOUND
	 *  if item is not in the array
	 * <p>
	 * Note: Must use appropriate comparison method for class
	 * @param student- StudentClass object to be found
	 * @return index of StudentClass object, or NOT_FOUND
	 */
	public int findStudentIndex( StudentClass student )
	{
	   int stdIndex;
	   
	   for( stdIndex = 0; stdIndex < size; stdIndex++ )
	   {
		   if(studentArr[ stdIndex ].compareTo( student ) == 0 )
		   {
			   return stdIndex;
		   }
	   }
	   return NOT_FOUND;
	}
	/**
	 * Removes student from array,shifts elements down to keep array contiguous
	 * <p>
	 * Note: Uses findStudentIndex
	 * @param student- StudentClass object to be removed
	 * @return StudentClass object that was removed, or null if not found
	 */
	public StudentClass removeStudent(StudentClass student)
	{
	   if( findStudentIndex( student ) != NOT_FOUND )
	   {
		   studentArr[ findStudentIndex( student ) ] = null;
		   return student;
	   }
	   return null;
	}
	/**
	 * Description: Sorts elements using the bubble sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array,
	 *  and returns the sorted result; does not modify
	 *   (this) object student array
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runBubbleSort()
	{
	   int leftIndex, rightIndex;
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr );
	   
	   for( leftIndex = 0; leftIndex < size - 1; leftIndex++ )
	   {
		   for(rightIndex = 0; rightIndex < size - leftIndex - 1; rightIndex++)
		   {
			   if( tempArr[ rightIndex ].compareTo(
                                              tempArr[ rightIndex + 1 ] ) > 0 )
			   {
				   swapValues( tempArr, rightIndex, rightIndex + 1 );
			   }
		   }
	   }
	   return tempArr;
	}
	/**
	 * Description: Sorts character elements using the insertion sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array,
	 *  and returns the sorted result; does not modify
	 *   (this) object student array
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runInsertionSort()
	{
	   int insertIndex, searchIndex;
	   StudentClass tempVal;
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr );
	   
	   for( insertIndex = 1; insertIndex < size; insertIndex++ )
	   {
		   searchIndex = insertIndex;
		   tempVal = tempArr[ searchIndex ];
		   
		   while( searchIndex > 0 && tempVal.compareTo(
                                             tempArr[ searchIndex - 1 ] ) < 0 )
		   {
			   tempArr[ searchIndex ] = tempArr[ searchIndex - 1 ];
			   searchIndex--;
		   }
		   tempArr[ searchIndex ] = tempVal;
	   }
	   return tempArr;
	   
	}
	/**
	 * Description: Sorts character elements using the selection sort algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array,
	 *  and returns the sorted result; does not modify
	 *   (this) object student array
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runSelectionSort()
	{
	   int insertIndex, searchIndex;
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr );
	   
	   for( insertIndex = 0; insertIndex < size - 1; insertIndex++ )
	   {
		   // to find the minimum element in the unsorted array
		   int minIndex = insertIndex;
		   
		   for( searchIndex = minIndex + 1; searchIndex < size; searchIndex++ )
		   {
			   if( tempArr[ searchIndex ].compareTo( tempArr[ minIndex ] ) < 0)
			   {
				   minIndex = searchIndex;
			   }
		   }
		   swapValues( tempArr, minIndex, insertIndex );
	   }
	   return tempArr;
	}
	/**
	 * Uses Shell's sorting algorithm to sort an array of integers
	 * Shell's sorting algorithm is an optimized insertion algorithm
	 * <p>
	 * Note: Creates new StudentClass array, sorts contents of array,
	 *  and returns the sorted result; does not modify
	 *   (this) object student array
	 * @return new StudentClass array with sorted items
	 */
	public StudentClass[] runShellSort()
	{
	   int shellIndex, gapIndex, gap;
	   StudentClass tempVal;
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr );
	   
	   for( gap = size / 2; gap > 0; gap /= 2 )
	   {
		   for( gapIndex = gap; gapIndex < size; gapIndex++ )
		   {
			    tempVal= tempArr[ gapIndex ];
			    shellIndex = gapIndex;
			    while( shellIndex >= gap && tempVal.compareTo(
                                            tempArr[ shellIndex - gap ] ) < 0 )
			    {
			    	tempVal = tempArr[shellIndex];
			    	tempArr[ shellIndex ] = tempArr[ shellIndex - gap ];
			    	tempArr[ shellIndex - gap ] = tempVal;
			    	shellIndex -= gap;
			    }
		   }
	   }
	   return tempArr;
	}
	/**
	 * Swaps values within given array
	 * @param stdArray- StudentClass array used for swapping
	 * @param indexOne- integer index for one of the two items to be swapped
	 * @param indexOther- integer index for the other of the two items
	 *  to be swapped
	 */
	private void swapValues( StudentClass[] stdArray,int indexOne,
                                                               int indexOther )
	{
	   // saves the values to local variables
	   StudentClass indexOneVal = stdArray[indexOne];
	   StudentClass indexOtherVal = stdArray[indexOther];
	   // swaps the values
	   stdArray[indexOne] = indexOtherVal;
	   stdArray[indexOther] = indexOneVal;
	}
}
