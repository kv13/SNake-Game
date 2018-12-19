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
		//copy paste from the git
	}
	public ArrayList<int[]> getArray(){
		return path;
	}
	public int getNextMove(int currentPos,int opponentCurrentPos) {
		Node root;
		int depth=0;
		root=new Node(depth,board);
		createMySubtree(root,depth+1,currentPos,opponentCurrentPos);
	}
	public void createMySubtree(Node root,int depth,int currentPos,int opponentCurrentPos) {
		double evaluation;
		int newDepth=depth;
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
			evaluation=evaluate(currentPos,dice);//evaluate must be done
			move[0]=currentPos;
			move[1]=dice;
			Node child;
			child=new Node(root,move,newDepth,clone,evaluation);
			root.add_toChildren(child);
			creatOpponentSubtree(child,newDepth+1,move[0],opponentCurrentPos,evaluation);
		}
	}
}
