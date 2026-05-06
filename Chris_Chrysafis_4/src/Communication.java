public abstract class  Communication {
	
	private String number1;
	private String number2;
	private int year;
	private int month;
	private int day;
	
	public Communication(String number1, String number2,int day,int month,int year) {
		this.number1=number1;
		this.number2=number2;
		this.day=day;
		this.month=month;
		this.year=year;
	}
	public void printInfo() {
		System.out.println("Between: " +number1 + " --- " +number2);
		System.out.println("On " + year + "/" + month + "/" + day );
	}
	
	public String getNumber1() {
		return number1;
	}
	
	public String getNumber2() {
		return number2;
	}
	public abstract int getDuration();
	public abstract String getMassage();

}
