package org.simbiosis.systemui.client.subbranch;

import java.util.ArrayList;
import java.util.List;

import org.kembang.grid.client.GridSelectionHandler;
import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormWidget;
import org.kembang.module.shared.SimpleBranchDv;
import org.simbiosis.systemui.client.editor.SubBranchTable;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SubBranchList extends FormWidget implements ISubBranch {

	Activity activity;
	AppStatus appStatus;

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, SubBranchList> {
	}

	List<SimpleBranchDv> branchList = new ArrayList<SimpleBranchDv>();
	List<SubBranchDv> subBranchList = new ArrayList<SubBranchDv>();

	SubBranchViewer subBranchViewer = new SubBranchViewer();
	SubBranchEditor subBranchEditor;
	Boolean isEditor = false;

	@UiField
	SubBranchTable subBranches;
	@UiField
	HorizontalPanel subBranchForm;

	public SubBranchList() {
		initWidget(uiBinder.createAndBindUi(this));
		//
		setHasNew(true);
		setHasEdit(true);
		setHasSave(false);
		setHasReload(true);
		setHasBack(false);
		//
		subBranches.setSelectionHandler(new GridSelectionHandler() {

			@Override
			public void onSelection() {
				onSelectionHandler();
			}
		});
		//
		subBranchForm.clear();
		subBranchForm.add(subBranchViewer);
	}

	@Override
	public void setActivity(Activity activity, AppStatus appStatus) {
		this.activity = activity;
		this.appStatus = appStatus;
		setFormActivity(activity);
		setAppStatus(appStatus);
	}

	@Override
	public FormWidget getFormWidget() {
		return this;
	}

	@Override
	public void setBranches(List<SimpleBranchDv> branches) {
		branchList.clear();
		branchList.addAll(branches);
		subBranchEditor = new SubBranchEditor(branches);
	}

	@Override
	public void setSubBranch(List<SubBranchDv> branches) {
		subBranchList.clear();
		subBranchList.addAll(branches);
		subBranches.clear();
		for (SubBranchDv subBranch : subBranchList) {
			subBranches.addRow(subBranch);
		}
	}

	void onSelectionHandler() {
		setViewerData(subBranches.getSelectedData());
	}

	private void setViewerData(SubBranchDv user) {
		subBranchForm.clear();
		subBranchForm.add(subBranchViewer);
		subBranchViewer.setUser(user);
		showBack(false);
		showSave(false);
		isEditor = false;
	}

	private void setEditorData(SubBranchDv user) {
		subBranchForm.clear();
		subBranchForm.add(subBranchEditor);
		subBranchEditor.setSubBranch(user);
		showBack(true);
		showSave(true);
		isEditor = true;
	}

	@Override
	public void editSelected() {
		setEditorData(subBranches.getSelectedData());
	}

	@Override
	public void newSubBranch() {
		setEditorData(new SubBranchDv());
	}

	@Override
	public void viewSelected() {
		setViewerData(subBranches.getSelectedData());
	}

	@Override
	public void clearViewer() {
		setViewerData(new SubBranchDv());
	}

	@Override
	public SubBranchDv getSubBranch() {
		if (isEditor) {
			return subBranchEditor.getSubBranch();
		} else {
			return subBranches.getSelectedData();
		}
	}

}
