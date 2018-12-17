/*import java.util.ArrayList;
public class Node {
	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	public Node() {
		parent=null;
		children=null;;
		nodeDepth=0;
		nodeMove=null;
		nodeBoard=null;
		nodeEvaluation=0;
	}
	public Node(int[] nMove,int nDepth,Board nBoard){
		System.out.println("Inside the constructor for root");
		parent=null;
		nodeDepth=nDepth;
		nodeMove=null;
		nodeBoard=new Board(nBoard);
		nodeEvaluation=0;
		children=new ArrayList<Node>(6);//because everytime there is 6 possible moves
		nodeMove=new int[nMove.length];
	}
	
	public void set_Parent(Node Parent) {
		parent=Parent;
	}
	 public Node get_Parent() {
		return parent;
	}
	 public void set_nodeBoard(Board board) {
		 nodeBoard=new Board(board);
	 }
	 public Board get_nodeBoard() {
		 return nodeBoard;
	 }
	 public void  set_Evaluation(double eval) {
		 nodeEvaluation=eval;
	 }
	 public double get_Evaluation() {
		 return nodeEvaluation;
	 }
	 public void set_Depth(int depth) {
		 nodeDepth=depth;
	 }
	 public int get_Depth() {
		 return nodeDepth;
	 }
	 public void set_Move(int[] move) {
		 nodeMove=move;
	 }
	 public int[] get_Move() {
		 return nodeMove;
	 }
	 public void set_children(Node child) {
		 children.add(child);
	 }
	 public ArrayList<Node> get_children(){
		 return children;
	 }
}*/
/*import java.util.ArrayList;
public class Node{
	Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	public Node(){
		parent=null;
		children=null;
		nodeMove=null;
		nodeBoard=null;
		nodeEvaluation=0;
		nodeDepth=0;
	}
	public Node(int nDepth,Board nBoard){
		System.out.println("this is the constructor for the root");
		parent=null;
		nodeDepth=nDepth;
		children=new ArrayList<Node>(6);
		nodeMove=null;
		nodeEvaluation=0;
		nodeBoard=new Board(nBoard);		
	}
	public Node(Node nParent,int[] nMove,int nDepth,Board nBoard,double nEval){
		nodeBoard=new Board(nBoard);
		nodeDepth=nDepth;
        nodeEvaluation=nEval;
        children=new ArrayList<Node>(6);
        nodeMove=new int[nMove.length];
        for(int i=0;i<nMove.length;i++){
        	nodeMove[i]=nMove[i];
        }
        if(nParent.get_Parent()==null){
        	parent=new Node(nParent.get_Depth(),nParent.get_nodeBoard());
        }
        else if(nParent.get_Parent()!=null){
        	parent=new Node(nParent.get_Parent(),nParent.get_Move(),nParent.get_Depth(),nParent.get_nodeBoard(),nParent.get_Evaluation());
        }
    }
	public Node(Node X) {
	}
    public void set_Parent(Node nParent){
    	if(nParent.get_Parent()==null){
    		parent=new Node(nParent.get_Depth(),nParent.get_nodeBoard());
    	}
    	else if(nParent.get_Parent()!=null) {
    		parent=new Node(nParent.get_Parent(),nParent.get_Move(),nParent.get_Depth(),nParent.get_nodeBoard(),nParent.get_Evaluation());
    	}
    }
    public Node get_Parent(){
    	return parent;
    }
	public void set_nodeBoard(Board nBoard){
		nodeBoard=new Board(nBoard);
	}
	public Board get_nodeBoard(){
		return nodeBoard;
	}	
    public void  set_Evaluation(double eval) {
        nodeEvaluation=eval;
	}
	public double get_Evaluation() {
		return nodeEvaluation;
	}
	public void set_Depth(int depth) {
		nodeDepth=depth;
	}
	public int get_Depth() {
		return nodeDepth;
	}
	public void set_Move(int[] move){
		nodeMove=new int[move.length];
		for(int i=0;i<move.length;i++){
			nodeMove[i]=move[i];
		}
	}
	public int[] get_Move(){
		return nodeMove;
	}
	public void set_children(Node child){
		Node X=new Node(child);
		children.add(X);
		//this one has a problem
	}
	public ArrayList<Node> get_children(){
		return children;
	}
}
*/
//Node class
//it maybe has problem in the constructors because i did not take care of initialize the ArrayList .
import java.util.ArrayList;
public class Node{
    Node parent;
	ArrayList<Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	public Node(){
		parent=null;
		children=null;
		nodeMove=null;
		nodeBoard=null;
		nodeEvaluation=0;
		nodeDepth=0;
	}
	public Node (Node X){
		nodeDepth=X.get_Depth();
		nodeBoard=new Board(X.get_nodeBoard());
		nodeEvaluation=X.get_Evaluation();
		nodeMove=new int[X.get_Move().length];
		for(int i=0;i<X.get_Move().length;i++){
			nodeMove[i]=X.get_Move()[i];
		}
		if(X.get_Parent()==null){
        	parent=new Node(X.get_Parent().get_Depth(),X.get_Parent().get_nodeBoard());
        }
        else if(X.get_Parent()!=null){
        	parent=new Node(X.get_Parent(),X.get_Parent().get_Move(),X.get_Parent().get_Depth(),X.get_Parent().get_nodeBoard(),X.get_Parent().get_Evaluation());
        }
		for(int i=0;i<X.get_children().size();i++) {
			children.add(X.get_child(i));
		}
	}
	public Node(int nDepth,Board nBoard){
		System.out.println("this is the constructor for the root");
		parent=null;
		nodeDepth=nDepth;
		children=new ArrayList<Node>(6);
		nodeMove=null;
		nodeEvaluation=0;
		nodeBoard=new Board(nBoard);		
	}
	public Node(Node nParent,int[] nMove,int nDepth,Board nBoard,double nEval){
		nodeBoard=new Board(nBoard);
		nodeDepth=nDepth;
        nodeEvaluation=nEval;
        children=new ArrayList<Node>(6);
        nodeMove=new int[nMove.length];
        for(int i=0;i<nMove.length;i++){
        	nodeMove[i]=nMove[i];
        }
        /*
        if(nParent.get_Parent()==null){
        	parent=new Node(nParent.get_Depth(),nParent.get_nodeBoard());
        }
        else if(nParent.get_Parent()!=null){
        	parent=new Node(nParent.get_Parent(),nParent.get_Move(),nParent.get_Depth(),nParent.get_nodeBoard(),nParent.get_Evaluation());
        }*/
        parent.nodeDepth=nParent.get_Depth();
        parent.nodeBoard=new Board(nParent.get_nodeBoard());
        parent.nodeEvaluation=nParent.get_Evaluation();
        parent.nodeMove=new int[nParent.get_Move().length];
        parent.nodeMove=nParent.get_Move();
        parent.children=new ArrayList<Node>();
        parent.children=nParent.get_children();
        
    }
    public void set_Parent(Node nParent){
    	if(nParent.get_Parent()==null){
    		parent=new Node(nParent.get_Depth(),nParent.get_nodeBoard());
    	}
    	else if(nParent.get_Parent()!=null) {
    		parent=new Node(nParent.get_Parent(),nParent.get_Move(),nParent.get_Depth(),nParent.get_nodeBoard(),nParent.get_Evaluation());
    	}
    }
    public Node get_Parent(){
    	return parent;
    }
	public void set_nodeBoard(Board nBoard){
		nodeBoard=new Board(nBoard);
	}
	public Board get_nodeBoard(){
		return nodeBoard;
	}	
    public void  set_Evaluation(double eval) {
        nodeEvaluation=eval;
	}
	public double get_Evaluation() {
		return nodeEvaluation;
	}
	public void set_Depth(int depth) {
		nodeDepth=depth;
	}
	public int get_Depth() {
		return nodeDepth;
	}
	public void set_Move(int[] move){
		nodeMove=new int[move.length];
		for(int i=0;i<move.length;i++){
			nodeMove[i]=move[i];
		}
	}
	public int[] get_Move(){
		return nodeMove;
	}
	public void set_children(Node child){
		Node X=new Node(child);
		children.add(X);
		//this one has a problem
	}
	public ArrayList<Node> get_children(){
		return children;
	}
	public Node get_child(int i) {
		return children.get(i);
	}
}