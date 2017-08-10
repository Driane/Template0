package org.formation.jsf.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


@ManagedBean(name = "languageController")
@SessionScoped
public class LanguageController /*implements ValueChangeListener*/{
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	private String language = locale.getLanguage();

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public Locale getLocale() {
		return locale;
	}

	public String changeLanguage(String language) {
		updateLanguage(language);
		return "";
	}
	
	private void updateLanguage(String lang) {
		language = lang;
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		System.out.println("updte " + lang);
	}

//	@Override
//	public void processValueChange(ValueChangeEvent arg0) throws AbortProcessingException {
//		updateLanguage((String) arg0.getNewValue()); 
//	}

	public void processMyChange(ValueChangeEvent evt) {
		updateLanguage(evt.getNewValue().toString()); 
	}
	
	
	public List<SelectItem> getLocales() {
		Iterator<Locale> locales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
		
		List<SelectItem> ret = new ArrayList<SelectItem>();
		while ( locales.hasNext() ) {
			Locale l = locales.next();
			ret.add(new SelectItem(l.getLanguage(),l.getDisplayName(locale)));
		}
		return ret;
	}

}
