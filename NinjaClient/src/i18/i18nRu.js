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
      },
      register: {
        formName: 'Регистрация',
        email: 'Электронная почта',
        matchingPassword: 'Повторение пароля',
        emailRequired: 'Email обязателен',
        google: 'Зарегистрируйтесь через Google',
      },
      general: {
        username: 'Имя пользователя',
        password: 'Пароль',
        nameRequired: 'Имя обязательно',
        passwordRequired: 'Пароль обязателен',
      }
    },
  },
  notification: {
    authSuccess: 'Авторизация пройдена успешно.',
    authError: 'Ошибка авторизации!',
  },
  buttons: {
    authBtn: 'Авторизация',
    registerBtn: 'Регистрация',
    closeBtn: 'Закрыть',
    backBtn: 'Назад',
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
    },
    exists: {
      email: 'Пользователь с таким email уже существует',
    },
  },
  success: {
    registerUser: 'Регистрация прошла успешно',
  },
}

export default messagesRu