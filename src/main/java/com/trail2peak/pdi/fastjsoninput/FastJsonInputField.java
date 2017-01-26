package com.trail2peak.pdi.fastjsoninput;

import org.pentaho.di.core.Const;
import org.pentaho.di.core.exception.KettleValueException;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.row.value.ValueMetaBase;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.i18n.BaseMessages;
import org.w3c.dom.Node;

/**
 * Describes a Json field.
 *
 * @author Samatar
 * @author edube
 * @since 2015-01-07
 */
public class FastJsonInputField implements Cloneable {
	private static Class<?> PKG = FastJsonInputMeta.class; // for i18n purposes,
															// needed by
															// Translator2!!

	public static final int TYPE_TRIM_NONE = 0;
	public static final int TYPE_TRIM_LEFT = 1;
	public static final int TYPE_TRIM_RIGHT = 2;
	public static final int TYPE_TRIM_BOTH = 3;

	public static final String[] trimTypeCode = { "none", "left", "right",
			"both" };

	public static final String[] trimTypeDesc = {
			BaseMessages.getString(PKG, "FastJsonInputField.TrimType.None"),
			BaseMessages.getString(PKG, "FastJsonInputField.TrimType.Left"),
			BaseMessages.getString(PKG, "FastJsonInputField.TrimType.Right"),
			BaseMessages.getString(PKG, "FastJsonInputField.TrimType.Both") };

	private String name;
	private String path;

	private int type;
	private int length;
	private String format;
	private int trimtype;
	private int precision;
	private String currencySymbol;
	private String decimalSymbol;
	private String groupSymbol;
	private boolean repeat;

	public FastJsonInputField(String fieldname) {
		this.name = fieldname;
		this.path = "";
		this.length = -1;
		this.type = ValueMetaInterface.TYPE_STRING;
		this.format = "";
		this.trimtype = TYPE_TRIM_NONE;
		this.groupSymbol = "";
		this.decimalSymbol = "";
		this.currencySymbol = "";
		this.precision = -1;
		this.repeat = false;
	}

	public FastJsonInputField() {
		this("");
	}

	public String getXML() {
		StringBuffer retval = new StringBuffer(400);

		retval.append("      <field>").append(Const.CR);
		retval.append("        ").append(
				XMLHandler.addTagValue("name", getName()));
		retval.append("        ").append(
				XMLHandler.addTagValue("path", getPath()));
		retval.append("        ").append(
				XMLHandler.addTagValue("type", getTypeDesc()));
		retval.append("        ").append(
				XMLHandler.addTagValue("format", getFormat()));
		retval.append("        ").append(
				XMLHandler.addTagValue("currency", getCurrencySymbol()));
		retval.append("        ").append(
				XMLHandler.addTagValue("decimal", getDecimalSymbol()));
		retval.append("        ").append(
				XMLHandler.addTagValue("group", getGroupSymbol()));
		retval.append("        ").append(
				XMLHandler.addTagValue("length", getLength()));
		retval.append("        ").append(
				XMLHandler.addTagValue("precision", getPrecision()));
		retval.append("        ").append(
				XMLHandler.addTagValue("trim_type", getTrimTypeCode()));
		retval.append("        ").append(
				XMLHandler.addTagValue("repeat", isRepeated()));

		retval.append("      </field>").append(Const.CR);

		return retval.toString();
	}

	public FastJsonInputField(Node fnode) throws KettleValueException {
		setName(XMLHandler.getTagValue(fnode, "name"));
		setPath(XMLHandler.getTagValue(fnode, "path"));
		setType(ValueMetaBase.getType(XMLHandler.getTagValue(fnode, "type")));
		setFormat(XMLHandler.getTagValue(fnode, "format"));
		setCurrencySymbol(XMLHandler.getTagValue(fnode, "currency"));
		setDecimalSymbol(XMLHandler.getTagValue(fnode, "decimal"));
		setGroupSymbol(XMLHandler.getTagValue(fnode, "group"));
		setLength(Const.toInt(XMLHandler.getTagValue(fnode, "length"), -1));
		setPrecision(Const
				.toInt(XMLHandler.getTagValue(fnode, "precision"), -1));
		setTrimType(getTrimTypeByCode(XMLHandler
				.getTagValue(fnode, "trim_type")));
		setRepeated(!"N".equalsIgnoreCase(XMLHandler.getTagValue(fnode,
				"repeat")));
	}

	public static final int getTrimTypeByCode(String tt) {
		if (tt == null) {
			return 0;
		}

		for (int i = 0; i < trimTypeCode.length; i++) {
			if (trimTypeCode[i].equalsIgnoreCase(tt)) {
				return i;
			}
		}
		return 0;
	}

	public static final int getTrimTypeByDesc(String tt) {
		if (tt == null) {
			return 0;
		}

		for (int i = 0; i < trimTypeDesc.length; i++) {
			if (trimTypeDesc[i].equalsIgnoreCase(tt)) {
				return i;
			}
		}
		return 0;
	}

	public static final String getTrimTypeCode(int i) {
		if (i < 0 || i >= trimTypeCode.length) {
			return trimTypeCode[0];
		}
		return trimTypeCode[i];
	}

	public static final String getTrimTypeDesc(int i) {
		if (i < 0 || i >= trimTypeDesc.length) {
			return trimTypeDesc[0];
		}
		return trimTypeDesc[i];
	}

	public Object clone() {
		try {
			FastJsonInputField retval = (FastJsonInputField) super.clone();

			return retval;
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String value) {
		this.path = value;
	}

	public void setName(String fieldname) {
		this.name = fieldname;
	}

	public int getType() {
		return type;
	}

	public String getTypeDesc() {
		return ValueMetaBase.getTypeDesc(type);
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getTrimType() {
		return trimtype;
	}

	public String getTrimTypeCode() {
		return getTrimTypeCode(trimtype);
	}

	public String getTrimTypeDesc() {
		return getTrimTypeDesc(trimtype);
	}

	public void setTrimType(int trimtype) {
		this.trimtype = trimtype;
	}

	public String getGroupSymbol() {
		return groupSymbol;
	}

	public void setGroupSymbol(String group_symbol) {
		this.groupSymbol = group_symbol;
	}

	public String getDecimalSymbol() {
		return decimalSymbol;
	}

	public void setDecimalSymbol(String decimal_symbol) {
		this.decimalSymbol = decimal_symbol;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currency_symbol) {
		this.currencySymbol = currency_symbol;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public boolean isRepeated() {
		return repeat;
	}

	public void setRepeated(boolean repeat) {
		this.repeat = repeat;
	}

	public void flipRepeated() {
		repeat = !repeat;
	}
}
