import java.util.Random;
public class minesweeper{
	public static int [][]board;
	public static final int rows = 9;
	public static final int columns = 9;
	public static final int bombs = 10;
	public static Random r;
	
	public static void main(String [] args){
		//0 equals nothing
		//-1 equals bomb
		this.board = new int[this.rows][this.columns];
		r = new Random();
		createGame(this.board);
		printBoard(this.board);
		
	}
	public static void printBoard(int[][] board){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				System.out.print(board[i][j]);	
			}	
			System.out.println();
		}
	}
	public static void createGame(int[][] board){
		int auxBombs = 0;
		while(bombs > auxBombs){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					if(r.nextInt(5) == 0){
						board[i][j] = -1;
					}	
				}
			}
		}
	}

	//return -1 if is a bomb
	//return 0 if is not a bomb and dont have bombs adjacents
	//return a number of bombs adjacents
	/*			x=2 y=2
	1,1| 1,2 | 1,3 		x-1,y-1| x-1,y | x-1,y+1 
	2,1| 2,2 | 2,3		x,y-1  | x,y   | x,y+1
	3,1| 3,2 | 3,3		x+1,y-1| x+1,y | x+1,y+1
	*/
	/*
	public static int returnAdjacents(int x,int y){
		int cont = 0;
		
		
	}
	*/
}
