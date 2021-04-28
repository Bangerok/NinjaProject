package ru.bangerok.ninja.service.impl;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

		private final MessageSource messages;

		public MessageServiceImpl(MessageSource messages) {
				this.messages = messages;
		}

		@Override
		public String getMessage(String path) {
				return getMessage(path, null);
		}

		@Override
		public String getMessageWithArgs(String path, Object[] args) {
				return getMessage(path, args);
		}

		private String getMessage(String path, Object[] args) {
				return messages.getMessage(path, args, new Locale("ru", "RU"));
		}
}