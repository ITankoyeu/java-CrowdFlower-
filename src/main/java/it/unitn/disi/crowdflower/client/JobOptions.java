package it.unitn.disi.crowdflower.client;

public class JobOptions {

	Boolean track_clones;
	Boolean include_unfinished;
	Boolean front_load;
	String mail_to;
	long after_gold;
	long calibrated_unit_time;
	String keywords;
	
	public JobOptions(Boolean track_clones, Boolean include_unfinished,
			Boolean front_load, String mail_to, long after_gold,
			long calibrated_unit_time, String keywords) {
		super();
		this.track_clones = track_clones;
		this.include_unfinished = include_unfinished;
		this.front_load = front_load;
		this.mail_to = mail_to;
		this.after_gold = after_gold;
		this.calibrated_unit_time = calibrated_unit_time;
		this.keywords = keywords;
	}
	
}
