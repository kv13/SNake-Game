
public class Ladder {
	private int ladderId;
	private int upStepId;
	private int downStepId;
	private boolean broken;
	Ladder(){
		ladderId=0;
		upStepId=0;
		downStepId=0;
		broken=false;
	}
	Ladder(int lId,int uSId,int dSId,boolean check){
		ladderId=lId;
		upStepId=uSId;
		downStepId=dSId;
		broken=check;
	}
	Ladder(Ladder ladder){
		ladderId=ladder.get_ladderId();
		upStepId=ladder.get_upStepId();
		downStepId=ladder.get_downStepId();
		broken=ladder.get_broken();
	}
	public void set_ladderId(int lId) {
		ladderId=lId;
	}
	public void set_upStepId(int uSId) {
		upStepId=uSId;
	}
	public void set_downStepId(int dSId) {
		downStepId=dSId;
	}
	public void set_broken(boolean check) {
		broken=check;
	}
	public int get_ladderId() {
		return ladderId;
	}
	public int get_upStepId() {
		return upStepId;
	}
	public int get_downStepId() {
		return downStepId;
	}
	public boolean get_broken() {
		return broken;
	}
}
