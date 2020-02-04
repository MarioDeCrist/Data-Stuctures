package p4_package;
/**
 * @author Mario DeCristofaro
 * @project four
 * @date October 2, 2019
 */

public class SudokuGeneratorClass 
{
    /**
     * Private cell class to identify fixed (locked) cells and data (number)
     */
    private class CellNode
    {
       boolean fixedCell;
       int value;
       /**
        * Default constructor for cell node
        */
       public CellNode()
	   {
          fixedCell = false;
          value = 0;
	   }
       /**
        * Copy constructor for cell node
        * @param copied- CellNode object to be copied
        */
       public CellNode( CellNode copied )
       {
          fixedCell = copied.fixedCell;
          value = copied.value;
       }
    }
    /**
     * Constant for side of grid
     */
    private final int GRID_SIDE = 9;
    /**
     * Constant for side of sub grid
     */
    private final int SUB_GRID_SIDE = 3;
    /**
     * Constant for range of numbers in Sudoku
     */
    private final int SUDOKU_RANGE = 9;
    /**
     * Two dimensional array for holding cell nodes with fixed/locked code and
     *  number
     */
    private CellNode[][] sudokuArray;
    
    /**
     * Default generator class array sets up and initializes the Sudoku array
     */
    public SudokuGeneratorClass()
    {
       int rowIndex, colIndex;
       sudokuArray = new CellNode[ GRID_SIDE ][ GRID_SIDE ];
       
       for( rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++ )
       {
    	   for( colIndex = 0; colIndex < GRID_SIDE; colIndex++ )
    	   {
    		   sudokuArray[ rowIndex ][ colIndex ] = new CellNode();
    	   }
       }
    }
    /**
     * Generator class copy constructor
     * <p>
     * Note: Must create new CellNode for each copied element to eliminate
     *  aliasing
     * @param copied - SudokuGeneratorClass object to be copied
     */
    public SudokuGeneratorClass( SudokuGeneratorClass copied )
    {
       int rowIndex, colIndex;
       sudokuArray = new CellNode[ GRID_SIDE ][ GRID_SIDE ];
       
       for( rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++ )
       {
    	   for( colIndex = 0; colIndex < GRID_SIDE; colIndex++ )
    	   {
    		   sudokuArray[ rowIndex ][ colIndex ] = new CellNode(
                                  copied.sudokuArray[ rowIndex ][ colIndex ] );
    	   }
       }
    }
    /**
     * Method called to create the Sudoku game
     * <p>
     * Note: Sets up sub diagonal grids and calls helper
     * @param numEmpties- integer value indicating how many Sudoku cells to
     *  leave for the game player to fill in
     * @param showGrid- Boolean value that supports display of the transactions
     *  and the grids as the program runs
     */
    public void createSudoku( int numEmpties, boolean showGrid )
    {
    	setDiagonalSubGrids();
    	
    	if( showGrid )
    	{
    		displayGrid();
    	}
    	createSudokuHelper( 0, 0, showGrid );
    	displayGrid();
    	removeNumbers( numEmpties );
    	System.out.println( "SolutionFound:" );
    	displayGrid();
    }
    /**
     * Sudoku creation method tries new numbers at each cell from left to right
     *  and top to bottom; backtracks if numbers don't work for given cell
     * @param rowPos- integer row location of current element
     * @param colPos- integer column location of current element
     * @param showGrid- Boolean indicator that shows grids and transactions as
     *  the method progresses if true
     * @return Boolean value to indicate success or failure of the recursive
     *  process
     */
    private boolean createSudokuHelper( int rowPos, int colPos,
                                                             boolean showGrid )
    {
       int testVal;
       
       if( colPos >= GRID_SIDE )
       {
    	   colPos = 0;
    	   rowPos++;
    	   if(showGrid)
    	   {
    		   displayGrid();
    	   }
       }
       if( rowPos >= GRID_SIDE )
       {
    	   return true;
       }
       if( sudokuArray[ rowPos ][ colPos ].fixedCell )
       {
    	   return createSudokuHelper( rowPos, colPos + 1, showGrid );
       }
       for( testVal = 1; testVal <= SUDOKU_RANGE; testVal++ )
       {
    	   if( !hasConflict( rowPos, colPos, testVal ) )
    	   {
    		   sudokuArray[ rowPos ][ colPos ].value = testVal;
    		   if( showGrid )
    		   {
    			   System.out.println( "\t\tTrying " + testVal + " at row: " +
                                            rowPos + " and column " + colPos );
    		   }
    		   if( createSudokuHelper( rowPos, colPos + 1, showGrid ) )
        	   {
        		   return true;
        	   }
    	   }
    	   sudokuArray[ rowPos ][ colPos ].value = 0;
       }
       if( showGrid )
       {
    	   System.out.println( "\tBacktracking from row: " + rowPos +
                                                    " and column: " + colPos );
       }
       return false;
       
    }
    /**
     * Displays grid as it is currently set up
     * Uses character formatting for grid display
     */
    public void displayGrid()
    {
       int rowIndex, colIndex;
       System.out.println( "\n\t#===|===|===#===|===|===#===|===|===#" );
       
       for( rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++ )
       {
    	   System.out.print( "\t# " );
    	   
    	   for( colIndex = 0; colIndex < GRID_SIDE; colIndex++ )
    	   {
    		   if( sudokuArray[ rowIndex ][ colIndex ].value == 0 )
    		   {
    			   System.out.print(" ");
    		   }
    		   else
    		   {
    			   System.out.print(sudokuArray[ rowIndex ][ colIndex ].value);
    		   }
    		   if( ( colIndex + 1 ) % SUB_GRID_SIDE == 0 )
    		   {
    			   System.out.print( " # " );
    		   }
    		   else
    		   {
    			   System.out.print( " | " );
    		   }
    	   }
    	   if( ( rowIndex + 1 ) % SUB_GRID_SIDE == 0 )
    	   {
    		   System.out.println( "\n\t#===|===|===#===|===|===#===|===|===#"
                                                                             );
    	   }
    	   else
    	   {
    		   System.out.println( "\n\t#---|---|---#---|---|---#---|---|---#"
                                                                             );
    	   }
       }
       System.out.println();
    }
    /**
     * Generates random value between 1 and 9
     * <p>
     * Note: Uses double stage process. Calls random to get number between 1
     *  and 9, then loops that many times generating random values.
     *   Finally takes the last value generated. Uses Math.random
     * @return integer random value generated
     */
    private int genRandSudokuValue()
    {
       int randIter = (int)( Math.random() * SUDOKU_RANGE ) + 1;
       int index, randVal = 0;
       
       for( index = 0; index < randIter; index++ )
       {
    	   randVal = (int)( Math.random() * SUDOKU_RANGE ) + 1;
       }
       return randVal;
       
    }
    /**
     * Checks for conflict of a given number in a given element
     * <p>
     * Note: Uses isInRow, isInCol, and isInSubGrid in one line of code to
     *  indicate if the number has already been used in the same row,
     *   the same column, or the same sub grid
     * @param rowLocIndex - integer row index of element
     * @param colLocIndex - integer column index of element
     * @param value- integer value to be tested for conflict
     * @return Boolean result of test
     */
    private boolean hasConflict( int rowLocIndex, int colLocIndex, int value )
    {
       return isInRow( rowLocIndex, value ) || isInCol( colLocIndex, value ) ||
                                isInSubGrid( rowLocIndex, colLocIndex, value );
    }
    /**
     * Checks for conflict of value in the same column
     * @param colIndex - integer column index
     * @param value - integer value to be tested
     * @return Boolean result of test
     */
    private boolean isInCol( int colIndex, int value )
    {
       int rowIndex;
       
       for( rowIndex = 0; rowIndex < GRID_SIDE; rowIndex++ )
       {
    	    if( sudokuArray[ rowIndex ][ colIndex ].value == value )
    	    {
    	    	return true;
    	    }
       }
       return false;
    }
    /**
     * Checks for conflict of value in the same row
     * @param rowIndex - integer row index
     * @param value - integer value to be tested
     * @return Boolean result of test
     */
    private boolean isInRow( int rowIndex, int value )
    {
       int colIndex;
       
       for( colIndex = 0; colIndex < GRID_SIDE; colIndex++ )
       {
    	   if( sudokuArray[ rowIndex ][ colIndex ].value == value )
    	   {
    		   return true;
    	   }
       }
       return false;
    }
    /**
     * Checks for conflict of value in sub grid
     * <p>
     * Note: Must find upper left corner of sub grid from the row and column
     *  indices, then search the sub grid
     * @param rowLocIndex - integer row index of test item
     * @param colLocIndex - integer column index of test item
     * @param value - integer value to be tested
     * @return Boolean result of test
     */
    private boolean isInSubGrid( int rowLocIndex, int colLocIndex, int value )
    {
       int rowIndex, colIndex;
       int startRowIndex = rowLocIndex - ( rowLocIndex % SUB_GRID_SIDE );
       int startColIndex = colLocIndex - ( colLocIndex % SUB_GRID_SIDE );
       
       for( rowIndex = startRowIndex; rowIndex < startRowIndex + SUB_GRID_SIDE;
                                                                   rowIndex++ )
       {
    	   for( colIndex = startColIndex; colIndex < startColIndex +
                                                    SUB_GRID_SIDE; colIndex++ )
    	   {
    		   if( sudokuArray[ rowIndex ][ colIndex ].value == value )
    		   {
    			   return true;
    		   }
    	   }
       }
       return false;
    }
    /**
     * Randomly removes the specified number of items from the Sudoku array
     *  for preparing the game
     * @param numbersToBeRemoved - integer number of elements to be cleared
     */
    private void removeNumbers( int numbersToBeRemoved )
    {
       int index;
       int randRow = genRandSudokuValue();
       int randCol = genRandSudokuValue();
       
       for( index = 0; index < numbersToBeRemoved; index++ )
       {
    	   while( sudokuArray[ randRow ][ randCol ].value == 0 )
    	   {
    		   randRow = genRandSudokuValue() - 1;
    		   randCol = genRandSudokuValue() - 1;
    	   }
    	   sudokuArray[ randRow ][ randCol ].value = 0;
       }
    }
    /**
     * Sets all three diagonal sub grids in preparation for setting other
     *  values
     * <p>
     * Note: Calls setInitialSubGrid for each grid to be set up
     */
    private void setDiagonalSubGrids()
    {
       int rowIndex = 0, colIndex= 0;
       
       setInitialSubGrid( rowIndex, colIndex );
       
       rowIndex += 3; colIndex += 3;
       setInitialSubGrid( rowIndex, colIndex );
       
       rowIndex += 3; colIndex += 3;
       setInitialSubGrid( rowIndex, colIndex );
    }
    /**
     * Sets one sub grid with non-repeating values
     * @param startRow - integer row of upper left corner of sub grid to set up
     * @param startCol- integer column of upper left corner of sub grid to
     *  set up
     */
    private void setInitialSubGrid( int startRow, int startCol )
    {
       int rowIndex, colIndex, randValue = 5;
       
       for( rowIndex = startRow; rowIndex < startRow + SUB_GRID_SIDE;
                                                                   rowIndex++ )
       {
    	   for( colIndex = startCol; colIndex < startCol + SUB_GRID_SIDE;
                                                                   colIndex++ )
    	   {
    		   do
    		   {
    			   randValue = genRandSudokuValue();
    		   }
    		   while( isInSubGrid( rowIndex, colIndex, randValue ) );
    		   
    		   sudokuArray[ rowIndex ][ colIndex ].value = randValue;
    		   sudokuArray[ rowIndex ][ colIndex ].fixedCell = true;
    	   }
       }
    }
}
