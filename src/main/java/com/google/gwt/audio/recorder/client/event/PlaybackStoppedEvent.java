package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PlaybackStoppedEvent extends GwtEvent<PlaybackStoppedHandler> {

	private static Type<PlaybackStoppedHandler> TYPE;

	public static Type<PlaybackStoppedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PlaybackStoppedHandler>());
	}

	private String name;

	public PlaybackStoppedEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(PlaybackStoppedHandler handler) {
		handler.onPlaybackStopped(this);
	}

	@Override
	public GwtEvent.Type<PlaybackStoppedHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}