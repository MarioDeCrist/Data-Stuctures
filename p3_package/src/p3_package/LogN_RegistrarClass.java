package p3_package;

/**
 * @author Mario DeCristofaro
 * @project Three
 * @date September 23, 2019
 */

public class LogN_RegistrarClass
{
    /**
     * Private capacity and size data
     */
	private int capacity;
	/**
	 * Constant default capacity
	 */
	private final int DEFAULT_CAPACITY = 10;
	/**
	 * Constant used if item not found in array
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
	public LogN_RegistrarClass()
	{
	   capacity = DEFAULT_CAPACITY;
	   size = 0;
	   studentArr = new StudentClass[ capacity ];
	}
	/**
	 * Copy constructor
	 * @param copied- LogN_RegistrarClass object to be copied
	 */
	public LogN_RegistrarClass( LogN_RegistrarClass copied )
	{
	   capacity = copied.capacity;
	   size = copied.size;
	   studentArr = new StudentClass[ capacity ];
	   copyArrayData( this.studentArr, copied.studentArr, 0, size - 1 );
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
	 */
	public void addStudent(String stdName, int stdID,char stdGender,
                                                                 double stdGPA)
	{
	   StudentClass newStudent = new StudentClass( stdName, stdID, stdGender,
                                                                      stdGPA );
	   addStudent( newStudent );
	}
	/**
	 * Adds a StudentClass item to list
	 * <p>
	 * Note: Overloaded method
	 * @param newStudent- StudentClass object to be added to array
	 */
	public void addStudent( StudentClass newStudent )
	{
	   checkForResize();
	   
	   if( size < capacity )
	   {
		   studentArr[ size ] = newStudent;
		   size++;
	   }
	}
	/**
	 * Checks array capacity to verify there is room to accept new data;
	 *  if array is at capacity, resizes array and copies data as needed to
	 *   double capacity of the array
	 */
	private void checkForResize()
	{
	   int index;
	   StudentClass tempArray[];
	   
	   if( capacity == size )
	   {
		   capacity = 2 * capacity;
		   tempArray = new StudentClass[ capacity ];
		   
		   for( index = 0; index < size; index++ )
		   {
			   tempArray[ index ] = studentArr[ index ];
		   }
		   studentArr = tempArray;
	   }
	}
	/**
	 * Copies student list from one array to other
	 * <p>
	 * Note: Must create new StudentClass object to assign to each element to
	 *  destination array to eliminate aliasing
	 * @param dest- StudentClass array to which data is copied
	 * @param source- StudentClass array from which data is copied
	 * @param lowIndex- integer index at which to start copying from
	 *  source array, inclusive
	 * @param highIndex- integer index at which to end copying from
	 *  source array, inclusive
	 */
	private void copyArrayData(StudentClass[] dest,StudentClass[] source,
                                                  int lowIndex, int highIndex )
	{
	   int destIndex = 0;
	   int sourceIndex;
	   
	   for( sourceIndex = lowIndex; sourceIndex <= highIndex; sourceIndex++,
                                                                  destIndex++ )
	   {
		   dest[ destIndex ] = new StudentClass( source[ sourceIndex ] );
	   }
	}
	/**
	 * Optional method, local array dump for diagnostics
	 */
	public void diagnosticArrayDump()
	{
	   StudentClass tempArr[] = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr, 0, size - 1 );
	   int index;
	   
	   for( index = 0; index < size; index++ )
	   {
		   System.out.println( tempArr[ index ] );
	   }
	}
	/**
	 * Finds student in array, returns data
	 * Note: Method must use findStudentIndex
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
	 * Finds student's index in array, returns index, or returns NOT_FOUND if
	 *  item is not in the array
	 * @param student- StudentClass object to be found
	 * @return index of StudentClass object, or NOT_FOUND
	 */
	private int findStudentIndex( StudentClass student )
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
	 * Note: Method must use findStudentIndex
	 * @param student- StudentClass object to be removed
	 * @return StudentClass object that was removed, or null if not found
	 */
	public StudentClass removeStudent( StudentClass student )
	{
	   if( findStudentIndex( student ) != NOT_FOUND )
	   {
		   studentArr[ findStudentIndex( student ) ] = null;
		   return student;
	   }
	   return null;
	}
	/**
	 * Merges StudentClass values brought in between a low and high index
	 *  segment (inclusive) of an array
	 * Creates local temporary array for the exact size needed to conduct merge
	 * @param workingArray- lowest index of array segment to be managed
	 * @param lowIndex- middle index of array segment to be managed
	 * @param middleIndex- high index of array segment to be managed
	 * @param highIndex- StudentClass array to be managed by method
	 */
	private void runMerge(StudentClass[] workingArray,int lowIndex,
                                               int middleIndex, int highIndex )
	{
	   int locCapacity = ( highIndex - lowIndex ) + 1;
	   int locMid = ( locCapacity - 1 ) / 2;
	   int locHigh = locCapacity - 1;
	   int wkgArrIndex = lowIndex;
	   int firstHalf = 0;
	   int secondHalf = locMid + 1;
	   
	   StudentClass[] tempArr = new StudentClass[ locCapacity  ];
	   copyArrayData( tempArr, workingArray, lowIndex, highIndex );
	   
	   // sorts values into tempArr to merge back with workingArray
	   while( firstHalf <= locMid && secondHalf <= locHigh )
	   {
		   if( tempArr[ firstHalf ].compareTo( tempArr[ secondHalf ] ) < 0 )
		   {
			   workingArray[ wkgArrIndex ] = tempArr[ firstHalf ];
			   firstHalf++;
		   }
		   else
		   {
			   workingArray[ wkgArrIndex ] = tempArr[ secondHalf ];
			   secondHalf++;
		   }
		   wkgArrIndex++;
	   }
	   // garbage collector
	   while( firstHalf <= locMid )
	   {
		   workingArray[ wkgArrIndex ] = tempArr[ firstHalf ];
		   firstHalf++;
		   wkgArrIndex++;
	   }
	   while( secondHalf <= locHigh )
	   {
		   workingArray[ wkgArrIndex ] = tempArr[ secondHalf ];
		   secondHalf++;
		   wkgArrIndex++;
	   }
	}
	/**
	 * StudentClass data sorted using merge sort algorithm
	 * <p>
	 * Note: Calls runMergeSortHelper with lower and upper indices of array
	 *  to be sorted
	 * Note: Creates new StudentClass array, sorts contents of array, and
	 *  returns the sorted result; does not modify (this) object student array
	 * @return StudentClass array containing sorted data
	 */
	public StudentClass[] runMergeSort()
	{
	   StudentClass[] tempArr = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr, 0, size - 1 );
	   runMergeSortHelper( tempArr, 0, size - 1 );
	   return tempArr;
	}
	/**
	 * Merge sort helper, recursively breaks given array segment down to
	 *  smaller segments between lowIndex and highIndex (inclusive),
	 *   then sorts data using merge sort method
	 * @param workingArray- String array holding unsorted values
	 * @param lowIndex- lowest index of array segment to be managed;
	 *  this varies as the segments are broken down recursively
	 * @param highIndex- highest index of array segment to be managed;
	 *  this varies as the segments are broken down recursively
	 */
	private void runMergeSortHelper( StudentClass[] workingArray, int lowIndex,
                                                                int highIndex )
	{
	   int midIndex;
	   
	   if( lowIndex < highIndex )
	   {
		   midIndex = ( highIndex + lowIndex ) / 2;
		   runMergeSortHelper( workingArray, lowIndex, midIndex );
		   runMergeSortHelper( workingArray, midIndex + 1 , highIndex );
		   runMerge( workingArray, lowIndex, midIndex, highIndex );
	   }
	}
	/**
	 * Partitions array using the first value as the pivot;
	 *  when this method is complete the partition value is in the correct
	 *   location in the array
	 * @param workingArray - StudentClass array holding array to be managed
	 *  by method
	 * @param lowIndex- low index of array segment to be partitioned
	 * @param highIndex- high index of array segment to be partitioned
	 * @return integer index of partition pivot
	 */
	private int runPartition( StudentClass[] workingArray, int lowIndex,
                                                                int highIndex )
	{
	   int pivotIndex = lowIndex;
	   int wkgIndex;
	   
	   for( wkgIndex = lowIndex + 1; wkgIndex < size; wkgIndex++ )
	   {
		   if( workingArray[ wkgIndex ].compareTo( workingArray[ lowIndex ]
                                                                        ) < 0 )
		   {
			   pivotIndex++;
			   swapValues( workingArray, wkgIndex, pivotIndex );
		   }
	   }
	   //swaps with lowIndex once pivot is found
	   swapValues( workingArray, lowIndex, pivotIndex );
	   return pivotIndex;
	}
	/**
	 * StudentClass data sorted using quick sort algorithm
	 * <p>
	 * Note: Calls runQuickSortHelper with lower and upper indices of array to
	 *  be sorted
	 * Note: Creates new StudentClass array, sorts contents of array, and
	 *  returns the sorted result; does not modify (this) object student array
	 * @return StudentClass array containing sorted data
	 */
	public StudentClass[] runQuickSort()
	{
	   StudentClass[] tempArr = new StudentClass[ capacity ];
	   copyArrayData( tempArr, studentArr, 0, size - 1 );
	   runQuickSortHelper( tempArr, 0, size - 1 );
	   return tempArr;
	}
	/**
	 * Helper method run with parameters that support recursive access
	 * @param workingArray- StudentClass array holding unsorted values
	 * @param lowIndex- low index of the segment of the array to be processed
	 * @param highIndex- high index of the segment of the array to be processed
	 */
	private void runQuickSortHelper( StudentClass[] workingArray, int lowIndex,
                                                                int highIndex )
	{
	   int partIndex;
	   
	   if( highIndex > lowIndex )
	   {
		   partIndex = runPartition( workingArray, lowIndex, highIndex );
		   runQuickSortHelper( workingArray, lowIndex, partIndex - 1 );
		   runQuickSortHelper( workingArray, partIndex + 1, highIndex );
	   }
	}
	/**
	 * Swaps values within given array
	 * @param workingArray- array of Strings used for swapping
	 * @param indexOne- integer index for one of the two items to be swapped
	 * @param indexOther- integer index for the other of the two items to
	 *  be swapped
	 */
	private void swapValues( StudentClass[] workingArray, int indexOne,
                                                               int indexOther )
	{
	   //saves the values to local variables
       StudentClass indexOneVal = workingArray[ indexOne ];
       StudentClass indexOtherVal = workingArray[ indexOther ];
	   //swaps the values
       workingArray[ indexOne ] = indexOtherVal;
       workingArray[ indexOther ] = indexOneVal;
	}
}
