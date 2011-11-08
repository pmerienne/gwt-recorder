package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NoMicrophoneFoundHandler extends EventHandler {

	void onNoMicrophoneFound(NoMicrophoneFoundEvent event);
}