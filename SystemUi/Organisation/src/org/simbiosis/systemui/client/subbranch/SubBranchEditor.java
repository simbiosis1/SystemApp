package org.simbiosis.systemui.client.subbranch;

import java.util.List;

import org.kembang.module.client.editor.BranchListBox;
import org.kembang.module.shared.SimpleBranchDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SubBranchEditor extends Composite implements Editor<SubBranchDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, SubBranchEditor> {
	}

	interface Driver extends
			SimpleBeanEditorDriver<SubBranchDv, SubBranchEditor> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	TextBox code;
	@UiField
	TextBox address;
	@UiField
	TextBox name;
	@UiField
	BranchListBox branch;

	public SubBranchEditor(List<SimpleBranchDv> branches) {
		initWidget(uiBinder.createAndBindUi(this));
		branch.setList(branches);
		//
		driver.initialize(this);
	}

	public void setSubBranch(SubBranchDv subBranch) {
		driver.edit(subBranch);
	}

	public SubBranchDv getSubBranch() {
		return driver.flush();
	}
}
