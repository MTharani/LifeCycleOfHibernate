package com.edu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Mainapp {

	public static void main(String[] args) 
	{
		MyLaptop lop=new MyLaptop();     //object creation
		lop.setLid(1);
		lop.setLname("lenovo");
		lop.setLprice(40000); //trasient state
		//hibernate
		Configuration conn=new Configuration().configure().addAnnotatedClass(MyLaptop.class);
		ServiceRegistry reg=new ServiceRegistryBuilder().applySettings(conn.getProperties()).buildServiceRegistry();
		SessionFactory sf=conn.buildSessionFactory(reg);
		Session sess=sf.openSession();
		Transaction tx=sess.beginTransaction();
		
		/**sess.save(lop);     //persistent state
		lop.setLprice(45000);
		tx.commit();
		**/
		
		/***sess.save(lop);
		tx.commit();
		sess.evict(lop);
		lop.setLprice(5000); //detach state
		**/
		
		sess.save(lop);
		sess.delete(lop);
		tx.commit();//remove state

	}

}
