
public class Board {
	int N, M;
	int[][] tiles;
	Snake[] snakes;
	Ladder[] ladders;
	Apple[] apples;
	
	public Board() {
		N = 0;
		M = 0;
		tiles = new int[N][M];
		snakes = new Snake[0];
		ladders = new Ladder[0];
		apples = new Apple[0];
	}
	
	public Board(Board b) {
		this.N = b.M;
		this.M = b.N;
		this.tiles = b.getTiles();
		this.ladders = new Ladder[b.getLadders().length];
		for (int i = 0; i < b.getLadders().length; i++) {
			this.ladders[i] = new Ladder(b.getLadders()[i]);
		}
		this.apples = new Apple[b.getApples().length];
		for (int i = 0; i < b.getApples().length; i++) {
			this.apples[i] = new Apple(b.getApples()[i]);
		}
		this.snakes = new Snake[b.getSnakes().length];
		for (int i = 0; i < b.getSnakes().length; i++) {
			this.snakes[i] = new Snake(b.getSnakes()[i]);
		}
	}
	
	public Board(int n, int m, int s, int l, int a) {
		N = n;
		M = m;
		tiles = new int[n][m];
		snakes = new Snake[s];
		ladders = new Ladder[l];
		apples = new Apple[a];
	}
	
	public int getN() {
		return N;
	}
	
	public int getM() {
		return M;
	}
	
	public int[][] getTiles() {
		return tiles;
	}
	
	public Snake[] getSnakes() {
		return snakes;
	}
	
	public Ladder[] getLadders() {
		return ladders;
	}
	
	public Apple[] getApples() {
		return apples;
	}
	
	public void setN(int n) {
		N = n;
	}
	
	public void setLadders(Ladder[] l) {
		ladders = l;
	}
	
	public void setApples(Apple[] a) {
		apples = a;
	}
	
	public void createBorad() {
		// tiles
		for (int i = N-1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if ((i+1)%2 == 0) {
					tiles[i][j] = j+1 + (N-i-1)*M;
				}else {
					tiles[i][M-j-1] = j+1 + (N-i-1)*M;
				}	
			}
		}
		
		// snakes
		for (int i = 0; i < snakes.length ; i++) {
			int randomHead = N + (int)(Math.random()*(N*M - N)); //the head must not be at level 1
			int levelHead = (randomHead-1)/N;
			int randomTail = (int)(Math.random()*(N*levelHead)); 
			snakes[i] = new Snake(i, randomHead, randomTail);
		}
		
		// ladders
		for (int i = 0; i < ladders.length ; i++) {
			int randomUpStep =  N + (int)(Math.random()*(N*M - N)); 
			int levelUpStep = (randomUpStep-1)/N;
			int randomDownStep;
			boolean F;
			
			// avoid having a base of a ladder on a tile that there is a snake's head
			do {
				F = false;
				randomDownStep = (int)(Math.random()*(N*levelUpStep)); 
				 for (int j = 0; j < snakes.length ; j++) {
					 if (snakes[j].getHeadId() == randomDownStep) {
						 F = true;
						 break;
					 }
				 }
			}while(F);
			ladders[i] = new Ladder(i, randomUpStep, randomDownStep, false);
		}		
		
		// apples
		for (int i = 0; i < apples.length ; i++) {
			String color = "";
			int points = 0;
			int tileId;
			boolean F;
			
			// avoid having an apple on a tile that there is a snake's head
			do {
				F = false;
				 tileId = (int)(Math.random()*(N*M));
				 for (int j = 0; j < snakes.length ; j++) {
					 if (snakes[j].getHeadId() == tileId) {
						 F = true;
						 break;
					 }
				 }
			}while(F);
			
			// color
			if ((int)(Math.random()*2)==1) {
				color = "red";
			}else {
				color = "black";
			}
			
			// points
			points = (int)(Math.random()*11);
			
			// create apple
			apples[i] = new Apple(i, tileId, color, points);
		}	
	}
	
	public void createElementBoard() {
		System.out.println("************ Element Board ************");
		System.out.println("Snakes");
		String[][] elementBoardSnake = new String[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				elementBoardSnake[i][j] = "___";
				// check for snake
				for (int t = 0; t < snakes.length ; t++) {
					 if (snakes[t].getHeadId() == tiles[i][j]) {
						 elementBoardSnake[i][j] = "SH" + snakes[t].getSnakeId();
						 break;
					 }
					 if (snakes[t].getTailId() == tiles[i][j]) {
						 elementBoardSnake[i][j] = "ST" + snakes[t].getSnakeId();
						 break;
					 }
				 }
				
				System.out.print(elementBoardSnake[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Ladders");
		String[][] elementBoardLadder = new String[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				elementBoardLadder[i][j] = "___";
				// check for ladder 
				for (int t = 0; t < ladders.length ; t++) {
					 if (ladders[t].getUpStepId() == tiles[i][j]) {
						 elementBoardLadder[i][j] = "LU" + ladders[t].getLadderId();
						 break;
					 }
					 if (ladders[t].getDownStepId() == tiles[i][j]) {
						 elementBoardLadder[i][j] = "LD" + ladders[t].getLadderId();
						 break;
					 }
				 }

				System.out.print(elementBoardLadder[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println("Apples");
		String[][] elementBoardApple = new String[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				elementBoardApple[i][j] = "___";
				// check for ladder 
				for (int t = 0; t < apples.length ; t++) {
					 if (apples[t].getAppleTileId() == tiles[i][j]) {
						 if (apples[t].getColor() == "red") {
							 elementBoardApple[i][j] = "AR" + apples[t].getAppleId();
							 break; 
						 }else {
							 elementBoardApple[i][j] = "AB" + apples[t].getAppleId();
							 break;
						 }
					 }
				 }

				System.out.print(elementBoardApple[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}
}
