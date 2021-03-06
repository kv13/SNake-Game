
public class Apple {
	int appleId;
	int appleTileId;
	String color;
	int points;
	
	public Apple() {
		appleId = 0;
		appleTileId = 0;
		color = "";
		points = 0;
	}
	
	public Apple(int a, int id, String c, int p) {
		appleId = a;
		appleTileId = id;
		color = c;
		points = p;
	}
	
	public Apple(Apple a) {
		appleId = a.appleId;
		appleTileId = a.appleTileId;
		color = a.color;
		points = a.points;
	}
	
	void setAppleTileId(int id) {
		appleTileId = id;
	}
	
	void setColor(String color) {
		if (color == "red" || color == "black") {
			this.color = color;
		}else {
			System.out.println("Not a valid color. The color will be set equal to red");
			color = "red";
		}
	}
	
	void setPoint(int p) {
		points = p;
	}
	
	int getAppleTileId() {
		return appleTileId;
	}
	
	int getAppleId() {
		return appleId;
	}
	
	String getColor() {
		return color;
	}
	
	int getPoints() {
		return points;
	}
}
