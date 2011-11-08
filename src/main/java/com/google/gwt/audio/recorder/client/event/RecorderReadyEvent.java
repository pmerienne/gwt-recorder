package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RecorderReadyEvent extends GwtEvent<RecorderReadyHandler> {

	private static Type<RecorderReadyHandler> TYPE;

	public static Type<RecorderReadyHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<RecorderReadyHandler>());
	}

	private int width;
	private int height;

	public RecorderReadyEvent(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	protected void dispatch(RecorderReadyHandler handler) {
		handler.onRecorderReady(this);
	}

	@Override
	public GwtEvent.Type<RecorderReadyHandler> getAssociatedType() {
		return getType();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}