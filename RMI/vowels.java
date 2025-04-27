/////////////////interf.java/////////////////////
***************************************************
  import java.rmi.*;
public interface interf extends Remote{
	public int count(String str) throws RemoteException;
}
******************************************************
 ///////////// impli.java//////////////
  import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
public class impli extends UnicastRemoteObject implements interf{
	protected impli()  throws RemoteException{
		super();
	}
	public int count(String str) throws RemoteException {
		int c=0;
		for(int i=0;i<str.length();i++) {
			if (str.charAt(i) == 'a' || 
				    str.charAt(i) == 'e' || 
				    str.charAt(i) == 'i' || 
				    str.charAt(i) == 'o' || 
				    str.charAt(i) == 'u') {
				    c++;
				}
		}
		return c;
	}
 }
******************************************************
  ///////////////////////////////////server.java////////////////////////////////
  import java.rmi.Naming;

public class server {

	public static void main(String[] args) {
		try {
			impli i =new impli();
			Naming.rebind("vow", i);
			
		}
		catch(Exception e) {
			System.out.println("Exception:"+e);
		}

	}

}
******************************************************
  ///////////////////////////////client.java*******************************************
  import java.util.*;
import java.rmi.*;
public class client {

	public static void main(String[] args) {
		
		try {
			String url="rmi://"+args[0]+"/vow";
			interf in=(interf) Naming.lookup(url);
			Scanner sc =new Scanner(System.in);
			String str;
			System.out.print("Enter first number: ");
			str=sc.next();
			
			int a=in.count(str);
			System.out.println(a+"vowels present");
		}catch(Exception e) {
			System.out.println("Exception:"+e);
			
		
		
	}

}
/////////////////////How to run////////////////////////////////////
javac *.java
rmiregistry &
java server
java client
history -c
history -w
