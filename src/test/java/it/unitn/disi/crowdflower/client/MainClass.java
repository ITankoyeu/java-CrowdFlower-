package it.unitn.disi.crowdflower.client;
import it.unitn.disi.crowdflower.client.model.CrowdFlowerJob;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;




/**Main class. The class provides main(). Where it reads config file and establish connection.
 * @author Ivan Tankoyeu
 * @since 08/05/2013
 */

public class MainClass {


	static String authKey; //authentication key 
	static URL CFurl;



	public static void main(String args[]) throws ClientProtocolException, IOException, ParseException, IllegalArgumentException, IllegalAccessException, URISyntaxException, InterruptedException{





		Properties prop = new Properties();

		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));

			//get the authentication key from properties file 
			authKey = prop.getProperty("authKey");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		//			JobConnection jc = new JobConnection();
		//				CFResponse response = new CFResponse();
		//		CFJavaAPI instance = new CFJavaAPI(authKey);
		//		
		//		instance.statusJob(195942);
		//		instance.statusJob(195939);
		//		instance.statusJob(195623);
		//		instance.statusJob(160783);
		//	WebHook wh = new WebHook(authKey, instance, jc);
		//	wh.webHook(200, 160783);
		//	wh.webHook(5000,195623);
		//	CrowdFlowerJob cfJob = new CrowdFlowerJob();
		//		int id =11;
		//		cfJob.setCml("11");
		//	
		//			HashMap<Integer,CrowdFlowerJob> map = instance.readAllJobs();
		//			System.out.println(map.size());
		//			 instance.createJob();
		//			    int id = 195389;
		//			  instance.readJob(194683);
		//				 instance.deleteJob(id);
		// System.out.println(cfj.toString());
		MainClass test = new MainClass();
		test.testTaskforCubrick();
	}


	/** The method creates a job similar to the task of Cubrick
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws URISyntaxException 
	 * 
	 */
	public void testTaskforCubrick() throws ClientProtocolException, IOException, ParseException, URISyntaxException{
		CFJavaAPI instance = new CFJavaAPI(authKey);

//		long status = instance.statusJob(147195);
//		if (status==0){
//			System.out.println("Status: "+status);
//			String judgements = instance.downloadJobReport(147195, false);
//			System.out.println(judgements);}
		CrowdFlowerJob cfJobServer = instance.createJob();
		int jobId = cfJobServer.getId();
		CrowdFlowerJob cfJobClient = new CrowdFlowerJob();
		String title = "Select different images of italian Monuments for photo collection";
		String instructions = "Select different images of italian Monuments for photo collection"; 
		String cmlFile = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\CMLCode_Diversity.txt";
		String cml = instance.readFile(cmlFile);
		cfJobClient.setTitle(title);
		cfJobClient.setInstructions(instructions);
		cfJobClient.setCml(cml);
		cfJobClient.setAuto_order(true);
		cfJobClient.setJudgments_per_unit(3);
		cfJobClient.setUnits_per_assignment(1);
		cfJobClient.setGold_per_assignment(5);	
		System.out.println("Updated id: "+jobId);
	
		cfJobServer =instance.updateJob(jobId, cfJobClient);
		instance.setChannel(jobId, "amt");
		//		
		//		
		String jsonTask ="{\"city\":\"Italy\",\"englishname\":\"San Petronio Basilica\","
				+ "\"photolink1\":\"http://api.entitypedia.org:8082/webapi/files/124\","
				+ "\"wikiurl\":\"http://en.wikipedia.org/wiki/San_Petronio_Basilica\"}";
		//		System.out.println(jsonTask);
		//				
		//		
		
		instance.uploadJobJSON("application/json", jsonTask, jobId);

		String filePath = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\Task.csv";
		//		//TODO change hardcoded path to relative one 
		//CrowdFlowerJob cfJobUploaded =instance.uploadJob("text/csv", filePath, jobId);
		//instance.uploadJob("text/csv", filePath, jobId);
		//		instance.orderJob(jobId);

		//		IWebHook webHook= new WebHook() {
		//			public void jobReady(long jobId) {
		//				// TODO: waiting for notification
		//			}
		//		};

		//CrowdFlowerJob cfJobServer = instance.createJob(cfJob /*,webHook*/);


		//		
		//		List<String> list;
		//		Comparator<String> comp = new Comparator<String>() {
		//			public int compare(String o1, String o2) {
		//				return o1.compareTo(o2);
		//			}
		//		};

		//System.out.println(cfJobServer.toString());


		//int id =  cfJobServer.getId();

		//System.out.println(id);


		//	instance.orderJob(id);
		//path to a file for uploading data
		//String filePath = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\Task.csv";
		//CrowdFlowerJob cfJobFinal=instance.uploadJob(id,"xlsx", filePath, true);
		//	instance.pauseJob(id);
		//System.out.println("Final results: "+cfJobFinal.toString());

		//		String path = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\out.zip";
		//		//instance.downloadJobReport(195939, true, path);
		//		String JSONlegend = instance.legendJob(195939);
		//		CFResponse response = new CFResponse();
		//		response.parseLegend(JSONlegend);
	}


}
