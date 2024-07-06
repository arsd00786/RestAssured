package RestAPI;

public class pojodemo {
	
	
	String  name;
	int ROI;
	int PF;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getROI() {
		return ROI;
	}
	public void setROI(int rOI) {
		ROI = rOI;
	}
	public int getPF() {
		return PF;
	}
	public void setPF(int pF) {
		PF = pF;
	}
	public pojodemo(String name, int rOI, int pF) {
		super();
		this.name = name;
		ROI = rOI;
		PF = pF;
	}
	
	
	

}
