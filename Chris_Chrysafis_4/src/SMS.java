

public class SMS extends Communication{
	private String massage;
	public SMS(String number1, String number2,int day,int month,int year,String massage) {
		super(number1,number2,day, month,year);
		this.massage=massage;
	}
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		super.printInfo();
		System.out.println("Text: " + massage);
	}
	
	public int getDuration() {
		return 0;
	}
	
	public String getMassage() {
		return massage;
	}

}

