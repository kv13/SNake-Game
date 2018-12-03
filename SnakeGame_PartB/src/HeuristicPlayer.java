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
		//πρωτα ελεγχουμε αμα υπαρχει καποιο φιδι.Σε αυτη την περιπτωση δινουμε -20 και απορριπτουμε την κινηση κατευθειαν 
		for(counterSnakes=0;counterSnakes<board.getSnakes().length;counterSnakes++) {
			if(nextStep==board.getSnakes()[counterSnakes].getHeadId()) {
				evaluation=evaluation-20;
				checkSnakes=true;
			}
		}
		if(!checkSnakes) {
			//αφου βεβαιωθουμε οτι η κινηση δεν μας παει σε στομα φιδιου ελεγχουμε στην αρχη για μηλο .Αν ειναι 
			//κοκκινο παιρνει +5 η αξιολογηση ενω αν ειναι μαυρο παιρνει -5
			for(counterApples=0;counterApples<board.getApples().length;counterApples++) {
				if(nextStep==board.getApples()[counterApples].getAppleTileId()) {
					if(board.getApples()[counterApples].getColor()=="red" &&(board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation+5;
					if(board.getApples()[counterApples].getColor()=="black"&& (board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation-10;
				}
			}
			//το πιο σημαντικο ειναι να υπαρχει καποια σκαλα γιατι σε αυτη την περιπτωση κανεις ουσιαστικα πολλαπλα βηματα
			//και αυξανεις την διαφορα σου με τον αντιπαλο.Οποτε αμα υπαρχει σκαλα τοτε η αξιολογηση παιρνει +20
			for(counterLadders=0;counterLadders<board.getLadders().length;counterLadders++) {
				if(nextStep==board.getLadders()[counterLadders].getDownStepId() && !board.getLadders()[counterLadders].getBroken()) {
					evaluation=evaluation+15;
				}
			} 
		}
		//εδω προσθετουμε και καποιους ακομα ποντους με το τι ζαρι φερνεις .Αμα στο ευρος των επομενων 6 πλακιδιων
		//δεν υπαρχει καποιο φιδι ή μηλο ή σκαλα τοτε ο παικτης θα πρεπει να διαλεξει την καλυτερη δυνατη επιλόγη που ειναι 
		//να κινηθει 6 πλακιδια 
		evaluation=evaluation +dice;
		return evaluation;
	}
	//εδω ουσιαστικα για τις 6 δυνατες επίλογες καλουμε καθε φορα την συναρτηση αξιολογησης .Συγκρινουμε τα αποτελεσματα
	//και επιστρεφουμε την κινηση με την καλυτερη αξιολογηση
	public int getNextMove(int currentPos) {
		double[][] possibleMoves=new double[6][2];
		for(int i=1;i<7;i++) {
			possibleMoves[i-1][0]=(double)i;
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
		//σε αυτο το σημειο αποθηκευουμε σε εναν πινακα ουσιαστικα ολα τα στοιχεια που αφορουν την καλυτερη κινηση
		//δηλαδη αποθηκευουμε ποσα πλακιδια κινηθηκε ποσα μηλα εφαγε ποσες σκαλες ανεβηκε ποσα φιδια τον τσιμπησανε .
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
	//η παρακατω συναρτηση δεν κανει τιποτα παραπανω απο το να εκτυπωνει για την εκαστοτε κινηση τα στατιστικα τησ(μηλα,φιδια κλπ)
	//ενω τελος υπαρχουν και καποιοι απαριθμητες που μετρανε τα συνολικα στατιστικα του παιχνιδιου 
	public void statistics(boolean last) {
		int len;
		int[] table=new int[6];
		if(!last) {
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
		if(last) {
			int totalNumberofSnakes=0;
			int totalNumberofLadders=0;
			int totalNumberofRApples=0;
			int totalNumberofBApples=0;
			for(int i=0;i<path.size();i++) {
				table=path.get(i);
				totalNumberofSnakes=totalNumberofSnakes+table[2];
				totalNumberofLadders=totalNumberofLadders+table[3];
				totalNumberofRApples=totalNumberofRApples+table[4];
				totalNumberofBApples=totalNumberofBApples+table[5];
			}
			System.out.println("The total numbers are");
			System.out.println("He is bitten from "+totalNumberofSnakes+" Snakes");
			System.out.println("He climbed "+totalNumberofLadders+"ladders");
			System.out.println("And he ate"+totalNumberofRApples+"red apples");
			System.out.println("And "+totalNumberofBApples+"black Apples");
		}
	}
	
}
