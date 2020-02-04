package p7_package;
/**
 * @author Mario DeCristofaro
 * @date October 28, 2019
 * @project seven
 * Binary Search Tree (BST) class for managing generic data
 * <p>
 * Note: Data used must have implemented Comparable interface
 * @param <GenericData>
 */
public class Generic_BST_Class <GenericData extends Comparable<GenericData>>
{
    /**
     * Traverse code - inorder
     */
    public static final int IN_TRAVERSE = 102;
    /**
     * Traverse code - postorder
     */
    public static final int POST_TRAVERSE = 103;
    /**
     * Traverse code - preorder
     */
    public static final int PRE_TRAVERSE = 101;
    /**
     * Root of BST
     */
    private Node rootNode;
    /**
     * Binary Search Tree node class for managing generic data
     */
    
    private class Node
    {
        /**
         * left child reference
         */
        private Node leftChildRef;
        /**
         * generic data held by node
         */
        private GenericData nodeData;
        /**
         * right child reference
         */
        private Node rightChildRef;
        /**
         * Initialization constructor for data
         * @param inData - GenericData quantity
         */
        public Node( GenericData inData )
        {
            nodeData = inData;
            
            leftChildRef = null;
            rightChildRef = null;
        }
    }    
    /**
     * Default class constructor, initializes BST
     */
    public Generic_BST_Class()
    {
        rootNode = null;
    }
    /**
     * Clears tree
     */
    public void clearTree()
    {
        rootNode = null;
    }
    /**
     * Provides inOrder traversal action using recursion
     * @param wkgRef - Node tree root reference at the current recursion
     *  level
     */
    private void displayInOrder( Node wkgRef )
    {
        if( wkgRef != null )
        {
            displayInOrder( wkgRef.leftChildRef );
            System.out.println( wkgRef.nodeData + " " );
            displayInOrder( wkgRef.rightChildRef );
        }
        
    }
    /**
     * Provides postOrder traversal action using recursion
     * @param wkgRef - Node tree root reference at the current recursion
     *  level
     */
    private void displayPostOrder( Node wkgRef )
    {
        if( wkgRef != null )
        {
            displayPostOrder( wkgRef.leftChildRef );
            displayPostOrder( wkgRef.rightChildRef );
            System.out.println( wkgRef.nodeData + " " );
        }
    }
    /**
     * Provides preOrder traversal action using recursion
     * @param wkgRef - Node tree root reference at the current recursion
     *  level
     */
    private void displayPreOrder( Node wkgRef )
    {
        if( wkgRef != null )
        {
            System.out.println( wkgRef.nodeData + " " );
            displayPreOrder( wkgRef.leftChildRef );
            displayPreOrder( wkgRef.rightChildRef );
        }
    }
    /**
     * Provides user with three ways to display BST data
     * @param traverseCode - int code for selecting BST traversal method,
     *  accepts PRE_TRAVERSE, IN_TRAVERSE, POST_TRAVERSE
     */
    public void displayTree( int traverseCode )
    {
        // in order display
        if( traverseCode == IN_TRAVERSE )
        {
            displayInOrder( rootNode );
        }
        // post order display
        else if( traverseCode == POST_TRAVERSE )
        {
            displayPostOrder( rootNode );
        }
        // pre order display
        else if( traverseCode == PRE_TRAVERSE )
        {
            displayPreOrder( rootNode );
        }
    }
    /**
     * Insert method for BST
     * <p>
     * Note: method adds first node if tree is empty; otherwise calls
     *  insertHelper method
     * @param inData - GenericData data to be added to BST
     * @return Boolean result of operation
     */
    public boolean insert( GenericData inData )
    {
        if( isEmpty() )
        {
            rootNode = new Node( inData );
            return true;
        }
        
        else
        {
            return insertHelper( rootNode, inData);
        }
    }
    /**
     * Recursive insert helper method for BST insert action
     * Adds new node to left or right of current node; does not allow
     *  duplicate values to be inserted into tree
     * @param wkgRef - Node tree root reference at the current recursion
     *  level
     * @param inData - GenericData item to be added to BST
     * @return Boolean result of operation
     */
    private boolean insertHelper( Node wkgRef, GenericData inData )
    {
        if( wkgRef.nodeData.compareTo( inData ) > 0 )
        {
            if( wkgRef.leftChildRef != null )
            {
                return insertHelper( wkgRef.leftChildRef, inData );
            }
            
            wkgRef.leftChildRef = new Node( inData );
        }
        
        else if( wkgRef.nodeData.compareTo( inData ) < 0 )
        {
            if( wkgRef.rightChildRef != null )
            {
                return insertHelper( wkgRef.rightChildRef, inData );
            }
            
            wkgRef.rightChildRef = new Node( inData );
        }
        
        return false;
    }
    /**
     * Test for empty tree
     * @return Boolean result of test
     */
    public boolean isEmpty()
    {
        return rootNode == null;
    }
    /**
     * Searches tree from given node to minimum value node below it, stores
     *  data value found, unlinks the node, and returns it to the calling
     *   method
     * @param parentNode- Node reference to current node
     * @param childNode- Node reference to child node to be tested
     * @return Node reference containing removed node
     */
    private Node removeFromMin( Node parentNode, Node childNode )
    {
        Node temp;
        
        if( childNode.leftChildRef != null )
        {
            return removeFromMin( childNode, childNode.leftChildRef );
        }
        
        temp = childNode;
        parentNode.leftChildRef = childNode.rightChildRef;
        return temp;
    }
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * Note: uses search initially to get value, if it is in tree; if value
     *  found, remove helper method is called, otherwise returns null
     * @param inData- GenericData that includes the necessary key
     * @return GenericData result of remove action
     */
    public GenericData removeItem( GenericData inData )
    {
        GenericData temp = search( inData );
        
        if( temp != null )
        {
            rootNode = removeItemHelper( rootNode, inData );
        }
        
        return temp;
    }
    /**
     * Remove helper for BST remove action
     * <p>
     * Note: Recursive method returns updated local root to maintain tree
     *  linkage
     * Note: uses removeFromMin method
     * @param wkgRef- Node tree root reference at the current recursion
     *  level
     * @param outData - GenericData item that includes the necessary key
     * @return Node reference result of remove helper action
     */
    private Node removeItemHelper( Node wkgRef, GenericData outData )
    {
        Node temp;
        
        if( wkgRef.nodeData.compareTo( outData ) < 0 )
        {
            wkgRef.rightChildRef = removeItemHelper( wkgRef.rightChildRef,
                                                                     outData );
        }
        
        if( wkgRef.nodeData.compareTo( outData ) > 0 )
        {
            wkgRef.leftChildRef = removeItemHelper( wkgRef.leftChildRef,
                                                                     outData );
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
                wkgRef.nodeData = wkgRef.rightChildRef.nodeData;
                wkgRef.rightChildRef = wkgRef.rightChildRef.rightChildRef;
            }
            
            else
            {
                temp = removeFromMin( wkgRef, wkgRef.rightChildRef );
                wkgRef.nodeData = temp.nodeData;
            }
        }
        
        return wkgRef;
    }
    /**
     * Searches for data in BST given GenericData with necessary key
     * @param searchData - GenericData item containing key
     * @return GenericData reference to found data
     */
    public GenericData search( GenericData searchData )
    {
        return searchHelper( rootNode, searchData );
    }
    /**
     * Helper method for recursive BST search action
     * @param wkgRef - Node tree root reference at the current recursion
     *  level
     * @param searchData - GenericData item containing key
     * @return GenericData item found
     */
    private GenericData searchHelper( Node wkgRef, GenericData searchData )
    {
        if( wkgRef != null )
        {
            if( wkgRef.nodeData.compareTo( searchData ) == 0 )
            {
                return wkgRef.nodeData;
            }
            
            if( wkgRef.nodeData.compareTo( searchData ) > 0 )
            {
                return searchHelper( wkgRef.leftChildRef, searchData );
            }
            
            else
            {
                return searchHelper( wkgRef.rightChildRef, searchData );
            }
        }
        
        return null;
    }
    
}
