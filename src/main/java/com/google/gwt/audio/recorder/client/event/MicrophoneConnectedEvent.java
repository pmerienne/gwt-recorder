package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MicrophoneConnectedEvent extends GwtEvent<MicrophoneConnectedHandler> {

	private static Type<MicrophoneConnectedHandler> TYPE;

	public static Type<MicrophoneConnectedHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<MicrophoneConnectedHandler>());
	}

	private String microphoneName;

	public MicrophoneConnectedEvent(String microphoneName) {
		this.microphoneName = microphoneName;
	}

	@Override
	protected void dispatch(MicrophoneConnectedHandler handler) {
		handler.onMicrophoneConnected(this);
	}

	@Override
	public GwtEvent.Type<MicrophoneConnectedHandler> getAssociatedType() {
		return getType();
	}

	public String getMicrophoneName() {
		return microphoneName;
	}

}