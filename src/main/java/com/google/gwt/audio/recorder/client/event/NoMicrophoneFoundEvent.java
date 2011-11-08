package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NoMicrophoneFoundEvent extends GwtEvent<NoMicrophoneFoundHandler>{

	private static Type<NoMicrophoneFoundHandler> TYPE;

	public static Type<NoMicrophoneFoundHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<NoMicrophoneFoundHandler>());
	}

	public NoMicrophoneFoundEvent(){

	}

	@Override
	protected void dispatch(NoMicrophoneFoundHandler handler) {
		handler.onNoMicrophoneFound(this);
	}

	@Override
	public GwtEvent.Type<NoMicrophoneFoundHandler> getAssociatedType() {
		return getType();
	}

}