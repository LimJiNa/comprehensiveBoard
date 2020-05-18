package com.comprehensive.common;

import java.util.Arrays;

public class SearchCriteria extends Criteria {
	private String[] searchType;
	private String keyword;

	public String[] getSearchType() {
		if(searchType == null) {
			return new String[0];
		}
		return searchType;
	}

	public void setSearchType(String[] searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + Arrays.toString(searchType) + ", keyword=" + keyword + "]";
	}
}
