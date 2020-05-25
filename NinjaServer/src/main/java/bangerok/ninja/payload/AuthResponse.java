package bangerok.ninja.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {

		private final String accessToken;
		private String tokenType = "Bearer";
}
