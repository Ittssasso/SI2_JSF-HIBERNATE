package eredua.bean;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

import com.sun.media.sound.SoftTuning;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class QuestionBean {
	
	BLFacade facadeBL= FacadeBean.getBusinessLogic();
	private Date data;
	private static ArrayList<Event> events = new ArrayList<Event>();
	private static List<Question> questions = new ArrayList<Question>();
	private static List<Question> allQuestions = new ArrayList<Question>();
	private Event event = new Event();
	private Question question = new Question();
	private User user = new User();
	private String questionS;
	private String minBet;
	private String name;
	private String surname;
	private String DNI;
	private String email;
	private String password;
	private String cPassword;
	private String money;
	

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getcPassword() {
		return cPassword;
	}

	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}

	public String getQuestion() {
		return questionS;
	}

	public void setQuestion(String question) {
		this.questionS = question;
	}

	public String getMinBet() {
		return minBet;
	}

	public void setMinBet(String minBet) {
		this.minBet = minBet;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event ev) {
		this.event = ev;
	}

	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data=data;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<Event> getEvents(){
		return events;
	}
	
	public static void setEvents(ArrayList<Event> events) {
		QuestionBean.events = events;
	}
	
	public List<Question> getQuestions() {
		if(this.event != null) {
		questions= this.event.getQuestions();
		}
		return questions;
	}
	
	public void setQuestions(ArrayList<Question> questions) {
		QuestionBean.questions = questions;
	}
	
	
	
	public String create() {
		return "create";
	}
	
	public String query() {
		this.setData(null);
		QuestionBean.setEvents(null);
		this.setQuestions(null);
		return "query";
	}
	
	public String close() {
		this.setData(null);
		QuestionBean.setEvents(null);
		this.setQuestions(null);
		return "close";
	}
	
	public String back() {
		if(email==null) {
			return "close";
		}else
			return "back";
	}
	
	public String signUp() {
		return "signUp";
	}
	
	public String login() {
		return "logIn";
	}
	
	public String menuErregistratutaEz() {
		return "mEe";
	}
	
	public String saioaItxi() {
		email=null;
		return "saioaItxi";
	}
	
	public String show() {
		return "show";
	}
	
	public String insert() {
		return "insert";
	}
	
	
	public void onDateSelect(SelectEvent event) {
		events = (ArrayList<Event>) facadeBL.getEvents((Date) event.getObject());
		questions =null;
	}
	
	public void onEventSelect(SelectEvent event) {
		this.event=(Event) event.getObject();
		questions= this.event.getQuestions();
	}
	
	public List<Question> showAllQuestions(){
		allQuestions = facadeBL.getAllQuestions();
		return allQuestions;
	}
	
	public void eventSelect(AjaxBehaviorEvent event) {
		this.setEvent(this.event);
	}
	
	public Question createQuestion() {
		try {
			float mB=Float.parseFloat(minBet);
			if(mB <0 ) 
				throw new Exception();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera ondo gehitu da."));
			question = facadeBL.createQuestion(event, questionS, mB);
			this.setData(null);
		}catch (NumberFormatException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Zenbaki bat sartu behar duzu."));
		} catch (EventFinished e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Gertaera amaitu da."));
		} catch (QuestionAlreadyExist e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera dagoeneko existitzen da."));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Sartutako zenbakia negatiboa da."));
		};
			return question;
	}
	
	public String createUser() {
		String em = "";
		if(name != "" && surname !="" && DNI !="" && email !="" && password!="" ) {
			if(password.length()>=8) {
				if(password.equals(cPassword)) {
					user = facadeBL.createUser(name, surname, DNI, email, password);	
					if(user != null) {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Ondo erregistratu zara!"));
						em="yes";
					}else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Jada erregistratuta zaude"));
						em="no";
					}
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Pasahitzak ez dira berdinak!"));
				}
			}else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Pasahitza motxeegia da!"));
				}
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Hutsuneak bete!"));
		}
		return em;
	}
	
	public String isLogin() {
		String em = "";
		if(email!="" && password!="") {
				user = facadeBL.isLogin(email, password);
				if(user != null) {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Ondo kautotu zara: " +user.getEmail()+ "!"));
					em = "yes";
				}else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage("Emaila edota pasahitza oker sartu duzu, berriro saiatu!"));
					em = "no";
				}		
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Hutsuneak bete!"));
		}	
		return em;
	}
	
	public User insertMoney() {
		user = facadeBL.insertMoney(money, user);
		if (user != null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Dirua ongi sartu da!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Dirua ez da ongi sartu!"));
		}
		return user;
	}
	
	
	
}
