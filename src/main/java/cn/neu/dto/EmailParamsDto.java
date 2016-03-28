package cn.neu.dto;

public class EmailParamsDto {
	private String toEmail;
	private String title;
	private String content;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "EmailParamsDto [toEmail=" + toEmail + ", title=" + title + ", content=" + content + "]";
	}

}
