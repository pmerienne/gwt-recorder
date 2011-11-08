package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RecordingHandler extends EventHandler {

	void onRecording(RecordingEvent event);
}