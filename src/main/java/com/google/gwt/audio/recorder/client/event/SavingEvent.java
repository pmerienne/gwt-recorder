package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavingEvent extends GwtEvent<SavingHandler> {

	private static Type<SavingHandler> TYPE;

	public static Type<SavingHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SavingHandler>());
	}

	private String name;

	public SavingEvent(String name) {
		this.name = name;
	}

	@Override
	protected void dispatch(SavingHandler handler) {
		handler.onSaving(this);
	}

	@Override
	public GwtEvent.Type<SavingHandler> getAssociatedType() {
		return getType();
	}

	public String getName() {
		return name;
	}

}