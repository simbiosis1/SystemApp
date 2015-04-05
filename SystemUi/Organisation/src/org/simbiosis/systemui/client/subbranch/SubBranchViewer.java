package org.simbiosis.systemui.client.subbranch;

import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SubBranchViewer extends Composite implements Editor<SubBranchDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, SubBranchViewer> {
	}

	interface Driver extends
			SimpleBeanEditorDriver<SubBranchDv, SubBranchViewer> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	Label code;
	@UiField
	Label name;
	@UiField
	Label address;
	@UiField
	Label strBranch;

	public SubBranchViewer() {
		initWidget(uiBinder.createAndBindUi(this));
		//
		driver.initialize(this);
	}

	public void setUser(SubBranchDv user) {
		driver.edit(user);
	}
}
