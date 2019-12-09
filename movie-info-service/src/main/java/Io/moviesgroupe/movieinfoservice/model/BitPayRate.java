package Io.moviesgroupe.movieinfoservice.model;

public class BitPayRate {
	private String code;
	private String name;
	private int rate;
	public BitPayRate() {}
	public BitPayRate(String code, String name, int rate) {
		this.code = code;
		this.name = name;
		this.rate = rate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return this.getCode()+" "+this.getName()+" "+this.getRate();
	}

}
