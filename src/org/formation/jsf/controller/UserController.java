package org.formation.jsf.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;

import org.formation.jsf.model.Genre;
import org.formation.jsf.model.Membre;

@ManagedBean
public class UserController {

	private String login,password;
	private Membre membre = new Membre();
	

	private static Map<String, Membre> users;

	static {
	users = new HashMap<String, Membre>();
	users.put("admin", new Membre("admin", "admin", 12, Genre.HOMME));

	}
	
	
	
	public UserController() {
		super();
	}

	public String checkPassword() {
		Membre m = users.get(login); //DBManager.getInstance().getMembre(login);
		if (m != null && m.getPassword().equals(password) ) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("loggedUser", m);
			return "topics";
		}
		return "";
		
	}
	
	public String doInscription() {
		if (membre.getLogin().equals(""))
			return "";
		users.put(membre.getLogin(), membre);
		//DBManager.getInstance().inscrireMembre(membre);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("loggedUser", membre);
		return "topics";
	}

	public void validateLogin(FacesContext facesContext, UIComponent component, Object value) {
		String login = (String)value;
		Membre m = users.get(login); //DBManager.getInstance().getMembre(login);
		if ( m != null ) {
			ResourceBundle bundle =  facesContext.getApplication().getResourceBundle(facesContext, "messages");
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error.duplicateLogin"),bundle.getString("error.duplicateLogin")));
		}
	}
	
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("loggedUser");
		return "accueil";
	}
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}
	
	
}
