
public class Apple {
	private int appleId;
	private int appleTiledId;
	private String color;
	private int points ;
	Apple(){
		appleId=0;
		appleTiledId=0;
		color=null;
		points=0;
	}
	Apple(int aId,int aTId,String Color ,int Points){
		appleId=aId;
		appleTiledId=aTId;
		color=Color;
		points=Points;
	}
	Apple(Apple apple ){
		appleId=apple.get_appleId();
		appleTiledId=apple.get_appleTiledId();
		color=apple.get_color();
		points=apple.get_points();
	}
	public void set_appleId(int aId) {
		appleId=aId;
	}
	public void set_appleTiledId(int aTiledId) {
		appleTiledId=aTiledId;
	}
	public void set_color(String Color) {
		color=Color;
	}
	public void set_points(int Points) {
		points=Points;
	}
	public int get_appleId() {
		return appleId;
	}
	public int get_appleTiledId() {
		return appleTiledId;
	}
	public String get_color() {
		return color;
	}
	public int get_points() {
		return points;
	}
}
