
//Mehul Joshi
//CSE 143 ProgrammingProject
//Linear AssassinManager
import java.util.*;

public class AssassinManager {
	private AssassinNode killRing;
	private AssassinNode graveyard;

	public AssassinManager(List<String> names) {
		if(names.isEmpty()) {
			throw new IllegalArgumentException();
		}
		AssassinNode current = killRing;
		for(int i = 0; i < names.size(); i++) {
			if(i == 0) {//First element
				killRing = new AssassinNode(names.get(i));
				killRing.killer = names.get(names.size() - 1);
				current = killRing;
			} else {//The remaining indices
				current.next = new AssassinNode(names.get(i));
				current.next.killer = names.get(i -1);
				current = current.next;
			}
		}
	}	
	
	public void printKillRing() {
		AssassinNode current = killRing;
		String result = "";
		String store = "";
		while(current != null) {
			result = current.killer + " is stalking " + current.name;
			if(current == killRing)
				store = result;
			else
				System.out.printf("%" + (result.length() + 4) + "s\n", result);
			current = current.next;
		}
		
		System.out.printf("%" + (store.length() + 4) + "s\n", store);
	}

	public void printGraveyard() {
		AssassinNode current = graveyard;
		String result = "";
		while(current != null) {
			result = current.name + " was killed by " + current.killer;
			System.out.printf("%" + (result.length() + 4) + "s\n", result);
			current = current.next;
		}
	}
	
	public boolean killRingContains(String name) {
		AssassinNode current = killRing;
		while(current!=null) {
			if(current.name.equalsIgnoreCase(name))
				return true;
			current = current.next;
		}
		return false;
	}
	
	public boolean graveyardContains(String name) {
		AssassinNode current = graveyard;
		while(current != null) {
			if(current.name.equalsIgnoreCase(name)) 
				return true;
			current = current.next;
		}
		return false;
		
	}
	
	public boolean gameOver()
	{
		return killRing.next == null;
	}
	
	public String winner() {
		return gameOver()? killRing.name : null;
	}

	public void kill(String name) {
		//Doesn't contain the name
		if(!killRingContains(name)) 
			throw new IllegalArgumentException();
		//Contains the name
		AssassinNode current = killRing;
		AssassinNode temp = null;
		AssassinNode rest = null;
		while(current != null && current.next != null) {
			if(current == killRing && current.name.equalsIgnoreCase(name)) {//Kill the first element
				if(graveyard == null) {
					graveyard = current;
					temp = graveyard;
					killRing = killRing.next;
					graveyard.next = null;
				} else {
					temp = graveyard;
					graveyard = current;
					killRing = killRing.next;
					graveyard.next = temp;
				}
				adjustKillers();
				break;
			}
			if(current.next.name.equalsIgnoreCase(name)) {//kill one of the remaining elements
				if(graveyard == null) {
					graveyard = current.next;
					temp = graveyard;
					//adjust the nodes
					rest = temp.next;
					current.next = rest;
					graveyard.next  =null;
				} else {
					temp = graveyard;
					graveyard = current.next;
					//adjust the nodes
					rest = graveyard.next;
					current.next = rest;
					graveyard.next = temp;
				}
				adjustKillers();
				break;
			}
			current = current.next;
		}
		
	}
	
	private void adjustKillers() {
		AssassinNode current = killRing;
		String k1 = "";
		while(current != null) {
			if(current.next == null) {
				k1 = current.name;
			} else {
				current.next.killer = current.name;
			}
			current = current.next;
		}
		killRing.killer = k1;
	}
	
	public void asString() {
		System.out.println("The killRing: ");
		AssassinNode current = killRing;
		while(current != null) {
			System.out.print(current.name + " -> ");
			if(current.next == null)
				System.out.println("/");
			current = current.next;
		}
	}
	
	public void graveyardAsString() {
		System.out.println("The graveyard: ");
		AssassinNode current = graveyard;
		while(current != null) {
			System.out.print(current.name + " -> ");
			current = current.next;
		}
		System.out.println("/");
	}


}

