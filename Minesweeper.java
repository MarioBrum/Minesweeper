import java.util.Random;
import java.util.Scanner;
public class Minesweeper{
	private String [][]board;
	private int rows;
	private int columns;
	private int bombs;
	private String [][]boardToShow;
	private boolean [][]opened;
	private Random r;
	
	// Construtor da classe
    public Minesweeper(int rows, int columns, int bombs) {
        this.rows = rows;
        this.columns = columns;
		this.bombs = bombs;
		this.r = new Random();
		this.boardToShow = new String[this.rows][this.columns];
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				boardToShow[i][j] = "[]";	
			}	
			System.out.println();
		}
		opened = new boolean[rows][columns];
    }
	//only for test, the main game board with showed bombs
	public void printBoard(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				System.out.print(board[i][j] + " ");	
			}	
			System.out.println();
		}
	}
	
	public void showBoard(){
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				System.out.print(" " +boardToShow[i][j] + " ");	
			}	
			System.out.println();
		}
	}
	
	public void createGame(){
		this.board = new String[this.rows][this.columns];
		int auxBombs = 0;
		while(bombs > auxBombs){
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					if(r.nextInt(5) == 0 && bombs > auxBombs){
						board[i][j] = "*";
						auxBombs++;
					}	
				}
			}
		}
		//printBoard();
		System.out.println();
		int value;
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.columns; j++){
				value = returnAdjacents(i, j);
				if(value == -1){
					board[i][j] = "*";
				}
				else{
					board[i][j] = " " +String.valueOf(value);
				}			
			}
		}
		printBoard();
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
		if(this.board[x][y] == "*"){return -1;}
		if(x-1 >= 0 && y-1 >= 0 && this.board[x-1][y-1] == "*"){cont++;}
		if(y-1 >= 0 && this.board[x][y-1] == "*") { cont++;}
		if(x+1 <= this.rows-1 && y-1 >= 0 && this.board[x+1][y-1] == "*") { cont++;}
		if(x-1 >= 0 && this.board[x-1][y] == "*") { cont++;}
		if(x+1 <= this.rows-1 && this.board[x+1][y] == "*") { cont++;}
		if(x-1 >= 0 && y+1 <= this.columns-1 && this.board[x-1][y+1] == "*") { cont++;}
		if(y+1 <= columns-1 && this.board[x][y+1] == "*") { cont++;}
		if(x+1 <= rows-1 && y+1 <= columns-1 && this.board[x+1][y+1] == "*") { cont++;}
		
		return cont;
		
	}

	//method to open a board when clicked in a field with no bombs and not adjacent to a bomb
	public void openAdjacents(int x, int y) {
    if (x < 0 || x >= rows || y < 0 || y >= columns || opened[x][y] || !board[x][y].contains("0")) {
        return;
    }
    opened[x][y] = true;
    boardToShow[x][y] = " 0";
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            if (dx == 0 && dy == 0) continue;          
            int newX = x + dx;
            int newY = y + dy;
            openAdjacents(newX, newY);
        }
    }
}

	public boolean open(int x,int y){
		int value = returnAdjacents(x, y);
		System.out.println("value:" + value);
		if(value == 0 ){
			//System.out.println("value before openadjacents:" + value);
			boardToShow[x][y] = " 0";
			openAdjacents(x, y);
			//System.out.println("value after openadjacents:" + value);
		}
		if(value == -1){return false;}
		else{
			boardToShow[x][y] = " " + String.valueOf(value);
			return true;
		}
	}
	//visual mechanic to mark a bomb in showed board
	public void mark(int x,int y){
		if(board[x][y].contains("0")){
			System.out.println("Unnable to mark, already open!!");
		}
		else{
			boardToShow[x][y] = " P";
		}
	}

	public static void main(String [] args){
		//0 equals nothing
		//-1 equals bomb
		Scanner input = new Scanner(System.in);
		int x;int y;
		Minesweeper mineGame = new Minesweeper(9,9,12);
		boolean gameState = true;
		mineGame.createGame();
		while(gameState){
			mineGame.showBoard();
			int option = 1;
			System.out.println("Select a move:\n"
							+ "1. Open board\n"
							+ "2. Mark as bomb");
			option = input.nextInt();
			System.out.println("Write a coordinate(ex.: 0 4): ");
			x = input.nextInt();
			y = input.nextInt();
			switch (option) {
				case 1:
					gameState = mineGame.open(x, y);
					break;
				
				case 2:
					mineGame.mark(x, y);
					break;
			
				default:
					System.out.println("Wrong command!!");
					break;
			}
		}
		System.out.println("You lose!");
		mineGame.printBoard();
		//mineGame.printBoard();
		//System.out.println("returnAdjacents 0,0: " + mineGame.returnAdjacents(0,0));
		//System.out.println("returnAdjacents 5,5: " + mineGame.returnAdjacents(5,5));
		//System.out.println("returnAdjacents 8,8: " + mineGame.returnAdjacents(8,8));
		
		
	}
}
