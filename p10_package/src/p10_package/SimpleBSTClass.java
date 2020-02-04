package p10_package;

/**
 * @author Mario DeCristofaro
 * @date November 18, 2019
 * @project ten
 * Simple Binary Search Tree class that can store SimpleStudentClass data
 */
public class SimpleBSTClass
{
    /**
     * Root of BST
     */
    private SimpleStudentClass treeRoot;
    
    /**
     * Default class constructor
     */
    public SimpleBSTClass()
    {
        treeRoot = null;
    }
    
    /**
     * Copy constructor
     * @param copied - SimpleBSTClass object to be copied
     */
    public SimpleBSTClass( SimpleBSTClass copied )
    {
        treeRoot = copyConstructorHelper( copied.treeRoot );
    }
    
    /**
     * Clears tree of all data
     */
    public void clearTree()
    {
        treeRoot = null;
    }
    
    /**
     * Recursive copy constructor helper
     * @param wkgCopiedRef - reference to SimpleStudentClass node
     * @return SimpleStudentClass reference to link to calling method/node
     */
    private SimpleStudentClass copyConstructorHelper( SimpleStudentClass
                                                                 wkgCopiedRef )
    {
        SimpleStudentClass localNode = null;
        
        if( wkgCopiedRef != null )
        {
            localNode = new SimpleStudentClass( wkgCopiedRef );
            localNode.leftChildRef = copyConstructorHelper(
                                                   wkgCopiedRef.leftChildRef );
            localNode.rightChildRef = copyConstructorHelper(
                                                  wkgCopiedRef.rightChildRef );
        }
        
        return localNode;
    }
    
    /**
     * Displays BST in order
     */
    public void displayInOrder()
    {
        displayInOrderHelper( treeRoot );
    }
    
    /**
     * Recursively implements inOrder traversal action
     * @param wkgRef - SimpleStudentClass tree root reference at the current
     *  recursion level
     */
    private void displayInOrderHelper( SimpleStudentClass wkgRef )
    {
        if( wkgRef != null )
        {
            displayInOrderHelper( wkgRef.leftChildRef );
            System.out.println( wkgRef  + " " );
            displayInOrderHelper( wkgRef.rightChildRef );
        }
    }
    
    /**
     * Returns larger of two values
     * <p>
     * Note: used by treeHeightHelper
     * @param one- one of the two values to be tested
     * @param other- the other of the two values to be tested
     * @return highest value of the two input values
     */
    private int getMax( int one, int other )
    {
        int max = one;
        
        if( other > max )
        {
            max = other;
        }
        
        return max;
    }
    
