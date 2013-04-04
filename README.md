# GWT audio recorder library.

Use flash to record audio data from a microphone. Converts the audio data to a WAV file. Uploads the WAV file to the server.
The WAV file is Posted as a multipart form-data request. Additional fields can be added to the request.
The save button must be clicked inside the flash application, see Upload and download require user interaction for more information.

## Demo
See http://audio-recorder-showcase.cloudfoundry.com/

## Maven integration : 

<repository>
	<id>gwt-recorder</id>
	<name>gwt-recorder repo</name>
	<url>http://github.com/pmerienne/gwt-recorder/raw/master/releases</url>
</repository>

<dependency>
	<groupId>com.google.gwt</groupId>
	<artifactId>audio-recorder</artifactId>
	<version>0.0.2</version>
</dependency>

## Usage 

// Create simple UI
RecorderControl recorderControl = new RecorderControl();
RootPanel.get().add(recorderControl);
// Create and configure recorder
Recorder recorder = new Recorder("images/upload.png");
recorder.setUploadURL(GWT.getModuleBaseURL() + "file");
recorderControl.setRecorder(recorder);
