package sclab.quan.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.gson.Gson;



public class profile_User {
	
	static int LIMIT=1000;
	static String Session_ori = "session=1f883112-f304-4419-8b86-da493e08980a";
	String id;
	String alias;
    String uuid;
  

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	String no_GatheringSupport;
    String no_AchivedSupport;
    String no_InReview;
    String no_Approved;
    String no_NotApproved;
    String no_Production;
    String no_OnShelves;
    String no_Expired;
    String no_Any;
    
    String no_Supporting;
    
	

    String no_ProFollowing;
    String no_UserFollowing;
    String no_FollowER;

	
    String Clutch_Power;
    String _1k_Club;
    String Socializer;
    String Trailblazer;
    String _5k_Club;
    String _10k_Club;
    String Autobiographer;
    String Pioneer;
    String Luminary;
    
	

	public profile_User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getNo_ProFollowing() {
		return no_ProFollowing;
	}


	public void setNo_ProFollowing(String no_ProFollowing) throws Exception {
		this.no_ProFollowing = GetAllProjectFollowing(uuid);
	}


	public String getNo_UserFollowing() {
		return no_UserFollowing;
	}


	public void setNo_UserFollowing(String no_UserFollowing) throws Exception {
		this.no_UserFollowing = GetAllUserFollowing(uuid);
	}


	public String getNo_FollowER() {
		return no_FollowER;
	}


	public void setNo_FollowER(String no_FollowER) throws Exception {
		this.no_FollowER = GetAllUserFollowER(uuid);
	}


	public void modprofile_User(String id, String alias, String uuid) {
		
		this.id = id;
		this.alias = alias;
		this.uuid = uuid;
		
		this.no_GatheringSupport = "0";
		this.no_AchivedSupport =  "0";
		this.no_InReview =  "0";
		this.no_Approved =  "0";
		this.no_NotApproved = "0";
		this.no_Production = "0";
		this.no_OnShelves =  "0";
		this.no_Expired =  "0";
		this.no_Any =  "0";
		this.no_Supporting= "0";
		this.no_ProFollowing =  "0";
		this.no_UserFollowing =  "0";
		this.no_FollowER =  "0";
		this.Clutch_Power =  "0";
		this._1k_Club =  "0";
		this.Socializer =  "0";
		this.Trailblazer =  "0";
		this._5k_Club =  "0";
		this._10k_Club =  "0";
		this.Autobiographer =  "0";
		this.Pioneer =  "0";
		this.Luminary =  "0";
	}
	
	
//Phan nay dung de scraping activity
	  
	


	public static String sendGet_ForActivity(String uuid,String max_ts) throws Exception {
		     String Session = Session_ori;
			//System.out.println("sendGet_ForActivity");
			String url = "https://ideas.lego.com/stream/query/user_activity/"+uuid+"?user_uuid=92c47282-50f9-474c-a499-8a40d8a0cfc6&limit=200"+max_ts;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/atom+xml");
			con.setRequestProperty("Cookie", Session);
		    con.setRequestProperty("Connection", "keep-alive");

			//int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			//BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
		    BufferedReader in = null;

		    if(con.getResponseCode() == 200)
		    {
		    in = new BufferedReader(new
		    InputStreamReader(con.getInputStream()));
		    }
		    else
		    {
		    in = new BufferedReader(new
		    InputStreamReader(con.getErrorStream()));
		    }
		    
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			
			//System.out.println("GET full response Header Activity: " + con.getHeaderFields().toString());
			//System.out.println("Out Json of Activity: " + response.toString());
			//System.out.println("FINISH sendGet_ForActivity");
			//System.out.println("======================================================");

			return response.toString();

		}
			
