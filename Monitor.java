import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
class APIAStatus extends Thread 
{ 
	private String apiURL;
	private String APIName;
	private int st;
	private int et;
	private int ct;
	private Socket s;
	APIAStatus( String data, String name, Socket soc,int startTime,int endTime,int crtTime) {
		apiURL = data;
		APIName = name;
		st = startTime;
		et = endTime;
		ct = crtTime;
		s = soc;
	 }

	public synchronized void hitAPI(long time){
		try
		{ 	
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			if(conn.getResponseCode() == 200){
				if(st > ct || ct > et){
					dout.writeUTF(APIName + " is UP");
				} else {
					dout.writeUTF("No Notification Period On for : "+ APIName);
				}
				System.out.println ("Done " + time);
				dout.flush();  
				dout.close();
			}
			if (conn.getResponseCode() != 200) {
				if(st > ct || ct > et){
					dout.writeUTF(APIName + " is Down");
				} else {
					dout.writeUTF("No Notification Period On for : "+ APIName);
				}
				System.out.println ("Done " + time);
				dout.flush();  
				dout.close();
			}	
			conn.disconnect();
			s.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}	catch (Exception e) 
			{ 
			System.out.println ("Exception is caught"); 
			} 

	 }
	public synchronized void checkAPIStatus(){
		Date date = null;
		long time = 0;
		try{
		switch(APIName){  
			//Case statements  
			case "API_1":   
			date= new Date();
			time = date.getTime();
			if(time - Monitor.prevAPI_1 < 1000){
				Thread.sleep(500);
				System.out.println ("API_1 being hit too frequently " + time);
			}
			Monitor.prevAPI_1 = time;
			hitAPI(time);
			break;  
			case "API_2": 
			date= new Date();
			time = date.getTime();
			if(time - Monitor.prevAPI_2 < 1000){
				Thread.sleep(500);
				System.out.println ("API_2 being hit too frequently " + time);
			}
			Monitor.prevAPI_2 = time;
			hitAPI(time);
			break;  
			case "API_3": 
			date= new Date();
			time = date.getTime();
			if(time - Monitor.prevAPI_3 < 1000){
				Thread.sleep(500);
				System.out.println ("API_3 being hit too frequently " + time);
			}
			Monitor.prevAPI_3 = time;
			hitAPI(time);
			break;  
			//Default case statement  
			default:System.out.println("Incorrect API URL");  
			}
	
		} catch(Exception e){
			System.out.print("Thread issues");
		}
	}
    public void run() 
    {   
        try
        { 
			checkAPIStatus();
		}catch (Exception e) 
		{ 
			System.out.println ("Exception is caught"); 
		} 
    } 
} 
class Monitor{
	static long prevAPI_1 = 0;
	static long prevAPI_2 = 0;
	static long prevAPI_3 = 0;

	public static void main(String[] args) throws IOException{
		String apiURL,APIName;
		int crtTime,startTime,endTime;
		String[] data;
		ServerSocket ss=new ServerSocket(6666);  
		while(true){
			Socket s = null;
			crtTime = Integer.parseInt(new SimpleDateFormat("kk:mm").format(new Date()).replace(":", ""));
			try{
				s=ss.accept();//establishes connection   
				DataInputStream dis=new DataInputStream(s.getInputStream());
				String  str=(String)dis.readUTF();
				data = str.split(" ");
				apiURL = data[0];
				APIName = data[1];
				startTime = Integer.parseInt(data[2]); 
				endTime = Integer.parseInt(data[3]);
				APIAStatus object1 = new APIAStatus(apiURL,APIName,s,startTime,endTime,crtTime); 
				object1.start();   
			} catch (Exception e){  
				e.printStackTrace(); 
			} 
			
		}  
	}
}