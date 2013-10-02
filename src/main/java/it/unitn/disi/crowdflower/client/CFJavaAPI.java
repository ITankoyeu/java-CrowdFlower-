package it.unitn.disi.crowdflower.client;
import it.unitn.disi.crowdflower.client.model.CrowdFlowerJob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.parser.ParseException;


/**
 * @author Ivan
 *
 */
public class CFJavaAPI {

	String authKey;
	JobConnection jc = new JobConnection();

	public CFJavaAPI(String authKey) {
		super();
		this.authKey = authKey;

	}

	/**
	 * @param id
	 * @return
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CrowdFlowerJob readJob(int id) throws ParseException, ClientProtocolException, IOException{

		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		//System.out.println(param);
		String url = jc.buildURI(pairs, "jobs", "json", param);
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		CrowdFlowerJob cfJob = response.parseJSONObjJob(inStr);
		return cfJob;
	}

	/** The method 
	 * @return a hashmap where key equals to CrowdFlower job ID and value is entity of a CrowFlowerJob  class  
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public HashMap<Integer,CrowdFlowerJob> readAllJobs() throws ClientProtocolException, IOException, ParseException{

		CFResponse response = new CFResponse();
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURIGet(pairs, "jobs", "json");
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(!response.isMessage(inStr))  //If not a message
		{
			//System.out.println("IS: "+inStr);
			HashMap<Integer,CrowdFlowerJob> cfJobs = response.parseJsonMassJobResponse(inStr);
			return cfJobs;
		}
		else {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}
	} 




	/** The method aims to create a CrowdFlower job without any given paraqmeters
	 * @return created CrowdFlower job
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws URISyntaxException 
	 */
	public CrowdFlowerJob createJob() throws ClientProtocolException, IOException, ParseException, URISyntaxException{

		CFResponse response = new CFResponse(); 
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("job[without_data]", "true"));
		String url = jc.buildURIPost( "jobs", "json");
		InputStream is=jc.HTTPmethodPost(pairs, url);
		String inStr = convertStreamToString(is);
		if(!response.isMessage(inStr)){
			CrowdFlowerJob cfJob = response.parseJSONObjJob(inStr);
			return cfJob;}
		else  {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}

	}

	/** The methods creates a CrowdFlower job with properties taken from the existing CfJob see parameter.
	 * @param cfJob java model of CrowdFlower job
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @throws URISyntaxException 
	 */
	public CrowdFlowerJob createJob(CrowdFlowerJob cfJob ) throws ClientProtocolException, IOException, ParseException, URISyntaxException{

		CFResponse response = new CFResponse(); 
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));

		if (cfJob.getAuto_order()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order]", cfJob.getAuto_order().toString()));}
		if (cfJob.getAuto_order_threshold()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order_threshold]",cfJob.getAuto_order_threshold()));}
		if (cfJob.getAuto_order_timeout()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order_timeout]",cfJob.getAuto_order_timeout()));}
		if (cfJob.getCml()!=null)
		{pairs.add(new BasicNameValuePair("job[cml]",cfJob.getCml()));}
		if (cfJob.getCml_fields()!=null)
		{pairs.add(new BasicNameValuePair("job[cml_fields]",cfJob.getCml_fields()));}

		//TODO ArrayList<String> confidence_fields; 

		if (cfJob.getCss()!=null)
		{pairs.add(new BasicNameValuePair("job[css]",cfJob.getCss()));} 
		if (cfJob.getCustom_key()!=null)
		{pairs.add(new BasicNameValuePair("job[custom_key]",cfJob.getCustom_key()));} 

		//TODO ArrayList<String> excluded_countries;
		//TODO ArrayList<String> included_countries;

		if (cfJob.getInstructions()!=null)
		{pairs.add(new BasicNameValuePair("job[instructions]",cfJob.getInstructions()));} 
		if (cfJob.getJs()!=null)
		{pairs.add(new BasicNameValuePair("job[js]",cfJob.getJs()));} 
		if ((Long)cfJob.getJudgments_per_unit()!=null)
			//		{pairs.add(new BasicNameValuePair("job[judgments_per_unit]",String.valueOf(cfJob.getJudgments_per_unit())));} 
			//		if (cfJob.getLanguage()!=null)
			//		{pairs.add(new BasicNameValuePair("job[language]",cfJob.getLanguage()));} 
			//		if ((Long)cfJob.getMax_judgments_per_unit()!=null)
			//		{pairs.add(new BasicNameValuePair("job[max_judgments_per_unit]",String.valueOf(cfJob.getMax_judgments_per_unit())));} 
			//		if ((Long)cfJob.getMax_judgments_per_worker()!=null)
			//		{pairs.add(new BasicNameValuePair("job[max_judgments_per_worker]",String.valueOf(cfJob.getMax_judgments_per_worker())));} 
			//		if (cfJob.getMin_unit_confidence()!=null)
			//		{pairs.add(new BasicNameValuePair("job[min_unit_confidence]",cfJob.getMin_unit_confidence()));} 

			// TODO JobOptions options;

			if (cfJob.getPages_per_assignment()!=null)
			{pairs.add(new BasicNameValuePair("job[pages_per_assignment]",cfJob.getPages_per_assignment()));}
		if (cfJob.getProblem()!=null)
		{pairs.add(new BasicNameValuePair("job[problem]",cfJob.getProblem()));}
		if (cfJob.getSend_judgments_webhook()!=null)
		{pairs.add(new BasicNameValuePair("job[send_judgments_webhook]",cfJob.getSend_judgments_webhook()));}
		if (cfJob.getState()!=null)
		{pairs.add(new BasicNameValuePair("job[state]",cfJob.getState()));}
		if (cfJob.getTitle()!=null)
		{pairs.add(new BasicNameValuePair("job[title]",cfJob.getTitle()));}
		//if ((Long)cfJob.getUnits_per_assignment()!=null)
		//{pairs.add(new BasicNameValuePair("job[units_per_assignment]",String.valueOf(cfJob.getUnits_per_assignment())));} 
	//	if (cfJob.getWebhook_uri()!=null)
		//{pairs.add(new BasicNameValuePair("job[webhook_uri]",cfJob.getWebhook_uri()));}
		String url = jc.buildURIPost( "jobs", "json");
		InputStream is=jc.HTTPmethodPost(pairs, url);
		String inStr = convertStreamToString(is);
		//System.out.println("Test: "+inStr);

		if(!response.isMessage(inStr)){
			CrowdFlowerJob cfJobServer = response.parseJSONObjJob(inStr);
			System.out.println("Job created successfully!");
			return cfJobServer;}
		else  {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}

	}

	public int setChannel(int id, String channel) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("channels[]", channel));
		String url = jc.buildUriChannel( "jobs", param, "channels");
	//	System.out.println("Set channel url: "+url);
		InputStream is=jc.HTTPmethodPut(pairs, url);
		String inStr = convertStreamToString(is);
		System.out.println(inStr);
