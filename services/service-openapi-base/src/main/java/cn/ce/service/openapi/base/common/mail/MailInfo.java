package cn.ce.service.openapi.base.common.mail;

/**  
 * 发送邮件需要使用的基本信息  
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MailInfo {
    // 邮件接收者的地址-单人
	private String toOne;
	// 邮件接收者的地址-多人
    private List<String> to          = new ArrayList<String>();
    // 抄送者的地址
    private List<String> cc          = new ArrayList<String>();
    // 密送者的地址
    private List<String> bcc         = new ArrayList<String>();
    // 邮件主题
    private String       subject;
    // 邮件的文本内容
    private String       content;
    // 邮件附件的文件名
    private List<File>   attachFiles = new ArrayList<File>();

    public MailInfo addTo(String... to) {
        for (String t : to) {
            this.to.add(t);
        }
        return this;
    }

    public MailInfo addCc(String... cc) {
        for (String c : cc) {
            this.cc.add(c);
        }
        return this;
    }

    public MailInfo addBcc(String... bcc) {
        for (String c : bcc) {
            this.cc.add(c);
        }
        return this;
    }

    public MailInfo addAttachFile(File... attachFiles) {
        for (File file : attachFiles) {
            this.attachFiles.add(file);
        }
        return this;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getAttachFiles() {
        return attachFiles;
    }

    public void setAttachFiles(List<File> attachFiles) {
        this.attachFiles = attachFiles;
    }

	public String getToOne() {
		return toOne;
	}

	public void setToOne(String toOne) {
		this.toOne = toOne;
	}

}