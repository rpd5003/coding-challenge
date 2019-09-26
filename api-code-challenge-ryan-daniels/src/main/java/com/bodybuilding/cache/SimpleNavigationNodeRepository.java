package com.bodybuilding.cache;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ListIterator;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;

import com.bodybuilding.models.NavigationNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.google.gson.Gson;


@Configuration
public class SimpleNavigationNodeRepository implements NavigationNodeRepository {

	private NavigationNode matchingNode = new NavigationNode(null, null, null, null);
	private boolean foundMatch = false;

	//method to load entire json structure from navigation.json into JsonNode for searching
	//cacheable to load only once from disk
	@Cacheable("navigationNodes")
	public JsonNode loadNavigation() {
		JsonNode returnNode = null;
		try {
			String absolutePath = "";
			ObjectMapper mapper = new ObjectMapper();
			URL res = getClass().getClassLoader().getResource("navigation.json");
			File file = Paths.get(res.toURI()).toFile();
			absolutePath = file.getAbsolutePath();
			returnNode = mapper.readTree(new File(absolutePath));
		} catch (Exception e) {
			System.out.println(e);
		}
		return returnNode;
	}

	//method to search json structure by given id
	//if no match found, return null, which will throw 404 in controller
	@Override
	public NavigationNode getById(String id) {
		try {
			JsonNode rootNode = loadNavigation();
			if (!rootNode.isNull()) {
				traverse(rootNode, id);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (!isFoundMatch()) {
			return null;
		} else {
			return getMatchingNode();
		}
	}

	//recursive calls to parse json structure
	private void traverse(JsonNode node, String id) {
		if (node.getNodeType() == JsonNodeType.OBJECT) {
			traverseObject(node, id);
		} else if (node.getNodeType() == JsonNodeType.ARRAY) {
			traverseArray(node, id);
		}

	}

	//traverse a json object, comparing each by the id provided
	//if a match found, deserialize into model class and return to controller for output
	private void traverseObject(JsonNode node, String id) {
		ObjectMapper mapper = new ObjectMapper();
		node.fieldNames().forEachRemaining((String fieldName) -> {
			JsonNode childNode = node.get(fieldName);
			String tempId = "\"" + id + "\"";
			if (fieldName.equals("id") && childNode.toString().equals(tempId)) {
				try {
					NavigationNode matchedNode = mapper.readValue(node.toString(), NavigationNode.class);
					//if the matched node is not root, prune the children, as per requirements
					if (!matchedNode.getId().equals("root")) {
						matchedNode.setChildren(null);
					}
					setMatchingNode(matchedNode);
					setFoundMatch(true);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			if (childNode.getNodeType() == JsonNodeType.ARRAY) {
				traverse(childNode, id);
			}
		});
	}

	private void traverseArray(JsonNode node, String id) {
		for (JsonNode jsonArrayNode : node) {
			if (jsonArrayNode.getNodeType() == JsonNodeType.ARRAY
					|| jsonArrayNode.getNodeType() == JsonNodeType.OBJECT) {
				traverse(jsonArrayNode, id);
			}
		}
	}

	public NavigationNode getMatchingNode() {
		return matchingNode;
	}

	public void setMatchingNode(NavigationNode matchingNode) {
		this.matchingNode = matchingNode;
	}

	public boolean isFoundMatch() {
		return foundMatch;
	}

	public void setFoundMatch(boolean foundMatch) {
		this.foundMatch = foundMatch;
	}
}
