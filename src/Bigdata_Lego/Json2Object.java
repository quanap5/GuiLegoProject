package Bigdata_Lego;
//24 system.out
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Json2Object {

	public Json2Object() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void Json2Object_ID(String home_, ArrayList<String> input) throws IOException {

		for (int index_file = 0; index_file < input.size(); index_file++) {
			FileWriter IDproject_IDcommentFile = null;
			FileWriter ID_userFile  = null;
			String newLine = System.getProperty("line.separator");
			try {
				
			
				File ID = new File(home_ + "////ID");
				
	            if (ID.exists()==false && ID.isDirectory()==false) {
	            	ID.mkdir();
				}
			
				IDproject_IDcommentFile = new FileWriter(home_ + "////ID////" + input.get(index_file));
				
				
                File ID_user = new File(home_ + "////ID_user");
				
	            if (ID_user.exists()==false && ID_user.isDirectory()==false) {
	            	ID_user.mkdir();
				}
			
	            ID_userFile = new FileWriter(home_ + "////ID_user////" + input.get(index_file));
				
				

				BufferedReader file2json;
				file2json = new BufferedReader(new FileReader(home_ + input.get(index_file)));
				Gson gson = new GsonBuilder().create();
				MyBigdata myBig = gson.fromJson(file2json, MyBigdata.class);
				for (int i = 0; i < myBig.getData().length; i++) {
					//System.out.println("============================================================================");
					//System.out.println("Input file: " + input.get(index_file));
					//System.out.println("-----------------------------" + i + "-----------------------------------");
					IDproject_IDcommentFile.write(myBig.getData()[i].getUser().getUuid() + newLine);
					IDproject_IDcommentFile.write(myBig.getData()[i].getUuid());
					
				
					
					if (i < myBig.getData().length - 1) {
						IDproject_IDcommentFile.write(newLine);
					}
					
					
					ID_userFile.write(myBig.getData()[i].getUser().getId() + newLine);
					ID_userFile.write(myBig.getData()[i].getUser().getAlias() + newLine);
					ID_userFile.write(myBig.getData()[i].getUser().getUuid());
					if (i < myBig.getData().length - 1) {
						ID_userFile.write(newLine);
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			IDproject_IDcommentFile.close();
			ID_userFile.close();
			//System.out.println("GET ID project and Comment sucess");
		}

	}
	
	public void Json2Object_Project(String home_, ArrayList<String> input) throws IOException {

		for (int index_file = 0; index_file < input.size(); index_file++) {
			FileWriter ProjectFile = null;
			
			String newLine = System.getProperty("line.separator");
			try {
				
			
				File Project = new File(home_ + "////Project");
				
	            if (Project.exists()==false && Project.isDirectory()==false) {
	            	Project.mkdir();
				}
			
	            ProjectFile = new FileWriter(home_ + "////Project////" + input.get(index_file));
				
	            BufferedReader file2json;
				file2json = new BufferedReader(new FileReader(home_ + input.get(index_file)));
				Gson gson = new GsonBuilder().create();
				MyBigdata myBig = gson.fromJson(file2json, MyBigdata.class);
				for (int i = 0; i < myBig.getData().length; i++) {
					//System.out.println("============================================================================");
					//System.out.println("Input file: " + input.get(index_file));
					//System.out.println("-----------------------------" + i + "-----------------------------------");
					//Gson gson2 = new Gson();
					//String json2 = gson2.toJson(this);
					
					ProjectFile.write(Long.toString(Instant.now().getEpochSecond()));
					ProjectFile.write(new Gson().toJson(myBig.getData()[i]));
					
				
					
					if (i < myBig.getData().length - 1) {
						ProjectFile.write(newLine);
					}
					
					
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ProjectFile.close();
			
			//System.out.println("GET ID project and Comment sucess");
		}

	}


	public static void GetCommentThread(String home_, ArrayList<String> input) throws Exception {

		for (int index_file = 0; index_file < input.size(); index_file++) {
			FileWriter CommentFile = null;
			String newLine = System.getProperty("line.separator");
			try {
				File Comment =new File(home_ + "////Comment");
				  if (Comment.exists()==false && Comment.isDirectory()==false) {
		            	Comment.mkdir();
		            	//System.out.println("TAO MOI");
					}
				
				Comment.mkdir();
				
				
				CommentFile = new FileWriter(home_ + "////Comment////" + input.get(index_file)+"Comment.txt");
                ArrayList<String> listID=new ArrayList<>();
                listID=loadWordsList(home_+"////ID////"+input.get(index_file));
				
				for (int i = 1; i < listID.size(); i=i+2) {
					//System.out.println("======================================================================");
					//System.out.println("inputFile: " + input.get(index_file));
					//System.out.println("UUIDcomment: " + listID.get(i));
					//System.out.println("----------------------------"+i+"-----------------------------------");
                    String JsonComment= GetAllComment(listID.get(i));
                    CommentFile.write(Long.toString(Instant.now().getEpochSecond()));
                    CommentFile.write(listID.get(i));
                    CommentFile.write(JsonComment);
					if (i < listID.size() - 1) {
						CommentFile.write(newLine);
					}

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			CommentFile.close();
			//System.out.println("GET Comment Thread sucess");

		}
	}
	
	public static String sendGet_ForComment(String uuid,String next_index) throws Exception {
		String Session = "session=1f883112-f304-4419-8b86-da493e08980a";
		//String body = null;
		//System.out.println("sendGet_ForComment");
		String url = "https://ideas.lego.com/comments/project/" + uuid
				+ "/comments?order=oldest&from=min&style=children&max_id=&min_id=&limit=250"
				+ "&id=comments-query-/comments" + "/project/" + uuid + "/comments"+next_index;
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
		
		//System.out.println("GET full response Header Comment: " + con.getHeaderFields().toString());
		//System.out.println("Out Json of comment: " + response.toString());
		//System.out.println("FINISH sendGet_ForComment");
		//System.out.println("======================================================");
//		
//		if (response.lastIndexOf("id")>0) {
//			body=body+", "+response.toString().substring(response.toString().indexOf("results")+11, response.toString().lastIndexOf("}]}")+1);
//			//System.out.println("Body : " +body);
//			next_index=response.toString().substring(response.toString().lastIndexOf("id")+6, response.toString().lastIndexOf("}]}")-1);
//			//System.out.println("next Index of comment: " +next_index);
//			
//		}
//		else {
//			//System.out.println("next Index of comment: " +"END");
//		}
//		
		return response.toString();

	}
	
	public static String GetAllComment(String uuid) throws Exception {
		String all_comment="";
		String next_index="";
		String medium=sendGet_ForComment(uuid, next_index);
		while (medium.lastIndexOf("id")>0) {
			if (all_comment== "") {
				all_comment=medium.substring(medium.indexOf("results")+11, medium.lastIndexOf("}]}")+1);
			}else {
				all_comment=all_comment+", "+medium.substring(medium.indexOf("results")+11, medium.lastIndexOf("}]}")+1);	
			}
			//System.out.println("Body Comment : " +all_comment);
			next_index="&min_id="+medium.toString().substring(medium.lastIndexOf("id")+6, medium.lastIndexOf("}]}")-1);
			//System.out.println("next Index of comment: " +next_index);
			//System.out.println("next Index length: " +next_index.length());
			if (next_index.length()==44) {
				medium=sendGet_ForComment(uuid, next_index);
			}
			else {
				medium="";
			}
			
			
		}
		
		all_comment= "{\"results\": ["+all_comment+"]}";
		return all_comment;
		
	}

	/**
	 * Read word from text file
	 * 
	 * @return ArrayList<String>
	 * @see loadWordsList
	 */
	public static ArrayList<String> loadWordsList(String pLoc) {
		ArrayList<String> wordlist = new ArrayList<String>();
		try {
			ArrayList<String> words = (ArrayList<String>) Files.readAllLines(Paths.get(pLoc), StandardCharsets.UTF_8);
			for (String string : words) {
				wordlist.add(string.trim());
			}
		} catch (IOException ex) {
			// //System.out.println("Load Word error");
		}
		return wordlist;
	}
	
}