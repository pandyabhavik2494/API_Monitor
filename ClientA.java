import java.io.*;  
import java.net.*; 
import java.util.*;
class ClientA extends TimerTask {
	private String apiURL;
	private String APIName;
	private String startTime;
	private String endTime;
	ClientA( String data,String name,String sTime, String eTime) {
		apiURL = data;
		APIName = name;
		startTime = sTime;
		endTime = eTime;
	 }
	public void run(){
		Socket s = null; 
		try{   
			s=new Socket("localhost",6666);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			dout.writeUTF(apiURL + " " + APIName + " " + startTime + " " + endTime);  
			DataInputStream dis=new DataInputStream(s.getInputStream());
			String  str=(String)dis.readUTF();
			System.out.println(str);
			dout.flush();  
			dout.close();
			s.close();
		}catch(Exception e){
			System.out.println(e);
		} 
			 
}
	public static void main(String[] args) {
		int poll1 = 0, poll2 = 0, poll3 = 0;
		String startTimeAPI_1= null,startTimeAPI_2= null,startTimeAPI_3= null,endTimeAPI_1= null,endTimeAPI_2= null,endTimeAPI_3 = null;
				System.out.println("Answer the following questions as true/false");
				Scanner myObj = new Scanner(System.in);

				System.out.println("Do you want to monitor API 1?");
				Boolean chkAPI1 = myObj.nextBoolean();
				if(chkAPI1){
					System.out.println("Polling frequency API 1(In Secs)?");
					poll1 = myObj.nextInt();
					System.out.println("Enter time in 24 HR format(eg: 1045 for 10:45)");
					myObj.nextLine();
					System.out.println("Disable Notification From:");
					startTimeAPI_1 = myObj.nextLine();
					System.out.println("Disable Notification till:");
					endTimeAPI_1 = myObj.nextLine();

				}

				System.out.println("Do you want to monitor API 2?");
				Boolean chkAPI2 = myObj.nextBoolean();
				if(chkAPI2){
					System.out.println("Polling frequency API 2(In Secs)?");
					poll2 = myObj.nextInt();
					System.out.println("Enter time in 24 HR format(eg: 1045 for 10:45)");
					myObj.nextLine();
					System.out.println("Disable Notification From:");
					startTimeAPI_2 = myObj.nextLine();
					System.out.println("Disable Notification till:");
					endTimeAPI_2 = myObj.nextLine();
				}

				System.out.println("Do you want to monitor API 3?");
				Boolean chkAPI3 = myObj.nextBoolean();
				if(chkAPI3){
					System.out.println("Polling frequency API 3(In Secs)?");
					poll3 = myObj.nextInt();
					System.out.println("Enter time 24 HR format(eg: 1045 for 10:45)");
					myObj.nextLine();
					System.out.println("Disable Notification From:");
					startTimeAPI_3 = myObj.nextLine();
					System.out.println("Disable Notification till:");
					endTimeAPI_3 = myObj.nextLine();
				}
				myObj.close();
				if(chkAPI1){
					Timer t1 = new Timer();
					t1.schedule(new ClientA("http://services.groupkt.com/country/get","API_1",startTimeAPI_1,endTimeAPI_1), 0, poll1*1000);
				}
				if(chkAPI2){
					Timer t2 = new Timer();
					t2.schedule(new ClientA("http://services.groupkt.com/state/get","API_2",startTimeAPI_2,endTimeAPI_2), 0, poll2*1000);
				}
				if(chkAPI3){
					Timer t3 = new Timer();
					t3.schedule(new ClientA("https://jsonplaceholder.typicode.com/todos/1","API_3",startTimeAPI_3,endTimeAPI_3), 0, poll3*1000);
				}
	}
}