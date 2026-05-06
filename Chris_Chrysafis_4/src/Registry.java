import java.util.ArrayList;

public class Registry {
	
	ArrayList<Communication>communications=new ArrayList<Communication>();
	ArrayList<Suspect> suspects= new ArrayList<Suspect>();
	
	public void addSuspect(Suspect aSuspect) {
		suspects.add(aSuspect);
	}
	
	public void addCommunication(Communication aCommunication) {
		communications.add(aCommunication);
		Suspect suspect1=null;
		for(Suspect sus:suspects) {
			if(sus.getPhones().contains(aCommunication.getNumber1())) {
				suspect1 = sus;
			}
		}
		Suspect suspect2=null;
		for(Suspect sus:suspects) {
			if(sus.getPhones().contains(aCommunication.getNumber2())) {
				suspect2 = sus;
			}
		}
		suspect1.addSuspect(suspect2);
		suspect2.addSuspect(suspect1);
		
		
	}
	
	public Suspect getSuspectWithMostPartners() {
		int max=0;
		Suspect suspect= null;
		for(Suspect susp:suspects) {
			if(max<=susp.getPartners().size()) {
				max=susp.getPartners().size();
				suspect = susp;
				
			}
		}
		return suspect;
	}
	
	public PhoneCall  getLongestPhoneCallBetween(String number1, String number2) {
		int max=0;
		PhoneCall call=null;
		for(Communication calls:communications) {
			if(calls.getClass()==PhoneCall.class) {
				if(number1.equals(calls.getNumber1()) && number2.equals(calls.getNumber2())) {
					if(max<calls.getDuration()) {
						max= calls.getDuration();
						call = (PhoneCall)calls;
						
					}
				}
			}
			
		}
		return call;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1, String number2) {
		ArrayList<SMS> massages=new ArrayList<SMS>();
		ArrayList<String>words= new ArrayList<String>();
		words.add("Bomb");
		words.add("Attack");
		words.add("Explosive");
		words.add("Gun");
		for(Communication sms:communications) {
			if(sms.getClass()==SMS.class) {
				if(number1.equals(sms.getNumber1())&& number2.equals(sms.getNumber2())) {
					for(String word:words) {
						if(sms.getMassage().contains(word)) {
							massages.add((SMS)sms);
							break;
						}
					}
				}
			}
		}
		return massages;
		
	}
	
	public void printSuspectsFromCountry(String country) {
		System.out.println("Suspects coming from "+ country);
		for(Suspect suspe:suspects) {
			if(suspe.getCountry().equals(country)) {
				System.out.println(suspe.getName()+" ("+suspe.getCodeName()+")");
			}
		}
	}
	
	public ArrayList<Suspect> getSuspects(){
		return suspects;
	}

}

