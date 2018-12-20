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
		int newPosition = 0;
		int dice;
		boolean check=false;
		Game game = new Game(0);
		Board board = new Board(N, M, 3, 3, 6);
		board.createBorad();
		board.createElementBoard();
		Player player1;
		player1=new Player(1,"player1",0,board);
		MinMaxPlayer player2;
		player2=new MinMaxPlayer(2,"player 2",0,board);
		ArrayList<Player> players=new ArrayList<Player>(2);
		players.add(player1);
		players.add(player2);
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
			if(sorted.get(i)==player2.getID()) {
				check=false;
				break;
			}
		}
		int[] currentPosition=new int[players.size()];
		for (int i = 0; i < players.size(); i++) {
			currentPosition[i] = 1;
		}
		System.out.println("*********** The game begins **********");
		System.out.println();
		game.setRound(0);
		boolean lastcheck=false;
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
					dice=player2.getNextMove(currentPosition[1],currentPosition[0]);
					newPosition=player2.move(currentPosition[1],dice)[0];//also this one need work
					currentPosition[1]=newPosition;
					//this one need implementation player2.statistics(lastcheck);
					if(currentPosition[1]>N*M) {
						System.out.println("Player"+player2.getName()+"won");
						lastcheck=true;
						break;
					}
				}
				if(!check) {
					dice=player2.getNextMove(currentPosition[0],currentPosition[1]);
					newPosition=player2.move(currentPosition[0],dice)[0];//i need to work it
					currentPosition[0]=newPosition;
					//player2.statistics(lastcheck);WORK IS NEED
					if(currentPosition[0]>N*M) {
						System.out.println("Player"+player2.getName()+"won");
						lastcheck=true;
						break;
					}
					dice=(int)(Math.random()*6+1);
					newPosition=player1.move(currentPosition[1],dice)[0];//i need to work on it 
					currentPosition[1]=newPosition;
					if(currentPosition[1]>N*M) {
						System.out.println("Player"+player1.getName()+"won");
						break;
					}					
				}
			}
			if(game.getRound()==100) {
				System.out.println("the game has reach its limit of 100 rounds");
			}			
		}
	}
		/*for (;;) {
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
		System.out.println(winnerName +" won the game!!!");
		
	}*/
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
