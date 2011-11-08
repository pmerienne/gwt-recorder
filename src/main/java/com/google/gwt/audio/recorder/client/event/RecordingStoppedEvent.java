package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RecordingStoppedEvent extends GwtEvent<RecordingStoppedHandler> {

	private static Type<RecordingStoppedHandler> TYPE;

	public static Type<RecordingStoppedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<RecordingStoppedHandler>());
	}

	private String name;

	private int duration;

	public RecordingStoppedEvent(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	@Override
	protected void dispatch(RecordingStoppedHandler handler) {
		handler.onRecordingStopped(this);
	}

	@Override
	public GwtEvent.Type<RecordingStoppedHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

}