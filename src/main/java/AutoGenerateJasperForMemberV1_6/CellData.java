package AutoGenerateJasperForMemberV1_6;

public class CellData {
	public String text = "";
	public boolean isNumeric = false;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isNumeric() {
		return isNumeric;
	}
	public void setNumeric(boolean isNumeric) {
		this.isNumeric = isNumeric;
	}
	public CellData(String text, boolean isNumeric) {
		super();
		this.text = text;
		this.isNumeric = isNumeric;
	}
}
