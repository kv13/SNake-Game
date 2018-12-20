import java.util.ArrayList;
public class Node {
	Node parent;
	ArrayList<Node>children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	public Node() {
		parent=null;
		children=null;
		nodeMove=null;
		nodeBoard=null;
		nodeEvaluation=0;
		nodeDepth=0;
	}
	public Node(int nDepth,Board nBoard) {
		//this is the constructor for the roots
		parent=new Node();//maybe this one is not need it 
		parent=null;
		nodeDepth=nDepth;
		nodeBoard=new Board(nBoard);
		nodeMove=null;
		children=new ArrayList<Node>(6);
		nodeEvaluation=0;
	}
	public Node(Node nParent,int[] nMove,int nDepth,Board nBoard,double Eval) {
		//this is the constructor for every node that is not a root
		parent=new Node();//maybe this one is not necessary
		parent=nParent;
		nodeMove=new int[nMove.length];
		for(int i=0;i<nodeMove.length;i++) {
			nodeMove[i]=nMove[i];
		}
		nodeDepth=nDepth;
		nodeBoard=new Board(nBoard);
		nodeEvaluation=Eval;
		children=new ArrayList<Node>(6);
	}
	public Node(Node x) {
		parent=new Node();//maybe this is not necessary
		parent=x.get_parent();
		nodeDepth=x.get_depth();
		nodeMove=new int[x.get_move().length];
		for(int i=0;i<nodeMove.length;i++) {
			nodeMove[i]=x.get_move()[i];
		}
		nodeBoard=new Board(x.get_board());
		nodeEvaluation=x.get_evaluation();
		children=new ArrayList<Node>();
		for(int i=0;i<x.get_children().size();i++) {
			children.add(x.get_children().get(i));
		}
	}
	public Node get_parent() {
		return parent;
	}
	public void set_parent(Node nParent) {
		parent=nParent;
	}
	public ArrayList<Node> get_children(){
		return children;
	}
	public Node get_child(int pointer) {
		return children.get(pointer);
	}
	public void add_toChildren(Node x) {
		children.add(x);
	}
	public int[] get_move() {
		return nodeMove;
	}
	public void  set_move(int currentPos,int move) {
		nodeMove[0]=currentPos;
		nodeMove[1]=move;
	}
	public Board get_board() {
		return nodeBoard;
	}
	public void set_board(Board nBoard) {
		nodeBoard=new Board(nBoard);
	}
	public int get_depth() {
		return nodeDepth;
	}
	public void set_depth(int depth) {
		nodeDepth=depth;
	}
	public double get_evaluation() {
		return nodeEvaluation;
	}
	public void set_evaluation(double eval) {
		nodeEvaluation=eval;
	}
}
