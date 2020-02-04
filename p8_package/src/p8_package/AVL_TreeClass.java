package p8_package;

/**
 * @author Mario DeCristofaro
 * @project eight
 * @date November 4, 2019
 * Binary Search Tree (BST) class with self-balancing attributes specifically
 *  using the Adelson-Velsky and Landis (AVL) strategy
 */
public class AVL_TreeClass
{
    /**
     * Constant used to represent dash
     */
    private static final char DASH = '-';
    /**
     * Null character returned if data not available
     */
    private final char NULL_CHAR = '\0';
    /**
     * Class global variable used to display tree structure
     */
    private boolean rowStartFlag;
    /**
     * Constant used to represent space
     */
    private static final char SPACE = ' ';
    /**
     * Root of AVL Tree
     */
    private Node treeRoot;
    
    private class Node
    {
        /**
         * Character data for Node class
         */
        private char data;
        /**
         * left child reference for tree
         */
        private Node leftChildRef;
        /**
         * right child reference for tree
         */
        private Node rightChildRef;
        /**
         * Copy constructor for AVL tree node
         * @param copied- Node object to be copied
         */
        public Node( Node copied )
        {
            data = copied.data;
            leftChildRef = null;
            rightChildRef = null;
        }
        /**
         * Initialization constructor for Node class
         * @param inData - char quantity
         */
        public Node( char inData )
        {
            data = inData;
            leftChildRef = null;
            rightChildRef = null;
        }
        /**
         * Initialization constructor for data and child references
         * @param inData - char quantity
         * @param leftRef - reference for left child
         * @param rightRef - reference for right child
         */
        public Node( char inData, Node leftRef, Node rightRef )
        {
            inData = data;
            leftRef = leftChildRef;
            rightRef = rightChildRef;
        }
    }
    /**
     * Default class constructor
     */
    public AVL_TreeClass()
    {
        treeRoot = null;
        rowStartFlag = false;
    }
    /**
     * Copy constructor
     * @param copied - AVL_TreeClass object to be copied
     */
    public AVL_TreeClass( AVL_TreeClass copied )
    {
        treeRoot = copied.treeRoot;
        rowStartFlag = copied.rowStartFlag;
    }
    /**
     * clears tree
     */
    public void clearTree()
    {
        treeRoot = null;
    }
    /**
     * Recursive copy constructor helper
     * <p>
     * Note: Uses preorder strategy to copy nodes
     * @param wkgCopiedRef - Node reference at which method starts at each
     *  level of recursion
     * @return Node reference to link current node information to methods/Nodes
     *  calling this method
     */
    private Node copyConstructorHelper( Node wkgCopiedRef )
    {
        Node localNode = null;
        
        if( wkgCopiedRef != null )
        {
            localNode = new Node( wkgCopiedRef );
            localNode.leftChildRef = copyConstructorHelper(
                                                   wkgCopiedRef.leftChildRef );
            localNode.rightChildRef = copyConstructorHelper(
                                                  wkgCopiedRef.rightChildRef );
        }
        
        return localNode;
    }
    /**
     * Displays text-graphical representation of one level/line of the AVL tree
     * @param workingNode - node reference at current recursive level
     * @param nodeHeight - height of tree plus two for current height of nodes,
     *  including lowermost null children
     * @param displayLevel- level of tree at which the current line of display
     *  is to be presented
     * @param workingLevel- current level during recursive actions
     */
    private void displayAtTreeLevel( Node workingNode, int nodeHeight,
                                           int displayLevel, int workingLevel )
    {
        char charOut = workingNode.data;
        
        if( workingLevel == displayLevel )
           {
            displayValue( charOut, nodeHeight, workingLevel );

            return;
           }
        
        if( workingNode.leftChildRef != null )
           {
            displayAtTreeLevel( workingNode.leftChildRef, nodeHeight,
                                           displayLevel, workingLevel + 1 );
           }
        
        else
           {
            displayEmptyNodeSpaces(nodeHeight, displayLevel, workingLevel + 1);
           }
            
        if( workingNode.rightChildRef != null )
           {
            displayAtTreeLevel( workingNode.rightChildRef, nodeHeight,
                        displayLevel, workingLevel + 1 );
           }

        else
           {
            displayEmptyNodeSpaces(nodeHeight, displayLevel, workingLevel + 1);
           }              
    }
    /**
     * Local recursive method to display a specified number of a specified
     *  character
     * @param numChars - number of characters to display
     * @param outChar - character to display
     */
    private void displayChars( int numChars, char outChar )
    {
        if( numChars > 0 )
        {
            System.out.print( outChar );
            
            displayChars( numChars - 1, outChar );
        }
    }
    /**
     * Method that displays null or blank nodes for a tree at null locations
     * <p>
     * Note: used by displayAtTreeLevel
     * @param nodeHeight - height of tree plus two for current height of nodes,
     *  including lowermost null children
     * @param displayLevel- level of the tree at which the display will be
     *  applied
     * @param workingLevel- level of tree just below non-null node at which
     *  method is currently working
     */
    private void displayEmptyNodeSpaces( int nodeHeight, int displayLevel,
                                                             int workingLevel )
    {
        int nodesToDisplay = toPower( 2, displayLevel - workingLevel ); 
        char charOut = SPACE;
        
        if( displayLevel == workingLevel )
           {
            charOut = DASH;
           }
        
        while( nodesToDisplay > 0 )
           {
            displayValue( charOut, nodeHeight, displayLevel );
            
            nodesToDisplay--;
           }
    }
    /**
     * Displays text-graphical representation of AVL tree
     */
    public void displayTreeStructure()
    {
        int displayLevel, nodeHeight = getTreeHeight( treeRoot ) + 2;
        int workingLevel = 1;
        
        if( treeRoot != null )
           {
            for( displayLevel = 1; displayLevel <= nodeHeight; displayLevel++ )
               {
                rowStartFlag = true;
                
                displayAtTreeLevel( treeRoot, nodeHeight, 
                                             displayLevel, workingLevel );
                
                System.out.println();
               }
           }
        
        else
           {
            System.out.println( "\nEmpty Tree - No Display");
           }
    }
    /**
     * Method used to display a character or color letter along with calculated
     *  leading spaces
     * <p>
     * Note: used in displayAtTreeLevel and displayEmptyNodeSpaces
     * @param data - data value to display, either letter or color data
     * @param nodeHeight - height of tree plus two for current height of nodes,
     *  including lowermost null children
     * @param workingLevel - current level during recursive actions
     */
    private void displayValue( char data, int nodeHeight, int workingLevel )
    {
        int leadingSpaces;
        
        if( rowStartFlag )
           {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel );

            rowStartFlag = false;
           }
        
