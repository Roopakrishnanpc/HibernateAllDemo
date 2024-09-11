package com.mavenHibernate.HibernateDemo.HibernateLearning;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.query.NativeQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import com.mavenHibernate.HibernateDemo.HibernateLearning.Entity.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		//sampleEmployeePersoDemo();
	//relationalMappingDemoLapStudMaytoMany();
	//relationalMappingDemoLapStudOnetoMany();
	//relationalMappingDemoLapStudOnetoOne();
	//sampleCustomerBrandDemow();
	//lazyExample();
	//eagerDemo();
	//
    //firstlevelChacingDemo();
	//secondLevelChacngDemo();
    //hqlDemo1adddata();
    	//hqlSelectDemo1();
    	hqlsqldemo();
    	//statesDemo();
    	getloadDemo();
}
    
private static void getloadDemo() {
		// TODO Auto-generated method stub
	Configuration c=new Configuration().configure().addAnnotatedClass(PersistentLifeCycleDemo.class);
	ServiceRegistry s=new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
	SessionFactory sessionfactory=c.buildSessionFactory(s);
	Session session=sessionfactory.openSession();
	Transaction tx=session.beginTransaction();
	//By defau;t new state in java
	int primaryKey=5;
	PersistentLifeCycleDemo entityget = session.get(PersistentLifeCycleDemo.class, primaryKey);
	//Try to comment & see that get hit the db even when we comment the print statement below
	System.out.println(entityget);
	PersistentLifeCycleDemo entityload = session.get(PersistentLifeCycleDemo.class, primaryKey);
	//Try to comment & see that load doesn't hit the db when we comment the print statement below
	System.out.println(entityload);
	PersistentLifeCycleDemo entityload1 = session.get(PersistentLifeCycleDemo.class, primaryKey);
	//look the Thread and how it works
	//Basically get gives object load gives proxy object
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(entityload1);
	//Exception now 70 is not there in db
	PersistentLifeCycleDemo entityget1 = session.get(PersistentLifeCycleDemo.class, 70); //null
	System.out.println(entityget1); //this won't give any error when u try to get like below specific then throws exception
	//First null when u try to print u get exception
	//System.out.println(entityget1.getMarks()); //NullPinterException
	PersistentLifeCycleDemo entityload2 = session.load(PersistentLifeCycleDemo.class, 70); //u wom't get anything now
	//When u try to print ObjectNotFoundException
	//System.out.println(entityload2); //but here throws exception even here
	//System.out.println(entityload2.getMarks());
	
	tx.commit();
	}

private static void statesDemo() {
		// TODO Auto-generated method stub
	
	Configuration c=new Configuration().configure().addAnnotatedClass(PersistentLifeCycleDemo.class);
	ServiceRegistry s=new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
	SessionFactory sessionfactory=c.buildSessionFactory(s);
	Session session=sessionfactory.openSession();
	Transaction tx=session.beginTransaction();
	//By defau;t new state in java
	int primaryKey=5;
	PersistentLifeCycleDemo entity = session.get(PersistentLifeCycleDemo.class, primaryKey);
	PersistentLifeCycleDemo persist=new PersistentLifeCycleDemo();

	//Below are transient state, because we are not storing these objects in databasse
	persist.setRollno(53);
	persist.setMarks(93);
	persist.setStud_name("Ashwin");
	//In order to save somewhere we use persstent state.
	session.save(persist);
	//when u click save the it will be in sacve so whatever changes you do after that also we be updated
	persist.setMarks(95);
	//it will remove te data whixch we add
	session.remove(persist);

	tx.commit();
	
	//Now you want to make some change but not store in db so u use detach, you have to give commit
		session.detach(persist);
		persist.setMarks(98);
	
	}

