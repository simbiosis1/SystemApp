package org.simbiosis.systemui.client.subbranch;

import java.util.List;

import org.kembang.module.client.mvp.AppStatus;
import org.kembang.module.client.mvp.FormActivity;
import org.kembang.module.client.mvp.FormWidget;
import org.kembang.module.shared.SimpleBranchDv;
import org.simbiosis.systemui.shared.SubBranchDv;

public interface ISubBranch {
	void setActivity(Activity activity, AppStatus appStatus);

	FormWidget getFormWidget();

	void setBranches(List<SimpleBranchDv> branches);

	void setSubBranch(List<SubBranchDv> branchs);

	void editSelected();

	void newSubBranch();

	void viewSelected();

	void clearViewer();

	SubBranchDv getSubBranch();

	public abstract class Activity extends FormActivity {
	}

}
