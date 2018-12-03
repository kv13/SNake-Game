import java.util.ArrayList;
public class HeuristicPlayer extends Player {
	ArrayList<int[]> path;
	public HeuristicPlayer() {
		playerId=0;
		name="";
		score=0;
		path=null;//edw ligi prosoxi
	}
	public HeuristicPlayer(int id,String n ,int s,Board b) {
		playerId=id;
		name=n;
		score=s;
		board=b;
		path=new ArrayList<int[]>(6);
	}
	public HeuristicPlayer(HeuristicPlayer a) {
		playerId=a.getID();
		name=a.getName();
		score=a.getScore();
		int x=a.getArray().size();
		path=new ArrayList<int[]>(x);
		path=a.getArray();
	}
	public ArrayList<int[]> getArray(){
		return path;
	}
	public double evaluate(int currentPos,int dice ) {
		//η αξιολογηση θα ειναι απο -20 μεχρι 20
		int nextStep=currentPos+dice;
		double evaluation=0;
		int counterSnakes;
		int counterApples;
		int counterLadders;
		boolean checkSnakes=false;
		//first check for snakes head 
		for(counterSnakes=0;counterSnakes<board.getSnakes().length;counterSnakes++) {
			if(nextStep==board.getSnakes()[counterSnakes].getHeadId()) {
				evaluation=evaluation-20;
				checkSnakes=true;
			}
		}
		if(!checkSnakes) {
			//first check for apples red or black 
			for(counterApples=0;counterApples<board.getApples().length;counterApples++) {
				if(nextStep==board.getApples()[counterApples].getAppleTileId()) {
					if(board.getApples()[counterApples].getColor()=="red" &&(board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation+5;
					if(board.getApples()[counterApples].getColor()=="black"&& (board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation-10;
				}
			}
			for(counterLadders=0;counterLadders<board.getLadders().length;counterLadders++) {
				if(nextStep==board.getLadders()[counterLadders].getDownStepId() && !board.getLadders()[counterLadders].getBroken()) {
					evaluation=evaluation+15;
				}
			}
			//εδω θελω να προσθεσω μια ακομα εντολη η οποια θα προσθετει καποιους ακομα ποντους αν μετα την κινηση αυτη
			//ο παικτης βρισκεται μπροστα του αντιπαλου 
			//αν πχ ειναι 3 θα παρει και αλλες 3 μοναδες αν ειναι 6 θα παρει αλλες 6 
		}
		evaluation=evaluation +dice;
		return evaluation;
	}
	public int getNextMove(int currentPos) {
		double[][] possibleMoves=new double[6][2];
		for(int i=1;i<7;i++) {
			possibleMoves[i-1][0]=(double)i;//or maybe only the dice not the nextPos ...I dont know yet
			possibleMoves[i-1][1]=evaluate(currentPos,i);
		}
		double max=-100;
		int bestMove=-1;
		for(int i=0;i<possibleMoves.length;i++) {
			if(possibleMoves[i][1]>max) {
				max=possibleMoves[i][1];
				bestMove=(int)possibleMoves[i][0];
			}
		}
		int[] table=new int[6];
		int[] table1=new int[5];
		table1=move(currentPos,bestMove);
		table[0]=bestMove;
		table[1]=table1[0];
		table[2]=table1[1];
		table[3]=table1[2];
		table[4]=table1[3];
		table[5]=table1[4];
		path.add(table);
		return table[0];
		}
	public void statistics() {//it needs the whole statistics to be added
		int len;
		int[] table=new int[6];
		if(path.size()!=0) {
			len=path.size();
			table=path.get(len-1);
			System.out.println("HeuristiPlayer choose to move "+table[0]+"  Steps");
			System.out.println("So he is now in the position "+ table[1]);
			System.out.println("He is bitten from "+table[2]+" Snakes");
			System.out.println("He climbed "+table[3]+"ladders");
			System.out.println("And he ate"+table[4]+"red apples");
			System.out.println("And "+table[5]+"black Apples");
		}
	}
	
}
