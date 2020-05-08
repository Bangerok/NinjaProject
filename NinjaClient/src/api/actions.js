const actions = {
    getVacanciesData (context, payload) {
        const vacanciesData = {
            'Java': [
                {
                    title: 'Java разработчик 1',
                    createdDate: '01.01.2020',
                    generalInfo: {
                        organization: 'ООО Тестовое имя',
                        city: 'Киров',
                        address: 'Тестовый адрес',
                        phone: '+7 (999) 44-44-44',
                        contact: 'Тестов Тест Тестович',
                    },
                    vacanciesInfo: {
                        salary: '50000',
                        workType: 'Постоянная работа',
                        workSchedule: 'Удаленная работа',
                        workWithUser: 'Нет',
                        description: 'Тестовое описание',
                    },
                    requirements: {
                        education: 'Тестовое образование',
                        professional: 'Требования',
                        comments: 'Тестовый комментарий'
                    },
                },
                {
                    title: 'Java разработчик 2',
                    createdDate: '01.01.2019',
                    generalInfo: {
                        organization: 'ООО Тестовое имя',
                        city: 'Киров',
                        address: 'Тестовый адрес',
                        phone: '+7 (999) 44-44-44',
                        contact: 'Тестов Тест Тестович',
                    },
                    vacanciesInfo: {
                        salary: '70000',
                        workType: 'Постоянная работа',
                        workSchedule: 'Удаленная работа',
                        workWithUser: 'Да',
                        description: 'Тестовое описание 2',
                    },
                    requirements: {
                        education: 'Тестовое образование',
                        professional: 'Требования 2',
                        comments: 'Тестовый комментарий 2'
                    },
                },
                {
                    title: 'Java разработчик 3',
                    createdDate: '05.01.2020',
                    generalInfo: {
                        organization: 'ООО Тестовое имя 1',
                        city: 'Киров',
                        address: 'Тестовый адрес 2',
                        phone: '+7 (999) 55-55-55',
                        contact: 'Тестов2 Тест2 Тестович2',
                    },
                    vacanciesInfo: {
                        salary: '40000',
                        workType: 'Постоянная работа',
                        workSchedule: 'Удаленная работа',
                        workWithUser: 'Да',
                        description: 'Тестовое описание 3',
                    },
                    requirements: {
                        education: 'Тестовое образование 2',
                        professional: 'Требования 3',
                        comments: 'Тестовый комментарий 3'
                    },
                }
            ],
            'C++': [
                {
                    title: 'C++ разработчик 1',
                    createdDate: '01.01.2020',
                    generalInfo: {
                        organization: 'ООО Тестовое имя 1',
                        city: 'Киров',
                        address: 'Тестовый адрес 2',
                        phone: '+7 (999) 55-55-55',
                        contact: 'Тестов2 Тест2 Тестович2',
                    },
                    vacanciesInfo: {
                        salary: '40000',
                        workType: 'Постоянная работа',
                        workSchedule: 'Удаленная работа',
                        workWithUser: 'Да',
                        description: 'Тестовое описание 3',
                    },
                    requirements: {
                        education: 'Тестовое образование 2',
                        professional: 'Требования 3',
                        comments: 'Тестовый комментарий 3'
                    },
                },
                {
                    title: 'C++ разработчик 2',
                    createdDate: '01.01.2020',
                    generalInfo: {
                        organization: 'ООО Тестовое имя',
                        city: 'Киров',
                        address: 'Тестовый адрес',
                        phone: '+7 (999) 44-44-44',
                        contact: 'Тестов Тест Тестович',
                    },
                    vacanciesInfo: {
                        salary: '70000',
                        workType: 'Постоянная работа',
                        workSchedule: 'Удаленная работа',
                        workWithUser: 'Да',
                        description: 'Тестовое описание 2',
                    },
                    requirements: {
                        education: 'Тестовое образование',
                        professional: 'Требования 2',
                        comments: 'Тестовый комментарий 2'
                    },
                },
            ],
            'C#': [],
        };

        context.commit('setVacancies', vacanciesData[payload.language]);
    }
}

export default actions