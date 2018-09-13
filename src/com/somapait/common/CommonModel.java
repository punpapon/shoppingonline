package com.somapait.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.somapait.domain.SearchCriteria;
import com.somapait.domain.Transaction;

public class CommonModel implements Serializable {

	private static final long serialVersionUID = 3541361251724984558L;

	private Transaction transaction = new Transaction();

	private PageType page;

	private List<?> lstResult = new ArrayList<Object>();

	private SearchCriteria criteria;

	private String messagePopup;

	public enum PageType {
		HOME("home")
		, SEARCH("search")
		, ADD("add")
		, EDIT("edit")
		, VIEW("view")
		, REPORT("report")
		, PRINT("print") 
		, SEARCH_DIALOG("search_dialog")
		, CHOOSE_DIALOG("choose_dialog");

		private String page;

		private PageType(String page) {
			this.page = page;
		}

		public String getPage() {
			return page;
		}
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public PageType getPage() {
		return page;
	}

	public void setPage(PageType page) {
		this.page = page;
	}

	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	public List<?> getLstResult() {
		return lstResult;
	}

	public void setLstResult(List<?> lstResult) {
		this.lstResult = lstResult;
	}

	public String getMessagePopup() {
		return messagePopup;
	}

	public void setMessagePopup(String messagePopup) {
		this.messagePopup = messagePopup;
	}

}
