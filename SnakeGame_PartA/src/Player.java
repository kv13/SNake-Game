
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
	public int getID() {
		return playerId;
	}
	public void setID(int id) {
		playerId=id;
	}
	public String getName() {
		return(name);
	}
	
	public int getScore() {
		return(score);
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
	public int[] move(int id, int die,Board Nboard) {//κανω overriding την συναρτηση για να μπορω να την καλω για το καθε board που δημιουργω για καθε κινηση
		int Nscore=0;
		int newPosition = id + die;
		boolean flag;
		int[] result = new int[5];
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		
		do {
			flag = false;
			// check for snake' s head
			for (int j = 0; j < Nboard.getSnakes().length ; j++) {
				 if (Nboard.getSnakes()[j].getHeadId() == newPosition) {
					 flag = true;
					 newPosition =Nboard.getSnakes()[j].getTailId();
					 result[1]++;
					 System.out.println(name + " was bitten by a snake if he makes that move");
					 break;
				 }
			 }
			
			// check for ladder's downstep 
			for (int j = 0; j <Nboard.getLadders().length ; j++) {
				 if (Nboard.getLadders()[j].getDownStepId() == newPosition) {
					 if (Nboard.getLadders()[j].getBroken() == false) {
						 flag = true;
						 newPosition =Nboard.getLadders()[j].getUpStepId();
						 result[2]++;
						 System.out.println(name + " climped a ladder if he makes this move");
						 Nboard.getLadders()[j].setBroken(true);
						 break;
					 }
				 }
			 }
			
			// check for apple
			for (int j = 0; j <Nboard.getApples().length ; j++) {
				 if (Nboard.getApples()[j].getAppleTileId() == newPosition) {
					 if (Nboard.getApples()[j].getColor() == "red"){
					     Nscore +=Nboard.getApples()[j].getPoints(); 
					     result[3]++;
					 }else {
						 Nscore -=Nboard.getApples()[j].getPoints();
						 result[4]++;
					 }
					 Nboard.getApples()[j].setPoint(0); 
					 System.out.println(name + " ate a " +Nboard.getApples()[j].getColor() + " apple");
					 break;
				 }
			 }
		} while(flag);

		result[0] = newPosition;
		return(result);
	}
	
}
