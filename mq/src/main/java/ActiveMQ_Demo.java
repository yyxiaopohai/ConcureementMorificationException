import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQ_Demo {
    public static final String MQ_URL="tcp://192.168.230.128:61616";
    public static final String MQ_NAME="MQ_0805";
    public static void main(String[] args) throws JMSException {


        //获取工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //获取连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接建立会话
        connection.start();
        //获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(MQ_NAME);
        //创建生产者,生产出来消息放到哪个队列里
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("msg------" + i);
            producer.send(message);
        }

        producer.close();
        session.close();
        connection.close();


        System.out.println("-----------msg send seccess----------");

    }
}
