
import java.util.*;
class Rr{
	String state;
	int id;
	int index;
	int f;
}


public class Ring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number of processes:");
		int num=sc.nextInt();
		Rr[]proc=new Rr[num];
		for(int i=0;i<num;i++)
		{
			proc[i]=new Rr();
		}
		
		System.out.println("Enter the id of each process;");
		
		for(int i=0;i<num;i++) {
			proc[i].id=sc.nextInt();
			proc[i].index=i;
			proc[i].state="active";
			proc[i].f = 0;
		}
		
		
		for(int i=0;i<num;i++) {
			for(int j=i+1;j<num;j++) {
				if(proc[i].id>proc[j].id) {
					int temp=proc[i].id;
					proc[i].id=proc[j].id;
					proc[j].id=temp;
					
				}
			}
		}
		
		
		System.out.println("Processes after sorting by ID:");
        for (int i = 0; i < num; i++) {
            System.out.println("Index: " + i + ", ID: " + proc[i].id + ", State: " + proc[i].state);
        }
        
        
    proc[num-1].state="inactive";
        //uncomment if u want to assume all are not acitve
        
        int choice;
        while(true) {
        System.out.println("Menu");
        System.out.println("Enter 1 for election");
        System.out.println("Enter 2 to quit");
		choice=sc.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Current Processes:");
			for (int i = 0; i < num; i++) {//make it num-1 if we assume the highest number is inactive
                System.out.println("Index: " + i + ", ID: " + proc[i].id + ", State: " + proc[i].state);
            }
			System.out.println("Enter process id who will initiate the election:");
			int initid=sc.nextInt();
			
			int init=-1;
			for(int i=0;i<num;i++) {
				if(proc[i].id==initid) {
					init=i;
					break;
					
				}
			}
			
			if (init == -1) {
                System.out.println("Invalid Process ID.");
                break;
            }
			
			int maxid=proc[init].id;
			int cur=init;
			System.out.println("\nElection Started by Process " + proc[cur].id);
			
			for(int i=0;i<num;i++) {
				int next=(cur+1)%num;
				System.out.println("Process " + proc[cur].id + " sends message to " + proc[next].id);
				if(proc[next].id>maxid && proc[next].state.equals("active")) {
					maxid=proc[next].id;
				}
				cur=next;
			}
			System.out.println("Process " + maxid + " is selected as new co-ordinator.");
			break;
			
			 
		case 2:
			System.out.println("Program terminated.");
            break;
		default:
			System.out.println("Invalid choice.");
			
		}}
		
	}

}
///////////////////////////////////////////////////HOW TO RUN///////////////////////////////////////////////////
javac filename.java or javac *.java
java filename
