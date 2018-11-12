//Κλαση player 
//αρχικα οριζω τις συναρτησεις getters setters και τους constructors 
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
	//εδω η συναρτηση move καθοριζει τον τροπο με τον οποιον κινειται ο καθε παικτης
	public int[] move(int id,int die) {
		int where_is_player_now=id;//η θεση πριν την κινηση
		int dice =die;//τι εφερε το ζαρι .Παιρνει τιμες απο 1-6
		int nextstep=where_is_player_now+dice;//τελικη θεση
		int numberofapples=0;
		int numberofsnakes=0;
		int numberofladders=0;
		int numberofpoints=0;
		boolean check=false;
		//για  αρχη ελεγχω αν στο καθε βημα ο παικτης πεφτει πανω σε καποιο μηλο
		for(int i=where_is_player_now+1;i<=nextstep;i++) {
			for(int j=0;j<board.get_Apples().length;j++) {
				if(i==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {
					numberofpoints=numberofpoints+board.get_Apples()[j].get_points();
					board.get_Apples()[j].set_points(0);
					numberofapples=numberofapples+1;
					System.out.println("player "+playerId+" EAT AN APPLE");
				}
			}
		}
	//μεσα στην infinity loop  ελγχω αν στην τελικη θεση υπαρχει καποιο φιδι ή σκαλα .
	//σε περιπτωση που υπαρχει παω στην νεα τελικη θεση .Ελεγχω εκει αν υπαρχει καποιο μηλο και ξανα ελεγχω αν υπαρχει καποια σκαλα ή καποιο φιδι.
	//και αυτο επαναλαμβανεται συνεχως εως οτου να βρεθει ο παικτης σε θεση που δεν εχει καποιο φιδι ή σκαλα .Αυτο το πετυχαινω με μια μεταβλητη check 
	//της οποιας η τιμη στο τελος καθε λουπας οριζεται σε false .Αν δεν μπει ο κωδικας σε καποια if statement σημαινει πως στην συγκεκριμενη θεση δεν υπαρχει ουτε
	//φιδι ουτε σκαλα και εκει πρεπει η infinity loop να τελειωσει 
		for(;;) {
			for(int i=0;i<board.get_Snakes().length;i++) {
				if(board.get_Snakes()[i].get_headId()==nextstep) {
					System.out.println("player "+playerId +" BITTEN BY SNAKE");
					check=true;
					nextstep=board.get_Snakes()[i].get_tailId();
					numberofsnakes=numberofsnakes+1;
					for(int j=0;j<board.get_Apples().length;j++) {
						if(nextstep==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {
							numberofpoints=numberofpoints+board.get_Apples()[j].get_points();
							board.get_Apples()[j].set_points(0);
							numberofapples=numberofapples+1;
							System.out.println("player"+playerId +" EAT AN APPLE");
						}
					}
				}
			}
			for(int i=0;i<board.get_Ladders().length;i++) {
				if(board.get_Ladders()[i].get_downStepId()==nextstep && board.get_Ladders()[i].get_broken()==false) {
					System.out.println("player"+playerId +" CLIMB A LADDER");
					check=true;
					nextstep=board.get_Ladders()[i].get_upStepId();
					board.get_Ladders()[i].set_broken(true);
					numberofladders=numberofladders+1;
					for(int j=0;j<board.get_Apples().length;j++) {
						if(nextstep==board.get_Apples()[j].get_appleTiledId() && board.get_Apples()[j].get_points()!=0) {
							numberofpoints=numberofpoints+board.get_Apples()[j].get_points();
							System.out.println("player"+playerId +" EAT AN APPLE");
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
		//επιστρεφω εναν πινακα με τον αριθμο των φιδιων που εφαγαν τον παικτη ποσα μηλα εφαγε ποσους ποντους εχει την τελικη θεση και ποσες σκαλες ανεβηκε .
		int[] array=new int[5];
		array[0]=nextstep;
		array[1]=numberofsnakes;
		array[2]=numberofladders;
		array[3]=numberofapples;
		array[4]=numberofpoints;
		score= score + numberofpoints;
		return array;
	}
}
