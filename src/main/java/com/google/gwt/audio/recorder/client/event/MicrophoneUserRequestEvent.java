package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MicrophoneUserRequestEvent extends GwtEvent<MicrophoneUserRequestHandler>{

	private static Type<MicrophoneUserRequestHandler> TYPE;

	public static Type<MicrophoneUserRequestHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<MicrophoneUserRequestHandler>());
	}

	public MicrophoneUserRequestEvent(){

	}

	@Override
	protected void dispatch(MicrophoneUserRequestHandler handler) {
		handler.onMicrophoneUserRequest(this);
	}

	@Override
	public GwtEvent.Type<MicrophoneUserRequestHandler> getAssociatedType() {
		return getType();
	}

}