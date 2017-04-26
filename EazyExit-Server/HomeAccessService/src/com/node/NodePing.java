package com.node;

import java.net.InetAddress;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class NodePing {
	
	public String ping(String content) {

        String topic        = "myHome";
        int qos             = 2;
        String ip=getIP();
        System.out.println(ip);
        String broker       = "tcp://"+ip+":1883";
        String clientId     = "Ping";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            sampleClient.publish(topic, message);
            System.out.println("Message published");
            sampleClient.disconnect();
            System.out.println("Disconnected");
            return "ON";
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
            return "OFF";
        }
    }

	private String getIP() {
		String ipAddress=null;
		try {
		InetAddress addr = InetAddress.getLocalHost();
	      
        //Getting IPAddress of localhost - getHostAddress return IP Address
        // in textual format
        ipAddress = addr.getHostAddress();	
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return ipAddress;
	}


}
