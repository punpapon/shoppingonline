package com.somapait.domain;

import java.io.Serializable;

public abstract class SearchCriteria implements Serializable {

	private static final long serialVersionUID = -7032911859591086173L;

	private boolean checkMaxExceed = true;
	private String criteriaKey;
	private int linePerPage;
	private int start = 0;
	private long totalResult = 0;

	private HeaderSorts[] headerSorts;
	private String headerSortsSelect;
	private int headerSortsSize;

	private String statusForUpdate;
	private String selectedIds;

	public String getOrderList() {
		String orderReturn = null;
		try {
			StringBuilder orderList = new StringBuilder();
			if ((headerSortsSelect != null) && (headerSortsSelect.trim().equals("") == false)) {
				String[] indexs = headerSortsSelect.split(",");
				for (int i = 0; i < indexs.length; i++) {
					if (headerSorts[Integer.parseInt(indexs[i])].getOrder().trim().equals("") == false) {
						orderList.append(", ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getColumn().trim());
						orderList.append(" ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getOrder().trim());
					}
				}
			}

			if (orderList.length() > 0) {
				orderList.deleteCharAt(0);
			} else {
				if (orderList.length() > 0) {
					orderList.append(headerSorts[0].getColumn().trim());
					orderList.append(" ");
					orderList.append(headerSorts[0].getOrder().trim());
				}
			}
			orderReturn = orderList.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderReturn;
	}

	public String getCriteriaKey() {
		return criteriaKey;
	}

	public void setCriteriaKey(String criteriaKey) {
		this.criteriaKey = criteriaKey;
	}

	public int getLinePerPage() {
		return linePerPage;
	}

	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public long getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(long totalResult) {
		this.totalResult = totalResult;
	}

	public HeaderSorts[] getHeaderSorts() {
		return headerSorts;
	}

	public void setHeaderSorts(HeaderSorts[] headerSorts) {
		this.headerSorts = headerSorts;
	}

	public int getHeaderSortsSize() {
		return headerSortsSize;
	}

	public void setHeaderSortsSize(int headerSortsSize) {
		this.headerSortsSize = headerSortsSize;
	}

	public String getHeaderSortsSelect() {
		return headerSortsSelect;
	}

	public void setHeaderSortsSelect(String headerSortsSelect) {
		this.headerSortsSelect = headerSortsSelect;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public String getStatusForUpdate() {
		return statusForUpdate;
	}

	public void setStatusForUpdate(String statusForUpdate) {
		this.statusForUpdate = statusForUpdate;
	}

	public boolean isCheckMaxExceed() {
		return checkMaxExceed;
	}

	public void setCheckMaxExceed(boolean checkMaxExceed) {
		this.checkMaxExceed = checkMaxExceed;
	}

	public void setDefaultHeaderSorts() {

	}
}
