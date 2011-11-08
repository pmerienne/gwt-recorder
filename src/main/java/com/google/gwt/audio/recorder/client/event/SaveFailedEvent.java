package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SaveFailedEvent extends GwtEvent<SaveFailedHandler> {

	private static Type<SaveFailedHandler> TYPE;

	public static Type<SaveFailedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SaveFailedHandler>());
	}

	private String name;
	private String error;

	public SaveFailedEvent(String name, String error) {
		this.name = name;
		this.error = error;
	}

	@Override
	protected void dispatch(SaveFailedHandler handler) {
		handler.onSaveFailed(this);
	}

	@Override
	public GwtEvent.Type<SaveFailedHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

	public String getError() {
		return error;
	}

}