package it.unitn.disi.crowdflower.client;

import static org.junit.Assert.*;

import it.unitn.disi.crowdflower.client.model.CrowdFlowerJob;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CFJavaAPITest {
	String authKey;
	CFJavaAPI instance=new CFJavaAPI(authKey); 
	int jobId;
	String title;
	String instructions;
	String cmlFile;
	String cml; 

	@Test
	public void testReadConfigFile(){
		Properties prop = new Properties();
		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));
			//get the authentication key from properties file 
			assertEquals("xxx",prop.getProperty("authKeyTest"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}	
	}
	
	@Before 
	public void setUp(){
		Properties prop = new Properties();
		//	String authKey;
		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));
			//get the authentication key from properties file 
			authKey =prop.getProperty("authKey");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		instance = new CFJavaAPI(authKey);
	}
	
	@Test
	public void testCreateBlankJob() throws ClientProtocolException, IOException, ParseException, URISyntaxException {
		CrowdFlowerJob cfJob; 
		cfJob = instance.createJob();
		jobId=cfJob.getId();
		//System.out.println("Test job id: "+jobId);
		assertNotNull(jobId);
		instance.deleteJob(jobId);
	}
//	@After
//	public void testClean() throws ClientProtocolException, IOException, ParseException{
//	instance.deleteJob(jobId);
//	
//	}
		
	@Test //Creates  job from existing  object JOB 
	public void testCreateJobFromObj() throws ClientProtocolException, IOException, ParseException, URISyntaxException{
		String titleTest = "Test Job";
		CrowdFlowerJob cfJobClient = new CrowdFlowerJob(); 
		cfJobClient.setTitle(titleTest);
		CrowdFlowerJob cfJobServer = instance.createJob(cfJobClient);
		  jobId=cfJobServer.getId();
		assertTrue(titleTest.equals(cfJobServer.getTitle()));
	}
//	@After
//	public void testClean1() throws ClientProtocolException, IOException, ParseException{
//	instance.deleteJob(jobId);
//	
//	}
	
	@Test
	public void testReadAllJob() throws ClientProtocolException, IOException, ParseException{

		HashMap<Integer,CrowdFlowerJob> mapJobs;
		mapJobs = instance.readAllJobs();
		assertNotNull(mapJobs);
	}

	
	@Test// In this test we update blank job created early 
	public void testUpdateJob() throws ClientProtocolException, IOException, ParseException, URISyntaxException{
		CrowdFlowerJob cfJobServer = instance.createJob();
		jobId = cfJobServer.getId();
		CrowdFlowerJob cfJobClient = new CrowdFlowerJob();
		title = "Select different images of italian Monuments for photo collection";
		instructions = "Select different images of italian Monuments for photo collection"; 
		cmlFile = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\CMLCode_Diversity.txt";
		cml = instance.readFile(cmlFile);
		cfJobClient.setTitle(title);
		cfJobClient.setInstructions(instructions);
		cfJobClient.setCml(cml);
		cfJobClient.setAuto_order(true);
		cfJobClient.setJudgments_per_unit(3);
		cfJobClient.setUnits_per_assignment(1);
		cfJobClient.setGold_per_assignment(5);	
		System.out.println("Updated id: "+jobId);
		cfJobServer =instance.updateJob(jobId, cfJobClient);
		assertTrue(instructions.equals(cfJobServer.getInstructions()));
	}
	@After
	public void testClean2() throws ClientProtocolException, IOException, ParseException{
	instance.deleteJob(jobId);
	
	}
	
	@Before public void setUp2() throws IOException, ParseException, URISyntaxException{
		Properties prop = new Properties();
		//	String authKey;
		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));
			//get the authentication key from properties file 
			authKey =prop.getProperty("authKey");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		instance = new CFJavaAPI(authKey);
	CrowdFlowerJob cfJobServer = instance.createJob();
	jobId = cfJobServer.getId();
	CrowdFlowerJob cfJobClient = new CrowdFlowerJob();
	title = "Select different images of italian Monuments for photo collection";
	instructions = "Select different images of italian Monuments for photo collection"; 
	cmlFile = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\CMLCode_Diversity.txt";
	cml = instance.readFile(cmlFile);
	cfJobClient.setTitle(title);
	cfJobClient.setInstructions(instructions);
	cfJobClient.setCml(cml);
	cfJobClient.setAuto_order(true);
	cfJobClient.setJudgments_per_unit(3);
	cfJobClient.setUnits_per_assignment(1);
	cfJobClient.setGold_per_assignment(5);	
	System.out.println("Updated id: "+jobId);
	cfJobServer =instance.updateJob(jobId, cfJobClient);}

	@Test// In this test we set and view channels for an existing job
	public void testSetViewChannels() throws ClientProtocolException, IOException, ParseException{
		System.out.println(jobId);
		int setChannel=instance.setChannel(jobId, "amt"); // if  success returns 1 
		String containsAMT = instance.viewEnabledChannels(jobId);
		System.out.println("Enabled channel: "+containsAMT);
		assertTrue((setChannel==1)&&(containsAMT.equals("[\"amt\"]")));
	}
	
	
	
	@Before public void setUp3() throws IOException, ParseException, URISyntaxException{
		Properties prop = new Properties();
		//	String authKey;
		try {
			//load a properties file
			prop.load(new FileInputStream("config.properties"));
			//get the authentication key from properties file 
			authKey =prop.getProperty("authKey");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		instance = new CFJavaAPI(authKey);
	CrowdFlowerJob cfJobServer = instance.createJob();
	jobId = cfJobServer.getId();

	}
	@Test 
	public void testUploadJobData() throws ClientProtocolException, IOException, ParseException{
		String filePath = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\Task.csv";
		//TODO change hardcoded path to relative one 
		CrowdFlowerJob cfJobUploaded =instance.uploadJob("text/csv", filePath, jobId);
		assertNotNull(cfJobUploaded);
		
	}

	@Test // download reports. 195939 is the finished job  
	public void testDownloadJobReport() throws ClientProtocolException, IOException, ParseException{
		String fileOutPath = "C:\\work\\development\\cubrick\\cubrick\\src\\test\\resources\\out.zip";
		//TODO change hardcoded path to relative one and job ID
		instance.downloadJobReport(195939, true, fileOutPath);
		File fileOut = new File(fileOutPath);
		assertTrue(fileOut.exists());
		
	}
	
	
	@Ignore
	public void testWebHook() throws ClientProtocolException, IOException, ParseException, InterruptedException{
		JobConnection jc = new JobConnection();
	

		long time=5000; //5 secs
		int id1=160783; //finished job
		int id2=195623;//unfinished job
		
		IWebHook wh = new WebHook(authKey, instance, jc, time, id1){
			public void jobReady() {
				// read results 
				// call next pipeline
			}
		};
		
		//Boolean state2 = wh.webHook(time,id2);
		//assertTrue(state1);
	}
	

}

