package ru.geekbrains;

import ru.geekbrains.dto.ProductDto;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = connectionFactory.createContext("jmsUser", "123");
        Destination dest = (Destination) context.lookup("jms/productQueue");
        JMSProducer producer = jmsContext.createProducer();
        ObjectMessage objectMessage = jmsContext.createObjectMessage(
                new ProductDto(null, "Product from JMS", "Product from JMS", new BigDecimal(1000),
                        1L, null));
        producer.send(dest, objectMessage);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(JmsClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }

}
