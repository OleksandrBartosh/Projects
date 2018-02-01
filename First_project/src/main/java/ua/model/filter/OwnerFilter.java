package ua.model.filter;

import java.util.regex.Pattern;

public class OwnerFilter {
	
	private static final Pattern INT_PATTERN = Pattern.compile("^[0-9]{1,10}$");
	
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");

	private String minRate = "";
	
	private String maxRate = "";
	
	private String minCount = "";
	
	private String maxCount = "";

	public String getMinRate() {
		return minRate;
	}

	public String getMaxRate() {
		return maxRate;
	}

	public String getMinCount() {
		return minCount;
	}

	public String getMaxCount() {
		return maxCount;
	}

	public void setMinRate(String minRate) {
		if(DECIMAL_PATTERN.matcher(minRate).matches())
		this.minRate = minRate;
	}

	public void setMaxRate(String maxRate) {
		if(DECIMAL_PATTERN.matcher(maxRate).matches())
		this.maxRate = maxRate;
	}

	public void setMinCount(String minCount) {
		if(INT_PATTERN.matcher(minCount).matches())
		this.minCount = minCount;
	}

	public void setMaxCount(String maxCount) {
		if(INT_PATTERN.matcher(maxCount).matches())
		this.maxCount = maxCount;
	}
}
