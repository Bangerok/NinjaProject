package ru.bangerok.ninja.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiResponse {

		private final boolean success;
		private final String message;
}
