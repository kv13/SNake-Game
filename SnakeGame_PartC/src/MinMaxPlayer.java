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
		double evaluation=0;
		int nextStep=currentPos+dice;
		int counterSnakes;
		int counterApples;
		int  counterLadders;
		boolean checkSnakes=false;
		for(counterSnakes=0;counterSnakes<Nboard.getSnakes().length;counterSnakes++) {
			if(nextStep==Nboard.getSnakes()[counterSnakes].getHeadId()) {
				evaluation=evaluation-20;
				checkSnakes=true;
			}
		}
		if(!checkSnakes) {
			evaluation=2*dice;
			for(counterLadders=0;counterLadders<Nboard.getLadders().length;counterLadders++) {
				if(nextStep==Nboard.getLadders()[counterLadders].getDownStepId() && !(Nboard.getLadders()[counterLadders].getBroken())) {
					evaluation=evaluation+15;
				}
			}
			for(counterApples=0;counterApples<Nboard.getApples().length;counterApples++) {
				if(nextStep==Nboard.getApples()[counterApples].getAppleTileId()) {
					if(Nboard.getApples()[counterApples].getColor()=="red"&& (Nboard.getApples()[counterApples].getPoints()!=0))evaluation=evaluation+5;
					if(Nboard.getApples()[counterApples].getColor()=="black"&& (Nboard.getApples()[counterApples].getPoints()!=0))evaluation=evaluation-10;
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
		int[] table=new int[6];
		int[] table1=new int[5];
		int[] move=new int[2];
		for(int dice=1;dice<7;dice++) {
			clone=new Board(root.get_board());
			table1=move(currentPos,dice,clone);
			table[0]=currentPos;
			table[1]=table1[0];
			table[2]=table1[1];
			table[3]=table[2];
			table[4]=table1[3];
			table[5]=table1[4];
			evaluation=evaluate(currentPos,dice,clone);//evaluate must be done
			move[0]=currentPos;
			move[1]=dice;
			child=new Node(root,move,newDepth,clone,evaluation);
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
		double mybest;
		int bestmove=-1;
		double opponentbest=-100;
		double diference=-100;
		for(int i=0;i<root.get_children().size();i++) {
			mybest=root.get_child(i).get_evaluation();
			for(int j=0;j<root.get_child(i).get_children().size();j++) {
				if((root.get_child(i).get_child(j).get_evaluation())>opponentbest) {
					opponentbest=root.get_child(i).get_child(j).get_evaluation();
				}
			}
			if((root.get_child(i).get_evaluation()-opponentbest)>diference) {
				bestmove=root.get_child(i).get_move()[1];
				diference=root.get_child(i).get_evaluation()-opponentbest;
			}
		}
		return bestmove;
	}
}
