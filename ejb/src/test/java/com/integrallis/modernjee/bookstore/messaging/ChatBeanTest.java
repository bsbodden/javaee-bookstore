package com.integrallis.modernjee.bookstore.messaging;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ChatBeanTest {
	private String query, expectedResponse;
	
	public ChatBeanTest(String query, String expectedResponse) {
		this.query = query;
		this.expectedResponse = expectedResponse;
	}

	@Test
    public void testConversation() throws Exception {
        messagingClient.sendMessage(query);
        String response = messagingClient.receiveMessage();
        assertEquals(expectedResponse, response);
    }
	
	@Parameters
	public static List<Object[]> testData() {
		return Arrays.asList(new Object[][] {{ "Shall we play a game?", "Love to. How about Global Thermonuclear War?" }, 
				                             { "Do you read me, HAL?", "Affirmative, Dave. I read you" },
				                             { "Open the pod bay doors, HAL.", "I'm sorry, Dave. I'm afraid I can't do that." }});
	}
	
	@BeforeClass
	public static void initializeOpenEJB() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        initialContext = new InitialContext(properties);
	}
	
	@Before
	public void getMessagingClient() throws NamingException {
		messagingClient = (MessagingClientLocal) initialContext.lookup("MessagingClientBeanLocal");
	}
	
	private static InitialContext initialContext;
	private MessagingClientLocal messagingClient;
}

