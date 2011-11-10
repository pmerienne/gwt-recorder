package com.google.gwt.audio.recorder.client.widget;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

public class IconButton extends Button {

	private ImageResource upImage;

	private ImageResource disabledImage;

	@UiConstructor
	public IconButton(ImageResource upImage, ImageResource disabledImage) {
		super();
		this.upImage = upImage;
		this.disabledImage = disabledImage;
		this.refreshIcon();
	}

	private void setImage(ImageResource image) {
		Image img = new Image(image);
		while (this.getElement().getFirstChild() != null) {
			this.getElement().removeChild(this.getElement().getFirstChild());
		}
		this.getElement().appendChild(img.getElement());
	}

	public ImageResource getUpImage() {
		return upImage;
	}

	public void setUpImage(ImageResource upImage) {
		this.upImage = upImage;
		this.refreshIcon();
	}

	public ImageResource getDisabledImage() {
		return disabledImage;
	}

	public void setDisabledImage(ImageResource disabledImage) {
		this.disabledImage = disabledImage;
		this.refreshIcon();
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (enabled != this.isEnabled()) {
			super.setEnabled(enabled);
			this.refreshIcon();
		}
	}

	private void refreshIcon() {
		if (this.isEnabled() && upImage != null) {
			this.setImage(upImage);
		} else if (disabledImage != null) {
			this.setImage(disabledImage);
		}
	}
}
