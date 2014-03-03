package cn.btttech.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="log_type")
@DiscriminatorValue("log")
@Table(name="t_log", catalog="store_2")

public class Log  implements java.io.Serializable {

	 private static final long serialVersionUID = 4389475473056141143L;
	 private Integer logId;
     private User user;
     private String logRemark;
     private String logDo;
     private Date logTime;
     private String logCode;


    public Log() {
    }

    
    @Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false)
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}
    
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Column(name="log_remark", length=1024)
    public String getLogRemark() {
        return this.logRemark;
    }
    
    public void setLogRemark(String logRemark) {
        this.logRemark = logRemark;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name="log_time", length=10)

    public Date getLogTime() {
        return this.logTime;
    }
    
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
    
    @Column(name="log_code", length=1024)

    public String getLogCode() {
        return this.logCode;
    }
    
    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

    @Column(name="log_do", length=1024)
	public String getLogDo() {
		return logDo;
	}


	public void setLogDo(String logDo) {
		this.logDo = logDo;
	}
    
    
}