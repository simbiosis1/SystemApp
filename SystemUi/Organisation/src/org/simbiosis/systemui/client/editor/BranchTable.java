package org.simbiosis.systemui.client.editor;

import org.kembang.grid.client.ColumnDef;
import org.kembang.grid.client.ColumnType;
import org.kembang.grid.client.SimpleGrid;
import org.simbiosis.systemui.shared.BranchDv;

public class BranchTable extends SimpleGrid<BranchDv> {

	ColumnDef<BranchDv, String> colNr = new ColumnDef<BranchDv, String>(
			ColumnType.LABEL, "No", "24px", "20px") {

		@Override
		public String getDataValue(BranchDv data) {
			return data.getNr().toString();
		}
	};

	ColumnDef<BranchDv, String> colCode = new ColumnDef<BranchDv, String>(
			ColumnType.LABEL, "Kode", "34px", "30px") {

		@Override
		public String getDataValue(BranchDv data) {
			return data.getCode();
		}
	};

	ColumnDef<BranchDv, String> colName = new ColumnDef<BranchDv, String>(
			ColumnType.LABEL, "Cabang", "104px", "100px") {

		@Override
		public String getDataValue(BranchDv data) {
			return data.getName();
		}
	};

	ColumnDef<BranchDv, String> colAddress = new ColumnDef<BranchDv, String>(
			ColumnType.LABEL, "Alamat") {

		@Override
		public String getDataValue(BranchDv data) {
			return data.getAddress();
		}
	};

	public BranchTable() {
		addColumn(colNr);
		addColumn(colCode);
		addColumn(colName);
		addColumn(colAddress);
	}

}
