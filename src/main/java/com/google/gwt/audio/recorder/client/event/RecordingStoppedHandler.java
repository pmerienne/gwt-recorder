package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface RecordingStoppedHandler extends EventHandler {

	void onRecordingStopped(RecordingStoppedEvent event);
}