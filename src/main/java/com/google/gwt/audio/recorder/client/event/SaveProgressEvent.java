package com.google.gwt.audio.recorder.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SaveProgressEvent extends GwtEvent<SaveProgressHandler> {

	private static Type<SaveProgressHandler> TYPE;

	public static Type<SaveProgressHandler> getType() {
		return TYPE != null ? TYPE : (TYPE = new Type<SaveProgressHandler>());
	}

	private int byteLoaded;
	private int bytesTotal;

	public SaveProgressEvent(int byteLoaded, int bytesTotal) {
		this.byteLoaded = byteLoaded;
		this.bytesTotal = bytesTotal;
	}

	@Override
	protected void dispatch(SaveProgressHandler handler) {
		handler.onSaveProgress(this);
	}

	@Override
	public GwtEvent.Type<SaveProgressHandler> getAssociatedType() {
		return getType();
	}

	public int getByteLoaded() {
		return byteLoaded;
	}

	public int getBytesTotal() {
		return bytesTotal;
	}

}