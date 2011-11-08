package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavingEventEvent extends GwtEvent<SavingEventHandler> {

	private static Type<SavingEventHandler> TYPE;

	public static Type<SavingEventHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SavingEventHandler>());
	}

	private String name;

	public SavingEventEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(SavingEventHandler handler) {
		handler.onSavingEvent(this);
	}

	@Override
	public GwtEvent.Type<SavingEventHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}