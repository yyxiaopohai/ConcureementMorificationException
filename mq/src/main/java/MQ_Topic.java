import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQ_Topic {
    public static final String MQ_URL="tcp://192.168.230.128:61616";
    public static final String MQ_NAME="topic_0805";
    public static void main(String[] args) throws JMSException {

        //创建工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //获取连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //建立连接
        connection.start();
        //获取会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建主题
        Topic topic = session.createTopic(MQ_NAME);
        //生产消息到什么地方
        MessageProducer producer = session.createProducer(topic);

        //
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("---------topic提问卡"+i);
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

        System.out.println("----------topic send success -------------");
    }
}
