package p6_package;

/**
 * @author Mario DECristofaro
 * @date October 21, 2019
 * @project six Description: Class wrapper for a Java linked list of
 * StudentClass objects, with management operations
 */
public class StudentLinkedListClass
{
    /**
     * Member data
     */
    private StudentClass headRef;
    /**
     * For N not found in search
     */
    public static final int NOT_FOUND = -1;

    /**
     * Default constructor, initializes linked list
     */
    public StudentLinkedListClass()
    {
        headRef = null;
    }

    /**
     * Copy constructor, creates new nodes to eliminate aliasing
     * 
     * @param copied - StudentListClass object to be copied
     */
    public StudentLinkedListClass( StudentLinkedListClass copied )
    {
        StudentClass wkgRefCopied = copied.headRef;
        StudentClass wkgRef;
        headRef = null;

        if( wkgRefCopied != null )
        {
            headRef = new StudentClass( copied.headRef );
            wkgRef = headRef;
            wkgRefCopied = wkgRefCopied.nextRef;

            while( wkgRefCopied != null )
            {
                wkgRef.nextRef = new StudentClass( wkgRefCopied );
                wkgRef = wkgRef.nextRef;
                wkgRefCopied = wkgRefCopied.nextRef;
            }
        }
    }

    /**
     * Recursive helper method that appends data to end of list
     * 
     * @param wkgRef- StudentClass reference for linking nodes
     * @param student- StudentClass data to be added to the list
     * @return StudentClass link to calling method
     */
    private StudentClass appendDataAtEnd_Helper( StudentClass wkgRef,
                                                         StudentClass student )
    {
        if( wkgRef == null )
        {
            return student;
        }

        wkgRef.nextRef = appendDataAtEnd_Helper( wkgRef.nextRef, student );
        return wkgRef;
    }

    /**
     * Sets data at end of list
     * <p>
     * Note: Uses appendDataAtEnd_Helper
     * 
     * @param student - StudentClass object to be appended to list
     */
    public void appendDataAtEnd( StudentClass student )
    {
        headRef = appendDataAtEnd_Helper( headRef, student );
    }

    /**
     * Clears linked list of all valid values by setting linked list head to
     * null,
     */
    public void clear()
    {
        headRef = null;
    }

    /**
     * Displays student list
     */
    public void displayList()
    {
        StudentClass wkgRef = headRef;
        System.out.println( "Student Class List: " );

        while( wkgRef != null )
        {
            System.out.println( wkgRef.toString() );
            wkgRef = wkgRef.nextRef;
        }
        System.out.println();
    }

    /**
     * Gets number of student if found in list
     * 
     * @param student - StudentClass object for finding N
     * @return integer N of the StudentClass object,or NOT_FOUND if not in list
     */
    public int findStudentNumber( StudentClass student )
    {
        StudentClass wkgRef = headRef;
        int counter = 1;

        while( wkgRef != null )
        {
            if( wkgRef.compareTo( student ) == 0 )
            {
                return counter;
            }
            wkgRef = wkgRef.nextRef;
            counter++;
        }
        return NOT_FOUND;
    }

    /**
     * Recursive helper method for finding list size
     * 
     * @param wkgRef - StudentClass reference for recursion management
     * @return integer size at each recursive level
     */
    private int getCurrentSize_Helper( StudentClass wkgRef )
    {
        if( wkgRef != null )
        {
            return getCurrentSize_Helper( wkgRef.nextRef ) + 1;
        }
        
        return 0;
    }

    /**
     * Description: Gets current size of linked list
     * <p>
     * Note: Uses getCurrentSize_Helper
     * 
     * @return integer size of linked list
     */
    public int getCurrentSize()
    {
        return getCurrentSize_Helper( headRef );
    }

    /**
     * Acquires the Nth item in the list, starting with N = 1
     * 
     * @param N_value - integer N value for accessing student
     * @return StudentClass value at element or null if attempt to acquire data
     * out of bounds
     */
    public StudentClass getNthStudent( int N_value )
    {
        int counter = 1;
        StudentClass wkgRef = headRef;

        while( counter != N_value && wkgRef != null )
        {
            wkgRef = wkgRef.nextRef;
            counter++;
        }
        return wkgRef;
    }

    /**
     * Sets data at beginning of list; moves all subsequent data up by one
     * element
     * <p>
     * Note: No failure mode; data will be inserted at beginning no matter what
     * the size of the linked list is
     * 
     * @param student - StudentClass object to set at beginning
     */
    public void insertDataAtBeginning( StudentClass student )
    {
        StudentClass begStudent = student;
        begStudent.nextRef = headRef;
        headRef = begStudent;
    }

    /**
     * Description: Links data into list at Nth position, where N starts at 1
     * <p>
     * Note: Allows item to be appended to end of list
     * 
     * @param NthPos - integer value to indicate insertion location
     * @param student - StudentClass object to be inserted at Nth position in
     * list
     * @return Boolean indication of successful operation
     */
    public boolean insertDataAtNthPosition( int NthPos, StudentClass student )
    {
        StudentClass wkgRef = headRef;
        StudentClass temp;
        int counter = 1;
        if( NthPos == counter )
        {
            insertDataAtBeginning( student );
            return true;
        }
        else
        {
            while( wkgRef != null && counter < NthPos - 1 )
            {
                wkgRef = wkgRef.nextRef;
                counter++;
            }
            if( wkgRef != null )
            {
                temp = new StudentClass( student );
                temp.nextRef = wkgRef.nextRef;
                wkgRef.nextRef = temp;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for empty list
     * 
     * @return Boolean result of test for empty
     */
    public boolean isEmpty()
    {
        return( headRef == null );
    }

    /**
     * Description: Removes Nth item from linked list
     * 
     * @param numberN - integer number of element value to be removed,starts at
     *  N = 1
     * @return removed StudentClass value if successful, null if not
     */
    public StudentClass removeNthStudent( int numberN )
    {
        StudentClass wkgRef = headRef;
        StudentClass removed = null;
        int counter = 1;
        
        if( numberN == counter )
        {
            removed = wkgRef;
            headRef = headRef.nextRef;
        }
        else
        {
            while( getNthStudent( numberN - 1 ) != null && counter < numberN -
                                                                            1 )
            {
                wkgRef = wkgRef.nextRef;
                counter++;
            }
            if( getNthStudent( numberN - 1 ) != null )
            {
                removed = wkgRef.nextRef;
                wkgRef.nextRef = removed.nextRef;
            }
        }
        return removed;
    }

    /**
     * Description: Replaces item in linked list at specified Nth position,
     * where N starts at 1
     * 
     * @param NthPos - integer value to indicate position of value to be
     * replaced
     * @param student - StudentClass object to be inserted at Nth position
     * @return Boolean success if replaced, or failure if incorrect N was used
     */
    public boolean replaceDataAtNthPosition( int NthPos, StudentClass student )
    {
        int counter = 1;
        StudentClass wkgRef = headRef;
        
        while( counter < NthPos && wkgRef != null )
        {
            wkgRef = wkgRef.nextRef;
            counter++;
        }
        if( getNthStudent( NthPos - 1 ) != null )
        {
            wkgRef = student;
            wkgRef.name = student.name;
            wkgRef.gender = student.gender;
            wkgRef.gpa = student.gpa;
            wkgRef.studentID = student.studentID;
            return true;
        }
        return false;
    }
}
