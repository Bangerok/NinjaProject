package ru.bangerok.ninja.persistence.model.base;

/**
 * Класс перечисления для статусов сущностей проекта.
 * <p>
 * Для полной картины смотри: {@link BaseEntity#getStatus}.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public enum BaseStatus {
		/**
		 * Статус, сигнализирующий об активности записи в базе данных.
		 */
		ACTIVE,

		/**
		 * Статус, сигнализирующий об неактивности записи в базе данных.
		 */
		DELETED
}
