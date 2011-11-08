package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PlayingEvent extends GwtEvent<PlayingHandler> {

	private static Type<PlayingHandler> TYPE;

	public static Type<PlayingHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<PlayingHandler>());
	}

	private String name;

	public PlayingEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(PlayingHandler handler) {
		handler.onPlaying(this);
	}

	@Override
	public GwtEvent.Type<PlayingHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}