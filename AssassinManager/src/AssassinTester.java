import java.util.ArrayList;
import java.util.List;

public class AssassinTester {
	public static void main(String[] args) {
		List<String> names = new ArrayList<String>();
		names.add("John");
		names.add("Sally");
		names.add("Fred");
		names.add("Kyle");
		AssassinManager man = new AssassinManager(names);
		
		man.printKillRing();	
		System.out.println("The graveyard");
		man.printGraveyard();
		man.asString();
		
		man.graveyardAsString();
		
		man.kill("John");
		
		man.asString();
		man.graveyardAsString();
		man.printGraveyard();
		man.printKillRing();
		
		man.kill("Fred");
		
		man.asString();
		man.graveyardAsString();
		
		man.printKillRing();
	}
}
