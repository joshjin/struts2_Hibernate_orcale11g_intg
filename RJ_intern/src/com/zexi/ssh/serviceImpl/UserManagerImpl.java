package com.zexi.ssh.serviceImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zexi.ssh.forms.UserForm;
import com.zexi.ssh.service.UserManager;
import com.zexi.ssh.beans.User;
import com.zexi.ssh.dao.BaseDao;
import com.zexi.ssh.daoImpl.HibernateSessionFactory;
import com.zexi.ssh.daoImpl.UserDao;  

public class UserManagerImpl implements UserManager {
	
    private BaseDao dao;  
    
    private Session session;  
	
    public UserManagerImpl() {  
        dao = new UserDao();  
    }  
    
    @Override  
    public void regUser(UserForm userForm) {
        session = HibernateSessionFactory.currentSession();  
        dao.setSession(session);  
        Transaction ts = session.beginTransaction();  
        User user = new User();  
        user.setUsername(userForm.getUsername());  
        user.setPassword(userForm.getPassword());  
        user.setGender(userForm.getGender());  
        dao.saveObject(user);  
        ts.commit();  
        HibernateSessionFactory.closeSession();  

    }
}  