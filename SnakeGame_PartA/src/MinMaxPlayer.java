import java.util.ArrayList;
public class MinMaxPlayer extends Player{
	ArrayList<int[]> path;
	public MinMaxPlayer() {
		super();
		path=null;
	}
	public MinMaxPlayer(int id ,String n,int s,Board b) {
		super(id,n,s,b);
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
	public int getNextMove(int currentPos,int opponentCurrentPos) {
		int depth=0;
		int nextmove;
		Node root;
		root=new Node(depth,board);//its the root meaning that is the current state of Board.So evaluation=0 parent=0 depth=0 move=0 
		createMySubtree(root,depth+1,currentPos,opponentCurrentPos);
		nextmove=chooseMinMaxMove(root);
		int[] table=new int[6];
		int[] table1=new int[5];
		table1=move(root.get_child(nextmove).get_Move()[0],root.get_child(nextmove).get_Move()[1]);
		table[0]=root.get_child(nextmove).get_Move()[1];
		table[1]=table1[0];
		table[2]=table1[1];
		table[3]=table1[2];
		table[4]=table1[3];
		table[5]=table1[4];
		path.add(table);
		return table[0];
	}
	private void createMySubtree(Node root,int depth,int currentPos,int opponentCurrentPos ) {
		double evaluation;
		int newDepth=depth;
		int[] table=new int[5];
		int[] tablex=new int[5];
		Board clone;
		int[] move=new int[2];
		for(int dice=1;dice<7;dice++) {
			clone=new Board(root.get_nodeBoard());
			evaluation=evaluate(currentPos,dice,clone);
			tablex=move(currentPos,dice,clone);
			for(int i=0;i<table.length;i++) {
				table[i]=tablex[i];
			}
			move[0]=currentPos;
			move[1]=dice;
			Node child;
			child=new Node(root,move,newDepth,clone,evaluation);
			root.set_children(child);
			createOpponentSubtree(child,newDepth+1,table[0],opponentCurrentPos,evaluation);
		}
	}
	private void createOpponentSubtree(Node parent,int depth,int currentPos,int opponentCurrentPos,double evaluation) {
		double OpponentEvaluation;
		int newDepth=depth+1;
		int[] table=new int[5];
		int[] tablex=new int[5];
		int[] mov=new int[2];
		Board clone;
		for(int i =1;i<7;i++) {
			clone=new Board(parent.get_nodeBoard());
			OpponentEvaluation=evaluate(opponentCurrentPos,i,clone);
			tablex=move(opponentCurrentPos,i,clone);
			for(int j=0;j<table.length;j++) {
				table[j]=tablex[j];
			}
			mov[0]=opponentCurrentPos;
			mov[1]=i;
			Node child2;
			child2=new Node(parent,mov,newDepth,clone,OpponentEvaluation);//it needs implementation
			parent.set_children(child2);
		}
	}
	public int chooseMinMaxMove(Node root) {
		int bestmove=0;
		int pointer=root.get_children().size();
		int pointer2;
		double diference=-100;//one very small number so in any case the will be a move with a better grade whatever
		ArrayList<Node> child;
		for(int i=0;i<pointer;i++) {
			pointer2=root.get_child(i).get_children().size();
			for(int j=0;j<pointer2;j++) {
				if((root.get_child(i).get_Evaluation()-root.get_child(i).get_child(j).get_Evaluation())>diference) {
					diference=(root.get_child(i).get_Evaluation()-root.get_child(i).get_child(j).get_Evaluation());
					bestmove=i;
				}
			}
		}
		return bestmove;
	}
}
