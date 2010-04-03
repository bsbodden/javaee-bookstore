import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HelloBeanTest {
	@Test
	public void testSayHello() {
	    HelloBean b = new HelloBean();
	    assertTrue(b.sayHello().equals("Hello"));
	}
	
	@Test
	public void testSayHelloInContainer() {
		assertEquals("Hello", helloBean.sayHello());
	}
	
	@BeforeClass
	public static void initializeOpenEJB() throws NamingException {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
		initialContext = new InitialContext(properties);
	}
	
	@Before
	public void getMyBeans() throws NamingException {
		helloBean = (HelloLocal) initialContext.lookup("HelloBeanLocal");
	}
	
	private static InitialContext initialContext;
	private HelloLocal helloBean;
}
