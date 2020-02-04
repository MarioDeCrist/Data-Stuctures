package p6_package;

/**
 * @author Mario DeCristofaro
 * @date October 21, 2019
 * @project six Description: Stack class that inherits from the
 * StudentListClass; operations are all conducted using StudentListClass
 * operations; no direct array access is permitted
 */
public class StudentLinkedStackClass extends StudentLinkedListClass
{
    private static final String SPACE = " ";

    /**
     * Default constructor
     */
    public StudentLinkedStackClass()
    {
        super();
    }

    /**
     * Copy constructor
     * 
     * @param copied- StudentStackClass object to be copied
     */
    public StudentLinkedStackClass( StudentLinkedStackClass copied )
    {
        super( copied );
    }

    /**
     * Clears stack using parent's operation
     */
    public void clearStack()
    {
        super.clear();
    }

    /**
     * Recursive method displays spaces for displayStack
     * 
     * @param numSpaces- integer value specifying number of spaces to display
     */
    private void displaySpaces( int numSpaces )
    {
        if( numSpaces > 0 )
        {
            System.out.print(SPACE);
            displaySpaces( numSpaces - 1 );
        }
    }

    /**
     * Displays stack from bottom to top
     */
    public void displayStack()
    {
        int spaces = 2, index;
        System.out.println( "Bottom of Stack: " );
        
        for( index = 0; index < super.getCurrentSize(); index++ )
        {
            displaySpaces( spaces );
            spaces += 2;
            System.out.println( super.getNthStudent( index + 1 ) );
            
        }
        displaySpaces( spaces );
        System.out.println( "Top of Stack: " );
    }

    /**
     * Reports stack empty using parent's operation Overrides: isEmpty in class
     * StudentLinkedListClass
     * 
     * @return Boolean result of empty test
     */
    public boolean isEmpty()
    {
        return super.isEmpty();
    }

    /**
     * Peeks at the top of the stack, no state change
     * 
     * @return StudentClass object viewed at the top of the stack
     */
    public StudentClass peekTop()
    {
        return super.getNthStudent( getCurrentSize() );
    }

    /**
     * Pops from top of stack
     * 
     * @return StudentClass object popped from stack
     */
    public StudentClass pop()
    {
        return super.removeNthStudent( getCurrentSize() );
    }

    /**
     * Pushes onto top of stack
     * 
     * @param student - StudentClass object to be pushed onto stack
     */
    public void push( StudentClass student )
    {
        super.appendDataAtEnd( student );
    }
}
