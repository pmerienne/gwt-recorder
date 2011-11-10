package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavingHandler extends EventHandler {

	void onSaving(SavingEvent event);
}