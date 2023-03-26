package model;

import java.io.Serializable;
import java.sql.Date;

/**
 * スケジュール情報を格納するインスタンス
 * @author katsu
 *
 */

public class ScheduleBeans implements Serializable{
	private int id;
	private Date date;
	private String start;
	private String end;
	private String title;
	private String detail;
	private String schedule_id;

	public ScheduleBeans() {}
	public ScheduleBeans(int id, Date date, String start, String end, String title, String detail) {
		this.id =id;
		this.date = date;
		this.start = start;
		this.end = end;
		this.title = title;
		this.detail = detail;


	}


	public ScheduleBeans(int id, Date date, String start, String end, String title, String detail, String schedule_id) {
		this.id =id;
		this.date = date;
		this.start = start;
		this.end = end;
		this.title = title;
		this.detail = detail;
		this.schedule_id = schedule_id;

	}



	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

}
