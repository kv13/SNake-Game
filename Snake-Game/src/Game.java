import java.util.Random;
public class Game {

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
        array_1=new int[4];
        array_2=new int[4];
        for(;;) {
        	dice_pl1=rm.nextInt(6)+1;
        	dice_pl2=rm.nextInt(6)+1;
        	if(round==0) {
        		if(dice_pl1<dice_pl2) {
        			seira=1;
        			System.out.println("Pl1 first player");
        		}
        		else System.out.println("Pl2 first player ");
        		seira=2;
        	}
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
	}

}
