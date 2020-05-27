package ru.bangerok.ninja.enumeration;

import ru.bangerok.ninja.domain.User;

/**
 * Класс перечисления для провайдеров авторизаций пользователей.
 * <p>
 * Для полной картины смотри: {@link User#getAuthProvider}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public enum AuthProvider {
		local, google
}