	public static String GetAllActivity(String uuid) throws Exception {
		String all_activity="";
		String max_ts="";
		String medium=sendGet_ForActivity(uuid, max_ts);
		Long daybefore= Instant.now().getEpochSecond();
		while (medium.lastIndexOf("timestamp")>0 ) {
			if (all_activity== "") {
				all_activity=medium.substring(medium.indexOf("timestamp")-2, medium.lastIndexOf("}}]")+2);
			}else {
				all_activity=all_activity+", "+medium.substring(medium.indexOf("timestamp")-2, medium.lastIndexOf("}}]")+2);	
			}
			//System.out.println("Body Activity : " +all_activity);
			max_ts="&max_ts="+medium.toString().substring(medium.lastIndexOf("timestamp")+12, medium.lastIndexOf("timestamp")+25);
			//System.out.println("next Index of activity: " +max_ts+" "+ Instant.now().getEpochSecond()*1000);
			//System.out.println("next Index length: " +max_ts.length());
			if (daybefore*1000-Long.parseLong(max_ts.substring(8,21))< 7*24*60*60*1000) {
				medium=sendGet_ForActivity(uuid, max_ts);
			}
			else {
				medium="";
			}
			
			
		}
		
		all_activity= "["+all_activity+"]";
		return all_activity;
		
	}
	
