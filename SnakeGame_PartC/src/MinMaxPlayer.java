/*
 * Αυτη η κλαση περιλαμβανει  τις συναρτησεις evaluate ,getnextmove ,createMysubtree,createOpponentSubtree
 * Η getNextMove καλει την createMySubtree.H createMySubtree για καθε δυνατη κινηση καλει την evaluate  για να βρει  την αξιολογηση της .Για καθε δυνατη κινηση μας καλουμε 
 * μεσα απο την createMySubtree την createOpponentSubtree η οποια δημιουργει τις 6 δυνατες κινησεις του αντιπαλου .Τις οποιες τις βαθμολογουμε 
 * Ολες αυτες οι κινησεις (και των 2 παικτων) αποθηκευονται σε αντικειμενα Nodes τα οποια μετα τα βαζουμε στο δενδο που δημιοιυργειτε στην αρχη στην συναρτηση getNextMOve
 * Aφου δημιουργηθει το δενδρο καλω την συναρτηση ChooseMinMax η οποια θεωρει δεδομενο οτι ο αντιπαλος θα παιξει την καλυτερη δυνατη κινηση του  και μετα προσπαθει να
 * βρει ποια δικη μας κινηση μεγιστοποιει την διαφορα μεταξυ των βαθμων της δικη μας κινησης-της κινησης του αντιπαλου και επιστρεφει αυτη την κινηση ,η οποια ειναι 
 * και η κινηση που θα κανει ο παικτης μας 
 */
import java.util.ArrayList;
public class MinMaxPlayer extends Player{
	ArrayList<int[]> path;
	public MinMaxPlayer() {
		super();
		path=null;
	}
	public MinMaxPlayer(int id,String Name,int Score,Board B) {
		super(id,Name,Score,B);
		path=new ArrayList<int[]>();
	}
	public MinMaxPlayer(MinMaxPlayer a) {	
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
	public int getNextMove(int currentPos,int opponentCurrentPos) {
		Node root;
		int depth=0;
		root=new Node(depth,board);
		createMySubtree(root,depth+1,currentPos,opponentCurrentPos);
		int nextmove;
		nextmove=ChooseMinMax(root);
		return nextmove;
	}
	public double evaluate(int currentPos,int dice,Board Nboard) {
		//για την evaluation στην δικη μας την υλοποιηση ειναι πιο σημαντικο να παμε οσο πιο μακρυα γινεται και σε δευτερη μοιρα να παρουμε καποιο μηλο
		//γιατι στο τελος αυτος που θα νικησει ειναι αυτος που τερματιζει πρωτος .
		//οποτε αν πχ σε μια θεση εχω σκαλα και διπλα εχω μηλο θα επιλεξω τη σκαλα
		double evaluation=0;
		int nextStep=currentPos+dice;
		int counterSnakes;
		int counterApples;
		int  counterLadders;
		boolean checkSnakes=false;
		for(counterSnakes=0;counterSnakes<board.getSnakes().length;counterSnakes++) {
			if(nextStep==board.getSnakes()[counterSnakes].getHeadId()) {
				evaluation=evaluation-20;
				checkSnakes=true;
			}
		}
		if(!checkSnakes) {
			evaluation=2*dice;
			for(counterLadders=0;counterLadders<Nboard.getLadders().length;counterLadders++) {
				if(nextStep==board.getLadders()[counterLadders].getDownStepId() && !(board.getLadders()[counterLadders].getBroken())) {
					evaluation=evaluation+15;
				}
			}
			for(counterApples=0;counterApples<board.getApples().length;counterApples++) {
				if(nextStep==board.getApples()[counterApples].getAppleTileId()) {
					if(board.getApples()[counterApples].getColor()=="red"&& (board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation+5;
					if(board.getApples()[counterApples].getColor()=="black"&& (board.getApples()[counterApples].getPoints()!=0))evaluation=evaluation-10;
				}
			}
		}
		return evaluation;
}
	public void createMySubtree(Node root,int depth,int currentPos,int opponentCurrentPos) {
		double evaluation;
		int newDepth=depth;
		Node child;
		Board clone;
		Board clone1;
		int[] table=new int[6];
		int[] table1=new int[5];
		int[] move=new int[2];
		System.out.println("LETS CHECK OUR POSSIBLE MOVES ");
		for(int dice=1;dice<7;dice++) {
			System.out.println("For our move "+dice);
			clone=new Board(root.get_board());
			clone1=new Board(root.get_board());
			table1=move(currentPos,dice,clone);
			table[0]=currentPos;
			table[1]=table1[0];
			table[2]=table1[1];
			table[3]=table[2];
			table[4]=table1[3];
			table[5]=table1[4];
			evaluation=evaluate(currentPos,dice,clone1);//evaluate must be done
			move[0]=currentPos;
			move[1]=dice;
			child=new Node(root,move,newDepth,root.get_board(),evaluation);
			root.add_toChildren(child);
			createOpponentSubtree(child,newDepth+1,move[0],opponentCurrentPos,evaluation);
		}
	}
	public void createOpponentSubtree(Node parent,int depth,int currentPos,int opponentCurrentPos,double parentEval) {
		Board clone;
		int newDepth=depth;
		double opponentEval;
		Node child;
		int[] table1=new int[6];
		int[] table=new int[5];
		int[] move=new int[2];
		System.out.println("Opponent possible moves");
		for(int i=1;i<7;i++) {
			clone=new Board(parent.get_board());
			table=move(opponentCurrentPos,i,clone);//then player2.move
			opponentEval=evaluate(opponentCurrentPos,i,clone);
			move[0]=opponentCurrentPos;
			move[1]=i;
			child=new Node(parent,move,newDepth,clone,opponentEval);
			parent.add_toChildren(child);
		}
	}
	public int ChooseMinMax(Node root) {
		ArrayList<Node> result=new ArrayList<Node>();
		result=root.get_children();
		double mybest=0;
		int bestmove=-1;
		double opponentbest=-100;
		double diference=-100;
		for(int i=0;i<root.get_children().size();i++) {
			mybest=root.get_child(i).get_evaluation();
			System.out.println("my evaluation "+mybest);
			for(int j=0;j<root.get_child(i).get_children().size();j++) {
				if((root.get_child(i).get_child(j).get_evaluation())>opponentbest) {
					opponentbest=root.get_child(i).get_child(j).get_evaluation();
				}
			}
			if((mybest-opponentbest)>=diference) {
				bestmove=root.get_child(i).get_move()[1];
				diference=mybest-opponentbest;
			}
		}
		System.out.println("So the best move is "+bestmove);
		return bestmove;
	}
}
