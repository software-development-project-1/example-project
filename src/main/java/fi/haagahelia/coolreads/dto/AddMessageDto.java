package fi.haagahelia.coolreads.dto;

import jakarta.validation.constraints.Size;

public class AddMessageDto {
	@Size(min=1, message="Content is required")
	private String content;
	
	public AddMessageDto() {
	}
	
	public AddMessageDto(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
