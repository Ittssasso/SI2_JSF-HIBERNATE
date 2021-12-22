package dataAccess;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import domain.Event;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

public interface DataAccessInterface {

		
	/**
	 * This method opens the database
	 */
	void open();
	
	/**
	 * This method closes the database
	 */
	void close();

	
	/**
	 * This method removes all the elements of the database
	 */
	void emptyDatabase();
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	void initializeDB();

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist;
	
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
	User createUser(String name, String surname, String DNI, String email, String password);
	
	/**
	 * This method is to know if user is logged or no.
	 * 
	 * @param email user email.
	 * @param password user password.
	 * @return user.
	 */
	User isLogin(String email, String password);

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	List<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	List<Date> getEventsMonth(Date date);

	/**
	 * This method return the list of all questions.
	 * 
	 * @return list of questions.
	 */
	List<Question> getAllQuestions();
	
	/**
	 * This method checks if the question has been previously added to the event 
	 * 
	 * @param event the event
	 * @param question the question to check  
	 * @return true if the event contains this the questions, false in other case
	 */
	boolean existQuestion(Event event, String question);
	
	/**
	 * This method insert money. 
	 * 
	 * @param money the value to insert.
	 * @param user user to whom money is going to be put.
	 * @return user.
	 */
	User insertMoney(String money, User user);

	

}