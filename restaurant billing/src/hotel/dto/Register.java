package hotel.dto;

public class Register {

	private int id;
	private String name;
	private long ph;
	private double wallet;
	private String email;
	private String pwd;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPh() {
		return ph;
	}
	public void setPh(long ph) {
		this.ph = ph;
	}
	
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Register(int id, String name, long ph,double wallet, String email, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.ph = ph;
		this.wallet=wallet;
		this.email = email;
		this.pwd = pwd;
	}
	

	public Register(String email) {
		super();
		this.email = email;
	}
	public Register() {
		super();
	}
	@Override
	public String toString() {
		return "register [id=" + id + ", name=" + name + ", ph=" + ph + ", wallet=" + wallet + ", email=" + email
				+ ", pwd=" + pwd + "]";
	}
	

	
	
}
