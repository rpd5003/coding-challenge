package com.bodybuilding.models;

import java.util.TreeSet;

public class NavigationNode implements Comparable<NavigationNode>{

    private String id;
    private String name;
    private String url;
    private TreeSet<NavigationNode> children;
  
    public NavigationNode() {
    }
    
    public NavigationNode(String id, String name, String url, TreeSet<NavigationNode> children) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.children = children;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setChildren(TreeSet<NavigationNode> children) {
		this.children = children;
	}

	public String getName() {
        return name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public TreeSet<NavigationNode> getChildren() {
        return children;
    }

	@Override
	public int compareTo(NavigationNode o) {
		if(o.getId().equals(getId())){
			return 0;
		} else {
			return 1;
		}
	}
}
