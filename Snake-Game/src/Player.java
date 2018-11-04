//constructors are needed 
public class Player {
	private int  playerId;
	private String name;
	private int score;
	private Board board;
	Player(){
		playerId=0;
		name=null;
		score=0;
		board=null;
	}
	Player(int pId,String Name,int Score,Board b){
		playerId=pId;
		name=Name;
		score=Score;
		board=b;
	}
	public void set_playerId(int pId) {
		playerId=pId;
	}
	public void set_name(String nm) {
		name=nm;
	}
	public void set_score(int points) {
		score=points;
	}
	public void set_board(Board b) {
		board=b;
	}
	public int get_playerId() {
		return playerId;
	}
	public String get_name() {
		return name;
	}
	public int get_score(){
		return score;
	}
	public Board get_board() {
		return board;
	}
	public int[] move(int id,int die) {
		int where_is_player_now=id;
		int dice =die;
		int nextstep=where_is_player_now+dice;
		int numberofapples=0;
		int numberofsnakes=0;
		int numberofladders=0;
		boolean check=false;
		for(int i=where_is_player_now+1;i<=nextstep;i++) {
			for(int j=0;j<board.get_Apples().length;j++) {
				if(i==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {//προς το παρον ολα τα μηλα εχουν 0 ποντους ...Που θα τα αρχικοποιω δεν λεει μεσα η εκφωνηση
					board.get_Apples()[j].set_points(0);
					numberofapples=numberofapples+1;
				}
			}
		}

		for(;;) {
			for(int i=0;i<board.get_Snakes().length;i++) {
				if(board.get_Snakes()[i].get_headId()==nextstep) {
					check=true;
					nextstep=board.get_Snakes()[i].get_tailId();
					numberofsnakes=numberofsnakes+1;
					for(int j=0;j<board.get_Apples().length;j++) {
						if(nextstep==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {
							board.get_Apples()[j].set_points(0);
							numberofapples=numberofapples+1;
						}
					}
				}
			}
			for(int i=0;i<board.get_Ladders().length;i++) {
				if(board.get_Ladders()[i].get_downStepId()==nextstep && board.get_Ladders()[i].get_broken()==false) {
					check=true;
					nextstep=board.get_Ladders()[i].get_upStepId();
					board.get_Ladders()[i].set_broken(true);
					numberofladders=numberofladders+1;
					for(int j=0;j<board.get_Apples().length;j++) {
						if(nextstep==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {
							board.get_Apples()[j].set_points(0);
							numberofapples=numberofapples+1;
						}
					}
				}
			}
			if(check) {
				check=false;
				continue;
			}
			if(!check)break;
		}
		int[] array=new int[4];
		array[0]=nextstep;
		array[1]=numberofsnakes;
		array[2]=numberofladders;
		array[3]=numberofapples;
		return array;
	}
}
