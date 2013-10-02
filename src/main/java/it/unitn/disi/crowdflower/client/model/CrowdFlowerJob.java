package it.unitn.disi.crowdflower.client.model;

import it.unitn.disi.crowdflower.client.JobOptions;

import java.util.ArrayList;

/**
 * Class aims to describe attributes and services of CrowdFlower jobs.
 * 
 * @author Ivan Tankoyeu 14/05/2013
 */
public class CrowdFlowerJob {

	// Below is the list of read/write attributes:
	private Boolean auto_order;
	private String auto_order_threshold;
	private String auto_order_timeout;
	private String cml;
	private String cml_fields;
	private ArrayList<String> confidence_fields;
	private String css;
	private String custom_key;
	private ArrayList<String> excluded_countries;
	private long gold_per_assignment;
	private ArrayList<String> included_countries;
	private String instructions;
	private String js;
	private long judgments_per_unit;
	private String language;
	private long max_judgments_per_unit;
	private long max_judgments_per_worker;
	private String min_unit_confidence;
	private JobOptions options;
	private String pages_per_assignment;
	private String problem;
	private String send_judgments_webhook;
	private String state;
	private String title;
	private long units_per_assignment;
	private String webhook_uri;

	// Read only attributes:
	private Boolean completed;
	private String completed_at;
	private String created_at;

	// TODO long gold; analysis of the variable is required

	private long golds_count;
	private int id;
	private long judgments_count;
	private long units_count;
	private String updated_at;

