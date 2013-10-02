package it.unitn.disi.crowdflower.client;


public abstract class WebHook implements IWebHook {


	// (1) Start thread to listen for updates every 5 secons
	// (2) Call jobReady();
	private class WebHookThread extends Thread{
		@Override
		public void run()
		{
			try{
			
			authKey = jc.getAuthKey();
			instance = new CFJavaAPI(authKey);
			Long restJudgements =  instance.statusJob(id);{

				while (restJudgements!=0){
					restJudgements =  instance.statusJob(id);
					Thread.sleep(time);
					System.out.println("The Job is not ready");
				}
				jobReady();
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	private String authKey;
	private CFJavaAPI instance; 
	private JobConnection jc;
	private long time;
	private int id;


	public WebHook(String authKey, CFJavaAPI instance, JobConnection jc, long time, int id) {
		super();

		this.authKey = authKey;
		this.instance = instance;
		this.jc=jc;
		this.id = id;
		this.time = time;
	}
}
