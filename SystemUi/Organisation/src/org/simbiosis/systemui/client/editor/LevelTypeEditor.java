package org.simbiosis.systemui.client.editor;

import org.kembang.editor.client.ListBoxEnumEditor;
import org.simbiosis.systemui.shared.LevelTypeEnum;

public class LevelTypeEditor extends ListBoxEnumEditor<LevelTypeEnum> {
	public LevelTypeEditor() {
		super(LevelTypeEnum.class);
	}
}
