package p4_package;

public class SudokuGeneratorClassMain
{
    public static void main( String[] args )
    {
    	boolean param = true;
    	int number = 25;
    	
    	SudokuGeneratorClass sudoku = new SudokuGeneratorClass();
    	
    	sudoku.createSudoku(number, param);
    }
}
