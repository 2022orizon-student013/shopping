package la.bean;

public class CardBean {
	private String name;
	private String num;
	private String month;
	private String year;
	private String pass;

	public CardBean() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public CardBean(String name, String num, String month, String year, String pass) {
		super();
		this.name = name;
		this.num = num;
		this.month = month;
		this.year = year;
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
