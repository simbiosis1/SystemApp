package org.simbiosis.systemui.client.user;

import java.util.List;

import org.kembang.module.client.editor.BranchListBox;
import org.kembang.module.shared.SimpleBranchDv;
import org.kembang.module.shared.UserDv;
import org.simbiosis.systemui.client.editor.LevelTypeEditor;
import org.simbiosis.systemui.client.editor.RoleListBox;
import org.simbiosis.systemui.client.editor.SubBranchListBox;
import org.simbiosis.systemui.shared.RoleDv;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UserEditor extends Composite implements Editor<UserDv> {

	private static UserViewerUiBinder uiBinder = GWT
			.create(UserViewerUiBinder.class);

	interface UserViewerUiBinder extends UiBinder<Widget, UserEditor> {
	}

	interface Driver extends SimpleBeanEditorDriver<UserDv, UserEditor> {
	}

	private Driver driver = GWT.create(Driver.class);

	@UiField
	TextBox name;
	@UiField
	TextBox realName;
	@UiField
	TextBox email;
	@UiField
	RoleListBox role;
	@UiField
	BranchListBox branch;
	@UiField
	SubBranchListBox subBranch;
	@UiField
	LevelTypeEditor level;
	@UiField
	CheckBox active;
	@UiField
	CheckBox changePassword;
	@UiField
	PasswordTextBox password;
	@UiField
	PasswordTextBox confirmPassword;

	public UserEditor(List<SimpleBranchDv> branchList,
			List<SubBranchDv> subBranchList, List<RoleDv> roleList) {
		initWidget(uiBinder.createAndBindUi(this));
		//
		role.setList(roleList);
		branch.setList(branchList);
		subBranch.setList(subBranchList);
		//
		driver.initialize(this);
		driver.edit(new UserDv());
		initChangePassword();
	}

	public void setUser(UserDv user) {
		driver.edit(user);
		initChangePassword();
	}

	public UserDv getUser() {
		return driver.flush();
	}

	@UiHandler("changePassword")
	public void onChangePasswordClick(ClickEvent event) {
		initChangePassword();
	}

	private void initChangePassword() {
		if (changePassword.getValue()) {
			password.setEnabled(true);
			confirmPassword.setEnabled(true);
		} else {
			password.setEnabled(false);
			confirmPassword.setEnabled(false);
		}
	}
}
