package ru.bangerok.ninja.domain.view;

/**
 * Класс-маркер для определения того, какие поля сущностей нужны в какой то момент, а какие нет.
 *
 * @author v.kuznetsov
 * @version 1.0
 */
public final class Views {

		public interface Id {

		}

		public interface IdName extends Id {

		}

		public interface FullProfile extends IdName {

		}
}
