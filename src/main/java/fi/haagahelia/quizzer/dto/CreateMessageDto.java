package fi.haagahelia.quizzer.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateMessageDto {
    @NotBlank(message = "Content is required")
    private String content;

    public CreateMessageDto(String content) {
        this.content = content;
    }

    public CreateMessageDto() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
