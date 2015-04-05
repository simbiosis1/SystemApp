package org.simbiosis.systemui.client.branch;

import java.util.List;

import org.kembang.module.client.mvp.FormActivityType;
import org.simbiosis.systemui.client.AppFactory;
import org.simbiosis.systemui.client.branch.IBranch.Activity;
import org.simbiosis.systemui.client.rpc.AppService;
import org.simbiosis.systemui.client.rpc.AppServiceAsync;
import org.simbiosis.systemui.shared.BranchDv;
import org.simbiosis.systemui.shared.CoaDv;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

public class BranchListActivity extends Activity {

	private final AppServiceAsync appService = GWT.create(AppService.class);

	Place myPlace;
	AppFactory appFactory;

	public BranchListActivity(Place myPlace, AppFactory appFactory) {
		setMainFactory(appFactory);
		this.myPlace = myPlace;
		this.appFactory = appFactory;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		IBranch myForm = appFactory.getBranch();
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
		IBranch myForm = appFactory.getBranch();
		myForm.newBranch();
	}

	private void onReload() {
		loadBranch();
		IBranch myForm = appFactory.getBranch();
		myForm.clearViewer();
	}

	private void onSave() {

		IBranch myForm = appFactory.getBranch();
		BranchDv dv = myForm.getBranch();
		showLoading();
		appService.saveBranch(getKey(), dv, new AsyncCallback<Void>() {

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
		IBranch myForm = appFactory.getBranch();
		myForm.viewSelected();
	}

	private void onEdit() {
		IBranch myForm = appFactory.getBranch();
		myForm.editSelected();
	}

	private void loadCommonData() {
		showLoading();
		appService.listCoaForTransaction(getKey(),
				new AsyncCallback<List<CoaDv>>() {

					@Override
					public void onSuccess(List<CoaDv> result) {
						IBranch myForm = appFactory.getBranch();
						myForm.setCoa(result);
						loadBranch();
					}

					@Override
					public void onFailure(Throwable caught) {
						hideLoading();
						Window.alert("Error : listCoa1");
					}
				});
	}

	private void loadBranch() {
		appService.listBranch(getKey(), new AsyncCallback<List<BranchDv>>() {

			@Override
			public void onFailure(Throwable caught) {
				hideLoading();
				Window.alert("Error : listBranch");
			}

			@Override
			public void onSuccess(List<BranchDv> result) {
				IBranch myForm = appFactory.getBranch();
				myForm.setBranch(result);
				hideLoading();
			}
		});
	}
}