private static void hqlsqldemo() {
		// TODO Auto-generated method stub
	Configuration con=new Configuration().configure().addAnnotatedClass(HqlDemoEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    NativeQuery sql=session.createNativeQuery("select * from hql_entity where marks>60");
    //Through below steps we are determining that it is a list of students
    sql.addEntity(HqlDemoEntity.class);
    List<HqlDemoEntity> list=sql.list();
    for(HqlDemoEntity o:list)
    {
    	System.out.println(o);
    }

    // Execute the native SQL query
    NativeQuery query = session.createNativeQuery("SELECT * FROM hql_entity WHERE marks > 60");
    List<Object[]> results = query.list();

    // Iterate over the results and print the values
    for (Object[] row : results) {
        int rollno = (int) row[0];
        int marks = (int) row[1];
        String stud_name = (String) row[2];
        

        System.out.println("Roll No: " + rollno + ", Name: " + stud_name + ", Marks: " + marks);
    }
    NativeQuery query1 = session.createNativeQuery("SELECT marks, stud_name FROM hql_entity WHERE marks > 60");
    //query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
    query1.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
    List results1 = query1.list();

    // Iterate over the results and print the values
    for (Object row : results1) {
        Map m=(Map)row;
        System.out.println(m.get("marks")+" : "+m.get("stud_name"));
        //System.out.println(row[0]+":"row[1]);
    }
    tx.commit();
	}

private static void hqlSelectDemo1() {
		// TODO Auto-generated method stub
	Configuration con=new Configuration().configure().addAnnotatedClass(HqlDemoEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    Query q=session.createQuery("from hql_entity");
    List<HqlDemoEntity> h=q.list();
    q.list();
    for(HqlDemoEntity hl:h)
    {
    	System.out.println(hl);
    }
    Query q1=session.createQuery("from hql_entity where marks >=50");
    List<HqlDemoEntity> h1=q.list();
    q.list();
    for(HqlDemoEntity hl:h1)
    {
    	System.out.println(hl);
    }
    Query q11=session.createQuery("from hql_entity where rollno=1");
    HqlDemoEntity h11=(HqlDemoEntity) q11.uniqueResult();
    System.out.println(h11);
    Query q111=session.createQuery("select rollno, marks, stud_name from hql_entity where rollno=1");
    Object[] h111=(Object[]) q111.uniqueResult();
    System.out.println(h111[0]+ " : " +h111[1]+" : "+h111[2]);
    for(Object hl:h111)
    {
    	System.out.println(hl);
    }
    Query q1111=session.createQuery("select rollno, marks, stud_name from hql_entity");
    List<Object[]> h1111=(List<Object[]>) q1111.list();
    //System.out.println(h1111[0]+ " : " +h1111[1]+" : "+h1111[2]);
    for(Object[] hl:h1111)
    {
    	//[0] because its already list of objects and we are trying to get ovject[] 0, 1 , 2
    	System.out.println(hl[0]+ " : " +hl[1]+" : "+hl[2]);
    }
    Query q11111=session.createQuery("select rollno, marks, stud_name from hql_entity h where h.marks>60");
    List<Object[]> h11111=(List<Object[]>) q11111.list();
    //System.out.println(h11111[0]+ " : " +h11111[1]+" : "+h11111[2]);
    for(Object[] hl:h11111)
    {
    	System.out.println(hl[0]+ " : " +hl[1]+" : "+hl[2]);
    }
    Query query=session.createQuery("select sum(h.marks)_name from hql_entity h where h.marks>60");
    List resu=(List) query.list();
    //System.out.println(h11111[0]+ " : " +h11111[1]+" : "+h11111[2]);
    for(Object hl:resu)
    {
    	System.out.println(hl);
    }
    Object result=query.uniqueResult();
    System.out.println(result);
    Long result1=(Long) query.uniqueResult();
    System.out.println(result1);
    int concatvalue=60;
    Query queryconcat=session.createQuery("select sum(h.marks)_name from hql_entity h where h.marks>"+concatvalue);
    Long result12=(Long) queryconcat.uniqueResult();
    System.out.println(result12);
    Query queryconcat1=session.createQuery("select sum(h.marks)_name from hql_entity h where h.marks> :concatvalue");
    queryconcat1.setParameter("concatvalue", concatvalue);
    Long result121=(Long) queryconcat1.uniqueResult();
    System.out.println(result121);
    //System.out.println(h111);
    tx.commit();
   // q.uniqueResult();
	}
private static void hqlDemo1adddata() {
		// TODO Auto-generated method stub
	
	Configuration con=new Configuration().configure().addAnnotatedClass(PersistentLifeCycleDemo.class);
	//Configuration con=new Configuration().configure().addAnnotatedClass(HqlDemoEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    Random r=new Random();
    
    for(int i=1; i<=50;i++)
    {
    	PersistentLifeCycleDemo hq=new PersistentLifeCycleDemo();
    	//HqlDemoEntity hq=new HqlDemoEntity();
    	hq.setRollno(i);
    	hq.setStud_name("Name "+i);
    	hq.setMarks(r.nextInt(100));
    	session.save(hq);
    }
    tx.commit();
	}
private static void secondLevelChacngDemo() {
	// TODO Auto-generated method stub
	CacheEgEntity cx=null;
//	Configuration con = new Configuration()
//	        .setProperty("hibernate.cache.use_second_level_cache", "true")
//	        .setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.infinispan.InfinispanRegionFactory")
//	        // Other properties...
//	        .configure().addAnnotatedClass(CacheEgEntity.class);
	Configuration con=new Configuration().configure().addAnnotatedClass(CacheEgEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //since same data is needed from the same session(session1) the same data will be extracted using the same query using the same sessio
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //ew query will be created 
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 2);
    System.out.println(cx);
    List<CacheEgEntity> resultList = session.createQuery("from CacheEgEntity", CacheEgEntity.class).list();

    for (CacheEgEntity entity : resultList) {
        System.out.println(entity);
    }
    for (CacheEgEntity entity : resultList) {
        System.out.println(entity);
    }
    tx.commit();
    Session session1=sf.openSession();
    Transaction tx1 = session1.beginTransaction();
    cx=(CacheEgEntity)session1.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //since same data is needed from the same session(session1) the same data will be extracted using the same query using the same sessio
    cx=(CacheEgEntity)session1.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //ew query will be created 
    cx=(CacheEgEntity)session1.get(CacheEgEntity.class, 2);
    System.out.println(cx);
    List<CacheEgEntity> resultList1 = session1.createQuery("from CacheEgEntity", CacheEgEntity.class).list();

    for (CacheEgEntity entity : resultList1) {
        System.out.println(entity);
    }
    for (CacheEgEntity entity : resultList1) {
        System.out.println(entity);
    }
    tx1.commit();
}
public static void firstlevelChacingDemo()
{
	CacheEgEntity cx=null;
	Configuration con=new Configuration().configure().addAnnotatedClass(CacheEgEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //since same data is needed from the same session(session1) the same data will be extracted using the same query using the same sessio
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 1);
    System.out.println(cx);
    //ew query will be created 
    cx=(CacheEgEntity)session.get(CacheEgEntity.class, 2);
    System.out.println(cx);
    List<CacheEgEntity> resultList = session.createQuery("from CacheEgEntity", CacheEgEntity.class).list();

    for (CacheEgEntity entity : resultList) {
        System.out.println(entity);
    }
    for (CacheEgEntity entity : resultList) {
        System.out.println(entity);
    }
    tx.commit();
    //session.getTransaction().commit();
}
//fetch=FetchType.EAGER
public static void eagerDemo()
{
	Configuration con=new Configuration().configure().addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(BrandEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction tx = session.beginTransaction();
    CustomerEntity cs = session.get(CustomerEntity.class, 100);
    System.out.println(cs.getName());
    tx.commit();
}
public static void lazyExample()
{
	//CustomerEntity p=new CustomerEntity();
	Configuration con=new Configuration().configure().addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(BrandEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    try {
        Transaction tx = session.beginTransaction();
        
        // Retrieve CustomerEntity with ID 101
        CustomerEntity cs = session.get(CustomerEntity.class, 100);
        System.out.println(cs.getName());
        if (cs != null) { // Check if the customer exists
            Collection<BrandEntity> brands = cs.getBrand();
            for (BrandEntity brand : brands) {
                System.out.println(brand); // Ensure BrandEntity has a proper toString() method
            }
        } else {
            System.out.println("Customer with ID 101 not found.");
        }
        
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        session.close();
        sf.close();
    }
}
private static void sampleCustomerBrandDemow()
{
	BrandEntity brand=new BrandEntity();
	brand.setBid(101);
	brand.setBaname("RS");
	brand.setPrice("Laptop");
	BrandEntity brand1=new BrandEntity();
	brand1.setBid(102);
	brand1.setBaname("KCRSA");
	brand1.setPrice("Desktop");
	BrandEntity brand2=new BrandEntity();
	brand2.setBid(103);
	brand2.setBaname("PArents");
	brand2.setPrice("Mouse");
	CustomerEntity p=new CustomerEntity();
	p.setId(100);
	p.setName("Sam");
	p.getBrand().add(brand);
	p.getBrand().add(brand1);
	brand.setCustomer(p);
	brand1.setCustomer(p);
	CustomerEntity p1 =new CustomerEntity();
	p1.setId(101);
	p1.setName("Roopa");
	p1.getBrand().add(brand2);   
	brand2.setCustomer(p1);
	Configuration con=new Configuration().configure().addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(BrandEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction  tx=session.beginTransaction();
    //Update & Create
    session.save(brand);
    session.save(brand1);
    session.save(brand2);
    session.save(p);
    session.save(p1);

    tx.commit();
    //Fetch
  //  System.out.print(session.get(CustomerEntity.class, 101));

    
}
private void sampleEmployeePersoDemowithEmployeeEntity()
{
	EmployeeNameEntity emp=new EmployeeNameEntity();
	emp.setFirstName("Roopa");
	emp.setLastName("Sri");
	//PersonEntity p=null this will also work
	PersonEntity p=new PersonEntity();
    p.setPersonID(12);
    p.setName(emp);
    p.setPositions("Java");
	Configuration con=new Configuration().configure().addAnnotatedClass(PersonEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction  tx=session.beginTransaction();
    //Update & Create
    session.save(p);
    //Fetch
    System.out.print(session.get(PersonEntity.class, 12));
    tx.commit();
}
private static void relationalMappingDemoLapStudMaytoMany()
{
	LaptiopEntity lap=new LaptiopEntity();
	LaptiopEntity lap1=new LaptiopEntity();
	lap.setId(101);
	lap.setLame("Dell");
	lap1.setId(102);  
	lap1.setLame("Dell");
	StudentEntity stud1=new StudentEntity();
	StudentEntity stud=new StudentEntity();
	stud1.setRollno(1);
	stud1.setSname("Roopa");
	stud1.setMarks(100);
	stud1.getLap().add(lap);
	stud1.getLap().add(lap);
	lap.getStudent().add(stud);
	stud.setRollno(2);
	stud.setSname("Sam");
	stud.setMarks(100);
	//stud.getLap(lap);
	//adding the laptop into arraylist
	stud.getLap().add(lap);
	lap.getStudent().add(stud);
	Configuration con=new Configuration().configure().addAnnotatedClass(StudentEntity.class).addAnnotatedClass(LaptiopEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction  tx=session.beginTransaction();
    //Update & Create
    session.save(lap);
    session.save(lap1);
    session.save(stud1);
    session.save(stud);
    
    //Fetch
    //System.out.print(session.get(StudentEntity.class, 12));
    tx.commit();
	
}


/*   private static void relationalMappingDemoLapStudOnetoMany()
{
	LaptiopEntity lap=new LaptiopEntity();
	LaptiopEntity lap1=new LaptiopEntity();
	lap.setId(101);
	lap.setLame("Dell");
	lap1.setId(102);
	lap1.setLame("Dell");
	StudentEntity stud1=new StudentEntity();
	StudentEntity stud=new StudentEntity();
	stud1.setRollno(1);
	stud1.setSname("Roopa");
	stud1.setMarks(100);
	stud1.getLaptop().add(lap);
	//it's not necessary to mention this but u will get null
	lap.setStud(stud);
	stud.setRollno(2);
	stud.setSname("Sam");
	stud.setMarks(100);
	//stud.getLap(lap);
	//adding the laptop into arraylist
	stud.getLaptop().add(lap);
	lap1.setStud(stud);
	Configuration con=new Configuration().configure().addAnnotatedClass(StudentEntity.class).addAnnotatedClass(LaptiopEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction  tx=session.beginTransaction();
    //Update & Create
    session.save(lap);
    session.save(lap1);
    session.save(stud1);
    session.save(stud);
    
    //Fetch
    //System.out.print(session.get(StudentEntity.class, 12));
    tx.commit();
	
}

*/
/* private static void relationalMappingDemoLapStudOnetoOne()
{
	LaptiopEntity lap=new LaptiopEntity();
	LaptiopEntity lap1=new LaptiopEntity();
	lap.setId(101);
	lap.setLame("Dell");
	lap1.setId(102);
	lap1.setLame("Dell");
	StudentEntity stud1=new StudentEntity();
	StudentEntity stud=new StudentEntity();
	stud1.setRollno(1);
	stud1.setSname("Roopa");
	stud1.setMarks(100);
	stud.setLapto(lap);

	stud.setRollno(2);
	stud.setSname("Sam");
	stud.setMarks(100);
	stud.setLapto(lap1);
	//adding the laptop into arraylist

	Configuration con=new Configuration().configure().addAnnotatedClass(StudentEntity.class).addAnnotatedClass(LaptiopEntity.class);
    ServiceRegistry sr=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
    SessionFactory sf=con.buildSessionFactory(sr);
    Session session=sf.openSession();
    Transaction  tx=session.beginTransaction();
    //Update & Create
    session.save(lap);
    session.save(lap1);
    session.save(stud1);
    session.save(stud);
    
    //Fetch
    //System.out.print(session.get(StudentEntity.class, 12));
    tx.commit();
	
}*/
}
