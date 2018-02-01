package ua.model.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CargoFilter {

	private static final Pattern INT_PATTERN = Pattern.compile("^[0-9]{1,10}$");
	
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^([0-9]{1,18}\\.[0-9]{0,2})|([0-9]{1,18}\\,[0-9]{0,2})|([0-9]{1,18})$");
	
	private List<String> goodsIds = new ArrayList<>();
	
	private String minWeight = "";
	
	private String maxWeight = "";
	
	private String minHeight = "";
	
	private String maxHeight = "";

	private String minWidth = "";
	
	private String maxWidth = "";

	private String minLength = "";
	
	private String maxLength = "";
	
	private List<String> cityFrom = new ArrayList<>();

	private List<String> cityTo = new ArrayList<>();

	private String minPrice = "";
	
	private String maxPrice = "";

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public String getMinWeight() {
		return minWeight;
	}

	public String getMaxWeight() {
		return maxWeight;
	}

	public String getMinHeight() {
		return minHeight;
	}

	public String getMaxHeight() {
		return maxHeight;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public String getMaxWidth() {
		return maxWidth;
	}

	public String getMinLength() {
		return minLength;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public List<String> getCityFrom() {
		return cityFrom;
	}

	public List<String> getCityTo() {
		return cityTo;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public void setMinWeight(String minWeight) {
		if(INT_PATTERN.matcher(minWeight).matches())
		this.minWeight = minWeight;
	}

	public void setMaxWeight(String maxWeight) {
		if(INT_PATTERN.matcher(maxWeight).matches())
		this.maxWeight = maxWeight;
	}

	public void setMinHeight(String minHeight) {
		if(INT_PATTERN.matcher(minHeight).matches())
		this.minHeight = minHeight;
	}

	public void setMaxHeight(String maxHeight) {
		if(INT_PATTERN.matcher(maxHeight).matches())
		this.maxHeight = maxHeight;
	}

	public void setMinWidth(String minWidth) {
		if(INT_PATTERN.matcher(minWidth).matches())
		this.minWidth = minWidth;
	}

	public void setMaxWidth(String maxWidth) {
		if(INT_PATTERN.matcher(maxWidth).matches())
		this.maxWidth = maxWidth;
	}

	public void setMinLength(String minLength) {
		if(INT_PATTERN.matcher(minLength).matches())
		this.minLength = minLength;
	}

	public void setMaxLength(String maxLength) {
		if(INT_PATTERN.matcher(maxLength).matches())
		this.maxLength = maxLength;
	}

	public void setCityFrom(List<String> cityFrom) {
		this.cityFrom = cityFrom;
	}

	public void setCityTo(List<String> cityTo) {
		this.cityTo = cityTo;
	}

	public void setMinPrice(String minPrice) {
		if(DECIMAL_PATTERN.matcher(minPrice).matches())
		this.minPrice = minPrice;
	}

	public void setMaxPrice(String maxPrice) {
		if(DECIMAL_PATTERN.matcher(maxPrice).matches())
		this.maxPrice = maxPrice;
	}
}
