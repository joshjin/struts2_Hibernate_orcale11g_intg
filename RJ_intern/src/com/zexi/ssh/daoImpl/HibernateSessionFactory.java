package com.zexi.ssh.daoImpl;

import org.hibernate.HibernateException;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    //private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();        
	private static final Configuration cfg = new Configuration().configure("Hibernate.cfg.xml");  
	private static SessionFactory sessionFactory;
    //private static String configFile = CONFIG_FILE_LOCATION;
    
    public static Session currentSession() throws HibernateException {        
    	System.out.println("set up session");
        Session session = threadLocal.get();            
        if (session == null || session.isOpen() == false) {                
            if (sessionFactory == null) {                  
                try {                     
                    sessionFactory = cfg.buildSessionFactory();                  
                } catch (Exception e) {                      
                    e.printStackTrace();                  
                }              
             }                
             session = sessionFactory.openSession();              
             threadLocal.set(session);            
         }            
         return session;      
        }  
    public static void closeSession() throws HibernateException {  
        Session session = threadLocal.get();  
        threadLocal.set(null);  
        if (session != null) {  
            session.close();  
        }  
    }  
}
