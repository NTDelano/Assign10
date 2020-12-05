package src;


import javax.swing.Icon;

public class ListItem  {
	private int valA;
	private int valB;

	public ListItem(int valA, int valB) {
		this.valA = valA;
		this.valB = valB;
	}

	public int getValA() {
		return valA;
	}
	
	public void setValA(int newVal) {
		this.valA = newVal;
	}
	
	public int getValB() {
		return valB;
	}
	
	public void setValB(int newVal) {
		this.valB = newVal;
	}

	public Icon createIcon(int diameter) {
		Icon adapter = new ItemIcon(this, diameter);
		return adapter;
	}

	
}


