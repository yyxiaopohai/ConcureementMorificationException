import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Test_MQ_Consumer {
    public static final String MQ_URL="tcp://192.168.230.128:61616";
    public static final String MQ_NAME="MQ_0805";
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //当消费中的事务打开，没有集体提交的时候，会出现重复消费，
        //在消费系统中，如果事务打开，需要进行commit，不然会出现“重复消费”
        //在消费系统中，如果是手动接受，需要添加textMessage.acknowledge();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Queue queue = session.createQueue(MQ_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener((message) ->{
            if (message != null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                    //textMessage.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        } );
    }
}
