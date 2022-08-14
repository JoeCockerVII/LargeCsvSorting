package com.example.csvsorting.domain.entity;

import java.util.Iterator;

public class ClientDetails {

    private Integer fid;
    private String serialNum;
    private String memberCode;
    private String acctType;
    private String openedDt;
    private String acctRteCde;
    private String reportingDt;
    private String creditLimit;

    public ClientDetails(Integer fid, String serialNum, String memberCode, String acctType, String openedDt, String acctRteCde, String reportingDt, String creditLimit) {
        this.fid = fid;
        this.serialNum = serialNum;
        this.memberCode = memberCode;
        this.acctType = acctType;
        this.openedDt = openedDt;
        this.acctRteCde = acctRteCde;
        this.reportingDt = reportingDt;
        this.creditLimit = creditLimit;
    }

    public ClientDetails(String[] words) {
        if(words.length == 0 || words == null)
            throw new IllegalArgumentException("Error creating object");
        this.fid = Integer.parseInt(words[0]);
        this.serialNum = words[1];
        this.memberCode = words[2];
        this.acctType = words[3];
        this.openedDt = words[4];
        this.acctRteCde = words[5];
        this.reportingDt = words[6];
        this.creditLimit = words[7];
    }

    public String getCsvString(){
        StringBuffer sb = new StringBuffer();
        sb.append(fid.toString()).append(";");
        sb.append(serialNum).append(";");
        sb.append(memberCode).append(";");
        sb.append(acctType).append(";");
        sb.append(openedDt).append(";");
        sb.append(acctRteCde).append(";");
        sb.append(reportingDt).append(";");
        sb.append(creditLimit);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "fid=" + fid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getOpenedDt() {
        return openedDt;
    }

    public void setOpenedDt(String openedDt) {
        this.openedDt = openedDt;
    }

    public String getAcctRteCde() {
        return acctRteCde;
    }

    public void setAcctRte(String acctRte) {
        this.acctRteCde = acctRte;
    }

    public String getReportingDt() {
        return reportingDt;
    }

    public void setReportingDt(String reportingDt) {
        this.reportingDt = reportingDt;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }
}
