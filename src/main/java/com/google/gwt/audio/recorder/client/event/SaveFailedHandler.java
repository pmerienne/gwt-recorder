package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SaveFailedHandler extends EventHandler {

	void onSaveFailed(SaveFailedEvent event);
}