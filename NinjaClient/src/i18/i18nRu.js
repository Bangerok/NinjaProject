const messagesRu = {
    navigation: {
        companyName: 'СмартЛайт',
        companyDescription: 'Аутсорсинговая компания',
        menu: {
            general: 'Главная',
            vacancies: {
                title: 'Вакансии',
                submenu: {
                  java: 'Java',
                  cplusplus: 'C++',
                  csharp: 'C#',
                },
            },
            executor: 'Исполнитель',
            technologies: 'Технологии',
            customer: 'Заказчик',
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
            title: 'Страница находится в разработке!!!',
            description: 'Пожалуйста, вернитесь немного позже.',
        },
        vacancies: {
            dontActive: 'На данный момент нет открытых вакансий. Вернитесь попозже.',
            data: {
                titles: {
                    generalInfo: 'Общая информация',
                    vacanciesInfo: 'Информация о вакансии',
                    requirements: 'Требования к кандидату',
                },
                subTitles: {
                    organization: 'Организация',
                    city: 'Город',
                    address: 'Адрес',
                    phone: 'Телефон',
                    contact: 'Контактное лицо',
                    salary: 'Заработная плата (руб)',
                    workType: 'Характер работы',
                    workSchedule: 'График работы',
                    workWithUser: 'Работа с пользователями',
                    description: 'Описание',
                    education: 'Образование',
                    professional: 'Профессиональные требования',
                    comments: 'Комментарии',
                }
            },
        },
        technologies: {
            web: 'WEB технологии',
            database: 'Базы данных',
            programming: 'Программирование и фреймворки',
        },
    },
    components: {
        auth: {
            name: 'Имя',
            nameRequired: 'Имя обязательно',
            emailRequired: 'E-mail обязателен',
            isValidNameMsg: 'Поле должно быть меньше чем 50 символов',
            isValidEmailMsg: 'E-mail должен быть корректным: *@*.*',
        },
    },
    notification: {
        authSuccess: 'Авторизация пройдена успешно.',
        authError: 'Ошибка авторизации!!!',
    },
    buttons: {
        authBtn: 'Авторизация',
        clearBtn: 'Сбросить',
        closeBtn: 'Закрыть',
        openAll: 'Открыть все',
        closeAll: 'Закрыть все',
    },
    filters: {
        title: 'Фильтр',
        emptyResults: 'Отсутствуют результаты при текущей фильтрации.',
    },
}

export default messagesRu