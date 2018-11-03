import java.util.Random;
public class Board {
	private int N,M;
	private int[][] tiles;
	Snake[] snakes;
	Ladder[] ladders;
	Apple[] apples;
	Board(){
		N=0;
		M=0;
		snakes=null;
		ladders=null;
		apples=null;
		//edw tha arxikopoihsw tous pinakes snakes,ladders,apples???
	}
	Board(int x, int y,int numSnakes ,int numLadders,int numApples){
		N=x;
		M=y;
		tiles=new int[N][M];
		snakes=new Snake[numSnakes];
		ladders=new Ladder[numLadders];
		apples=new Apple[numApples];
	}
	Board(Board board) {
		N=board.get_N();
		M=board.get_M();
		tiles=new int[N][M];
		tiles=board.get_tiles();
		snakes=new Snake[board.get_Snakes().length];
		snakes=board.get_Snakes();
		ladders=new Ladder[board.get_Ladders().length];
		ladders=board.get_Ladders();
		apples=new Apple[board.get_Apples().length];
		apples=board.get_Apples();
		
	}
	public  void set_N(int n) {
		N=n;
	}
	public  void set_M(int m) {
		M=m;
	}
	public int get_N() {
		return N;
	}
	public int get_M() {
		return M;
	}
	public int[][] get_tiles(){
		return tiles;
	}
	public void set_Snakes(int numSnakes) {
		snakes=new Snake[numSnakes];
	}
	public  Snake[] get_Snakes() {
		return snakes;
	}
	public void set_Ladders(int numLadders) {
		ladders=new Ladder[numLadders];
	}
	public Ladder[] get_Ladders() {
		return ladders;
	}
	public void set_Apples(int numApples) {
		apples=new Apple[numApples];
		
	}
	public Apple[] get_Apples() {
		return apples;
	}
	public void creatBoard() {
		Random rm=new Random();
		int number;
		int counter=1;
		int piliko;
		int upoloipo;
		int raw=0;
		boolean is_there_a_snake;
		for(int i=0;i<N;i=i+2) {
			for(int j=0;j<M;j++) {
				tiles[N-1-i][j]=counter;
				counter=counter+1;
				}
			counter=counter+M;
		}
		counter=1+M;
		for(int i=1;i<N-1;i=i+2) {
			for(int  j=0;j<M;j++) {
				tiles[N-1-i][M-1-j]=counter;
				counter=counter+1;
			}
			counter=counter+M;
		}
		for(int i=0;i<snakes.length;i++) {
			snakes[i]=new Snake();
			snakes[i].set_snakeId(i);
			number=rm.nextInt(N*M)+1;
			snakes[i].set_headId(number);
			piliko=number/M;
			upoloipo=number%M;
			if(upoloipo==0) {
				if(piliko>1)raw=piliko-1;
			}
			else {
				raw=piliko;
			}
			number=rm.nextInt(Math.abs(raw*M))+1;
			snakes[i].set_tailId(number);
		}
		for (int i=0;i<ladders.length;i++) {
			ladders[i]=new Ladder();
			ladders[i].set_ladderId(i);
			number=rm.nextInt(N*M)+1;
			ladders[i].set_upStepId(number);
			piliko=number/M;
			upoloipo=number%M;
			if(upoloipo==0) {
				if(piliko>1)raw=piliko-1;
			}
			else {
				raw=piliko;
			}
			number=rm.nextInt(Math.abs(raw*M))+1;
			ladders[i].set_downStepId(number);
			ladders[i].set_broken(false);
		}
		for(int i=0;i<apples.length;i++) {
			apples[i]=new Apple();
			apples[i].set_appleId(i);
			apples[i].set_color("red");
			for(;;) {
				number=rm.nextInt(N*M)+1;
				is_there_a_snake=false;
				for(int j =0;j<snakes.length;j++) {
					if(snakes[j].get_headId()==number) is_there_a_snake=true;
			}
				if(is_there_a_snake)continue;
				apples[i].set_appleTiledId(number);
				//ta xrwmata kai tous pontous poso na ta dwsw ???
				if(!is_there_a_snake) break ;
		}
	}
  }
	public void createElementBoard() {
		int counter;
		String[][] elementBoardSnakes;
		String[][] elementBoardLadders;
		String[][] elementBoardApples;
		elementBoardSnakes=new String[N][M];
		elementBoardLadders=new String[N][M];
		elementBoardApples=new String[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				elementBoardSnakes[i][j]="__";
				elementBoardLadders[i][j]="__";
				elementBoardApples[i][j]="__";
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int x=0;x<snakes.length;x++) {
					if(tiles[i][j]==snakes[x].get_headId()) {
						if(elementBoardSnakes[i][j]=="__") {
							elementBoardSnakes[i][j]="SH"+snakes[x].get_snakeId();
						}
						else if(elementBoardSnakes[i][j]!="__") {
							elementBoardSnakes[i][j]=elementBoardSnakes[i][j]+"SH"+snakes[x].get_snakeId();
						}
					}
					if(tiles[i][j]==snakes[x].get_tailId()) {
						if(elementBoardSnakes[i][j]=="__") {
							elementBoardSnakes[i][j]="ST"+snakes[x].get_snakeId();
						}
						else if(elementBoardSnakes[i][j]!="__") {
							elementBoardSnakes[i][j]=elementBoardSnakes[i][j]+"ST"+snakes[x].get_snakeId();
						}
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int x=0;x<ladders.length;x++) {
					if(tiles[i][j]==ladders[x].get_upStepId()) {
						if(elementBoardLadders[i][j]=="__") {
							elementBoardLadders[i][j]="LU"+ladders[x].get_ladderId();
						}
						else if(elementBoardLadders[i][j]!="__") {
							elementBoardLadders[i][j]=elementBoardLadders[i][j]+"LU"+ladders[x].get_ladderId();
						}
					}
					if(tiles[i][j]==ladders[x].get_downStepId()) {
						if(elementBoardLadders[i][j]=="__") {
							elementBoardLadders[i][j]="LD"+ladders[x].get_ladderId();
						}
						else if(elementBoardLadders[i][j]!="__") {
							elementBoardLadders[i][j]=elementBoardLadders[i][j]+"LD"+ladders[x].get_ladderId();
						}
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int x=0;x<apples.length;x++) {
					if(tiles[i][j]==apples[x].get_appleTiledId()) {
						if(elementBoardApples[i][j]=="__") {
							elementBoardApples[i][j]="AR"+apples[x].get_appleId();//θεωρω για αρχη ολα τα μηλα κοκκινα 
						}
						else if(elementBoardApples[i][j]!="__") {
							elementBoardApples[i][j]=elementBoardApples[i][j]+"AR"+apples[x].get_appleId();
						}
					}
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(elementBoardSnakes[i][j]+" ");
			}
			System.out.println();
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(elementBoardLadders[i][j]+" ");
			}
			System.out.println();
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(elementBoardApples[i][j]+" ");
			}
			System.out.println();
		}
	}
}
