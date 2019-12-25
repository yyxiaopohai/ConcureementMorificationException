import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQ_Consumer {
    public static final String MQ_URL="tcp://192.168.230.128:61616";

    public static final String MQ_NAME="MQ_0805";
    public static void main(String[] args) throws JMSException {
        //获取工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(MQ_URL);
        //获取连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //建立连接
        connection.start();
        //创建消费
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        Queue queue = session.createQueue(MQ_NAME);
        //从哪消费
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener((message)->{
            if (message != null && message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("------消费:"+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        /*while (true){
            TextMessage message = (TextMessage) consumer.receive();
//            如果使用有参方法的时候，需要手动释放资源
//            producer.close();
//            session.close();
//            connection.close();
            //TextMessage message = (TextMessage) consumer.receive(4000);
            if (null != message){
                System.out.println("---------消费："+message.getText());

            }else{
                break;
            }
        }*/

    }
}
