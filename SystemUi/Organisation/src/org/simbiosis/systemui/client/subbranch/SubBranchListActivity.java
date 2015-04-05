package org.simbiosis.systemui.client.subbranch;

import java.util.List;

import org.kembang.module.client.mvp.FormActivityType;
import org.kembang.module.client.rpc.SystemService;
import org.kembang.module.client.rpc.SystemServiceAsync;
import org.kembang.module.shared.SimpleBranchDv;
import org.simbiosis.systemui.client.AppFactory;
import org.simbiosis.systemui.client.rpc.AppService;
import org.simbiosis.systemui.client.rpc.AppServiceAsync;
import org.simbiosis.systemui.client.subbranch.ISubBranch.Activity;
import org.simbiosis.systemui.shared.SubBranchDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

public class SubBranchListActivity extends Activity {

	private final AppServiceAsync appService = GWT.create(AppService.class);
	private final SystemServiceAsync systemSrv = GWT.create(SystemService.class);

	Place myPlace;
	AppFactory appFactory;

	public SubBranchListActivity(Place myPlace, AppFactory appFactory) {
		setMainFactory(appFactory);
		this.myPlace = myPlace;
		this.appFactory = appFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		ISubBranch myForm = appFactory.getSubBranch();
		myForm.setActivity(this, appFactory.getAppStatus());
		if (appFactory.getAppStatus().isLogin()) {
			loadCommonData();
		}
		appFactory.showApplication(panel, myForm.getFormWidget());
	}

	@Override
	public void dispatch(FormActivityType formActivityType) {
		switch (formActivityType) {
		case FA_NEW:
			onNew();
			break;
		case FA_EDIT:
			onEdit();
			break;
		case FA_SAVE:
			onSave();
			break;
		case FA_RELOAD:
			onReload();
			break;
		case FA_BACK:
			onBack();
			break;
		default:
			break;
		}
	}

	private void onNew() {
		ISubBranch myForm = appFactory.getSubBranch();
		myForm.newSubBranch();
	}

	private void onReload() {
		loadCommonData();
		ISubBranch myForm = appFactory.getSubBranch();
		myForm.clearViewer();
	}

	private void onSave() {
		ISubBranch myForm = appFactory.getSubBranch();
		SubBranchDv dv = myForm.getSubBranch();
		showLoading();
		appService.saveSubBranch(getKey(), dv, new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				hideLoading();
				Window.alert("Data sudah disimpan");
				onReload();
			}

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : saveBranch");
			}
		});

	}

	private void onBack() {
		ISubBranch myForm = appFactory.getSubBranch();
		myForm.viewSelected();
	}

	private void onEdit() {
		ISubBranch myForm = appFactory.getSubBranch();
		myForm.editSelected();
	}

	private void loadCommonData() {
		showLoading();
		systemSrv.listSimpleBranch(getKey(), new AsyncCallback<List<SimpleBranchDv>>() {

			@Override
			public void onSuccess(List<SimpleBranchDv> result) {
				ISubBranch myForm = appFactory.getSubBranch();
				myForm.setBranches(result);
				loadSubBranch();
			}

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : listBranch");
			}
		});
	}

	private void loadSubBranch() {
		appService.listSubBranch(getKey(),
				new AsyncCallback<List<SubBranchDv>>() {

					@Override
					public void onFailure(Throwable caught) {
						hideLoading();
						Window.alert("Error : listSubBranch");
					}

					@Override
					public void onSuccess(List<SubBranchDv> result) {
						ISubBranch myForm = appFactory.getSubBranch();
						myForm.setSubBranch(result);
						hideLoading();
					}
				});
	}

}
