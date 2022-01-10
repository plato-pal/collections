package collections;

import java.io.Serializable;
import java.math.BigDecimal;

public class CollectionsNewPolicyMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String policyNumber;
	private BigDecimal policyCost;
	
	public CollectionsNewPolicyMessage() {}

	public CollectionsNewPolicyMessage(String policyNumber, BigDecimal policyCost) {
		this.policyNumber = policyNumber;
		this.policyCost = policyCost;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public BigDecimal getPolicyCost() {
		return policyCost;
	}

	public void setPolicyCost(BigDecimal policyCost) {
		this.policyCost = policyCost;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CollectionsNewPolicyMessage [policyNumber=");
		builder.append(policyNumber);
		builder.append(", policyCost=");
		builder.append(policyCost);
		builder.append("]");
		return builder.toString();
	}
}
