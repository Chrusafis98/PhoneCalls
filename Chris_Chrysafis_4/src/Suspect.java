import java.util.ArrayList;

public class Suspect {
	private String name;
	private String country;
	private String codeName;
	private String city;
	
	ArrayList<String>phones = new ArrayList<String>();
	ArrayList<Suspect>partners = new ArrayList<Suspect>();
	
	public Suspect(String name, String codeName, String country, String city) {
		this.name=name;
		this.codeName=codeName;
		this.country=country;
		this.city=city;
	}
	
	public void addNumber(String number) {
		phones.add(number);
	}
	public void addSuspect(Suspect suspect) {
		if(!partners.contains(suspect)) {
			partners.add(suspect);
		}
				
	}
	
	public boolean isConnectedTo(Suspect aSuspect) {
		if(partners.contains(aSuspect)) {
			return true;
		}else
			return false;
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect){
		ArrayList<Suspect> result = new ArrayList<Suspect>();
		for(Suspect s1:this.partners) {
			for(Suspect s2:aSuspect.getPartners()) {
				if(s1.equals(s2)){
					result.add(s1);
				}
			}
		}
		return result;
	}
	
	public ArrayList<Suspect> getPartners(){
		return partners;
	}
	
	public void printInfo() {
		for(Suspect suspect:partners) {
			if(country.equals(suspect.getCountry())) {
				System.out.println("Name:" + suspect.getName() + "Code Name:"+suspect.getCodeName()+"*");
			}else {
				System.out.println("Name:" + suspect.getName() + "Code Name:"+suspect.getCodeName());
			}
		
		}
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getCodeName() {
		return codeName;
	}
	
	public String getCountry() {
		return country;
	}
	
	public ArrayList<String> getPhones(){
		return phones;
	}
	
	public ArrayList<Suspect> SuggestedPartners(){
		ArrayList<Suspect>SuspParts= new ArrayList<Suspect>();
		for(Suspect suspPart:partners) {
			for(Suspect partnersOfSusPart:suspPart.getPartners()) {
				if(!partners.contains(partnersOfSusPart)) {
					if(!this.equals(partnersOfSusPart)) {
						SuspParts.add(partnersOfSusPart);
						
					}
					
					
				}
			}
		}
		return SuspParts;
		
	}
	

}
