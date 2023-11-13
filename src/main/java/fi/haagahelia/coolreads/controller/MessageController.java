package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.repository.MessageRepository;
import jakarta.validation.Valid;
import fi.haagahelia.coolreads.dto.AddMessageDto;
import fi.haagahelia.coolreads.model.Message;

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

	@GetMapping("/react-messages")
	public String renderReactMessageList(Model model) {
		return "reactmessagelist";
	}

	@GetMapping("/messages/add")
	public String renderAddMessageForm(Model model) {
		model.addAttribute("message", new AddMessageDto());

		return "addmessage";
	}

	@PostMapping("/messages/add")
	public String addMessage(@Valid @ModelAttribute("message") AddMessageDto message, BindingResult bindingResult,
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
