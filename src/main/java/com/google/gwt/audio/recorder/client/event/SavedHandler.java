package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SavedHandler extends EventHandler {

	void onSaved(SavedEvent event);
}