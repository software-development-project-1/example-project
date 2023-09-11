package fi.haagahelia.quizzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.quizzer.dto.CreateMessageDto;
import fi.haagahelia.quizzer.model.Message;
import fi.haagahelia.quizzer.repository.MessageRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageRestController {
	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("")
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@GetMapping("/{id}")
	public Message getMessageById(@PathVariable Long id) {
		return messageRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + id + " does not exist"));
	}

	@PostMapping("")
	public Message createMessage(@Valid @RequestBody CreateMessageDto message, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					bindingResult.getAllErrors().get(0).getDefaultMessage());
		}

		Message newMessage = new Message(message.getContent());
		return messageRepository.save(newMessage);
	}
}
