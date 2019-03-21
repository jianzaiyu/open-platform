package cn.ce.service.openapi.base.oauth.entity;

public class AuthorizeClientEntity {

	 /** 最大配额*/
    private int quotaMax;
    
    /** 最大配额 重置周期 */
    private int quotaRenewalRate;
    
    /** 频次  */
    private int rate;
    
    /** 访问频次计数周期  */
    private int per;
    
    private int allowance;
    
    private Integer expires;
    
    private int quotaRenews;
    
    private int quotaRemaining;

	public int getQuotaMax() {
		return quotaMax;
	}

	public void setQuotaMax(int quotaMax) {
		this.quotaMax = quotaMax;
	}

	public int getQuotaRenewalRate() {
		return quotaRenewalRate;
	}

	public void setQuotaRenewalRate(int quotaRenewalRate) {
		this.quotaRenewalRate = quotaRenewalRate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getPer() {
		return per;
	}

	public void setPer(int per) {
		this.per = per;
	}

	public int getAllowance() {
		return allowance;
	}

	public void setAllowance(int allowance) {
		this.allowance = allowance;
	}

	public Integer getExpires() {
		return expires;
	}

	public void setExpires(Integer expires) {
		this.expires = expires;
	}

	public int getQuotaRenews() {
		return quotaRenews;
	}

	public void setQuotaRenews(int quotaRenews) {
		this.quotaRenews = quotaRenews;
	}

	public int getQuotaRemaining() {
		return quotaRemaining;
	}

	public void setQuotaRemaining(int quotaRemaining) {
		this.quotaRemaining = quotaRemaining;
	}

	@Override
	public String toString() {
		return "AuthorizeClientEntity [quotaMax=" + quotaMax + ", quotaRenewalRate=" + quotaRenewalRate + ", rate="
				+ rate + ", per=" + per + ", allowance=" + allowance + ", expires=" + expires + ", quotaRenews="
				+ quotaRenews + ", quotaRemaining=" + quotaRemaining + "]";
	}
	
}
