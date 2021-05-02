package ru.bangerok.ninja.service.impl;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.bangerok.ninja.service.MessageService;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

		private final MessageSource messages;

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