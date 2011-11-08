package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavedEvent extends GwtEvent<SavedHandler> {

	private static Type<SavedHandler> TYPE;

	public static Type<SavedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SavedHandler>());
	}

	private String name;

	public SavedEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(SavedHandler handler) {
		handler.onSaved(this);
	}

	@Override
	public GwtEvent.Type<SavedHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}