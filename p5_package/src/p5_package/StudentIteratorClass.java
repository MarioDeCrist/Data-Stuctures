package p5_package;

/**
 * @author Mario DeCristofaro
 * @project five
 * @date October 7, 2019
 * Iterator class uses StudentListClass data;
 * conducts iterator operations using StudentListClass as data within the class
 */
public class StudentIteratorClass
{
	/**
	 * Current index of iterator
	 */
	private int currentIndex;
	/**
	 * StudentClassList data used by this class
	 */
	private StudentListClass iteratorList;
	/**
	 * Constant character for display
	 */
	private final char LEFT_BRACKET = '[';
	/**
	 * Constant character for display
	 */
	private final char RIGHT_BRACKET = ']';
	/**
	 * Constant character for display
	 */
	private final char SPACE = ' ';
	/**
	 * Default constructor for StudentIteratorClass
	 */
	public StudentIteratorClass()
	{
       currentIndex = 0;
       iteratorList = new StudentListClass();
	}
	/**
	 * Initialization constructor for StudentIteratorClass
	 * @param initCapacity-integer value at which to set initial array capacity
	 */
	public StudentIteratorClass( int initCapacity )
	{
       iteratorList = new StudentListClass( initCapacity );
       currentIndex = 0;
	}
	/**
	 * Copy constructor for StudentIteratorClass
	 * @param copied - StudentIteratorClass object to be copied
	 */
	public StudentIteratorClass( StudentIteratorClass copied )
	{
       iteratorList = new StudentListClass( copied.iteratorList );
       currentIndex = copied.currentIndex;
	}
	/**
	 * Clears array
	 */
	public void clear()
	{
       iteratorList.clear();
       setToBeginning();
	}
	/**
	 * Recursive method displays spaces for displayQueue
	 * @param numSpaces - integer value specifying number of spaces to display
	 */
	private void displaySpaces( int numSpaces )
	{
       if( numSpaces > 0 )
       {
    	   System.out.print( SPACE );
    	   displaySpaces( numSpaces - 1 );
       }
	}
	/**
	 * Gets value at iterator cursor location
	 * @return StudentClass object returned; null if not found
	 */
	public StudentClass getAtCurrent()
	{
       return iteratorList.getNthStudent( currentIndex );
	}
	/**
	 * Inserts new value after value at iterator cursor
	 * <p>
	 * Note: Current value must remain the same after data set, unless there is
	 *  only one item in the set
	 * @param newValue - StudentClass object to be inserted in list
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean insertAfterCurrent( StudentClass newValue )
	{
       if( iteratorList.isEmpty() )
       {
    	   return iteratorList.insertDataAtNthPosition( currentIndex + 1,
                                                                    newValue );
       }
       return iteratorList.insertDataAtNthPosition( currentIndex + 2, newValue
                                                                             );
	}
	/**
	 * Inserts new StudentClass object before item at iterator cursor
	 * <p>
	 * Note: Current value must remain the same after data set, unless there is
	 *  only one item in the set
	 * @param newValue- StudentClass object to be inserted in list
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean insertBeforeCurrent( StudentClass newValue )
	{
       if( isEmpty() )
       {
    	   currentIndex = 1;
    	   return iteratorList.insertDataAtNthPosition(currentIndex, newValue);
       }
       
       moveNext();
       return iteratorList.insertDataAtNthPosition(currentIndex - 1, newValue);
	}
	/**
	 * Reports if iterator cursor is at beginning
	 * If list is empty, cursor is not at beginning
	 * @return Boolean result of action; true if at beginning, false otherwise
	 */
	public boolean isAtBeginning()
	{
       return !isEmpty() && ( currentIndex == 0 );
	}
	/**
	 * Reports if iterator cursor is at end
	 * If list is empty, cursor is not at end
	 * @return Boolean result of action; true if at end, false otherwise
	 */
	public boolean isAtEnd()
	{
       return !isEmpty() && ( currentIndex == iteratorList.getCurrentSize()
                                                                         - 1 );
	}
	/**
	 * Reports if list is empty
	 * @return Boolean result of action; true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
       return iteratorList.isEmpty();
	}
	/**
	 * If possible, moves iterator cursor one position to the right, or next
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean moveNext()
	{
       if( !isEmpty() && !isAtEnd() )
       {
    	   currentIndex++;
    	   return true;
       }
       return false;
	}
	/**
	 * If possible, moves iterator cursor one position to the left, or previous
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean movePrev()
	{
       if( !isEmpty() && !isAtBeginning() )
       {
    	   currentIndex--;
    	   return true;
       }
       return false;
	}
	/**
	 * Removes and returns a data value from the iterator cursor position
	 * <p>
	 * Note: cursor must be located at succeeding element unless last
	 *  item removed
	 * @return StudentClass object removed from list, or null if not found
	 */
	public StudentClass removeAtCurrent()
	{
           if( currentIndex >= iteratorList.getCurrentSize() )
	       {
	    	   movePrev();
	       }
	       return iteratorList.removeNthStudent( currentIndex + 1 );
	}
	/**
	 * Replaces item at iterator cursor with new StudentClass object
	 * @param newValue - StudentClass object to be inserted in list
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean replaceAtCurrent( StudentClass newValue )
	{
       return iteratorList.replaceDataAtNthPosition( currentIndex, newValue );
	}
	/**
	 * Shows space-delimited list with cursor location indicated
	 */
	public void runDiagnosticDisplay()
	{
       int spaces = 2, index;
       System.out.println("Left End of Iterator: ");
       
       for( index = 0; index < iteratorList.getCurrentSize(); index++ )
       {
    	   displaySpaces( spaces );
    	   spaces += 2;
    	   //finds cursor
    	   if( index == currentIndex )
    	   {
    		   System.out.print( LEFT_BRACKET );
    		   System.out.print( iteratorList.getNthStudent( index + 1 ) );
    		   System.out.print( RIGHT_BRACKET );
    	   }
    	   else
    	   {
    		   System.out.print( iteratorList.getNthStudent( index + 1 ) );
    	   }
    	   System.out.println();
       }
       displaySpaces( spaces );
       System.out.println( "Right End Of Iterator" );
	}
	/**
	 * Sets iterator cursor to beginning of list
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean setToBeginning()
	{
       if( isEmpty() )
       {
    	   return false;
       }
       currentIndex = 0;
       return true;
	}
	/**
	 * Sets iterator cursor to the end of the list
	 * @return Boolean result of action; true if successful, false otherwise
	 */
	public boolean setToEnd()
	{
       if( isEmpty() )
       {
    	   return false;
       }
       currentIndex = iteratorList.getCurrentSize() - 1;
       
       return true;
	}
}
