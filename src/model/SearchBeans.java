package model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 検索用インスタンスのフィールド設定
 * @author katsu
 *
 */
public class SearchBeans implements Serializable{
	private int user_id;
	private String name;

	private Date date;
	private String start;
	private String end;
	private String title;
	private String detail;
	private String schedule_id;

	public SearchBeans() {}
	public SearchBeans (int user_id, String name, Date date, String start, String end, String title, String detail) {
		this.user_id = user_id;
		this.name = name;

		this.date = date;
		this.start = start;
		this.end = end;
		this.title = title;
		this.detail = detail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}


}