//		if ((response.parseJSONObjMessage(inStr))!=1){ // if method executed with error
//			System.out.println("Set channels: " +inStr);}
        return response.parseJSONObjMessage(inStr);
	}


	/** The method deletes job. 
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void deleteJob( int id) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		//	System.out.println(param);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs", param, "json" );
		InputStream is=jc.HTTPmethodDelete(url);
		String inStr = convertStreamToString(is);
		if ((response.parseJSONObjMessage(inStr))!=1){ // if method executed with error
			System.out.println(inStr);}

	}

	/** The method upload data for existing job. 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 * 
	 */
	public CrowdFlowerJob uploadJob(String format, String filePath, int id) throws ClientProtocolException, IOException, ParseException{

		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		File file = new File(filePath);
		
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("force", "true"));
		String url = jc.buildURI(pairs, "jobs", param,"upload","json");

		System.out.println("upload url: "+url);
		InputStream is = jc.HTTPmethodPostUpload(file, url,format);
		String inStr = convertStreamToString(is);
		if(!response.isMessage(inStr)){
			CrowdFlowerJob cfJobServer = response.parseJSONObjJob(inStr);
			return cfJobServer;}
		else  {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}

	}

	public CrowdFlowerJob uploadJobJSON(String format, String jsonTask, int id) throws ClientProtocolException, IOException, ParseException{

		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
	//	File file = new File(filePath);
		
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("force", "true"));
		String url = jc.buildURI(pairs, "jobs", param,"upload","json");

		System.out.println("upload url: "+url);
		InputStream is = jc.HTTPmethodPostUpload(jsonTask, url,format);
		String inStr = convertStreamToString(is);
		if(!response.isMessage(inStr)){
			CrowdFlowerJob cfJobServer = response.parseJSONObjJob(inStr);
			return cfJobServer;}
		else  {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}

	}
	/** The method upload data for existing job. 
	 * @param id is an id of a job for which data is uploading
	 * @return 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 */
	public CrowdFlowerJob uploadJob(String format, String filePath, Boolean force) throws ClientProtocolException, IOException, ParseException{

		CFResponse response = new CFResponse(); 
		//String param = Integer.toString(id);
		File file = new File(filePath);
		
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("force", "true"));
		String url = jc.buildURIUpload( "jobs",pairs, "upload","json");

		System.out.println("upload url: "+url);
		InputStream is = jc.HTTPmethodPostUpload(file, url,format);
		String inStr = convertStreamToString(is);
		if(!response.isMessage(inStr)){
			CrowdFlowerJob cfJobServer = response.parseJSONObjJob(inStr);
			return cfJobServer;}
		else  {System.out.println(response.parseJSONObjMessage(inStr)); // shows the message
		return null;}              

	}

	
	/** The method make a copy of job with a given ID 
	 * @param id is a job id
	 * @param all_units If set to true, all of this job’s units will be copied to the new job.
	 * @param gold If set to true, only golden units will be copied to the new job. 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 * @throws URISyntaxException 
	 */
	public void copyJob(int id, Boolean all_units, Boolean gold) throws ClientProtocolException, IOException, ParseException, URISyntaxException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);

		List<NameValuePair> pairs = new ArrayList<NameValuePair>();

		pairs.add(new BasicNameValuePair("job[all_units]", all_units.toString()));
		pairs.add(new BasicNameValuePair("job[gold]", gold.toString()));
		
		InputStream is = jc.HTTPmethodPost(pairs, param);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}


	}

	/** The method  pauses a job and temporarily stop judgements from coming in.
	 * @param id
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void pauseJob(int id) throws ParseException, ClientProtocolException, IOException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs", param,"pause", "json");
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
	}

	/** The method resumes paused job.
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void resumeJob(int id) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs", param,"resume", "json");
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
	}

	/** The method cancels a job and  permanently stops judgements from coming in and refund account for any judgements not yet received.
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public void cancelJob(int id) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs", param,"cancel", "json");
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
	}

	public void orderJob(int id) throws ClientProtocolException, IOException, ParseException, URISyntaxException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		pairs.add(new BasicNameValuePair("debit[units_count]","8"));
		pairs.add(new BasicNameValuePair("channels[0]","amt"));
		
		String url = jc.buildURI(pairs, "jobs", param,"orders","json");
		System.out.println(url);
		InputStream is=jc.HTTPmethodPost(pairs, url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
	}
	
	
	
	/** The method allows to check the status/progress of your job at any time with this method.
	 * @param id
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 * @return number of unfinished gudgements
	 */
	public Long statusJob(int id) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs", param,"ping", "json");
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		System.out.println("Status: "+inStr);
		//TODO parse status of a job
		Long restJudgements = response.parseStatus(inStr);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
		return restJudgements;
	}

	/** The method updates job with the according to a set newly defined parameters 
	 * @param id
	 * @param cfJob
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public CrowdFlowerJob updateJob(int id, CrowdFlowerJob cfJob) throws ParseException, ClientProtocolException, IOException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		
		if (cfJob.getAuto_order()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order]", cfJob.getAuto_order().toString()));}
		if (cfJob.getAuto_order_threshold()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order_threshold]",cfJob.getAuto_order_threshold()));}
		if (cfJob.getAuto_order_timeout()!=null)
		{pairs.add(new BasicNameValuePair("job[auto_order_timeout]",cfJob.getAuto_order_timeout()));}
		if (cfJob.getCml()!=null)
		{pairs.add(new BasicNameValuePair("job[cml]",cfJob.getCml()));}
		if (cfJob.getCml_fields()!=null)
		{pairs.add(new BasicNameValuePair("job[cml_fields]",cfJob.getCml_fields()));}
		// TODO ArrayList<String> confidence_fields; 
		if (cfJob.getCss()!=null)
		{pairs.add(new BasicNameValuePair("job[css]",cfJob.getCss()));} 
		if (cfJob.getCustom_key()!=null)
		{pairs.add(new BasicNameValuePair("job[custom_key]",cfJob.getCustom_key()));} 
		//TODO ArrayList<String> excluded_countries;
//		if (cfJob.getGold_per_assignment()!=(Long)null)
//		{pairs.add(new BasicNameValuePair("gold_per_assignment",String.valueOf(cfJob.getGold_per_assignment())));} 
		//	if (cfJob.getGold_per_assignment()!=(Long)null)
		//		pairs.add(new BasicNameValuePair("included_countries","Argentina")); 
		// TODO ArrayList<String> included_countries;
		if (cfJob.getInstructions()!=null)
		{pairs.add(new BasicNameValuePair("job[instructions]",cfJob.getInstructions()));} 
		if (cfJob.getJs()!=null)
		{pairs.add(new BasicNameValuePair("job[js]",cfJob.getJs()));} 
//		if (cfJob.getJudgments_per_unit()!=(Long)null)
//		{pairs.add(new BasicNameValuePair("judgments_per_unit",String.valueOf(cfJob.getJudgments_per_unit())));} 
		if (cfJob.getLanguage()!=null)
		{pairs.add(new BasicNameValuePair("job[language]",cfJob.getLanguage()));} 
//		if (cfJob.getMax_judgments_per_unit()!=(Long)null)
//		{pairs.add(new BasicNameValuePair("max_judgments_per_unit",String.valueOf(cfJob.getMax_judgments_per_unit())));} 
//		if (cfJob.getMax_judgments_per_worker()!=(Long)null)
//		{pairs.add(new BasicNameValuePair("max_judgments_per_worker",String.valueOf(cfJob.getMax_judgments_per_worker())));} 
		if (cfJob.getMin_unit_confidence()!=null)
		{pairs.add(new BasicNameValuePair("job[min_unit_confidence]",cfJob.getMin_unit_confidence()));} 

		// TODO JobOptions options;

		if (cfJob.getPages_per_assignment()!=null)
		{pairs.add(new BasicNameValuePair("job[pages_per_assignment]",cfJob.getPages_per_assignment()));}
		if (cfJob.getProblem()!=null)
		{pairs.add(new BasicNameValuePair("job[problem]",cfJob.getProblem()));}
		if (cfJob.getSend_judgments_webhook()!=null)
		{pairs.add(new BasicNameValuePair("job[send_judgments_webhook]",cfJob.getSend_judgments_webhook()));}
		if (cfJob.getState()!=null)
		{pairs.add(new BasicNameValuePair("job[state]",cfJob.getState()));}
		if (cfJob.getTitle()!=null)
		{pairs.add(new BasicNameValuePair("job[title]",cfJob.getTitle()));}
//		if (cfJob.getUnits_per_assignment()!=(Long)null)
//		{pairs.add(new BasicNameValuePair("units_per_assignment",String.valueOf(cfJob.getUnits_per_assignment())));} 
		if (cfJob.getWebhook_uri()!=null)
		{pairs.add(new BasicNameValuePair("job[webhook_uri]",cfJob.getWebhook_uri()));}
		
		String url = jc.buildUriP( "jobs", "json", param);
		//System.out.println(url);
		InputStream is=jc.HTTPmethodPut(pairs,url);
		String inStr = convertStreamToString(is);
		//System.out.println(inStr);
		CrowdFlowerJob cfJobServer = response.parseJSONObjJob(inStr);
		if ((response.parseJSONObjMessage(inStr))!=1){ // if method executed with error
			System.out.println(inStr);}
	return cfJobServer;
	
	
	}


	/**The method  shows the generated keys that will end up being submitted with a form.
	 * @param id
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public String legendJob(int id) throws ParseException, ClientProtocolException, IOException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs",param, "legend","json" );
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
		return inStr;
	}


	/** The method downloads  CSV of all judgments collected for a given job is available.
	 * @param id of a Job
	 * @param full is a parameter required by CrowdFlower. If set to true , 
	 * each row in the generated csv will represent one judgement.
	 *  If set to false, each row will represent a single unit with the best answer chosen
	 *   by our aggregation logic.
	 * @param path is the file path where file will be downloaded on the disk 
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 */
	public void downloadJobReport(int id, Boolean full, String path) throws ClientProtocolException, IOException, ParseException{

		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("full", full.toString()));
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs",param, "csv" );
		InputStream is=jc.HTTPmethodGet(url);
		FileOutputStream fos = new FileOutputStream(new File(path));
		int inByte;
		while((inByte = is.read()) != -1) fos.write(inByte);
		is.close();
		fos.close();
	}

	/** The method downloads all judgemnts in JSON format
	 * @param id id of a Job
	 * @param full parameter required by CrowdFlower.
	 *  if set to true , each row in the generated file will represent one judgement.
	 *   If set to false, each row will represent a single unit with the best 
	 *   answer chosen by our aggregation logic.
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws ParseException
	 */
	public String downloadJobReport(int id, Boolean full) throws ClientProtocolException, IOException, ParseException{
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("full", full.toString()));
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURI(pairs, "jobs",param,"judgments", "json" );
		InputStream is=jc.HTTPmethodGet(url);
		return  convertStreamToString(is);
		
	}
	
	public String viewEnabledChannels(int id) throws ClientProtocolException, IOException, ParseException{
		CFResponse response = new CFResponse(); 
		String param = Integer.toString(id);
		ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("key", authKey));
		String url = jc.buildURIGetChannel(pairs, "jobs",param,"channels");
		System.out.println("View channels url: "+url);
		InputStream is = jc.HTTPmethodGet(url);
		String inStr = convertStreamToString(is);
		if(response.isMessage(inStr))  //If not a message
		{
			System.out.println("Message: "+inStr);
		}
		return response.parseChannelView(inStr);
	}

	/** The method converts InputStream to String
	 * @param is 
	 * @return 
	 */
	public  String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next() : "";
	}
	
	/** Read from file and make string
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader( new FileReader (file));
		String         line = null;
		StringBuilder  stringBuilder = new StringBuilder();
		String         ls = System.getProperty("line.separator");
		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line );
			stringBuilder.append( ls );
		}
		return stringBuilder.toString();
	}
}
