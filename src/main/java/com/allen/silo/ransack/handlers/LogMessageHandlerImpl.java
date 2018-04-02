package com.allen.silo.ransack.handlers;

import org.mini2Dx.minibus.MessageData;
import org.mini2Dx.minibus.MessageExchange;
import org.mini2Dx.minibus.MessageHandler;

import com.allen.silo.ransack.display.PlayableDisplay;

public class LogMessageHandlerImpl implements MessageHandler {

	@Override
	public void onMessageReceived(String messageType, MessageExchange source, MessageExchange receiver, MessageData messageData) {
		LogMessageDataImpl m = (LogMessageDataImpl)messageData;
		PlayableDisplay.enqueueLog(new Log(m.getMessage()));
	}

}
