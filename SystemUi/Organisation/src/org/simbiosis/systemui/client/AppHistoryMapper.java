package org.simbiosis.systemui.client;

import org.kembang.module.client.mvp.KembangHistoryMapper;
import org.simbiosis.systemui.client.places.Branch;
import org.simbiosis.systemui.client.places.Role;
import org.simbiosis.systemui.client.places.SubBranch;
import org.simbiosis.systemui.client.places.User;

import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ User.Tokenizer.class, Role.Tokenizer.class,
		Branch.Tokenizer.class, SubBranch.Tokenizer.class })
public interface AppHistoryMapper extends KembangHistoryMapper {

}
