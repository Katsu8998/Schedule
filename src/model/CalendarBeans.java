package model;

import java.io.Serializable;

public class CalendarBeans implements Serializable{
	private int year;
	private int month;
	private String [][] date;



	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String[][] getDate() {
		return date;
	}
	public void setDate(String[][] date) {
		this.date = date;
	}



}
