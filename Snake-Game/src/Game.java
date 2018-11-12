/* Βεργοπουλος Κωνσταντινος ΑΕΜ8508 email vkonstant@ece.auth.gr 6986786139
   Κοτσακιαχιδης Γιαννης    ΑΕΜ9029 email ioankots@ece.auth.gr  6982289241
 */




import java.util.Random;
public class Game {
//εδω βρισκεται η main απο την οποια ξεκιναει η εκτελεση του προγραμματος .Οριζω δυο παικτες και Φτιαχνω το ταμπλο  του παιχνιδιου αρχικα.
	public static void main(String[] args) {
		Random rm;
		rm=new Random();
		int dice_pl1,dice_pl2;
		int round=0;
		int seira=1;
		Board board;
		board=new Board(20,10,3,3,6);
		board.creatBoard();
		board.createElementBoard();
        Player pl1,pl2;
        pl1=new Player(1,"kostas",0,board);
        pl2=new Player(2,"John",0,board);
        int current_thesis_1 ,current_thesis_2;
        current_thesis_1=0;
        current_thesis_2=0;
        dice_pl1=0;
        dice_pl2=0;
        int[] array_1,array_2;
        array_1=new int[5];
        array_2=new int[5];
        for(;;) {
        	dice_pl1=rm.nextInt(6)+1;
        	dice_pl2=rm.nextInt(6)+1;
        	 //εδω τυχαια καθοριζω ποιος παικτης θα παιξει πρωτος .
        	if(round==0) {
        		if(dice_pl1<dice_pl2) {
        			seira=1;
        			System.out.println("Pl1 first player");
        		}
        		else System.out.println("Pl2 first player ");
        		seira=2;
        	}
        //στον 1ο γυρο ξεκιναει το παιχνιδι .Καλω την move που μας δινει την τελικη θεση του παικτη και ελεγχω αν αυτη η θεση ειναι μεγαλυτερη ή ιση απο την τελικη
        //θεση του ταμπλο που οριζεται ως ΜxN .Αν καποιος εχει φτασει εκει τοτε το παιχνιδι εχει τελειωσει .
        	if(round!=0) {
        		if(seira==1) {
        			array_1=pl1.move(current_thesis_1,dice_pl1);
        			current_thesis_1=array_1[0];
        			if(array_1[0]>=200) {
        				System.out.println("Player pl1 won");
        				break;
        			}
        			array_2=pl2.move(current_thesis_2,dice_pl2);
        			current_thesis_2=array_2[0];
        			if(array_2[0]>=200) {
        				System.out.println("PLayer pl2 won");
        				break;
        			}
        			
        		}
        		else {
        			array_2=pl2.move(current_thesis_2,dice_pl2);
        			current_thesis_2=array_2[0];
        			if(array_2[0]>=200) {
        				System.out.println("PLayer pl2 won");
        				break;
        			}
        			array_1=pl1.move(current_thesis_1,dice_pl1);
        			current_thesis_1=array_1[0];
        			if(array_1[0]>=200) {
        				System.out.println("Player pl1 won");
        				break;
        			}
        			
        		}
        	}
        	round=round+1;
        }
        System.out.println("numberof rounds"+round);
        System.out.println("STATUS OF PLAYER1 "+pl1.get_score());
        System.out.println("STATUS OF PLAYER2 "+ pl2.get_score());
	}

}
