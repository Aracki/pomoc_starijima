package com.example.pomoc_starijima.Sat;


public class _Slava {

	private String imeSlave;
	private int id;
	private int day;
	private int month;
	
	private String koSlavi;

	public _Slava(int idR, String i, int d, int m, String koSlavi) {
		this.id = idR;
		this.imeSlave = i;
		this.day = d;
		this.month = m;
		
		this.koSlavi = koSlavi;
		// TODO Auto-generated constructor stub
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getImeSlave() {
		return imeSlave;
	}

	public void setImeBolnice(String ime) {
		this.imeSlave = ime;
	}

	
	public String getKoSlavi() {
		return koSlavi;
	}

	public void setSat(String koSlavi) {
		this.koSlavi = koSlavi;
	}

}

