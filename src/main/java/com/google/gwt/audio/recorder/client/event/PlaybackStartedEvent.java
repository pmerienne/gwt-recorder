package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PlaybackStartedEvent extends GwtEvent<PlaybackStartedHandler> {

	private static Type<PlaybackStartedHandler> TYPE;

	public static Type<PlaybackStartedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PlaybackStartedHandler>());
	}

	private String name;
	private int latency;

	public PlaybackStartedEvent(String name, int latency) {
		this.name = name;
		this.latency = latency;
	}

	@Override
	protected void dispatch(PlaybackStartedHandler handler) {
		handler.onPlaybackStarted(this);
	}

	@Override
	public GwtEvent.Type<PlaybackStartedHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

	public int getLatency() {
		return latency;
	}

}