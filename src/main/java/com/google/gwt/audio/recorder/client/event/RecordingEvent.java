package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RecordingEvent extends GwtEvent<RecordingHandler> {

	private static Type<RecordingHandler> TYPE;

	public static Type<RecordingHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<RecordingHandler>());
	}

	private String name;

	public RecordingEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(RecordingHandler handler) {
		handler.onRecording(this);
	}

	@Override
	public GwtEvent.Type<RecordingHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}