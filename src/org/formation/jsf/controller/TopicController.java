package org.formation.jsf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.formation.jsf.model.Topic;

@ManagedBean
public class TopicController {

	private Topic topic = new Topic();
	private UIData dataTable;
	private String prefix;


	private static Map<Long, Topic> topics;
	static{
		topics = new HashMap<>();
		topics.put(new Long(0), new Topic(0, "jsf", "is JSF the coolest presentation framework ?"));
	}
	
	public List<Topic> getTopics(){
		if(prefix != null && prefix.length()>0) {
			return filterTopics();
		}
		return new ArrayList<Topic>(topics.values());
		
	}
	private List<Topic> filterTopics() {
		System.out.println("F I L T E R !");
		List<Topic> ret = new ArrayList<Topic>();
			for( Topic t : topics.values()) {
				if(t.getTitle().toLowerCase().startsWith(prefix.toLowerCase()))
				ret.add(t);
			}
			return ret;
	}
	public String selectTopic() {
		Topic t = (Topic)dataTable.getRowData();
		//t = DBManager.getInstance().loadFullTopic(t.getId());
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		request.setAttribute("topic", t);
		return "topic";
	}
    public String createTopic() {
    	//DBManager.getInstance().createTopic(this.topic);
    	this.topic.setId(new Long(topics.size()).longValue());
    	topics.put(this.topic.getId(), this.topic);
    	
    	return "topics";
    }
    
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public UIData getDataTable() {
		return dataTable;
	}
	public void setDataTable(UIData dataTable) {
		this.dataTable = dataTable;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}

