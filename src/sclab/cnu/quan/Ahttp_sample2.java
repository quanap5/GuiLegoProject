package sclab.cnu.quan;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import sclab.quan.User.profile_User;
import Bigdata_Lego.Json2Object;

public class Ahttp_sample2 {
	String Session_ori = "session=1f883112-f304-4419-8b86-da493e08980a";

	Long Slep;
	ArrayList<String> outFileJson = new ArrayList<>();
	String DEST_FOLDER;

	public Ahttp_sample2() {
		super();
		// TODO Auto-generated constructor stub

	}

	public String getDEST_FOLDER() {
		return DEST_FOLDER;
	}

	public void setDEST_FOLDER(String dEST_FOLDER) {
		this.DEST_FOLDER = dEST_FOLDER;
	}

	public Long getSlep() {
		return Slep;
	}

	public void setSlep(Long slep) {
		this.Slep = slep;
	}

	// private final static long Slep=60000;
	List<String> cookies;
	static String CSRFToken;

	public void Scraping(ArrayList<Integer> type, ArrayList<Integer> size) throws Exception {
		ArrayList<String> payload = new ArrayList<>();
		String Session = this.Session_ori;
		payload.clear();
		// Ahttp_sample2 http = new Ahttp_sample2();

		System.out.println("Testing 1 - Send Http GET request");
		

		String payload_Gathering = "{\"from\":3000,\"size\":" + size.get(0)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Gathering Support\"]}}]}}";
		String payload_Achieved = "{\"from\":0,\"size\":" + size.get(1)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Achieved Support\"]}}]}}";
		String payload_InReview = "{\"from\":0,\"size\":" + size.get(2)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"In Review\"]}}]}}";
		String payload_ProjectApproach = "{\"from\":0,\"size\":" + size.get(3)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Project Approved\"]}}]}}";
		String payload_ProjectNOTApproach = "{\"from\":0,\"size\":" + size.get(4)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Project Not Approved\"]}}]}}";
		String payload_InProduct = "{\"from\":0,\"size\":" + size.get(5)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"In Production\"]}}]}}";
		String payload_OnShelves = "{\"from\":0,\"size\":" + size.get(6)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"On Shelves\"]}}]}}";
		String payload_Expired = "{\"from\":0,\"size\":" + size.get(7)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Expired\"]}}]}}";
		String payload_Any = "{\"from\":18000,\"size\":" + size.get(8)
				+ ",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":"
				+ "[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":"
				+ "{\"model_type\":\"project\"}}]}}";

		payload.add(payload_Gathering);
		payload.add(payload_Achieved);
		payload.add(payload_InReview);
		payload.add(payload_ProjectApproach);
		payload.add(payload_ProjectNOTApproach);

		payload.add(payload_InProduct);
		payload.add(payload_OnShelves);
		payload.add(payload_Expired);
		payload.add(payload_Any);

		// Loop time for schedule periodically
		int TIME_INDEX=1;

		for (int time_index = 1; time_index <TIME_INDEX+1; time_index++) {
			outFileJson.clear();
			// Main function of scraping
			for (int i = 0; i < type.size(); i++) {
				
				CSRFToken = sendGet_ForCSRFToken(Session);
				sendPost_Auto(time_index, CSRFToken, Session, payload.get(type.get(i)));

			}

			Json2Object obj_Json2Object = new Json2Object();
			profile_User obj_profileUser= new profile_User();
			obj_Json2Object.Json2Object_ID(DEST_FOLDER, outFileJson);
			System.out.println("-----Suceed ID-----");
			obj_Json2Object.Json2Object_Project(DEST_FOLDER, outFileJson);
			System.out.println("-----Suceed Project-----");
			Json2Object.GetCommentThread(DEST_FOLDER, outFileJson);
			System.out.println("-----Suceed Comment-----");
			obj_profileUser.GetActivityThread(DEST_FOLDER, outFileJson);
			System.out.println("-----Suceed Activity-----");
//			profile_User.GetProjectFollowingThread(DEST_FOLDER, outFileJson);
//			System.out.println("-----Suceed ProjectFollowing-----");
//			profile_User.GetUserFollowingThread(DEST_FOLDER, outFileJson);
//			System.out.println("-----Suceed UserFollowing-----");
//			profile_User.GetUserFollowERThread(DEST_FOLDER, outFileJson);
//			System.out.println("-----Suceed UserFollower-----");
			//obj_profileUser.getFullProfileThread(DEST_FOLDER, outFileJson);
			//System.out.println("-----Suceed Fullprofile-----");
			if (time_index<TIME_INDEX) {
				Thread.sleep(getSlep() * 1000);
			}
			
		}
//		///////////////////////////////////////////////////////////////////////////////
		//LOOP2 incase of certain time
//		  Timer timer = new Timer();
//		  
//		  Calendar date = Calendar.getInstance();
//		    date.set(
//		      Calendar.DAY_OF_WEEK,
//		      Calendar.FRIDAY
//		    );
//		    date.set(Calendar.HOUR, 0);
//		    date.set(Calendar.MINUTE, 0);
//		    date.set(Calendar.SECOND, 0);
//		    date.set(Calendar.MILLISECOND, 0);
//		  
//		  
//		  TimerTask task = new TimerTask() {
//		      @Override
//		   public void run() {
//		    	   Json2Object obj_Json2Object = new Json2Object();
//					profile_User obj_profileUser= new profile_User();
//					try {
//						obj_Json2Object.Json2Object_ID(DEST_FOLDER, outFileJson);
//						System.out.println("-----Suceed ID-----");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					try {
//						Json2Object.GetCommentThread(DEST_FOLDER, outFileJson);
//						System.out.println("-----Suceed Comment-----");
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					try {
//						obj_profileUser.GetActivityThread(DEST_FOLDER, outFileJson);
//						System.out.println("-----Suceed Activity-----");
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
////					profile_User.GetProjectFollowingThread(DEST_FOLDER, outFileJson);
////					System.out.println("-----Suceed ProjectFollowing-----");
////					profile_User.GetUserFollowingThread(DEST_FOLDER, outFileJson);
////					System.out.println("-----Suceed UserFollowing-----");
////					profile_User.GetUserFollowERThread(DEST_FOLDER, outFileJson);
////					System.out.println("-----Suceed UserFollower-----");
//					try {
//						obj_profileUser.getFullProfileThread(DEST_FOLDER, outFileJson);
//						System.out.println("-----Suceed Fullprofile-----");
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//		       }
//		  };
//
//		  System.out.println("Current time" + System.currentTimeMillis());
//		  timer.schedule(task, 10000,1000);
//		  System.out.println("Current time" + System.currentTimeMillis());
		
		/////////////////////////////////////////////////////////////
		System.out.println("-----SUCESS-----");
		//Time spot
		System.out.println("Timestamp: "+Long.toString(Instant.now().getEpochSecond()));
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		System.out.println("Date: "+dateFormat.format(cal.getTime()));
	
		

	}

	// Get CSRFToken for automate
	private String sendGet_ForCSRFToken(String Session) throws Exception {
		System.out.println("sendGet_ForCSRFToken");
		String url = "https://ideas.lego.com/csrf?";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
		con.setRequestProperty("Accept", "*/*");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/atom+xml");
		con.setRequestProperty("Cookie", Session);
		con.setRequestProperty("Connection", "keep-alive");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		cookies = con.getHeaderFields().get("Set-Cookie");
		System.out.println("X-CSRFToken: " + response.toString());
		System.out.println("GET full response Header: " + con.getHeaderFields().toString());
		System.out.println("GET cookie: " + con.getHeaderFields().get("Set-Cookie").get(0));
		System.out.println("FINISH sendGet_ForCSRFToken");
		System.out.println("======================================================");
		return response.toString();

	}

	// HTTP POST request
	private void sendPost_Auto(int item, String CSRFToken, String Session, String payload) throws Exception {

		System.out.println("\nItem : " + item);
		String url = "https://ideas.lego.com/search/browse";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("host", "ideas.lego.com");
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
		con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		con.setRequestProperty("X-CSRFToken", CSRFToken);
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		con.setRequestProperty("Referer", "https://ideas.lego.com/discover");
		con.setRequestProperty("Cookie", Session);
		con.setRequestProperty("Connection", "keep-alive");
		// String payload =
		// "{\"from\":0,\"size\":"+i+",\"sort\":{\"type\":\"basic\",\"value\":{\"key\":\"published_at\",\"order\":\"desc\",\"sort\":[]}},\"facets\":[{\"type\":\"terms\",\"value\":{\"field\":\"tags\",\"size\":10,\"id\":\"tags\"}}],\"filter\":{\"type\":\"and\",\"value\":[{\"type\":\"model_type\",\"value\":{\"model_type\":\"project\"}},{\"type\":\"term\",\"value\":{\"field\":\"state\",\"filter\":[\"Gathering
		// Support\"]}}]}}";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(payload);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + payload);
		System.out.println("Response Code : " + responseCode);
		System.out.println("Cookie: " + con.getHeaderFields().toString());

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());
		FileWriter outFile = null;
		// Name of the type of project
		String name = payload.substring(payload.lastIndexOf('[') + 2, payload.lastIndexOf(']') - 4);
		System.out.println(name);
		// Index start
		String start = payload.substring(payload.indexOf("from") + 6, payload.indexOf("size") - 2);
		System.out.println(start);
		// Size of file: Number of the prokect extracted
		String size = payload.substring(payload.indexOf("size") + 6, payload.indexOf("sort") - 2);
		System.out.println(size);
		System.out.println(payload.length() - payload.indexOf("project"));
		String outString;
		if (payload.length() - payload.indexOf("project") == 13) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
			Calendar cal = Calendar.getInstance();
			//System.out.println("Date: "+dateFormat.format(cal.getTime()));
			outString = "////Any" + "Item" + item + "From" + start + "Size" + size + "time" + Slep + "seconds"+dateFormat.format(cal.getTime())+".txt";
			outFile = new FileWriter(DEST_FOLDER + outString);
			outFile.write(response.toString());
			outFileJson.add(outString);
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
			Calendar cal = Calendar.getInstance();
			//System.out.println("Date: "+dateFormat.format(cal.getTime()));
			outString = "////" + name + "Item" + item + "From" + start + "Size" + size + "time" + Slep +"seconds"+dateFormat.format(cal.getTime())+".txt";
			outFile = new FileWriter(DEST_FOLDER + outString);
			outFile.write(response.toString());
			outFileJson.add(outString);
		}

		System.out.println("Directory out:" + outString);
		outFile.close();
		System.out.println("sucess");
	}
}