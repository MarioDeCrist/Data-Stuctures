package p9_package;

/**
 * @author Mario DeCristofaro
 * @date November 11, 2019
 * @project nine
 * Array-based max heap class that manages students
 */
public class ArrayHeapClass
{
    /**
     * Management data for array
     */
    private int arrayCapacity;
    /**
     * Management data for array
     */
    private int arraySize;
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    /**
     * Array for heap
     */
    private int[] heapArray;
    /**
     * Constant for not found value
     */
    public final int NOT_FOUND = 99999;
    /**
     * Default constructor sets up array management conditions and default
     *  display flag setting
     */
    public ArrayHeapClass()
    {
        arraySize = 0;
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        heapArray = new int[ arrayCapacity ];
        displayFlag = true;
    }
    /**
     * Copy constructor copies array and array management conditions and
     *  default display flag setting
     * @param copied - ArrayHeapClass object to be copied
     */
    public ArrayHeapClass( ArrayHeapClass copied )
    {
        arraySize = copied.arraySize;
        arrayCapacity = copied.arrayCapacity;
        heapArray = new int[ arrayCapacity ];
        displayFlag = copied.displayFlag;
        int index;
        
        for( index = 0; index < arraySize; index++ )
        {
            heapArray[ index ] = copied.heapArray[ index ];
        }
    }
    /**
     * Accepts integer item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data
     *  addition
     * Note: Always checks for resize before adding data
     * @param newItem - integer item to be added
     */
    public void addItem( int newItem )
    {
        checkForResize();
        heapArray[ arraySize ] = newItem;
        
        if( displayFlag )
        {
            System.out.println( "\nAdding new value: " + newItem );
        }
        
        bubbleUpArrayHeap( arraySize );
        arraySize++;
    }
    /**
     * Recursive operation to reset data in the correct order for the max heap
     *  after new data addition
     * @param currentIndex - index of current item being assessed, and moved up
     *  as needed
     */
    private void bubbleUpArrayHeap( int currentIndex )
    {
        int parentIndex = ( currentIndex - 1 ) / 2;
        int temp;
        String child;
        
        if( currentIndex > 0 )
        {
            if( heapArray[ currentIndex ] > heapArray[ parentIndex ] )
            {
                temp = heapArray[ parentIndex ];
                heapArray[ parentIndex ] = heapArray[ currentIndex ];
                heapArray[ currentIndex ] = temp;
            }
            
            if( displayFlag )
            {
                if( currentIndex % 2 == 0 )
                {
                    child = "left";
                }
                
                else
                {
                    child =  "right";
                }
                
                System.out.println("  -BubbleUp: ");
                System.out.println("    -Swapping parent: " + heapArray[
                                  parentIndex ] + " with " + child + " child: "
                                                 + heapArray[ currentIndex ] );
            }
            
            bubbleUpArrayHeap( parentIndex );
        }
    }
    /**
     * Automatic resize operation used prior to any new data addition in the
     *  heap
     * <p>
     * Note: Tests for full heap array, and resizes to twice the current
     *  capacity as required
     */
    private void checkForResize()
    {
        int index;
        int[] temp;
        
        if( arraySize == arrayCapacity )
        {
            arrayCapacity = 2 * arrayCapacity;
            temp = new int[ arrayCapacity ];
            
            for( index = 0; index < arraySize; index++ )
            {
                temp[ index ] = heapArray[ index ];
            }
            
            heapArray = temp;
        }
    }
    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }
    /**
     * Removes integer item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
     *  removal
     * @return integer item removed
     */
    public int removeItem()
    {
        if( !isEmpty() )
        {
            int temp = heapArray[ 0 ];
            heapArray[ 0 ] = heapArray[ arraySize - 1 ];
            arraySize--;
            
            if( displayFlag )
            {
                System.out.println( "\nRemoving student: " + temp );
            }
            
            trickleDownArrayHeap( 0 );
            return temp;
        }
        
        return NOT_FOUND;
    }
    /**
     * Utility method to set the display flag for displaying internal
     *  operations of the heap bubble and trickle operations
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag( boolean setState )
    {
        displayFlag = setState;
    }
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        int index;
        
        for( index = 0; index < arraySize; index++ )
        {
            System.out.print( heapArray[ index ] + ", " );
        }
        
        System.out.println("");
    }
    /**
     * Recursive operation to reset data in the correct order for the max heap
     *  after data removal
     * @param currentIndex - index of current item being assessed, and moved
     *  down as required
     */
    private void trickleDownArrayHeap( int currentIndex )
    {
        int temp;
        int leftChild = ( 2 * currentIndex ) + 1;
        int rightChild = leftChild + 1;
        
        if( leftChild < arraySize && rightChild < arraySize )
        {
            if( heapArray[ leftChild ] > heapArray[ rightChild ] && heapArray[ 
                                      leftChild ] > heapArray[ currentIndex ] )
            {
                if( displayFlag )
                {
                    System.out.println( "  -TrickleDown: " );
                    System.out.println( "    -Swapping Parent: " + heapArray[ 
                                          currentIndex ] + " with left child: "
                                                    + heapArray[ leftChild ] );
                }
                
                temp = heapArray[ leftChild ];
                heapArray[ leftChild ] = heapArray[ currentIndex ];
                heapArray[ currentIndex ] = temp;
                trickleDownArrayHeap( leftChild );
            }
            
            else if( heapArray[ leftChild ] < heapArray[rightChild] &&
                          heapArray[ rightChild ] > heapArray[ currentIndex ] )
            {
                if( displayFlag )
                {
                    System.out.println( "  -TrickleDown: " );
                    System.out.println( "    -Swapping Parent: " + heapArray[ 
                                         currentIndex ] + " with right child: "
                                                   + heapArray[ rightChild ] );
                }
                
                temp = heapArray[ rightChild ];
                heapArray[ rightChild ] = heapArray[ currentIndex ];
                heapArray[ currentIndex ] = temp;
                trickleDownArrayHeap( rightChild );
            }
        }
    }
    
}
