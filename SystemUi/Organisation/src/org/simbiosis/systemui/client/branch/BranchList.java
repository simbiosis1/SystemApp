package org.simbiosis.systemui.client.branch;

import java.util.ArrayList;
import java.util.List;

import org.kembang.grid.client.GridSelectionHandler;
import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormWidget;
import org.simbiosis.systemui.client.editor.BranchTable;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class BranchList extends FormWidget implements IBranch {

	Activity activity;

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, BranchList> {
	}

	List<CoaDv> listCoa = new ArrayList<CoaDv>();
	List<BranchDv> listBranch = new ArrayList<BranchDv>();

	BranchViewer userViewer = new BranchViewer();
	BranchEditor userEditor;
	Boolean isEditor = false;

	@UiField
	BranchTable users;
	@UiField
	HorizontalPanel userForm;

	public BranchList() {
		initWidget(uiBinder.createAndBindUi(this));
		//
		setHasNew(true);
		setHasEdit(true);
		setHasSave(false);
		setHasReload(true);
		setHasBack(false);
		//
		users.setSelectionHandler(new GridSelectionHandler() {

			@Override
			public void onSelection() {
				onSelectionHandler();
			}
		});
	}

	@Override
	public void setActivity(Activity activity, AppStatus appStatus) {
		this.activity = activity;
		setFormActivity(activity);
		setAppStatus(appStatus);
	}

	@Override
	public FormWidget getFormWidget() {
		return this;
	}

	@Override
	public void setCoa(List<CoaDv> coas) {
		listCoa.clear();
		listCoa.addAll(coas);
		userEditor = new BranchEditor(listCoa);
	}

	@Override
	public void setBranch(List<BranchDv> branchs) {
		listBranch.clear();
		listBranch.addAll(branchs);
		this.users.clear();
		for (BranchDv user : branchs) {
			this.users.addRow(user);
		}
	}

	void onSelectionHandler() {
		setViewerData(users.getSelectedData());
	}

	private void setViewerData(BranchDv user) {
		userForm.clear();
		userForm.add(userViewer);
		userViewer.setUser(user);
		showBack(false);
		showSave(false);
		isEditor = false;
	}

	private void setEditorData(BranchDv user) {
		userForm.clear();
		userForm.add(userEditor);
		userEditor.setUser(user);
		showBack(true);
		showSave(true);
		isEditor = true;
	}

	@Override
	public void editSelected() {
		setEditorData(users.getSelectedData());
	}

	@Override
	public void newBranch() {
		setEditorData(new BranchDv());
	}

	@Override
	public void viewSelected() {
		setViewerData(users.getSelectedData());
	}

	@Override
	public void clearViewer() {
		setViewerData(new BranchDv());
	}

	@Override
	public BranchDv getBranch() {
		if (isEditor) {
			return userEditor.getBranch();
		} else {
			return users.getSelectedData();
		}
	}

}
