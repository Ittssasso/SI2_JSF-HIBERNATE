package businessLogic;

import java.util.Vector;

import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


/**
 * Interface that specifies the business logic.
 */
public interface BLFacade  {
	  

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
	Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
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
	public User createUser(String name, String surname, String DNI, String email, String password);
	
	/**
	 * This method is to know if user is logged or no.
	 * 
	 * @param email user email.
	 * @param password user password.
	 * @return user.
	 */
	public User isLogin(String email, String password);
	
	/**
	 * This method insert money. 
	 * 
	 * @param money the value to insert.
	 * @param user user to whom money is going to be put.
	 * @return user.
	 */
	public User insertMoney(String money, User user);
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date);
	
	/**
	 * This method return the list of all questions.
	 * 
	 * @return list of questions.
	 */
	public List<Question> getAllQuestions();
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeBD();

	
}
