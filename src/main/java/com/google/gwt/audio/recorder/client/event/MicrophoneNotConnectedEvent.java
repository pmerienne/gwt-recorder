package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MicrophoneNotConnectedEvent extends GwtEvent<MicrophoneNotConnectedHandler> {

	private static Type<MicrophoneNotConnectedHandler> TYPE;

	public static Type<MicrophoneNotConnectedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<MicrophoneNotConnectedHandler>());
	}

	public MicrophoneNotConnectedEvent() {

	}

	@Override
	protected void dispatch(MicrophoneNotConnectedHandler handler) {
		handler.onMicrophoneNotConnected(this);
	}

	@Override
	public GwtEvent.Type<MicrophoneNotConnectedHandler> getAssociatedType() {
		return getType();
	}

}