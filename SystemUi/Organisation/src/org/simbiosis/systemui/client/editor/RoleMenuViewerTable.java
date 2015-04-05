package org.simbiosis.systemui.client.editor;

import org.kembang.grid.client.AdvancedGrid;
import org.kembang.grid.client.ColumnDef;
import org.kembang.grid.client.ColumnType;
import org.simbiosis.systemui.shared.RoleMenuDv;

public class RoleMenuViewerTable extends AdvancedGrid<RoleMenuDv> {
	ColumnDef<RoleMenuDv, String> colNr = new ColumnDef<RoleMenuDv, String>(
			ColumnType.LABEL, "No", "34px", "30px") {

		@Override
		public String getDataValue(RoleMenuDv data) {
			return data.getNr().toString();
		}
	};

	ColumnDef<RoleMenuDv, String> colStatus = new ColumnDef<RoleMenuDv, String>(
			ColumnType.LABEL, "Stat", "40px", "36px") {

		@Override
		public String getDataValue(RoleMenuDv data) {
			return data.getStatus() ? "A" : "-";
		}
	};

	ColumnDef<RoleMenuDv, String> colName = new ColumnDef<RoleMenuDv, String>(
			ColumnType.LABEL, "Nama") {

		@Override
		public String getDataValue(RoleMenuDv data) {
			return data.getName();
		}
	};

	public RoleMenuViewerTable() {
		addColumn(colNr);
		addColumn(colStatus);
		addColumn(colName);
	}

}
