package com.google.gwt.audio.recorder.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ResourceBundle extends ClientBundle {

	public static ResourceBundle INSTANCE = GWT.create(ResourceBundle.class);

	@Source("play.png")
	ImageResource play();

	@Source("play-disabled.png")
	ImageResource playDisabled();

	@Source("stop.png")
	ImageResource stop();

	@Source("stop-disabled.png")
	ImageResource stopDisabled();

	@Source("stop-record.png")
	ImageResource stopRecord();

	@Source("record.png")
	ImageResource record();

	@Source("record-disabled.png")
	ImageResource recordDisabled();

}
