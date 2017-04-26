package com.helper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.stereotype.Component;

@Component
public class MqTTTUtil implements MqttCallback {
	
	private MqttClient clientRec;
	private MqttClient clientSend;
	private String topicNameSend = "discover";
	private String topicNameRecv = "discover2";
	private String mqtturl = "tcp://localhost:1883";
	private List<String> messageRecieved = new ArrayList<String>();
	
	

	@PostConstruct
	public void init() {
		createRecvClient();
		createSendClient();
	}

	private void createRecvClient() {
		try {
				clientRec = new MqttClient(mqtturl, "Recv");
				clientRec.connect();
				clientRec.setCallback(this);
				clientRec.subscribe(topicNameRecv);
				
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	private void createSendClient() {
		try {
				clientSend = new MqttClient(mqtturl, "Send");
				clientSend.connect();
				clientSend.subscribe(topicNameSend);				
			} catch (MqttException e) {
				e.printStackTrace();
			}
		
	}
	
	@Override
	public void connectionLost(Throwable cause) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage message)
			throws Exception {
		
		messageRecieved.add(message.toString());
		/*if(message.toString().equals(iphash)) {
			System.out.println("I am up");
			sendMessage("I am up");
		}*/
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {

	}
	
	public void sendMessage(String message) {
		
		try {
			
			MqttMessage mess = new MqttMessage(message.getBytes());
			MqttTopic topic = clientSend.getTopic(topicNameSend);
			topic.publish(mess);
			//clientSend.publish(mqtturl, mess);
			System.out.println("discovery message sent");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
    }
	
	public List<String> getMessageRecieved() {
		return messageRecieved;
	}
	
	@PreDestroy
	public void cleanup() throws Exception{
		try {
			clientRec.disconnect();	
			clientRec.close();
			clientSend.disconnect();
			clientSend.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