	public  void GetActivityThread(String home_, ArrayList<String> input) throws Exception {

		for (int index_file = 0; index_file < input.size(); index_file++) {
			FileWriter ActivityFile = null;
			String newLine = System.getProperty("line.separator");
			try {
				File Activity=new File(home_ + "////Activity");
				  if (Activity.exists()==false && Activity.isDirectory()==false) {
		            	Activity.mkdir();
		            	//System.out.println("TAO MOI folder");
					}
				
				Activity.mkdir();
				
				
				ActivityFile = new FileWriter(home_ + "////Activity////" + input.get(index_file)+"Activity.txt");
                ArrayList<String> listID=new ArrayList<>();
                listID=loadWordsList(home_+"////ID////"+input.get(index_file));
				
				for (int i = 0; i < listID.size(); i=i+2) {
					//System.out.println("======================================================================");
					//System.out.println("inputFile Activity: " + input.get(index_file));
					//System.out.println("UUIDUser Activity: " + listID.get(i));
					//System.out.println("----------------------------"+i+"-----------------------------------");
                    String JsonActivity= GetAllActivity(listID.get(i));
                    ActivityFile.write(JsonActivity);
					if (i < listID.size() - 2) {
						ActivityFile.write(newLine);
					}

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ActivityFile.close();
			//System.out.println("GET Activity Thread sucess");

		}
	}	
	
	public static ArrayList<String> loadWordsList(String pLoc) throws FileNotFoundException {
		ArrayList<String> wordlist = new ArrayList<String>();
		//Cach1
//		try {
//			ArrayList<String> words = (ArrayList<String>) Files.readAllLines(Paths.get(pLoc), StandardCharsets.UTF_8);
//			for (String string : words) {
//				wordlist.add(string.trim());
//			}
//		} catch (IOException ex) {
//			 System.out.println("Load Word error");
//		}
		
		Scanner s = new Scanner(new File(pLoc));
		//rayList<String> list = new ArrayList<String>();
		while (s.hasNextLine()){
			wordlist.add(s.nextLine());
		}
		s.close();
		
		return wordlist;
	}
	
//Phan nay dung de scraping Project following	
	
	public static String sendGet_ForProjectFollowing(String uuid,String offset) throws Exception {
		   String Session = Session_ori;
		//System.out.println("sendGet_ForProjectFollowing");
		String url = "https://ideas.lego.com/projects/user/"+uuid+"/watching?offset="+offset+"&limit="+Integer.toString(LIMIT);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0");
		con.setRequestProperty("Accept", "*/*");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/atom+xml");
		con.setRequestProperty("Cookie", Session);
	    con.setRequestProperty("Connection", "keep-alive");

		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		
		//System.out.println("GET full response Header Project Following: " + con.getHeaderFields().toString());
		//System.out.println("Out HTML of Project Following: " + response.toString());
		//System.out.println("FINISH sendGet_ForProjectFollowing");
		//System.out.println("======================================================");

		return response.toString();

	}
	
	public static String GetAllProjectFollowing(String uuid) throws Exception {
		//String AllProjectFollowing="";
		String offset="0";
		String medium=sendGet_ForProjectFollowing(uuid, offset);
		int i=0;
		Document doc;
		Elements li;
		Long all_size= (long) 0;
		while (medium.lastIndexOf("li")>0) {
//			if (AllProjectFollowing== "") {
//				AllProjectFollowing=medium;
//			}else {
//				AllProjectFollowing=AllProjectFollowing+medium;	
//			}
			doc=Jsoup.parse(medium);
			li=doc.select("li");
			all_size=all_size+li.size();
			//System.out.println("Body Project following : " +AllProjectFollowing);
			offset=Integer.toString(LIMIT*(++i));
			//System.out.println("off set next of project following: " +offset);
		    medium=sendGet_ForProjectFollowing(uuid, offset);
		    //System.out.println("medium: " +medium);
		}
		
		
//		Document doc=Jsoup.parse(AllProjectFollowing);
//		Elements li=doc.select("li");
		//System.out.println("Size __li__: "+li.size());
		
		System.out.println("no_ProFollowing "+Long.toString(all_size));
		return Long.toString(all_size);
		
		
	}
		
    public static void GetProjectFollowingThread(String home_, ArrayList<String> input) throws Exception {

		for (int index_file = 0; index_file < input.size(); index_file++) {
			FileWriter ProjectFollowingFile = null;
			String newLine = System.getProperty("line.separator");
			try {
				File ProjectFollowing=new File(home_ + "////ProjectFollowing");
				  if (ProjectFollowing.exists()==false && ProjectFollowing.isDirectory()==false) {
					  ProjectFollowing.mkdir();
		            	//System.out.println("TAO MOI folder ProjectFollowing");
					}
				
				  ProjectFollowing.mkdir();
				
				
				  ProjectFollowingFile = new FileWriter(home_ + "////ProjectFollowing////" + input.get(index_file)+"ProjectFollowing.txt");
                ArrayList<String> listID=new ArrayList<>();
                listID=loadWordsList(home_+"////ID////"+input.get(index_file));
				
				for (int i = 0; i < listID.size(); i=i+2) {
					//System.out.println("======================================================================");
					//System.out.println("inputFile ProjectFollowing: " + input.get(index_file));
					//System.out.println("UUIDUser ProjectFollowing: " + listID.get(i));
					//System.out.println("----------------------------"+i+"-----------------------------------");
//                    String JsonActivity= GetAllProjectFollowing(listID.get(i));
//                    ProjectFollowingFile.write(JsonActivity);
					if (i < listID.size() - 2) {
						ProjectFollowingFile.write(newLine);
					}

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ProjectFollowingFile.close();
			//System.out.println("GET ProjectFollowing Thread sucess");

		}
	}	
	
	//Phan nay dung de scraping User following	
	
	public static String sendGet_ForUserFollowing(String uuid,String offset) throws Exception {
		   String Session = Session_ori;
			//System.out.println("sendGet_ForUserFollowing");
			String url = "https://ideas.lego.com/stream/user/"+uuid+"/following?offset="+offset+"&limit="+Integer.toString(LIMIT);
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/atom+xml");
			con.setRequestProperty("Cookie", Session);
		    con.setRequestProperty("Connection", "keep-alive");

			//int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			
			//System.out.println("GET full response Header User Following: " + con.getHeaderFields().toString());
			//System.out.println("Out HTML of User Following: " + response.toString());
			//System.out.println("FINISH sendGet_ForUserFollowing");
			//System.out.println("======================================================");

			return response.toString();

		}
		
	public static String GetAllUserFollowing(String uuid) throws Exception {
			//String AllUserFollowing="";
			String offset="0";
			String medium=sendGet_ForUserFollowing(uuid, offset);
			int i=0;
			Document doc;
			Elements li;
			Long all_size= (long) 0;
			while (medium.lastIndexOf("li")>0) {
//				if (AllUserFollowing== "") {
//					AllUserFollowing=medium;
//				}else {
//					AllUserFollowing=AllUserFollowing+medium;	
//				}
				doc=Jsoup.parse(medium);
				li=doc.select("li");
				all_size=all_size+li.size();
				//System.out.println("Body User following : " +AllUserFollowing);
				offset=Integer.toString(LIMIT*(++i));
				//System.out.println("off set next of User following: " +offset);
			    medium=sendGet_ForUserFollowing(uuid, offset);
			    //System.out.println("medium: " +medium);
			    //Document doc=Jsoup.parse(medium);
     			//Elements li=doc.select("li");
     			//number=number+li.size();
			}
			
			
//			Document doc=Jsoup.parse(AllUserFollowing);
//			Elements li=doc.select("li");
			//System.out.println("Size __li__ user following: "+li.size());
			//this.no_UserFollowing=Long.toString(all_size);
			System.out.println("no_UserFollowing: "+ Long.toString(all_size));
			return Long.toString(all_size);
			
			
		}
			
	public static void GetUserFollowingThread(String home_, ArrayList<String> input) throws Exception {

			for (int index_file = 0; index_file < input.size(); index_file++) {
				FileWriter UserFollowingFile = null;
				String newLine = System.getProperty("line.separator");
				try {
					File UserFollowing=new File(home_ + "////UserFollowing");
					  if (UserFollowing.exists()==false && UserFollowing.isDirectory()==false) {
						  UserFollowing.mkdir();
			            	//System.out.println("TAO MOI folder UserFollowing");
						}
					
					  UserFollowing.mkdir();
					
					
					  UserFollowingFile = new FileWriter(home_ + "////UserFollowing////" + input.get(index_file)+"UserFollowing.txt");
	                ArrayList<String> listID=new ArrayList<>();
	                listID=loadWordsList(home_+"////ID////"+input.get(index_file));
					
					for (int i = 0; i < listID.size(); i=i+2) {
						//System.out.println("======================================================================");
						//System.out.println("inputFile UserFollowing: " + input.get(index_file));
						//System.out.println("UUIDUser UserFollowing: " + listID.get(i));
						//System.out.println("----------------------------"+i+"-----------------------------------");
//	                    String JsonActivity= GetAllUserFollowing(listID.get(i));
//	                    UserFollowingFile.write(JsonActivity);
						if (i < listID.size() - 2) {
							UserFollowingFile.write(newLine);
						}

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				UserFollowingFile.close();
				//System.out.println("GET UserFollowing Thread sucess");

			}
		}	
		
		//Phan nay dung de scraping User follower	
		
	public static String sendGet_ForUserFollowER(String uuid,String offset) throws Exception {
		   String Session = Session_ori;
			//System.out.println("sendGet_ForUserFollowER");
			String url = "https://ideas.lego.com/stream/user/"+uuid+"/followers?offset="+offset+"&limit="+Integer.toString(LIMIT);
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Content-Type", "application/atom+xml");
			con.setRequestProperty("Cookie", Session);
		    con.setRequestProperty("Connection", "keep-alive");

			//int responseCode = con.getResponseCode();
			//System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			
			//System.out.println("GET full response Header User Follower: " + con.getHeaderFields().toString());
			//System.out.println("Out HTML of User Follower: " + response.toString());
			//System.out.println("FINISH sendGet_ForUserFollowER");
			//System.out.println("======================================================");

			return response.toString();

		}
		
	public static String GetAllUserFollowER(String uuid) throws Exception {
		//	String AllUserFollowER="";
			String offset="0";
			String medium=sendGet_ForUserFollowER(uuid, offset);
			int i=0;
			Document doc;
			Elements li;
			Long all_size= (long) 0;
			while (medium.lastIndexOf("li")>0) {
//				if (AllUserFollowER== "") {
//					AllUserFollowER=medium;
//				}else {
//					AllUserFollowER=AllUserFollowER+medium;	
//				}
//				//System.out.println("Body User following : " +AllUserFollowER);
				doc=Jsoup.parse(medium);
				li=doc.select("li");
				all_size=all_size+li.size();
				offset=Integer.toString(LIMIT*(++i));
				//System.out.println("off set next of User follwer: " +offset);
			    medium=sendGet_ForUserFollowER(uuid, offset);
			    //System.out.println("medium: " +medium);
			}
			
			
//			Document doc=Jsoup.parse(AllUserFollowER);
//			Elements li=doc.select("li");
			//System.out.println("Size __li__ user following: "+li.size());
			//this.no_FollowER=Long.toString(all_size);
			System.out.println("no_FollowER: "+Long.toString(all_size));
			return Long.toString(all_size);
		
			
		}
			
	public static void GetUserFollowERThread(String home_, ArrayList<String> input) throws Exception {

			for (int index_file = 0; index_file < input.size(); index_file++) {
				FileWriter UserFollowERFile = null;
				String newLine = System.getProperty("line.separator");
				try {
					File UserFollowER=new File(home_ + "////UserFollowER");
					  if (UserFollowER.exists()==false && UserFollowER.isDirectory()==false) {
						  UserFollowER.mkdir();
			            	//System.out.println("TAO MOI folder UserFollowER");
						}
					
					  UserFollowER.mkdir();
					
					
					  UserFollowERFile = new FileWriter(home_ + "////UserFollowER////" + input.get(index_file)+"UserFollowER.txt");
	                ArrayList<String> listID=new ArrayList<>();
	                listID=loadWordsList(home_+"////ID////"+input.get(index_file));
					
					for (int i = 0; i < listID.size(); i=i+2) {
						//System.out.println("======================================================================");
						//System.out.println("inputFile UserFollowER: " + input.get(index_file));
						//System.out.println("UUIDUser UserFollowER: " + listID.get(i));
						//System.out.println("----------------------------"+i+"-----------------------------------");
	                //    String JsonActivity= GetAllUserFollowER(listID.get(i));
	                 //   UserFollowERFile.write(JsonActivity);
						if (i < listID.size() - 3) {
							UserFollowERFile.write(newLine);
						}

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				UserFollowERFile.close();
				//System.out.println("GET UserFollowER Thread sucess");

			}
		}		
		
		
		public static WebDriver login(String user, String password) throws InterruptedException {
            // Creating a new instance of the HTML unit driver
	        WebDriver driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    		// Navigate to URL
    		driver.get("https://ideas.lego.com/");
    		driver.findElement(By.id("legoid-login")).click();
    		driver.findElement(By.id("modal-legoid-login")).click();
    		// Maximize the window.
    		// driver.manage().window().maximize();
    		// switch to fame
    		driver.switchTo().frame(0);
    		// Enter UserName
    		driver.findElement(By.id("fieldUsername")).sendKeys(user);
    		// Enter Password
    		driver.findElement(By.id("fieldPassword")).sendKeys(password);
    		// Wait For Page To Load
    		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    		// Click on 'Sign In' button
    		driver.findElement(By.id("buttonSubmitLogin")).click();
    		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            Thread.sleep(5000);
   		    // This code will print the page title		
            System.out.println("Page title is: " + driver.getTitle());		
            // driver.quit();			
			return driver;
}			
		
		public void profile_User_Score( WebDriver driver){
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get("https://ideas.lego.com/profile/" +this.getAlias()+ "/projects");
			Document projects = Jsoup.parse(driver.getPageSource());
			Elements userMains = projects.select("h2");

			for (int i = 0; i < userMains.size(); i++) {
				Element e = userMains.get(i);
				switch (e.text()) {
				case "Gathering Support":
					Element nextSib_gather = e.nextElementSibling();
					Elements nextpanels_gather = nextSib_gather.select("article.panel-tile");
					System.out.print("number of Gathering Support: ");
					System.out.println(nextpanels_gather.size());
					this.no_GatheringSupport = Integer.toString(nextpanels_gather.size());
					break;
				case "Expired":
					Element nextSib_Expired = e.nextElementSibling();
					Elements nextpanels_Expired = nextSib_Expired.select("article.panel-tile");
					System.out.print("number of Expired: ");
					System.out.println(nextpanels_Expired.size());
					this.no_Expired = Integer.toString(nextpanels_Expired.size());
					break;
				case "Archived":
					Element nextSib_Archived = e.nextElementSibling();
					Elements nextpanels_Archived = nextSib_Archived.select("article.panel-tile");
					System.out.print("number of Archived: ");
					System.out.println(nextpanels_Archived.size());
					this.no_AchivedSupport = Integer.toString(nextpanels_Archived.size());
					break;
				case "In Review":
					Element nextSib_Review = e.nextElementSibling();
					Elements nextpanels_Review = nextSib_Review.select("article.panel-tile");
					System.out.print("number of Review: ");
					System.out.println(nextpanels_Review.size());
					this.no_InReview = Integer.toString(nextpanels_Review.size());
					break;
				case "Project Not Approved":
					Element nextSib_NotApproved = e.nextElementSibling();
					Elements nextpanels_NotApproved = nextSib_NotApproved.select("article.panel-tile");
					System.out.print("number of NotApproved: ");
					System.out.println(nextpanels_NotApproved.size());
					this.no_NotApproved = Integer.toString(nextpanels_NotApproved.size());
					break;
				default:
					break;
				}

			}
			// SUPPORTING
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.get("https://ideas.lego.com/profile/" + this.getAlias() + "/supporting");
			Document doc = Jsoup.parse(driver.getPageSource());
			// Elements secs = doc.select("section.header-user-achievement");
			// System.out.println(secs.toString());

			Elements panels = doc.select("article.panel-tile");
			System.out.print("number of supporting: ");
			System.out.println(panels.size());
			this.no_Supporting=Long.toString(panels.size());

			Elements secs = projects.select("section.header-user-achievement");
		
			
			for (final Element sec : secs) {
				final Elements heading2s = sec.select("h2");
				for (final Element heading2 : heading2s) {
					System.out.print(heading2.text() + ": ");
					System.out.println(heading2.select("span").text());
					this.Clutch_Power = heading2.select("span").text();
				}

				final Elements lis = sec.select("li");
				for (final Element li : lis) {
					if (li.attr("title").equals("10k Club")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this._10k_Club = li.select("span").text();
						if (_10k_Club.equals(null) || _10k_Club.equals("")) {
							this._10k_Club = "0";
						}
					}

					if (li.attr("title").equals("1k Club")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this._1k_Club = li.select("span").text();
						if (_1k_Club.equals(null) || _1k_Club.equals("")) {
							this._1k_Club = "0";
						}
					}

					if (li.attr("title").equals("5k Club")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this._5k_Club = li.select("span").text();
						if (_5k_Club.equals(null) || _5k_Club.equals("")) {
							this._5k_Club = "0";
						}
					}

					if (li.attr("title").equals("Pioneer")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this.Pioneer = li.select("span").text();
						if (Pioneer.equals(null) || Pioneer.equals("")) {
							this.Pioneer = "0";
						}
					}

					if (li.attr("title").equals("Luminary")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this.Luminary = li.select("span").text();
						if (Luminary.equals(null) || Luminary.equals("")) {
							this.Luminary = "0";
						}
					}

					if (li.attr("title").equals("Autobiographer")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this.Autobiographer = li.select("span").text();
						if (Autobiographer.equals(null) || Autobiographer.equals("")) {
							this.Autobiographer = "0";
						}
					}

					if (li.attr("title").equals("Trailblazer")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this.Trailblazer = li.select("span").text();
						if (Trailblazer.equals(null) || Trailblazer.equals("")) {
							this.Trailblazer = "0";
						}
					}

					if (li.attr("title").equals("Socializer")) {
						System.out.print(li.attr("title").toString() + ": ");
						System.out.println(li.select("span").text());
						this.Socializer = li.select("span").text();
						if (Socializer.equals(null) || Socializer.equals("")) {
							this.Socializer = "0";
						}
					}

				}}
		}
		
		
		public  String getFullProfile(WebDriver driver) throws Exception {
			this.setNo_ProFollowing(this.getUuid());
			this.setNo_UserFollowing(this.getUuid());
			this.setNo_FollowER(this.getUuid());
			this.profile_User_Score(driver);
			Gson gson2 = new Gson();
			String json2 = gson2.toJson(this);
			return json2;
		}
		
		public  void getFullProfileThread(String home_, ArrayList<String> input) throws Exception {
			
			WebDriver driver= login("quannguyen1","Chonn@m911005") ;
			for (int index_file = 0; index_file < input.size(); index_file++) {
				FileWriter FullProfileFile = null;
				String newLine = System.getProperty("line.separator");
				try {
					File FullProfile=new File(home_ + "////FullProfile");
					  if (FullProfile.exists()==false && FullProfile.isDirectory()==false) {
						  FullProfile.mkdir();
			            	//System.out.println("TAO MOI folder ProjectFollowing");
						}
					
					  FullProfile.mkdir();
					
					
					  FullProfileFile = new FileWriter(home_ + "////FullProfile////" + input.get(index_file)+"FullProfile.txt");
	                ArrayList<String> listID=new ArrayList<>();
	                listID=loadWordsList(home_+"////ID_user////"+input.get(index_file));
					
					for (int i = 0; i < listID.size()-2; i=i+3) {
						//System.out.println("======================================================================");
						//System.out.println("inputFile ProjectFollowing: " + input.get(index_file));
						//System.out.println("UUIDUser ProjectFollowing: " + listID.get(i));
						//System.out.println("----------------------------"+i+"-----------------------------------");
						System.out.println("IDUSER_size: "+ listID.size());
						System.out.println("IDUSER: "+listID.get(i)+"  "+listID.get(i+1)+"  "+listID.get(i+2));
						this.modprofile_User(listID.get(i),listID.get(i+1),listID.get(i+2));
						String FullProfile_= this.getFullProfile(driver);
	                    FullProfileFile.write(FullProfile_);
	                     System.out.println(FullProfile_);
						if (i < listID.size() - 3) {
							FullProfileFile.write(newLine);
						}

					}

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				FullProfileFile.close();
				//System.out.println("GET ProjectFollowing Thread sucess");

			}
			driver.close();
		}	
		
		
	public static void main(String[] args) throws Exception {
	//profile_User a= new profile_User("1025509","police6070","0d42ae43-b72b-4685-bf78-64b36016a6e6");
	
//	String all_activity=profile_User.GetAllActivity(a.getUuid());
//	//System.out.println(all_activity);
	
//	String project_following=profile_User.GetAllProjectFollowing(a.uuid);
//	//System.out.println(project_following);
//	Document doc=Jsoup.parse(project_following);
//	Elements li=doc.select("li");
	//System.out.println("Size __li__: "+li.size());
//		WebDriver driver= login("quannguyen1", "Chonn@m911005");
//	profile_User a=new profile_User("1050536","len_d69","e423bb33-a78e-4b7e-8534-53c2787816bb");
//	
	  //profile_User a=new profile_User(listID.get(i),listID.get(i+2),listID.get(i+3));
//		String FullProfile_=a.getFullProfile(driver);
     // FullProfileFile.write(FullProfile_);

//	System.out.println(FullProfile_);
	
}		
	  
}
