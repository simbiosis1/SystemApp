package org.simbiosis.systemui.client.branch;

import org.simbiosis.systemui.shared.BranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class BranchViewer extends Composite implements Editor<BranchDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, BranchViewer> {
	}

	interface Driver extends SimpleBeanEditorDriver<BranchDv, BranchViewer> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	Label code;
	@UiField
	Label name;
	@UiField
	Label address;
	@UiField
	Label strCoa1;
	@UiField
	Label strCoa2;

	public BranchViewer() {
		initWidget(uiBinder.createAndBindUi(this));
		//
		driver.initialize(this);
	}

	public void setUser(BranchDv user) {
		driver.edit(user);
	}
}
