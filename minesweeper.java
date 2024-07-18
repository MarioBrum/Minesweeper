import java.util.Random();
public class Minesweeper{
	private int [][] board;
	private final int rows = 9;
	private final int columns = 9;
	private final int bombs = 10;
	public static void main(String [] args){
		//0 equals nothing
		//-1 equals bomb
		int[][] board = new int[rows][columns];
		
	}
	
	public static void createAGame(){
		for(int i = 0; i < 
	}

	//return -1 if is a bomb
	//return 0 if is not a bomb and dont have bombs adjacents
	//return a number of bombs adjacents
	/*			x=2 y=2
	1,1| 1,2 | 1,3 		x-1,y-1| x-1,y | x-1,y+1 
	2,1| 2,2 | 2,3		x,y-1  | x,y   | x,y+1
	3,1| 3,2 | 3,3		x+1,y-1| x+1,y | x+1,y+1
	*/
	public static int returnAdjacents(int x,int y){
		int cont = 0;
		
		
	}
	
}
