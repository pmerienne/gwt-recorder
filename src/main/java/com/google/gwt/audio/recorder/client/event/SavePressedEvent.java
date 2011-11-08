package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SavePressedEvent extends GwtEvent<SavePressedHandler>{

	private static Type<SavePressedHandler> TYPE;

	public static Type<SavePressedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SavePressedHandler>());
	}

	public SavePressedEvent(){

	}

	@Override
	protected void dispatch(SavePressedHandler handler) {
		handler.onSavePressed(this);
	}

	@Override
	public GwtEvent.Type<SavePressedHandler> getAssociatedType() {
		return getType();
	}

}