    /**
     * Finds height of BST Note: empty tree: -1; root node only: 0; number of
     *  edges thereafter
     * @return - maximum number of edges from root node to lowest part of tree
     */
    public int getTreeHeight()
    {
        return treeHeightHelper( treeRoot );
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: Overloaded insert uses insert helper method with a
     *  SimpleStudentClass object
     * @param newNode - SimpleStudentClass object to be added to BST
     * @return Boolean result of action
     */
    public boolean insert( SimpleStudentClass newNode )
    {
        if( isEmpty() )
        {
            treeRoot = new SimpleStudentClass( newNode );
            return true;
        }
        
        return insertHelper( treeRoot, newNode );
    }
    
    /**
     * Insert method for BST
     * <p>
     * Note: Overloaded insert uses insert method with individual student
     *  information data items
     * @param inName- name data to be added to BST
     * @param inStudentID - student ID data to be added to BST
     * @param inGender- gender data to be added to BST
     * @param inGPA- gpa data to be added to BST
     * @return Boolean result of action
     */
    public boolean insert( String inName, int inStudentID, char inGender,
                                                                 double inGPA )
    {
        SimpleStudentClass student = new SimpleStudentClass( inName,
                                                inStudentID, inGender, inGPA );
        
        return insert( student );
    }
    
    /**
     * Insert helper method for BST insert action
     * <p>
     * Note: Does not allow duplicate entries (i.e., duplicate student IDs)
     * @param wkgRef - SimpleStudentClass tree root reference at the current
     *  recursion level
     * @param newNode - SimpleStudentClass object to be added to BST
     * @return Boolean result of insertion action
     */
    private boolean insertHelper( SimpleStudentClass wkgRef, SimpleStudentClass
                                                                      newNode )
    {
        if( wkgRef.studentID > newNode.studentID )
        {
            if( wkgRef.leftChildRef != null )
            {
                return insertHelper( wkgRef.leftChildRef, newNode );
            }
            
            wkgRef.leftChildRef = new SimpleStudentClass( newNode );
            return true;
        }
        
        else if( wkgRef.studentID < newNode.studentID )
        {
            if( wkgRef.rightChildRef != null )
            {
                return insertHelper( wkgRef.rightChildRef, newNode );
            }
            
            wkgRef.rightChildRef = new SimpleStudentClass( newNode );
            return true;
        }
        
        return false;
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
     * Recursively searches tree from given node to minimum value node, stores
     *  data value found, and then unlinks the node
     * @param minParent - SimpleStudentClass reference to parent of child node;
     *  used for linking from parent to child's right child
     * @param minChild- SimpleStudentClass reference to child node to be tested
     * @return SimpleStudentClass reference containing removed node
     */
    private SimpleStudentClass removeFromMin( SimpleStudentClass minParent,
                                                  SimpleStudentClass minChild )
    {
        SimpleStudentClass temp;
        
        if( minChild.leftChildRef != null ) 
        {
            return removeFromMin( minChild, minChild.leftChildRef );
        }
        
        temp = minChild;
        minParent.leftChildRef = minChild.rightChildRef;
        return temp;
    }
    
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: verifies data node is in the tree, then uses remove helper method
     * @param studentID- item that is used for search/removal
     * @return SimpleStudenClass result of remove action
     */
    public SimpleStudentClass removeNode( int studentID )
    {
        SimpleStudentClass temp = search( studentID );
        
        if( temp != null )
        {
            treeRoot = removeNodeHelper( treeRoot, studentID );
        }
        
        return temp;
    }
    
    /**
     * Recursive helper for BST remove action
     * <p>
     * Note: uses removeFromMin method
     * Note: Since removeNode calling method verifies data node existence, this
     *  method does not need to treat missing node condition
     * @param wkgRef - SimpleStudentClass tree root reference at the current
     *  recursion level
     * @param studentID - item that is used for search/removal
     * @return SimpleStudentClass reference result of remove helper action
     */
    private SimpleStudentClass removeNodeHelper( SimpleStudentClass wkgRef,
                                                                int studentID )
    {
        SimpleStudentClass temp;
        
        if( wkgRef.studentID < studentID )
        {
            wkgRef.rightChildRef = removeNodeHelper( wkgRef.rightChildRef,
                                                                   studentID );
        }
        
        else if( wkgRef.studentID > studentID )
        {
            wkgRef.leftChildRef = removeNodeHelper( wkgRef.leftChildRef,
                                                                   studentID );
        }
        
        else if( wkgRef.leftChildRef == null )
        {
            wkgRef = wkgRef.rightChildRef;
        }
        
        else if( wkgRef.rightChildRef == null )
        {
            wkgRef = wkgRef.leftChildRef;
        }
        
        else
        {
            if( wkgRef.rightChildRef.leftChildRef == null )
            {
                wkgRef.studentID = wkgRef.rightChildRef.studentID;
                wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
            }
            
            else
            {
                temp = removeFromMin( wkgRef, wkgRef.rightChildRef );
                wkgRef = temp;
            }
        }
        
        return wkgRef;
    }
    
    /**
     * Searches for data in BST given necessary student ID key
     * @param studentID - search value
     * @return SimpleStudentClass reference to found data
     */
    public SimpleStudentClass search( int studentID )
    {
        return searchHelper( treeRoot, studentID );
    }
    
    /**
     * Helper method for BST search action
     * @param wkgRef - SimpleStudentClass working reference at the current
     *  recursion level
     * @param studentID- item containing key (student ID number)
     * @return Boolean result of search
     */
    private SimpleStudentClass searchHelper( SimpleStudentClass wkgRef,
                                                                int studentID )
    {
        if( wkgRef != null )
        {
            if( wkgRef.studentID == studentID )
            {
                return wkgRef;
            }
            
            if( wkgRef.studentID > studentID )
            {
                return searchHelper( wkgRef.leftChildRef, studentID );
            }
            
            else
            {
                return searchHelper( wkgRef.rightChildRef, studentID );
            }
        }
        
        return null;
    }
    
    /**
     * Helper method to find height of BST
     * @param wkgRef - SimpleStudentClass working reference used at the current
     *  level of recursion
     * @return height of tree - maximum number of edges from root node to
     *  lowest part of tree
     */
    private int treeHeightHelper( SimpleStudentClass wkgRef )
    {
        if( wkgRef != null )
        {
            return getMax( treeHeightHelper( wkgRef.leftChildRef ),
                              treeHeightHelper( wkgRef.rightChildRef ) ) + 1;
        }
        
        return -1;
    }
    
}