        else
           {
            leadingSpaces = toPower( 2, nodeHeight - workingLevel + 1 ) - 1;
           }

        displayChars( leadingSpaces, SPACE );
        
        System.out.print( data );
    }
    /**
     * provides tree height to user
     * <p>
     * Note: uses getTreeHeight
     * @return integer height of tree
     */
    public int findTreeHeight()
    {
        return getTreeHeight( treeRoot );
    }
    /**
     *gets balance factor indicating if tree is unbalanced from given root down
     * @param wkgLocalRef- Node from which balance factor is found
     * @return integer balance factor
     */
    private int getBalanceFactor( Node wkgLocalRef )
    {
        return getTreeHeight( wkgLocalRef.leftChildRef ) - getTreeHeight(
                                                   wkgLocalRef.rightChildRef );
    }
    /**
     * Finds maximum of two given numbers
     * @param one - one of two values to be tested
     * @param other - other of two values to be tested
     * @return greater of the two values
     */
    private int getMax( int one,int other )
    {
        int max = one;
        
        if( other > max )
        {
            max = other;
        }
        
        return max;
    }
    /**
     * Tree height helper method
     * @param wkgLocalRef - Node node from which height is found
     * @return integer height of tree
     */
    private int getTreeHeight( Node wkgLocalRef )
    {
        if( wkgLocalRef != null )
        {
            return getMax( getTreeHeight( wkgLocalRef.leftChildRef ),
                              getTreeHeight( wkgLocalRef.rightChildRef ) ) + 1;
        }
        
        return -1;
    }
    /**
     * In order display of tree
     */
    public void inOrderDisplay()
    {
        inOrderDisplayHelper( treeRoot );
    }
    /**
     * Provides inOrder traversal action
     * @param wkgLocalRef- Node tree root reference at the current recursion
     *  level
     */
    private void inOrderDisplayHelper( Node wkgLocalRef )
    {
        if( wkgLocalRef != null )
        {
            inOrderDisplayHelper( wkgLocalRef.leftChildRef );
            System.out.print( wkgLocalRef.data + " " );
            inOrderDisplayHelper( wkgLocalRef.rightChildRef );
        }
    }
    /**
     * Insert method for AVL Tree
     * <p>
     * Note: uses insert helper method which returns the root node reference to
     *  this method
     * @param inData - char data to be added to AVL Tree
     */
    public void insert( char inData )
    {
        if( isEmpty() )
        {
            System.out.println( "Inserting " + inData + " and balancing " );
            treeRoot = new Node( inData );
        }
        
        else
        {
            System.out.println( "Inserting " + inData + " and balancing " );
            treeRoot = insertHelper( treeRoot, inData );
        }
    }
    /**
     * Insert helper method for AVL Tree insert action
     * <p>
     * Note: Does not allow duplicate keys
     * @param wkgLocalRef - Node tree root reference at the current recursion
     *  level
     * @param inData - char item to be added to AVL Tree
     * @return Node reference to current AVL Tree root
     */
    private Node insertHelper( Node wkgLocalRef, char inData )
    {
        int spacesNum = 2;
        
        if( wkgLocalRef.data > inData )
        {
            if( wkgLocalRef.leftChildRef == null )
            {
                 wkgLocalRef.leftChildRef = new Node( inData );
            }
            
            wkgLocalRef.leftChildRef = insertHelper( wkgLocalRef.leftChildRef,
                                                                      inData );
        }
        
        if( wkgLocalRef.data < inData )
        {
            if( wkgLocalRef.rightChildRef == null )
            {
                 wkgLocalRef.rightChildRef = new Node( inData );
            }
            wkgLocalRef.rightChildRef = insertHelper(wkgLocalRef.rightChildRef,
                                                                      inData );
        }
        
        if( getBalanceFactor( wkgLocalRef ) > 1 && wkgLocalRef.leftChildRef.
                                                         leftChildRef != null )
        {
            displayChars( spacesNum, SPACE );
            System.out.println( "Identified: Left Left Case ");
            spacesNum += 2;
            displayChars( spacesNum, SPACE );
            System.out.println( " - Rotating Right: " );
            return rotateRight( wkgLocalRef );
        }
        
        if( getBalanceFactor( wkgLocalRef ) < -1 && wkgLocalRef.
                                          rightChildRef.rightChildRef != null )
        {
            displayChars( spacesNum, SPACE );
            System.out.println( "Identified: Right Right Case " );
            spacesNum += 2;
            displayChars( spacesNum, SPACE );
            System.out.println( " - Rotating left: " );
            return rotateLeft( wkgLocalRef );
        }
        
        if( getBalanceFactor( wkgLocalRef ) > 1 && wkgLocalRef.
                                           leftChildRef.rightChildRef != null )
        {
            displayChars( spacesNum, SPACE );
            System.out.println( "Identified: Left Right Case " );
            spacesNum += 2;
            displayChars( spacesNum, SPACE );
            System.out.println( " - Rotating Right: " );
            wkgLocalRef.leftChildRef = rotateLeft( wkgLocalRef.leftChildRef );
            return rotateRight( wkgLocalRef );
        }
        
        if( getBalanceFactor( wkgLocalRef ) < -1 && wkgLocalRef.
                                           rightChildRef.leftChildRef != null )
        {
            displayChars( spacesNum, SPACE );
            System.out.println( "Identified: Right Left Case " );
            spacesNum += 2;
            displayChars( spacesNum, SPACE );
            System.out.println( " - Rotating left: " );
            wkgLocalRef.rightChildRef = rotateRight( wkgLocalRef.rightChildRef
                                                                             );
            return rotateLeft( wkgLocalRef );
        }
        
        return wkgLocalRef;
    }
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return treeRoot == null;
    }
    /**
     * Rotates local tree left or CCW
     * @param wkgLocalRef - reference of current item
     * @return Node resulting current root
     */
    private Node rotateLeft( Node wkgLocalRef )
    {
        Node tempNodeRL = wkgLocalRef.rightChildRef.leftChildRef;
        Node temp = wkgLocalRef.rightChildRef;
        wkgLocalRef.rightChildRef = tempNodeRL;
        temp.leftChildRef = wkgLocalRef;
        
        return temp;
    }
    /**
     * Rotates local tree right or CW
     * @param wkgLocalRef - reference of current item
     * @return Node resulting current root
     */
    private Node rotateRight( Node wkgLocalRef )
    {
        Node tempNodeLR = wkgLocalRef.leftChildRef.rightChildRef;
        Node temp = wkgLocalRef.leftChildRef;
        wkgLocalRef.leftChildRef = tempNodeLR;
        temp.rightChildRef = wkgLocalRef;
        
        return temp;
        
    }
    /**
     * Searches for data in AVL Tree given char with necessary key
     * @param searchData - char item containing key
     * @return char reference to found data
     */
    public char search( char searchData )
    {
        return searchHelper( treeRoot, searchData );
    }
    /**
     * Helper method for AVL Tree search action
     * @param wkgLocalRef - Node tree root reference at the current recursion
     *  level
     * @param searchData - char item containing key
     * @return char result of search
     */
    private char searchHelper( Node wkgLocalRef, char searchData )
    {
        if( wkgLocalRef != null )
        {
            if( wkgLocalRef.data == 0 )
            {
                return wkgLocalRef.data;
            }
            
            if( wkgLocalRef.data > 0 )
            {
                return searchHelper( wkgLocalRef.leftChildRef, searchData );
            }
            
            else
            {
                return searchHelper( wkgLocalRef.rightChildRef, searchData );
            }
        }
        
        return NULL_CHAR;
    }
    /**
     * Local recursive method to calculate exponentiation with integers
     * @param base - base of exponentiation
     * @param exponent - exponent of exponentiation
     * @return result of exponentiation calculation
     */
    private int toPower( int base, int exponent )
    {
        if( exponent > 0 )
        {
            return toPower( base, exponent - 1 ) * base;
        }
        
        return 1;
    }
    
}
