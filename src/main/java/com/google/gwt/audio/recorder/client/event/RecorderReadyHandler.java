package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RecorderReadyHandler extends EventHandler {

	void onRecorderReady(RecorderReadyEvent event);
}