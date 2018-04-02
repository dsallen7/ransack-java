package com.allen.silo.ransack.handlers;

import java.util.logging.Logger;

import org.mini2Dx.minibus.MessageData;
import org.mini2Dx.minibus.MessageExchange;
import org.mini2Dx.minibus.MessageHandler;

import com.allen.silo.ransack.main.Ransack;
import com.allen.silo.ransack.utils.Constants;

public class EventMessageHandlerImpl implements MessageHandler {
	public static Logger logger = Logger.getLogger(EventMessageHandlerImpl.class.getName());

	@Override
	public void onMessageReceived(String messageType, MessageExchange source, MessageExchange receiver, MessageData messageData) {
		//logger.log(Level.INFO, "messageType: " + messageType);
		EventMessageDataImpl m = (EventMessageDataImpl)messageData;
		switch(messageType){
		case Constants.RETURNCONTROL:
			Ransack.enqueueEvent(new Event(Constants.RETURNCONTROL));
		case Constants.OPENDIALOGUE:
			if (!m.getDialogueFrom().equalsIgnoreCase(m.getDialogueTo()))
				Ransack.enqueueEvent(new Event(Constants.OPENDIALOGUE, m.getDialogueFrom(), m.getDialogueTo(), m.getData()) );
			break;
		case Constants.CLOSEDIALOGUE:
			Ransack.enqueueEvent(new Event(Constants.CLOSEDIALOGUE));
			break;
		case Constants.BATTLE:
			break;
		case Constants.SHOPPING:
			break;
		case Constants.MINIGAME:
			break;
		case Constants.INTERMAP:
			Ransack.enqueueEvent(new Event(Constants.INTERMAP, m.getMapFrom(), m.getMapTo(), m.getDir()));
			break;
		case Constants.PORTAL:
			Ransack.enqueueEvent(new Event(Constants.PORTAL, m.getMapFrom(), m.getMapTo(), m.getNewL() ));
			break;
		case Constants.STAIRSUP:
			Ransack.enqueueEvent(new Event(Constants.STAIRSUP, m.getMapFrom(), m.getMapTo() ));
			break;
		case Constants.STAIRSDOWN:
			Ransack.enqueueEvent(new Event(Constants.STAIRSDOWN, m.getMapFrom(), m.getMapTo()));
			break;
		}
	}

}
