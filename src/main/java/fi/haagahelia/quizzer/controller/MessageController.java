package fi.haagahelia.quizzer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.quizzer.dto.CreateMessageDto;
import fi.haagahelia.quizzer.model.Message;
import fi.haagahelia.quizzer.repository.MessageRepository;
import jakarta.validation.Valid;

@Controller
public class MessageController {
	@Autowired
	private MessageRepository messageRepository;

	@GetMapping("/")
	public String listMessages(Model model) {
		List<Message> messages = messageRepository.findAll();
		model.addAttribute("messages", messages);

		return "messagelist";
	}

	@GetMapping("/messages/add")
	public String renderAddMessageForm(Model model) {
		model.addAttribute("message", new CreateMessageDto());

		return "addmessage";
	}

	@PostMapping("/messages/add")
	public String addMessage(@Valid @ModelAttribute("message") CreateMessageDto message, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("message", message);
			return "addmessage";
		}

		Message newMessage = new Message(message.getContent());
		messageRepository.save(newMessage);

		return "redirect:/";
	}
}
