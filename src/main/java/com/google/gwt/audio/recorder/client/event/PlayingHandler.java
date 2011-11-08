package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PlayingHandler extends EventHandler {

	void onPlaying(PlayingEvent event);
}