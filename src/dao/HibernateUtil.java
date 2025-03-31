package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateUtil {

	private static SessionFactory factory;
	private static Configuration cfg;

	public static Session getSession(String banco) {
		cfg = new AnnotationConfiguration();
		cfg.configure(banco);
		factory = cfg.buildSessionFactory();
		return factory.openSession();
	}

	public static void createDB(boolean create) {
		new SchemaExport(cfg).create(true, create);
	}
	
	public static void close() {
		factory.close();
	}

}
