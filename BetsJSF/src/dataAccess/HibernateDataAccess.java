package dataAccess;

import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.HibernateUtil;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess implements DataAccessInterface{

	Session db = HibernateUtil.getSessionFactory().getCurrentSession();
	public HibernateDataAccess() {
	}

public void initializeDB(){
		
		
		db.beginTransaction();
		db.getTransaction().begin();
	

//		try {
//
//			
//		   Calendar today = Calendar.getInstance();
//		   
//		   int month=today.get(Calendar.MONTH);
//		   month+=1;
//		   int year=today.get(Calendar.YEAR);
//		   if (month==12) { month=0; year+=1;}  
//	    
//			Event ev1=new Event( "Atlético-Athletic", UtilDate.newDate(year,month,17));
//			Event ev2=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,17));
//			Event ev3=new Event( "Getafe-Celta", UtilDate.newDate(year,month,17));
//			Event ev4=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,17));
//			Event ev5=new Event( "Español-Villareal", UtilDate.newDate(year,month,17));
//			Event ev6=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
//			Event ev7=new Event( "Malaga-Valencia", UtilDate.newDate(year,month,17));
//			Event ev8=new Event( "Girona-Leganés", UtilDate.newDate(year,month,17));
//			Event ev9=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
//			Event ev10=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,17));
//
//			Event ev11=new Event( "Atletico-Athletic", UtilDate.newDate(year,month,1));
//			Event ev12=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
//			Event ev13=new Event( "Getafe-Celta", UtilDate.newDate(year,month,1));
//			Event ev14=new Event( "Alavés-Deportivo", UtilDate.newDate(year,month,1));
//			Event ev15=new Event( "Español-Villareal", UtilDate.newDate(year,month,1));
//			Event ev16=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
//			
//
//			Event ev17=new Event( "Málaga-Valencia", UtilDate.newDate(year,month,28));
//			Event ev18=new Event( "Girona-Leganés", UtilDate.newDate(year,month,28));
//			Event ev19=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
//			Event ev20=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,28));
//			
//			Question q1;
//			Question q2;
//			Question q3;
//			Question q4;
//			Question q5;
//			Question q6;
//					
//			if (Locale.getDefault().equals(new Locale("es"))) {
//				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
//				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
//				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
//				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
//				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
//				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
//			}
//			else if (Locale.getDefault().equals(new Locale("en"))) {
//				q1=ev1.addQuestion("Who will win the match?",1);
//				q2=ev1.addQuestion("Who will score first?",2);
//				q3=ev11.addQuestion("Who will win the match?",1);
//				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
//				q5=ev17.addQuestion("Who will win the match?",1);
//				q6=ev17.addQuestion("Will there be goals in the first half?",2);
//			}			
//			else {
//				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
//				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
//				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
//				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
//				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
//				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
//				
//			}
//			
//			User u1= new User("Ane", "Martinez", "72545881V", "ane@ehu.eus", "12345678");
//			User u2= new User("Alex", "Perez", "98765652J", "alex@ehu.eus", "12345678");
//			User u3= new User("Miren", "Sanchez", "67854321G", "miren@ehu.eus", "12345678");
//			
//			db.persist(u1);
//			db.persist(u2);
//			db.persist(u3);
//			
//			db.persist(q1);
//			db.persist(q2);
//			db.persist(q3);
//			db.persist(q4);
//			db.persist(q5);
//			db.persist(q6);
//	
//	        
//			db.persist(ev1);
//			db.persist(ev2);
//			db.persist(ev3);
//			db.persist(ev4);
//			db.persist(ev5);
//			db.persist(ev6);
//			db.persist(ev7);
//			db.persist(ev8);
//			db.persist(ev9);
//			db.persist(ev10);
//			db.persist(ev11);
//			db.persist(ev12);
//			db.persist(ev13);
//			db.persist(ev14);
//			db.persist(ev15);
//			db.persist(ev16);
//			db.persist(ev17);
//			db.persist(ev18);
//			db.persist(ev19);
//			db.persist(ev20); 		
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
	}
	
	public List<Event> getEvents(Date date) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Query q = session.createQuery("from Event where eventDate = :date");
		 q.setParameter("date", date);
		 List result=q.list();
		 session.getTransaction().commit();
		 return result;
	}

	public List<Question> getAllQuestions(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		 Query q = session.createQuery("from Question");
		 List result=q.list();
		 session.getTransaction().commit();
		 return result;
	}

	@Override
	public void open() {
		System.out.println("Datu basea ireki da");
	}

	@Override
	public void close() {
		System.out.println("Datu basea itxi da");
	}

	@Override
	public void emptyDatabase() {
		System.out.println("Datua basea beteta dago");	
	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		Event ev= (Event) session.get(Event.class,event.getEventNumber());
		
		if(ev.DoesQuestionExists(question))  throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		Question q = ev.addQuestion(question, betMinimum);
		session.persist(ev);
		session.persist(q);
		session.getTransaction().commit();
		return q;
	}

	@Override
	public User createUser(String name, String surname, String dni, String email, String password){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		User u = new User(name, surname, dni, email, password);
		Query q = session.createQuery("from User where DNI = :dni");
		q.setParameter("dni", dni );
		List result = q.list();
		if(result.isEmpty()) {
			session.persist(u);
		}else {
			u = null;
		}
		session.getTransaction().commit();
		return u;
	}

	@Override
	public User isLogin(String em, String pass) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		User u = null;
		Query q = session.createQuery("from User where email = :em and password = :pass");
		q.setParameter("em", em);
		q.setParameter("pass", pass);
		List result=q.list();
		if (!result.isEmpty()){
			u = (User) result.get(0);
		}
		session.getTransaction().commit();
		return u;
	}
	
	@Override
	public List<Date> getEventsMonth(Date date) {
		return null;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		Event ev= (Event) session.get(Event.class,event.getEventNumber());	
		return ev.DoesQuestionExists(question);
	}
	
	@Override
	public User insertMoney(String money, User user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();	
		User u= (User) session.get(User.class, user.getDNI());
		float m = u.getMoney();
		float moneyToFloat = Float.parseFloat(money);
		float totala = m + moneyToFloat;
		u.setMoney(totala);
		session.persist(u);
		session.getTransaction().commit();
		return u;
	}
	
	
}

