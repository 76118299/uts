package java.com.uts.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TradeDetail extends BaseEntity {
	
    private String serialno;

    private Date tradeDate;

    private BigDecimal dedbitAmount;

    private BigDecimal creditAmount;

    private String businessType;

    private BigDecimal balance;

    private String oppAccountName;

    private String oppAccountNo;

    private String accountName;

    private String accountNo;

    private String digest;

    private String status;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno == null ? null : serialno.trim();
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public BigDecimal getDedbitAmount() {
        return dedbitAmount;
    }

    public void setDedbitAmount(BigDecimal dedbitAmount) {
        this.dedbitAmount = dedbitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOppAccountName() {
        return oppAccountName;
    }

    public void setOppAccountName(String oppAccountName) {
        this.oppAccountName = oppAccountName == null ? null : oppAccountName.trim();
    }

    public String getOppAccountNo() {
        return oppAccountNo;
    }

    public void setOppAccountNo(String oppAccountNo) {
        this.oppAccountNo = oppAccountNo == null ? null : oppAccountNo.trim();
    }

    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest == null ? null : digest.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}