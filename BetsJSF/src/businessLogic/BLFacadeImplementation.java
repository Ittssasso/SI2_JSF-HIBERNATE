package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Session;

import configuration.ConfigXML;
import dao.ObjectDbDAOManager;
import dataAccess.DataAccessInterface;
import dataAccess.HibernateDataAccess;
import domain.Question;
import domain.User;
import domain.Event;
import domain.HibernateUtil;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;
//
//	public BLFacadeImplementation()  {		
//		System.out.println("Creating BLFacadeImplementation instance");
//		ConfigXML c=ConfigXML.getInstance();
//		
//		/*if (c.getDataBaseOpenMode().equals("initialize")) {
//			
//		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
//			dbManager.initializeDB();
//			dbManager.close();
//			}
//		*/
//	}
//	
	
	public static void main(String args[]) {
		BLFacadeImplementation bl = new BLFacadeImplementation(new HibernateDataAccess());
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		//ConfigXML c=ConfigXML.getInstance();
		
		//if (c.getDataBaseOpenMode().equals("initialize")) {
			//da.emptyDatabase();
			//da.open();
		//DataAccessInterface da1= new HibernateDataAccess();
			//da.initializeDB();
			//da.close();
		    da.open();
			da.initializeDB();
			da.close();
			dbManager= da;
				
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */

   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	   //Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	   //session.beginTransaction();
	   dbManager.open();
	   Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */	
	public List<Event> getEvents(Date date)  {
		dbManager.open();
		List<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method return the list of all questions.
	 * 
	 * @return list of questions.
	 */
	public List<Question> getAllQuestions(){
		dbManager.open();
		List<Question> questions=dbManager.getAllQuestions();
		dbManager.close();
		return questions;
		
	}
    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
    public List<Date> getEventsMonth(Date date) {
		dbManager.open();
		List<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);
		//dB4oManager.close();
		dbManager.close();
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
	 
	/**
	* This method create a user.
	* 
	* @param name user name.
	* @param surname user surname.
	* @param DNI user DNI
	* @param email user email.
	* @param password user password.
	* @return user.
	*/
	@Override
	public User createUser(String name, String surname, String DNI, String email, String password) {
		dbManager.open();
		User u=null;
		u= dbManager.createUser(name, surname, DNI, email, password);
		dbManager.close();
		return u;
	}
	
	/**
	 * This method is to know if user is logged or no.
	 * 
	 * @param email user email.
	 * @param password user password.
	 * @return user.
	 */
	@Override
	public User isLogin(String email, String password) {
		dbManager.open();
		User u=null;
		u= dbManager.isLogin(email, password);
		dbManager.close();
		return u;
	}
	
	/**
	 * This method insert money. 
	 * 
	 * @param money the value to insert.
	 * @param user user to whom money is going to be put.
	 * @return user.
	 */
	@Override
	public User insertMoney(String money, User user) {
		dbManager.open();
		User u=null;
		u= dbManager.insertMoney(money, user);
		dbManager.close();
		return u;
	}
	

}

