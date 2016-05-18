import java.util.*;
import java.io.*;
import javax.json.*;
import java.net.*;

class Jango {
	static String filepath;
	static String filename;

	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	public static int[] genData(int min, int max, int size){
		int[] temp = new int[size];
		Random rand = new Random();
		temp[0] = 0;
		for(int i = 1; i < size; i++){
			temp[i] = rand.nextInt(max - min) + min;
		}
		return temp;
	}

	public static void writeToFile(int[] arr, FileWriter openedWriter){
		try {
			for(int i = 0; i < arr.length; i++) {
				openedWriter.append(Integer.toString(arr[i]));
				if (i != arr.length - 1) {
					openedWriter.append(",");
				}
			}
			openedWriter.append("\n");
			openedWriter.flush();
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}



	public static void go(){
		int[] n = new int[4];
		filepath = "";
		filename = filepath+"test.csv";
		try{
			FileWriter writer = new FileWriter(filename);
			writer.append("id,city,code,tzone\n");
			writer.flush();
			int lines = 0;
			while (lines < 100) {
				if (lines % 5 == 0){
					System.out.println("Writing to line: " + Integer.toString(lines+1));
				}
				n = genData(0, 9, n.length);
				n[0] = lines+1;
				writeToFile(n, writer);
				wait(100); //ms
				lines++;
			}
			writer.close();
		} catch(IOException ex) {
			ex.printStackTrace();
		}

	}

	public static JsonArray csvToJson(String file){
		JsonArray jArray = Json.createArrayBuilder()
			.add(Json.createObjectBuilder()).build();
		JsonArrayBuilder jArrBuilder = Json.createArrayBuilder();
		JsonObjectBuilder jObjBuilder = Json.createObjectBuilder();
		String[] fieldnames = {"id", "city", "code", "tzone"};
		String[] fields;
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line=br.readLine()) != null) {
				fields = line.split(",");
				jObjBuilder = Json.createObjectBuilder();
				for(int i = 0; i<fields.length; i++){
					jObjBuilder.add(fieldnames[i], fields[i]);
				}
				System.out.println(jObjBuilder.build().toString());
				jArrBuilder.add(jObjBuilder);
			}
			jArray = jArrBuilder.build();

		} catch(Exception ex){
			ex.printStackTrace();
		}
		return jArray;
	}
	public static String streamToString(InputStream is){
		Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	public static void sendToServer(){
		JsonArray jArr = csvToJson(filename);
		String json = jArr.toString();
		System.out.println("Printing csvToJson");
		System.out.println(json);
		String postUrl = "http://iamsitting.no-ip.org/api/";
		try{
			URL obj = new URL(postUrl);

			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestProperty("Content-type",
					"application/json; charset=UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");

			OutputStream os = conn.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();

			InputStream in = new BufferedInputStream(conn.getInputStream());
			String response = streamToString(in);
			System.out.println(response);
			//JsonReader jsonReader = Json.createReader(new StringReader("{}"));
			//JsonObject jObj = jsonReader.readObject();
			//jsonReader.close();
		} catch(MalformedURLException ex){ ex.printStackTrace();
		} catch(IOException ex){ ex.printStackTrace();
		}

		System.out.println("Sent!");
	}
	public static void main(String args[]) {
		go();
		System.out.println("Done recording!");
		System.out.println("Do you want to save? [y/n]");
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		if ((s.equals("Y")) || (s.equals("y"))){
			sendToServer();
		}
		else {
			try {
				File f = new File(filename);
				f.delete();
			} catch (Exception ex){
				ex.printStackTrace();
			}
			System.out.println("File Deleted!");
		}
		System.out.println("Done!");
	}
}

