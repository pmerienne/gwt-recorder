package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MicrophoneNotConnectedHandler extends EventHandler {

	void onMicrophoneNotConnected(MicrophoneNotConnectedEvent event);
}