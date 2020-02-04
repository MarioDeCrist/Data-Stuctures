package p5_package;

/**
 * @author Mario DeCristofaro
 * @project five
 * @date October 7, 2019
 * Description: Queue class that inherits from the StudentListClass;
 *  operations are all conducted using StudentListClass operations;
 *   no direct array access is permitted
 */
public class StudentQueueClass extends StudentListClass
{
	/**
	 * Constant for displaying spaces
	 */
	private static final String SPACE = " ";
	
	/**
	 * Default constructor
	 */
	public StudentQueueClass()
	{
       super();
	}
	/**
	 * Initialization constructor
	 * @param capacity- integer value to specify initial capacity
	 */
	public StudentQueueClass( int capacity )
	{
       super( capacity );
	}
	/**
	 * Copy constructor
	 * @param copied- StudentQueueClass object to be copied
	 */
	public StudentQueueClass( StudentQueueClass copied )
	{
       super( copied );
	}
	/**
	 * Clears queue using parent's operation
	 */
	public void clearQueue()
	{
       super.clear();
	}
	/**
	 * Dequeues from end of list, front of queue
	 * @return StudentClass object if available; null otherwise
	 */
	public StudentClass dequeue()
	{
       return super.removeNthStudent( super.getCurrentSize() );
	}
	/**
	 * Displays queue from tail to head
	 */
	public void displayQueue()
	{
       int spaces = 2, index;
       System.out.println("Tail of Queue: ");
       
       for(index = 0; index < super.getCurrentSize(); index++ )
       {
    	   displaySpaces( spaces );
    	   spaces += 2;
    	   System.out.println( super.getNthStudent( index + 1 ) );
       }
       displaySpaces(spaces);
       System.out.println("Head of Queue: ");
	}
	/**
	 * Recursive method displays spaces for displayQueue
	 * @param numSpaces- integer value specifying number of spaces to display
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
	 * Enqueues to beginning of list, tail of queue
	 * @param student- StudentClass object to be enqueued
	 */
	public void enqueue( StudentClass student )
	{
       super.insertDataAtBeginning( student );
	}
	/**
	 * Reports queue empty using parent's operation
	 * @overrides isEmpty in class StudentListClass
	 * @return Boolean result of empty test
	 */
	public boolean isEmpty()
	{
       return super.isEmpty();
	}
	/**
	 * Peek at front of queue, no state change
	 * @return StudentClass object if available; null otherwise
	 */
	public StudentClass peekFront()
	{
       return super.getNthStudent( 0 );
	}
}
