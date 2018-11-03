
public class Snake {
	private int snakeId;
	private int headId;
	private int tailId;
	Snake(){
		snakeId=0;
		headId=0;
		tailId=0;
	}
	Snake(int sId,int hId,int tId){
		snakeId=sId;
		headId=hId;
		tailId=tId;
	}
	Snake(Snake snake){
		snakeId=snake.get_snakeId();
		headId=snake.get_headId();
		tailId=snake.get_tailId();
	}
	public void set_snakeId(int Id) {
		snakeId=Id;
	}
	public void set_headId(int hId) {
		headId=hId;
	}
	public void set_tailId(int tId) {
		tailId=tId;
	}
	public int get_snakeId() {
		return snakeId;
	}
	public int get_headId() {
		return headId;
	}
	public int get_tailId() {
		return tailId;
	}

}
