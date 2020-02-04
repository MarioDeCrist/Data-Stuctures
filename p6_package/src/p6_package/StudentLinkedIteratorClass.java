package p6_package;

/**
 * @author Mario DeCristofaro
 * @date October 21, 2019
 * @project Six Iterator class uses StudentLinkedListClass data; conducts
 * iterator operations
 */
public class StudentLinkedIteratorClass
{
    /**
     * Current index of iterator
     */
    private int currentIndex;
    /**
     * StudentClassList data used by this class
     */
    private StudentLinkedListClass iteratorList;
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
    public StudentLinkedIteratorClass()
    {
        currentIndex = 0;
        iteratorList = new StudentLinkedListClass();
    }

    /**
     * Copy constructor for StudentIteratorClass
     * 
     * @param copied - StudentIteratorClass object to be copied
     */
    public StudentLinkedIteratorClass( StudentLinkedIteratorClass copied )
    {
        currentIndex = copied.currentIndex;
        iteratorList = new StudentLinkedListClass( copied.iteratorList );
    }

    /**
     * Clears array
     */
    public void clear()
    {
        iteratorList.clear();
        currentIndex = 0;
    }

    /**
     * Recursive method displays spaces for runDiagnosticDisplay
     * 
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
     * 
     * @return StudentClass object returned; FAILED_ACCESS if not found
     */
    public StudentClass getAtCurrent()
    {
        return iteratorList.getNthStudent( currentIndex );
    }

    /**
     * Inserts new value after value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless the list
     * was empty prior to data set
     * 
     * @param newValue - StudentClass object to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean insertAfterCurrent( StudentClass newValue )
    {
        if( isEmpty() )
        {
            currentIndex = 1;
            return iteratorList.insertDataAtNthPosition( currentIndex ,
                                                                    newValue );
        }
        return iteratorList.insertDataAtNthPosition( currentIndex + 1 ,
                                                                    newValue );
    }

    /**
     * Inserts new before value at iterator cursor
     * <p>
     * Note: Current value must remain the same after data set, unless the list
     * was empty prior to data set
     * 
     * @param newValue - StudentClass object to be inserted in list
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean insertBeforeCurrent( StudentClass newValue )
    {
        boolean flag = false;
        if( currentIndex < 1 )
        {
            currentIndex = 1;
            flag = iteratorList.insertDataAtNthPosition( currentIndex,
                                                                    newValue );
        }
        else
        {
            currentIndex++;
            flag = iteratorList.insertDataAtNthPosition( currentIndex - 1,
                                                                    newValue );
        }
        return flag;
    }

    /**
     * Reports if iterator cursor is at beginning If list is empty, cursor is
     * not at beginning
     * 
     * @return Boolean result of action; true if at beginning, false otherwise
     */
    public boolean isAtBeginning()
    {
        return !isEmpty() && ( currentIndex == 1 );
    }

    /**
     * Reports if iterator cursor is at end If list is empty, cursor is not at
     * end
     * 
     * @return Boolean result of action; true if at end, false otherwise
     */
    public boolean isAtEnd()
    {
        return !isEmpty() && ( currentIndex == iteratorList.getCurrentSize() );
    }

    /**
     * Reports if list is empty
     * 
     * @return Boolean result of action; true if empty, false otherwise
     */
    public boolean isEmpty()
    {
        return iteratorList.isEmpty();
    }

    /**
     * If possible, moves iterator cursor one position to the right, or next
     * 
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
     * 
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
     * Note: cursor must be located at succeeding element unless last item
     * removed
     * 
     * @return StudentClass object removed from list, or null if not found
     */
    public StudentClass removeAtCurrent()
    {
        StudentClass wkg = iteratorList.removeNthStudent( currentIndex );
        
        if( currentIndex > iteratorList.getCurrentSize() )
        {
            movePrev();
        }
        return wkg;
    }

    /**
     * Replaces value at iterator cursor with new value
     * 
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
        
        for( index = 1; index <= iteratorList.getCurrentSize(); index++ )
        {
            displaySpaces( spaces );
            spaces += 2;
            //finds cursor
            if( index == currentIndex )
            {
                System.out.print( LEFT_BRACKET );
            }
            
            System.out.print( iteratorList.getNthStudent( index ) );
            
            if( index == currentIndex )
            {
                System.out.print( RIGHT_BRACKET );
            }
            System.out.println();
        }
        
        displaySpaces( spaces );
        System.out.println( "Right End Of Iterator" );
    }

    /**
     * Sets iterator cursor to beginning of list
     * 
     * @return Boolean result of action; true if successful, false otherwise
     */
    public boolean setToBeginning()
    {
        if( isEmpty() )
        {
            return false;
        }
        
        currentIndex = 1;
        return true;
    }

    /**
     * Sets iterator cursor to the end of the list
     *
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
