import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
public class Game {
	int round;
	
	public Game() {
		round = 0;
	}
	
	public Game(int r) {
		round = r;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int r) {
		this.round = r;
	}

	public static void main(String[] args) {
		int N = 10;
		int M = 20;
		int dice;
		int newPosition = 0;
		boolean check=false;
		Game game = new Game(0);
		Board board = new Board(N, M, 3, 3, 6);
		board.createBorad();
		board.createElementBoard();
		Player player1;
		player1= new Player(0, "player 1", 0, board);
		HeuristicPlayer hPlayer2;
		hPlayer2=new HeuristicPlayer(2,"player 2",0,board);
		ArrayList<Player> players=new ArrayList<Player>(2);
		players.add(player1);
		players.add(hPlayer2);
		Map<Integer,Integer> sorted=new TreeMap<Integer,Integer>();
		sorted=game.setTurns(players);
		int counter=1;
		for(int i:sorted.keySet()) {
			System.out.println("Player with id"+sorted.get(i)+"roll the dice "+i+"so he plays"+counter);
			counter++;	
		}
		for(int i:sorted.keySet()) {
			if(sorted.get(i)==player1.getID()) {
				check=true;
				break;
			}
			if(sorted.get(i)==hPlayer2.getID()) {
				check=false;
				break;
			}
		}
		int[] currentPosition=new int[players.size()];
		for (int i = 0; i < players.size(); i++) {
			currentPosition[i] = 1;
		}
		System.out.println("THe game starts now");
		while(game.getRound()<100) {
			for(int i=0;i<sorted.size();i++) {
				if(check) {
					dice=(int)(Math.random()*6+1);
					newPosition=player1.move(currentPosition[0],dice)[0];
					currentPosition[0]=newPosition;
					if(currentPosition[0]>(N*M)) {
						System.out.println("Player"+player1.getName()+"won");
						break;
					}
					dice=hPlayer2.getNextMove(currentPosition[1]);
					newPosition=hPlayer2.move(currentPosition[1],dice)[0];
					currentPosition[1]=newPosition;
					if(currentPosition[1]>N*M) {
						System.out.println("Player"+hPlayer2.getName()+"won");
						break;
					}
				}
				if(!check) {
					dice=hPlayer2.getNextMove(currentPosition[0]);
					newPosition=hPlayer2.move(currentPosition[0],dice)[0];
					currentPosition[0]=newPosition;
					if(currentPosition[0]>N*M) {
						System.out.println("Player"+hPlayer2.getName()+"won");
						break;
					}
					dice=(int)(Math.random()*6+1);
					newPosition=player1.move(currentPosition[1],dice)[0];
					currentPosition[1]=newPosition;
					if(currentPosition[1]>N*M) {
						System.out.println("Player"+player1.getName()+"won");
						break;
					}
				}
			}
			if(currentPosition[0]>(N*M) || currentPosition[1]>(N*M))break;
			game.setRound(game.getRound()+1);
			
		}
		if(game.getRound()==100) {
			System.out.println("the game has reach its limit of 100 rounds");
		}
		
		/*int[] currentPosition = new int[players.length];
		int newPosition = 0;
		String winnerName = null;
		
		for (int i = 0; i < players.length; i++) {
			currentPosition[i] = 0;
		}
		
		System.out.println("*********** The game begins **********");
		System.out.println();
		game.round = 0;
		
		for (;;) {
			game.round++;
			//System.out.println("Round " + game.round);
			for (int i = 0; i < players.length; i++) {
				int die = 1 + (int)(Math.random()*6);
				newPosition = players[i].move(currentPosition[i], die)[0];
				if (newPosition >= N*M) {
					winnerName = players[i].getName();
					break;
				}
				currentPosition[i] = newPosition;
			}
			if (newPosition >= N*M) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("*********** The game is over *********");
		System.out.println();
		System.out.println("Rounds played: "+game.round);
		
		for (int i = 0; i < players.length; i++) {
			System.out.println(players[i].getName()+" gatherd " + players[i].getScore() + " points");
		}
		System.out.println(winnerName +" won the game!!!");*/
		System.exit(1);
	}
	public Map<Integer,Integer> setTurns(ArrayList<Player> players){
		Map<Integer,Integer> hash=new HashMap<Integer,Integer>();
		int[] dice=new int[players.size()];
		boolean check=false;
		for(int i=0;i<players.size();i++) {
			for(;;) {
				dice[i]=(int)(Math.random()*6+1);
				for(int j=0;j<i;j++) {
					if(dice[j]==dice[i])check=true;
				}
				if(!check) {
					check=false;
					break;
				}
			}
			hash.put(dice[i], players.get(i).getID());
		}
		Map<Integer,Integer> tree=new TreeMap<Integer,Integer>(hash);
		return tree;
	}

}
