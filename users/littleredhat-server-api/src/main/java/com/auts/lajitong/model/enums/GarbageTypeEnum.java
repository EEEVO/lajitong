package com.auts.lajitong.model.enums;


import java.util.HashMap;
import java.util.Map;

public enum GarbageTypeEnum {
	Plastics("401", "塑料"),
	Book("402", "纸张"),
	Metal("403", "金属"),
	Fabric("404", "织物");

	private static Map<String, GarbageTypeEnum> valueToEnumMap=new HashMap<String, GarbageTypeEnum>();
	static
	{
		GarbageTypeEnum[] AccountStatuss=GarbageTypeEnum.values();
		if(AccountStatuss!=null)
		{
			for (GarbageTypeEnum accountStatus: AccountStatuss) {
				valueToEnumMap.put(accountStatus.getValue(), accountStatus);
			}
		}
	}

	private GarbageTypeEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}

	private String value;
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static GarbageTypeEnum valueToEnum(String value)
	{
		return valueToEnumMap.getOrDefault(value, null);
	}
}
