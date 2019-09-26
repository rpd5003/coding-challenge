package com.bodybuilding.cache;

import com.bodybuilding.models.NavigationNode;

public interface NavigationNodeRepository {

	NavigationNode getById(String id);
	
}
