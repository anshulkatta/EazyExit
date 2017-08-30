package com.mqtt;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.fragment.NewSwitchFragment;
import com.provider.EazyExitContract;
import com.util.Util;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MQTTConnector {

    private Context context;
    IMqttMessageListener listener;

    public MQTTConnector(IMqttMessageListener listener) {
        this.listener = listener;
    }

    public MqttAndroidClient getClient(Context mContext, String broker, String clientid) {
        context = mContext;
        MqttAndroidClient   mqttClient = new MqttAndroidClient(mContext, broker, clientid);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        mqttClient.setCallback(new MqttCallbackHandler());
        return mqttClient;
    }

    public void connect(MqttAndroidClient mqttClient,String subscriptionTopic) {
        try {
            mqttClient.connect(null,new Listener(mqttClient,subscriptionTopic));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DisconnectedBufferOptions getDisconnectedbufferoptions() {
        DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
        disconnectedBufferOptions.setBufferEnabled(true);
        disconnectedBufferOptions.setBufferSize(100);
        disconnectedBufferOptions.setPersistBuffer(false);
        disconnectedBufferOptions.setDeleteOldestMessages(false);
        return disconnectedBufferOptions;
    }

    class Listener implements IMqttActionListener {
        String subscriptionTopic;
        MqttAndroidClient mqttClient;
        Listener(MqttAndroidClient mqttClient,String subscriptionTopic) {
            this.subscriptionTopic = subscriptionTopic;
            this.mqttClient = mqttClient;
        }
        public void onSuccess(IMqttToken asyncActionToken) {
            mqttClient.setBufferOpts(getDisconnectedbufferoptions());
            subscribeToTopic(mqttClient,subscriptionTopic);
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            exception.printStackTrace();
        }

    }

    private void subscribeToTopic(MqttAndroidClient mqttClient,String subscriptionTopic){
        try {
            mqttClient.subscribe(subscriptionTopic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d("MQTTConnector","connected now");
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d("MQTTConnector","Failed to subscribe");
                }
            });
            mqttClient.subscribe(subscriptionTopic, 0,listener);
            /*mqttClient.subscribe(subscriptionTopic, 0, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    if(topic.equals(Util.DISCOVERY_TOPIC) && message != null) {
                        String messageArr[] = message.toString().split(":");
                        if(messageArr !=null && messageArr.length >0) {
                            ContentValues value = Util.createContentValue("New Node",messageArr[0],
                                    "ON","PRIMARY","NEW","No Name Yet");
                            context.getContentResolver().insert
                                    (EazyExitContract.NodeEntry.CONTENT_URI, value);
                            value.clear();

                        }

                        Log.d("MQTTConnector","Message: " + topic + " : " + new String(message.getPayload()));
                    }

                }
            });*/

        } catch (MqttException ex){
            Log.d("MQTTConnector","Exception while subscribing");
            ex.printStackTrace();
        }
    }
}