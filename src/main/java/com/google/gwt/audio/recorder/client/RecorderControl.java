package com.google.gwt.audio.recorder.client;

import com.google.gwt.audio.recorder.client.event.MicrophoneConnectedEvent;
import com.google.gwt.audio.recorder.client.event.MicrophoneConnectedHandler;
import com.google.gwt.audio.recorder.client.event.MicrophoneNotConnectedEvent;
import com.google.gwt.audio.recorder.client.event.MicrophoneNotConnectedHandler;
import com.google.gwt.audio.recorder.client.event.MicrophoneUserRequestEvent;
import com.google.gwt.audio.recorder.client.event.MicrophoneUserRequestHandler;
import com.google.gwt.audio.recorder.client.event.NoMicrophoneFoundEvent;
import com.google.gwt.audio.recorder.client.event.NoMicrophoneFoundHandler;
import com.google.gwt.audio.recorder.client.event.PlaybackStartedEvent;
import com.google.gwt.audio.recorder.client.event.PlaybackStartedHandler;
import com.google.gwt.audio.recorder.client.event.PlaybackStoppedEvent;
import com.google.gwt.audio.recorder.client.event.PlaybackStoppedHandler;
import com.google.gwt.audio.recorder.client.event.PlayingEvent;
import com.google.gwt.audio.recorder.client.event.PlayingHandler;
import com.google.gwt.audio.recorder.client.event.RecorderReadyEvent;
import com.google.gwt.audio.recorder.client.event.RecorderReadyHandler;
import com.google.gwt.audio.recorder.client.event.RecordingEvent;
import com.google.gwt.audio.recorder.client.event.RecordingHandler;
import com.google.gwt.audio.recorder.client.event.RecordingStoppedEvent;
import com.google.gwt.audio.recorder.client.event.RecordingStoppedHandler;
import com.google.gwt.audio.recorder.client.event.SaveFailedEvent;
import com.google.gwt.audio.recorder.client.event.SaveFailedHandler;
import com.google.gwt.audio.recorder.client.event.SavePressedEvent;
import com.google.gwt.audio.recorder.client.event.SavePressedHandler;
import com.google.gwt.audio.recorder.client.event.SaveProgressEvent;
import com.google.gwt.audio.recorder.client.event.SaveProgressHandler;
import com.google.gwt.audio.recorder.client.event.SavedEvent;
import com.google.gwt.audio.recorder.client.event.SavedHandler;
import com.google.gwt.audio.recorder.client.event.SavingEvent;
import com.google.gwt.audio.recorder.client.event.SavingHandler;
import com.google.gwt.audio.recorder.client.resource.ResourceBundle;
import com.google.gwt.audio.recorder.client.widget.IconButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RecorderControl extends Composite implements MicrophoneConnectedHandler, MicrophoneNotConnectedHandler,
		MicrophoneUserRequestHandler, NoMicrophoneFoundHandler, PlaybackStoppedHandler, PlaybackStartedHandler,
		PlayingHandler, RecorderReadyHandler, RecordingHandler, RecordingStoppedHandler, SavedHandler,
		SavePressedHandler, SaveFailedHandler, SaveProgressHandler, SavingHandler {

	private static RecorderControlUiBinder uiBinder = GWT.create(RecorderControlUiBinder.class);

	interface RecorderControlUiBinder extends UiBinder<Widget, RecorderControl> {
	}

	private ImageResource playIcon = ResourceBundle.INSTANCE.play();
	private ImageResource playDisabledIcon = ResourceBundle.INSTANCE.playDisabled();
	private ImageResource stopIcon = ResourceBundle.INSTANCE.stop();
	private ImageResource stopDisabledIcon = ResourceBundle.INSTANCE.stopDisabled();
	private ImageResource recordIcon = ResourceBundle.INSTANCE.record();
	private ImageResource recordDisabledIcon = ResourceBundle.INSTANCE.recordDisabled();
	private ImageResource stopRecordIcon = ResourceBundle.INSTANCE.stopRecord();

	@UiField
	IconButton playbackButton;

	@UiField
	IconButton recordButton;

	@UiField
	Label status;

	@UiField
	HTMLPanel uploadContainer;

	private Recorder recorder;

	public RecorderControl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("playbackButton")
	protected void onPlaybackClicked(ClickEvent event) {
		if (this.recorder != null) {
			this.recorder.play();
		}
	}

	@UiHandler("recordButton")
	protected void onRecordClicked(ClickEvent event) {
		if (this.recorder != null) {
			this.recorder.record();
		}
	}

	public Recorder getRecorder() {
		return recorder;
	}

	public void setRecorder(Recorder recorder) {
		if (this.recorder != null) {
			this.uploadContainer.remove(this.recorder);
		}

		this.recorder = recorder;
		this.uploadContainer.add(this.recorder);

		// Handlers
		this.recorder.addHandler(MicrophoneConnectedEvent.getType(), this);
		this.recorder.addHandler(MicrophoneNotConnectedEvent.getType(), this);
		this.recorder.addHandler(MicrophoneUserRequestEvent.getType(), this);
		this.recorder.addHandler(NoMicrophoneFoundEvent.getType(), this);
		this.recorder.addHandler(PlaybackStoppedEvent.getType(), this);
		this.recorder.addHandler(PlaybackStartedEvent.getType(), this);
		this.recorder.addHandler(PlayingEvent.getType(), this);
		this.recorder.addHandler(RecorderReadyEvent.getType(), this);
		this.recorder.addHandler(RecordingEvent.getType(), this);
		this.recorder.addHandler(RecordingStoppedEvent.getType(), this);
		this.recorder.addHandler(SavedEvent.getType(), this);
		this.recorder.addHandler(SaveFailedEvent.getType(), this);
		this.recorder.addHandler(SaveProgressEvent.getType(), this);
		this.recorder.addHandler(SavingEvent.getType(), this);
	}

	@Override
	public void onRecorderReady(RecorderReadyEvent event) {
		recordButton.setEnabled(true);
		status.setText("Ready");
	}

	@Override
	public void onMicrophoneConnected(MicrophoneConnectedEvent event) {
		status.setText("Microphone : " + event.getMicrophoneName() + " found");
	}

	@Override
	public void onRecording(RecordingEvent event) {
		status.setText("Recording");
		playbackButton.setEnabled(false);
		recordButton.setUpImage(stopRecordIcon);
		recordButton.setDisabledImage(stopDisabledIcon);
	}

	@Override
	public void onRecordingStopped(RecordingStoppedEvent event) {
		status.setText("Recorded " + event.getName() + ", duration : " + event.getDuration() + " seconds");
		recordButton.setUpImage(recordIcon);
		recordButton.setDisabledImage(recordDisabledIcon);
		playbackButton.setUpImage(playIcon);
		playbackButton.setDisabledImage(playDisabledIcon);
		playbackButton.setEnabled(true);
	}

	@Override
	public void onPlaying(PlayingEvent event) {
		status.setText("Playing " + event.getName());
		recordButton.setUpImage(recordIcon);
		recordButton.setDisabledImage(recordDisabledIcon);
		playbackButton.setUpImage(stopIcon);
		playbackButton.setDisabledImage(stopDisabledIcon);
	}

	@Override
	public void onPlaybackStopped(PlaybackStoppedEvent event) {
		status.setText("Stop playing " + event.getName());
		recordButton.setUpImage(recordIcon);
		recordButton.setDisabledImage(recordDisabledIcon);
		playbackButton.setUpImage(playIcon);
		playbackButton.setDisabledImage(playDisabledIcon);
	}

	@Override
	public void onSaved(SavedEvent event) {
		status.setText(event.getName() + " saved");
	}

	@Override
	public void onSaveFailed(SaveFailedEvent event) {
		status.setText("Save fail for " + event.getName() + " : " + event.getError());
	}

	@Override
	public void onSaveProgress(SaveProgressEvent event) {
		status.setText(event.getByteLoaded() + "/" + event.getBytesTotal());
	}

	@Override
	public void onSaving(SavingEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPlaybackStarted(PlaybackStartedEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onNoMicrophoneFound(NoMicrophoneFoundEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMicrophoneUserRequest(MicrophoneUserRequestEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMicrophoneNotConnected(MicrophoneNotConnectedEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSavePressed(SavePressedEvent event) {
		// TODO Auto-generated method stub
	}

	public IconButton getPlaybackButton() {
		return playbackButton;
	}

	public IconButton getRecordButton() {
		return recordButton;
	}
}
