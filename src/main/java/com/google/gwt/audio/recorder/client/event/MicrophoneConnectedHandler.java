package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MicrophoneConnectedHandler extends EventHandler {

	void onMicrophoneConnected(MicrophoneConnectedEvent event);
}