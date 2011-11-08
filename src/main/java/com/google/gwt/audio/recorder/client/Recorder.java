package com.google.gwt.audio.recorder.client;

import com.google.gwt.audio.recorder.client.event.MicrophoneConnectedEvent;
import com.google.gwt.audio.recorder.client.event.MicrophoneNotConnectedEvent;
import com.google.gwt.audio.recorder.client.event.MicrophoneUserRequestEvent;
import com.google.gwt.audio.recorder.client.event.NoMicrophoneFoundEvent;
import com.google.gwt.audio.recorder.client.event.PlaybackStartedEvent;
import com.google.gwt.audio.recorder.client.event.PlaybackStoppedEvent;
import com.google.gwt.audio.recorder.client.event.PlayingEvent;
import com.google.gwt.audio.recorder.client.event.RecorderReadyEvent;
import com.google.gwt.audio.recorder.client.event.RecordingEvent;
import com.google.gwt.audio.recorder.client.event.RecordingStoppedEvent;
import com.google.gwt.audio.recorder.client.event.SaveFailedEvent;
import com.google.gwt.audio.recorder.client.event.SavePressedEvent;
import com.google.gwt.audio.recorder.client.event.SaveProgressEvent;
import com.google.gwt.audio.recorder.client.event.SavedEvent;
import com.google.gwt.audio.recorder.client.event.SavingEventEvent;
import com.google.gwt.audio.recorder.client.util.EventDispatcher;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;

public class Recorder extends EventDispatcher {

	private final static int DEFAULT_WIDTH = 24;
	private final static int DEFAULT_HEIGHT = 24;
	private final static String DEFAULT_APPLICATION_NAME = "RECORDER_APPLICATION";
	private final static String SWF_OBJECT = GWT.getModuleBaseURL() + "recorder.swf";

	private JavaScriptObject flashRecorder;
	private int recorderOriginalWidth = 0;
	private int recorderOriginalHeight = 0;
	private String uploadFormId = null;
	private String uploadFieldName = null;

	public Recorder(String containerId, ImageResource uploadImage) {
		this(containerId, uploadImage.getSafeUri().asString());
	}

	public Recorder(String containerId, String uploadImage) {
		this.loadFlashRecorder(DEFAULT_WIDTH, DEFAULT_HEIGHT, uploadImage, DEFAULT_APPLICATION_NAME, SWF_OBJECT,
				containerId);
	}

	private native void loadFlashRecorder(int width, int height, String uploadImage, String applicationName,
			String swfObject, String containerId) /*-{
		var instance = this;
		// Event management
		$wnd.microphone_recorder_events = function() {
			switch (arguments[0]) {
			case "ready":
				console.log("ready");
				var width = parseInt(arguments[1]);
				var height = parseInt(arguments[2]);
				instance.@com.google.gwt.audio.recorder.client.Recorder::onRecorderReady(II)(width, height);
				instance.@com.google.gwt.audio.recorder.client.Recorder::uploadFormId = "#recorderUploadForm";
				instance.@com.google.gwt.audio.recorder.client.Recorder::uploadFieldName = "recorderUploadFile[filename]";
				instance.@com.google.gwt.audio.recorder.client.Recorder::connect(Ljava/lang/String;I)(applicationName, 0);
				instance.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalWidth = width;
				instance.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalHeight = height;
				break;

			case "no_microphone_found":
				console.log("no_microphone_found");
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::onNoMicrophoneFound();
				break;

			case "microphone_user_request":
				console.log("microphone_user_request");
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::showPermissionWindow()
						();
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::onMicrophoneUserRequest();
				break;

			case "microphone_connected":
				console.log("microphone_connected");
				var mic = arguments[1];
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::defaultSize();
				instance.@com.google.gwt.audio.recorder.client.Recorder::onMicrophoneConnected(Ljava/lang/String;)(mic.name);
				break;

			case "microphone_not_connected":
				console.log("microphone_not_connected");
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::defaultSize();
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::onMicrophoneNotConnected();
				break;

			case "recording":
				console.log("recording");
				var name = arguments[1];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onRecording(Ljava/lang/String;)(name);
				instance.@com.google.gwt.audio.recorder.client.Recorder::hide();
				break;

			case "recording_stopped":
				console.log("recording_stopped");
				var name = arguments[1];
				var duration = arguments[2];
				instance.@com.google.gwt.audio.recorder.client.Recorder::show();
				instance.@com.google.gwt.audio.recorder.client.Recorder::onRecordingStop(Ljava/lang/String;I)(name, duration);
				break;

			case "playing":
				console.log("playing");
				var name = arguments[1];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onPlaying(Ljava/lang/String;)(name);
				break;

			case "playback_started":
				console.log("playback_started");
				var name = arguments[1];
				var latency = arguments[2];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onPlaybackStarted(Ljava/lang/String;I)(name, latency);
				break;

			case "stopped":
				console.log("stopped");
				var name = arguments[1];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onPlaybackStopped(Ljava/lang/String;)(name);
				break;

			case "save_pressed":
				console.log("save_pressed");
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::updateForm();
				instance
						.@com.google.gwt.audio.recorder.client.Recorder::onSavePressed();
				break;

			case "saving":
				console.log("saving");
				var name = arguments[1];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onSaving(Ljava/lang/String;)(name);
				break;

			case "saved":
				console.log("saved");
				var name = arguments[1];
				//				var data = $.parseJSON(arguments[2]);
				//				if (data.saved) {
				instance.@com.google.gwt.audio.recorder.client.Recorder::onSaved(Ljava/lang/String;)(name);
				break;

			case "save_failed":
				console.log("save_failed");
				var name = arguments[1];
				var errorMessage = arguments[2];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onSaveFailed(Ljava/lang/String;Ljava/lang/String;)(name, errorMessage);
				break;

			case "save_progress":
				console.log("save_progress");
				var name = arguments[1];
				var bytesLoaded = arguments[2];
				var bytesTotal = arguments[3];
				instance.@com.google.gwt.audio.recorder.client.Recorder::onSaveProgress(II)(bytesLoaded, bytesTotal);
				break;
			}
		}

		console.log("Load recorder.swf");
		var appWidth = width;
		var appHeight = height;
		var flashvars = {
			'event_handler' : 'microphone_recorder_events',
			'upload_image' : uploadImage
		};
		var params = {};
		var attributes = {
			'id' : applicationName,
			'name' : applicationName
		};
		$wnd.swfobject.embedSWF(swfObject, containerId, appWidth, appHeight,
				"10.1.0", "", flashvars, params, attributes);
		console.log("recorder.swf embedded");

	}-*/;

