
public class Player {
	int playerId;
	String name;
	int score;
	Board board;
	
	public Player() {
		playerId = 0;
		name = "";
		score = 0;
	}
	
	public Player(int id, String n, int s, Board b) {
		playerId = id;
		name = n;
		score = s;
		board = b;
	}
	
	public String getName() {
		return(name);
	}
	
	public int getScore() {
		return(score);
	}
	public int getID() {
		return playerId;
	}
	
	public int[] move(int id, int die) {
		int newPosition = id + die;
		boolean flag;
		int[] result = new int[5];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		
		do {
			flag = false;
			// check for snake' s head
			for (int j = 0; j < board.getSnakes().length ; j++) {
				 if (board.getSnakes()[j].getHeadId() == newPosition) {
					 flag = true;
					 newPosition = board.getSnakes()[j].getTailId();
					 result[1]++;
					 System.out.println(name + " was bitten by a snake");
					 break;
				 }
			 }
			
			// check for ladder's downstep 
			for (int j = 0; j < board.getLadders().length ; j++) {
				 if (board.getLadders()[j].getDownStepId() == newPosition) {
					 if (board.getLadders()[j].getBroken() == false) {
						 flag = true;
						 newPosition = board.getLadders()[j].getUpStepId();
						 result[2]++;
						 System.out.println(name + " climped a ladder");
						 board.getLadders()[j].setBroken(true);
						 break;
					 }
				 }
			 }
			
			// check for apple
			for (int j = 0; j < board.getApples().length ; j++) {
				 if (board.getApples()[j].getAppleTileId() == newPosition) {
					 if (board.getApples()[j].getColor() == "red"){
					     score += board.getApples()[j].getPoints(); 
					     result[3]++;
					 }else {
						 score -= board.getApples()[j].getPoints();
						 result[4]++;
					 }
					 board.getApples()[j].setPoint(0); 
					 System.out.println(name + " ate a " + board.getApples()[j].getColor() + " apple");
					 break;
				 }
			 }
		} while(flag);

		result[0] = newPosition;
		return(result);
	}
	
	public int[] move(int id, int die ,Board nBoard) {
		int newPosition = id + die;
		boolean flag;
		double virtualscore=score;
		int[] result = new int[5];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		
		do {
			flag = false;
			// check for snake' s head
			for (int j = 0; j < nBoard.getSnakes().length ; j++) {
				 if (nBoard.getSnakes()[j].getHeadId() == newPosition) {
					 flag = true;
					 newPosition = nBoard.getSnakes()[j].getTailId();
					 result[1]++;
					 System.out.println(name + " was bitten by a snake");
					 break;
				 }
			 }
			
			// check for ladder's downstep 
			for (int j = 0; j < nBoard.getLadders().length ; j++) {
				 if (nBoard.getLadders()[j].getDownStepId() == newPosition) {
					 if (nBoard.getLadders()[j].getBroken() == false) {
						 flag = true;
						 newPosition = nBoard.getLadders()[j].getUpStepId();
						 result[2]++;
						 System.out.println(name + " climped a ladder");
						 nBoard.getLadders()[j].setBroken(true);
						 break;
					 }
				 }
			 }
			
			// check for apple
			for (int j = 0; j < nBoard.getApples().length ; j++) {
				 if (nBoard.getApples()[j].getAppleTileId() == newPosition) {
					 if (nBoard.getApples()[j].getColor() == "red"){
					     virtualscore += nBoard.getApples()[j].getPoints(); 
					     result[3]++;
					 }else {
						 virtualscore -= nBoard.getApples()[j].getPoints();
						 result[4]++;
					 }
					 nBoard.getApples()[j].setPoint(0); 
					 System.out.println(name + " ate a " + nBoard.getApples()[j].getColor() + " apple");
					 break;
				 }
			 }
		} while(flag);

		result[0] = newPosition;
		return(result);
	}
}
