import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Test_MQ_Producer {
    public static final String MQ_URL="tcp://192.168.230.128:61616";
    public static final String MQ_NAME="MQ_0805";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //签收对于生产者，没有太大影响，默认为自动就行
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(MQ_NAME);

        MessageProducer producer = session.createProducer(queue);

        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//非持久
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("----------send success--------------" + i);
            producer.send(textMessage);
        }
        producer.close();
        //当事务改为true时，需要集中提交一次
        //session.commit();
        session.close();
        connection.close();
        System.out.println("---------ok-----------");
    }
}
