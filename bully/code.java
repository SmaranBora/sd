
import java.util.*;
public class bully {
	static boolean[]state=new boolean[5];
	static int coordinator=5;
	
	
	public static void up(int up) {
		if(state[up-1])
			System.out.println("Process "+up+"is already up.");
		else {
			state[up-1]=true;
			System.out.println("Process "+up+"is up and held election.");
			for(int i=up;i<5;i++) {
				System.out.println("Election msg sent from process"+up+"to process"+(i+1));
			}
				for(int i=4;i>=0;i--) {
					if(state[i]) {
						System.out.println("Coordinator message send from process " + (i + 1) + " to all");
						coordinator=i+1;
						break;
					}
				}
			}
			
		
	}
	
	public static void down (int down) {
		if(!state[down-1])
			System.out.println("Process " + down + " is already down.");
		else {
			state[down-1]=false;
			System.out.println("Process " + down + " is down.");
		}
	}
	
	
	public static void mess(int mess) {
		if(state[mess-1]) {
			if(state[coordinator-1])
				System.out.println("OK");
			else {
				System.out.println("Process " + mess + " finds Coordinator " + coordinator + " down");
				System.out.println("Election initiated by process " + mess);
				for(int i=mess;i<5;i++) {
					System.out.println("Election message send from process " + mess + " to process " + (i + 1));
					
				}
				for(int i=4;i>=0;i--) {
					if(state[i]) {
						coordinator=i+1;
						System.out.println("Coordinator message send from process " + coordinator + " to all");
                        break;
					}
				}
			}
		}
		else {
			System.out.println("Process " + mess + " is down.");
		}
	}
	
	
	public static void displaycoord() {
		System.out.println("Current coordinator is "+coordinator);
	}
	
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    // Initially bring all processes up
	    for (int i = 0; i < 5; i++) {
	        state[i] = true;
	    }

	    displaycoord(); // Initial coordinator

	    int choice;
	    do {
	        System.out.println("\n--- Bully Algorithm Menu ---");
	        System.out.println("1. Up a process");
	        System.out.println("2. Down a process");
	        System.out.println("3. Send a message");
	        System.out.println("4. Display Coordinator");
	        System.out.println("5. Exit");
	        System.out.print("Enter your choice: ");
	        choice = sc.nextInt();

	        switch (choice) {
	            case 1:
	                System.out.print("Enter process number to bring UP (1-5): ");
	                int upProc = sc.nextInt();
	                if (upProc >= 1 && upProc <= 5)
	                    up(upProc);
	                else
	                    System.out.println("Invalid process number!");
	                break;

	            case 2:
	                System.out.print("Enter process number to bring DOWN (1-5): ");
	                int downProc = sc.nextInt();
	                if (downProc >= 1 && downProc <= 5)
	                    down(downProc);
	                else
	                    System.out.println("Invalid process number!");
	                break;

	            case 3:
	                System.out.print("Enter process number to send a message (1-5): ");
	                int msgProc = sc.nextInt();
	                if (msgProc >= 1 && msgProc <= 5)
	                    mess(msgProc);
	                else
	                    System.out.println("Invalid process number!");
	                break;

	            case 4:
	                displaycoord();
	                break;

	            case 5:
	                System.out.println("Exiting...");
	                break;

	            default:
	                System.out.println("Invalid choice. Try again.");
	        }

	    } while (choice != 5);

	    sc.close();
	}
	


}
/////////////////////////////How to RUn////////////////////////////////////
javac *.java
java filename
