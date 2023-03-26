package model;

import java.io.Serializable;

/**
 * ユーザーインスタンスのフィールド設定
 * @author katsu
 *
 */
public class User implements Serializable{
	private String name;
	private String password;
	private int id;

	public User() {}
	public User(String password, String name, int id) {
		this.name = name;
		this.password = password;
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
