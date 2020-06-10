/**
 * Файл локализации системы на русском языке (default).
 */
const messagesRu = {
  navigation: {
    companyName: 'СмартЛайт',
    companyDescription: 'Аутсорсинговая компания',
    menu: {
      general: 'Главная',
      news: 'Новости',
      info: 'О проекте',
      notFound: 'Страница не найдена',
    }
  },
  pages: {
    notFound: {
      title: '404 - не найдена',
      description: 'К сожалению! Кажется, что эта страница не существует.',
    },
    inDevelopment: {
      title: 'Страница находится в разработке!',
      description: 'Пожалуйста, вернитесь немного позже.',
    },
    auth: {
      login: {
        formName: 'Форма авторизации',
        google: 'Войдите через Google',
      },
      register: {
        formName: 'Регистрация',
        username: 'Имя пользователя',
        matchingPassword: 'Повторение пароля',
        google: 'Зарегистрируйтесь через Google',
        emailRequired: 'Email обязателен',
      },
      general: {
        email: 'Электронная почта',
        password: 'Пароль',
        nameRequired: 'Имя обязательно',
        passwordRequired: 'Пароль обязателен',
      }
    },
  },
  dialogs: {
    title: {
      confirmation: 'Подтверждение'
    },
    content: {
      confirmation: 'Вы уверены, что хотите это сделать?',
      resendToken: 'Токен верификации для подтверждения электронной почты просрочен. Для окончания регистрации нужно получить новый. Вы согласны?'
    }
  },
  buttons: {
    authBtn: 'Авторизация',
    registerBtn: 'Регистрация',
    closeBtn: 'Закрыть',
    backBtn: 'Назад',
    exitBtn: 'Выход',
    yesBtn: 'Да',
  },
  tooltips: {
    nightMode: 'Включить/Выключить темный режим',
    minVariant: 'Свернуть/Развернуть меню',
    changeLanguageSystem: 'Изменить язык системы',
  },
  errors: {
    invalid: {
      empty: {
        username: 'Имя пользователя должно быть заполнено',
        email: 'Email должен быть заполнен',
        password: 'Пароль должен быть заполнен',
        matchingPassword: 'Повторный пароль должен быть заполнен',
      },
      matchingPassword: 'Введенные пароли не совпадают',
      email: 'Не верный формат почты. Требуется *@*.*',
      credential: 'Используются неверные данные для авторизации',
    },
  },
}

export default messagesRu