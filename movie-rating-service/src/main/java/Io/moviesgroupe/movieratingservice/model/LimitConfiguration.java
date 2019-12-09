package Io.moviesgroupe.movieratingservice.model;

public class LimitConfiguration {
	private int  maxlimit;
	private int minlimit;
	
	public LimitConfiguration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LimitConfiguration(int maxlimit, int minlimit) {
		super();
		this.maxlimit = maxlimit;
		this.minlimit = minlimit;
	}
	public int getMaxlimit() {
		return maxlimit;
	}
	public void setMaxlimit(int maxlimit) {
		this.maxlimit = maxlimit;
	}
	public int getMinlimit() {
		return minlimit;
	}
	public void setMinlimit(int minlimit) {
		this.minlimit = minlimit;
	}
	

}