	public CrowdFlowerJob(){}
	
//	public CrowdFlowerJob(Boolean auto_order, String auto_order_threshold,
//			String auto_order_timeout, String cml, String cml_fields,
//			ArrayList<String> confidence_fields, String css, String custom_key,
//			ArrayList<String> excluded_countries, Long gold_per_assignment,
//			ArrayList<String> included_countries, String instructions,
//			String js, long judgments_per_unit, String language,
//			long max_judgments_per_unit, long max_judgments_per_worker,
//			String min_unit_confidence, JobOptions options,
//			String pages_per_assignment, String problem,
//			String send_judgments_webhook, String state, String title,
//			long units_per_assignment, String webhook_uri, Boolean completed,
//			String completed_at, String created_at, long golds_count, int id,
//			long judgments_count, long units_count, String updated_at) {
//		super();
//		this.auto_order = auto_order;
//		this.auto_order_threshold = auto_order_threshold;
//		this.auto_order_timeout = auto_order_timeout;
//		this.cml = cml;
//		this.cml_fields = cml_fields;
//		this.confidence_fields = confidence_fields;
//		this.css = css;
//		this.custom_key = custom_key;
//		this.excluded_countries = excluded_countries;
//		this.gold_per_assignment = gold_per_assignment;
//		this.included_countries = included_countries;
//		this.instructions = instructions;
//		this.js = js;
//		this.judgments_per_unit = judgments_per_unit;
//		this.language = language;
//		this.max_judgments_per_unit = max_judgments_per_unit;
//		this.max_judgments_per_worker = max_judgments_per_worker;
//		this.min_unit_confidence = min_unit_confidence;
//		this.options = options;
//		this.pages_per_assignment = pages_per_assignment;
//		this.problem = problem;
//		this.send_judgments_webhook = send_judgments_webhook;
//		this.state = state;
//		this.title = title;
//		this.units_per_assignment = units_per_assignment;
//		this.webhook_uri = webhook_uri;
//		this.completed = completed;
//		this.completed_at = completed_at;
//		this.created_at = created_at;
//		// this.gold = gold;
//		this.golds_count = golds_count;
//		this.id = id;
//		this.judgments_count = judgments_count;
//		this.units_count = units_count;
//		this.updated_at = updated_at;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CrowdFlowerJob [auto_order=" + auto_order
				+ ", auto_order_threshold=" + auto_order_threshold
				+ ", auto_order_timeout=" + auto_order_timeout + ", cml=" + cml
				+ ", cml_fields=" + cml_fields + ", confidence_fields="
				+ confidence_fields + ", css=" + css + ", custom_key="
				+ custom_key + ", excluded_countries=" + excluded_countries
				+ ", gold_per_assignment=" + gold_per_assignment
				+ ", included_countries=" + included_countries
				+ ", instructions=" + instructions + ", js=" + js
				+ ", judgments_per_unit=" + judgments_per_unit + ", language="
				+ language + ", max_judgments_per_unit="
				+ max_judgments_per_unit + ", max_judgments_per_worker="
				+ max_judgments_per_worker + ", min_unit_confidence="
				+ min_unit_confidence + ", options=" + options
				+ ", pages_per_assignment=" + pages_per_assignment
				+ ", problem=" + problem + ", send_judgments_webhook="
				+ send_judgments_webhook + ", state=" + state + ", title="
				+ title + ", units_per_assignment=" + units_per_assignment
				+ ", webhook_uri=" + webhook_uri + ", completed=" + completed
				+ ", completed_at=" + completed_at + ", created_at="
				+ created_at + ", gold=0" + ", golds_count=" + golds_count
				+ ", id=" + id + ", judgments_count=" + judgments_count
				+ ", units_count=" + units_count + ", updated_at=" + updated_at
				+ "]";
	}

	/**
	 * @return the auto_order
	 */
	public Boolean getAuto_order() {
		return auto_order;
	}

	/**
	 * @param auto_order
	 *            the auto_order to set
	 */
	public void setAuto_order(Boolean auto_order) {
		this.auto_order = auto_order;
	}

	/**
	 * @return the auto_order_threshold
	 */
	public String getAuto_order_threshold() {
		return auto_order_threshold;
	}

	/**
	 * @param auto_order_threshold
	 *            the auto_order_threshold to set
	 */
	public void setAuto_order_threshold(String auto_order_threshold) {
		this.auto_order_threshold = auto_order_threshold;
	}

	/**
	 * @return the auto_order_timeout
	 */
	public String getAuto_order_timeout() {
		return auto_order_timeout;
	}

	/**
	 * @param auto_order_timeout
	 *            the auto_order_timeout to set
	 */
	public void setAuto_order_timeout(String auto_order_timeout) {
		this.auto_order_timeout = auto_order_timeout;
	}

	/**
	 * @return the cml
	 */
	public String getCml() {
		return cml;
	}

	/**
	 * @param cml
	 *            the cml to set
	 */
	public void setCml(String cml) {
		this.cml = cml;
	}

	/**
	 * @return the cml_fields
	 */
	public String getCml_fields() {
		return cml_fields;
	}

	/**
	 * @param cml_fields
	 *            the cml_fields to set
	 */
	public void setCml_fields(String cml_fields) {
		this.cml_fields = cml_fields;
	}

	/**
	 * @return the confidence_fields
	 */
	public ArrayList<String> getConfidence_fields() {
		return confidence_fields;
	}

	/**
	 * @param confidence_fields
	 *            the confidence_fields to set
	 */
	public void setConfidence_fields(ArrayList<String> confidence_fields) {
		this.confidence_fields = confidence_fields;
	}

	/**
	 * @return the css
	 */
	public String getCss() {
		return css;
	}

	/**
	 * @param css
	 *            the css to set
	 */
	public void setCss(String css) {
		this.css = css;
	}

	/**
	 * @return the custom_key
	 */
	public String getCustom_key() {
		return custom_key;
	}

	/**
	 * @param custom_key
	 *            the custom_key to set
	 */
	public void setCustom_key(String custom_key) {
		this.custom_key = custom_key;
	}

	/**
	 * @return the excluded_countries
	 */
	public ArrayList<String> getExcluded_countries() {
		return excluded_countries;
	}

	/**
	 * @param excluded_countries
	 *            the excluded_countries to set
	 */
	public void setExcluded_countries(ArrayList<String> excluded_countries) {
		this.excluded_countries = excluded_countries;
	}

	/**
	 * @return the gold_per_assignment
	 */
	public long getGold_per_assignment() {
		return gold_per_assignment;
	}

	/**
	 * @param gold_per_assignment
	 *            the gold_per_assignment to set
	 */
	public void setGold_per_assignment(long gold_per_assignment) {
		this.gold_per_assignment = gold_per_assignment;
	}

	/**
	 * @return the included_countries
	 */
	public ArrayList<String> getIncluded_countries() {
		return included_countries;
	}

	/**
	 * @param included_countries
	 *            the included_countries to set
	 */
	public void setIncluded_countries(ArrayList<String> included_countries) {
		this.included_countries = included_countries;
	}

	/**
	 * @return the instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * @param instructions
	 *            the instructions to set
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	/**
	 * @return the js
	 */
	public String getJs() {
		return js;
	}

	/**
	 * @param js
	 *            the js to set
	 */
	public void setJs(String js) {
		this.js = js;
	}

	/**
	 * @return the judgments_per_unit
	 */
	public long getJudgments_per_unit() {
		return judgments_per_unit;
	}

	/**
	 * @param judgments_per_unit
	 *            the judgments_per_unit to set
	 */
	public void setJudgments_per_unit(long judgments_per_unit) {
		this.judgments_per_unit = judgments_per_unit;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the max_judgments_per_unit
	 */
	public long getMax_judgments_per_unit() {
		return max_judgments_per_unit;
	}

	/**
	 * @param max_judgments_per_unit
	 *            the max_judgments_per_unit to set
	 */
	public void setMax_judgments_per_unit(long max_judgments_per_unit) {
		this.max_judgments_per_unit = max_judgments_per_unit;
	}

	/**
	 * @return the max_judgments_per_worker
	 */
	public long getMax_judgments_per_worker() {
		return max_judgments_per_worker;
	}

	/**
	 * @param max_judgments_per_worker
	 *            the max_judgments_per_worker to set
	 */
	public void setMax_judgments_per_worker(long max_judgments_per_worker) {
		this.max_judgments_per_worker = max_judgments_per_worker;
	}

	/**
	 * @return the min_unit_confidence
	 */
	public String getMin_unit_confidence() {
		return min_unit_confidence;
	}

	/**
	 * @param min_unit_confidence
	 *            the min_unit_confidence to set
	 */
	public void setMin_unit_confidence(String min_unit_confidence) {
		this.min_unit_confidence = min_unit_confidence;
	}

	/**
	 * @return the options
	 */
	public JobOptions getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public void setOptions(JobOptions options) {
		this.options = options;
	}

	/**
	 * @return the pages_per_assignment
	 */
	public String getPages_per_assignment() {
		return pages_per_assignment;
	}

	/**
	 * @param pages_per_assignment
	 *            the pages_per_assignment to set
	 */
	public void setPages_per_assignment(String pages_per_assignment) {
		this.pages_per_assignment = pages_per_assignment;
	}

	/**
	 * @return the problem
	 */
	public String getProblem() {
		return problem;
	}

	/**
	 * @param problem
	 *            the problem to set
	 */
	public void setProblem(String problem) {
		this.problem = problem;
	}

	/**
	 * @return the send_judgments_webhook
	 */
	public String getSend_judgments_webhook() {
		return send_judgments_webhook;
	}

	/**
	 * @param send_judgments_webhook
	 *            the send_judgments_webhook to set
	 */
	public void setSend_judgments_webhook(String send_judgments_webhook) {
		this.send_judgments_webhook = send_judgments_webhook;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the units_per_assignment
	 */
	public long getUnits_per_assignment() {
		return units_per_assignment;
	}

	/**
	 * @param units_per_assignment
	 *            the units_per_assignment to set
	 */
	public void setUnits_per_assignment(long units_per_assignment) {
		this.units_per_assignment = units_per_assignment;
	}

	/**
	 * @return the webhook_uri
	 */
	public String getWebhook_uri() {
		return webhook_uri;
	}

	/**
	 * @param webhook_uri
	 *            the webhook_uri to set
	 */
	public void setWebhook_uri(String webhook_uri) {
		this.webhook_uri = webhook_uri;
	}

	/**
	 * @return the completed
	 */
	public Boolean getCompleted() {
		return completed;
	}

	/**
	 * @return the completed_at
	 */
	public String getCompleted_at() {
		return completed_at;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	// /**
	// * @return the gold
	// */
	// public String getGold() {
	// return gold;
	// }

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public long getGolds_count() {
		return golds_count;
	}

	public void setGolds_count(long golds_count) {
		this.golds_count = golds_count;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public void setCompleted_at(String completed_at) {
		this.completed_at = completed_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setJudgments_count(long judgments_count) {
		this.judgments_count = judgments_count;
	}

	public void setUnits_count(long units_count) {
		this.units_count = units_count;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * @return the judgments_count
	 */
	public long getJudgments_count() {
		return judgments_count;
	}

	/**
	 * @return the units_count
	 */
	public long getUnits_count() {
		return units_count;
	}

	/**
	 * @return the updated_at
	 */
	public String getUpdated_at() {
		return updated_at;
	}

}
