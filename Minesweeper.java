import java.util.Random;
public class Minesweeper{
	private int [][]board;
	private int rows;
	private int columns;
	private int bombs;
	private Random r;
	
	// Construtor da classe
    public Minesweeper(int rows, int columns, int bombs) {
        this.rows = rows;
        this.columns = columns;
		this.bombs = bombs;
		this.r = new Random();
    }
	
	public void printBoard(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				System.out.print(board[i][j] + " ");	
			}	
			System.out.println();
		}
	}
	public void createGame(){
		this.board = new int[this.rows][this.columns];
		int auxBombs = 0;
		while(this.bombs > auxBombs){
			for(int i = 0; i < this.rows; i++){
				for(int j = 0; j < this.columns; j++){
					if(r.nextInt(5) == 1){
						this.board[i][j] = -1;
						auxBombs++;
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
	public int returnAdjacents(int x,int y){
		int cont = 0;
		if(this.board[x][y] == -1){return -1;}
		if(this.board[x-1][y-1] ==  -1) { cont++;}
		if(this.board[x][y-1] ==  -1) { cont++;}
		if(this.board[x+1][y-1] ==  -1) { cont++;}
		
		if(this.board[x-1][y] ==  -1) { cont++;}
		if(this.board[x+1][y] ==  -1) { cont++;}
		
		if(this.board[x-1][y+1] ==  -1) { cont++;}
		if(this.board[x][y+1] ==  -1) { cont++;}
		if(this.board[x+1][y+1] ==  -1) { cont++;}
		return cont;
		
	}
	public static void main(String [] args){
		//0 equals nothing
		//-1 equals bomb
		Minesweeper mineGame = new Minesweeper(9,9,10);
		mineGame.createGame();
		mineGame.printBoard();
		System.out.println("returnAdjacents 0,0: " + mineGame.returnAdjacents(0,0));
		System.out.println("returnAdjacents 5,5: " + mineGame.returnAdjacents(5,5));
		System.out.println("returnAdjacents 8,8: " + mineGame.returnAdjacents(8,8));
		
		
	}
}
