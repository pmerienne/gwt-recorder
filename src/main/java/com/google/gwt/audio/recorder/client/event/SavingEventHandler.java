package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavingEventHandler extends EventHandler {

	void onSavingEvent(SavingEventEvent event);
}