	private void _connect(String applicationName, Integer attempts) {
		this.flashRecorder = DOM.getElementById(applicationName);
	}

	private native void connect(String applicationName, int attempts) /*-{
		var instance = this;
		if (navigator.appName.indexOf("Microsoft") != -1) {
			this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder = $wnd.window[applicationName];
		} else {
			this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder = $wnd.document[applicationName];
		}
		if (attempts >= 40) {
			return;
		}

		// flash app needs time to load and initialize
		console
				.log(this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder);
		if (this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				&& this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder.init) {
			this.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalWidth = this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder.width;
			this.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalHeight = this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder.height;
			if (this.@com.google.gwt.audio.recorder.client.Recorder::uploadFormId) {
				var frm = document[this.@com.google.gwt.audio.recorder.client.Recorder::uploadFormId];
				this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
						.init(
								frm.attr('action').toString(),
								this.@com.google.gwt.audio.recorder.client.Recorder::uploadFieldName,
								frm.serializeArray());
			}
			return;
		}
		setTimeout(
				function() {
					instance.@com.google.gwt.audio.recorder.client.Recorder::connect(Ljava/lang/String;I)(applicationName, attempts + 1);
				}, 100);
	}-*/;

	public native void playBack(String name) /*-{
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				.playBack(name);
	}-*/;

	public native void record(String name, String filename) /*-{
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				.record(name, filename);
	}-*/;

	public native void resize(int width, int height) /*-{
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder.width = width
				+ "px";
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder.height = height
				+ "px";
	}-*/;

	public native void defaultSize() /*-{
		this
				.@com.google.gwt.audio.recorder.client.Recorder::resize(II)
				(
						this.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalWidth,
						this.@com.google.gwt.audio.recorder.client.Recorder::recorderOriginalHeight);
	}-*/;

	public native void show() /*-{
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				.show();
	}-*/;

	public native void hide() /*-{
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				.hide();
	}-*/;

	public native void updateForm() /*-{
		var frm = document[this.@com.google.gwt.audio.recorder.client.Recorder::uploadFormId];
		this.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
				.update(frm.serializeArray());
	}-*/;

	public native void showPermissionWindow() /*-{
		var instance = this;
		this.@com.google.gwt.audio.recorder.client.Recorder::resize(II)(240, 160);
		// need to wait until app is resized before displaying permissions screen
		setTimeout(
				function() {
					instance.@com.google.gwt.audio.recorder.client.Recorder::flashRecorder
							.permit();
				}, 1);
	}-*/;

	private void onRecorderReady(int width, int height) {
		this.fireEvent(new RecorderReadyEvent(width, height));
	}

	private void onNoMicrophoneFound() {
		this.fireEvent(new NoMicrophoneFoundEvent());
	}

	private void onMicrophoneUserRequest() {
		this.fireEvent(new MicrophoneUserRequestEvent());
	}

	private void onMicrophoneConnected(String name) {
		this.fireEvent(new MicrophoneConnectedEvent(name));
	}

	private void onMicrophoneNotConnected() {
		this.fireEvent(new MicrophoneNotConnectedEvent());
	}

	private void onRecording(String name) {
		this.fireEvent(new RecordingEvent(name));
	}

	private void onRecordingStop(String name, int duration) {
		this.fireEvent(new RecordingStoppedEvent(name, duration));
	}

	private void onPlaying(String name) {
		this.fireEvent(new PlayingEvent(name));
	}

	private void onPlaybackStopped(String name) {
		this.fireEvent(new PlaybackStoppedEvent(name));
	}

	private void onPlaybackStarted(String name, int latency) {
		this.fireEvent(new PlaybackStartedEvent(name, latency));
	}

	private void onSavePressed() {
		this.fireEvent(new SavePressedEvent());
	}

	private void onSaved(String name) {
		this.fireEvent(new SavedEvent(name));
	}

	private void onSaveFailed(String name, String error) {
		this.fireEvent(new SaveFailedEvent(name, error));
	}

	private void onSaveProgress(int byteLoaded, int bytesTotal) {
		this.fireEvent(new SaveProgressEvent(byteLoaded, bytesTotal));
	}

	private void onSaving(String name) {
		this.fireEvent(new SavingEventEvent(name));
	}
}
