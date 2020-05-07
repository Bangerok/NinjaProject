import general from './../pages/General'

let state = {
    notification: {
        color: '',
        text: '',
        show: false,
    },
    pages: {
      vacancies: [],
    },
    navigation: {
        minVariant: null,
        links: [
            {
                path: '/',
                name: 'navigation.menu.general',
                icon: 'mdi-view-dashboard',
                hasSubmenus: false,
                isSubmenu: false,
                component: general
            },
            /*{
                path: '/vacancies',
                name: 'navigation.menu.vacancies.title',
                icon: 'mdi-account-search',
                hasSubmenus: true,
                isSubmenu: false,
                component: vacancies,
            },
            {
                path: '/vacancies/language1',
                name: 'navigation.menu.vacancies.submenu.java',
                icon: 'mdi-fire',
                hasSubmenus: false,
                isSubmenu: true,
                component: vacancies
            },
            {
                path: '/vacancies/language2',
                name: 'navigation.menu.vacancies.submenu.cplusplus',
                icon: 'mdi-fire',
                hasSubmenus: false,
                isSubmenu: true,
                component: vacancies
            },
            {
                path: '/vacancies/language3',
                name: 'navigation.menu.vacancies.submenu.csharp',
                icon: 'mdi-fire',
                hasSubmenus: false,
                isSubmenu: true,
                component: vacancies
            },
            {
                path: '/technologies',
                name: 'navigation.menu.technologies',
                icon: 'mdi-clipboard',
                hasSubmenus: false,
                isSubmenu: false,
                component: technologies
            },
            {
                path: '/executor',
                name: 'navigation.menu.executor',
                icon: 'mdi-account-check',
                hasSubmenus: false,
                isSubmenu: false,
                component: executor
            },
            {
                path: '/customer',
                name: 'navigation.menu.customer',
                icon: 'mdi-account-group',
                hasSubmenus: false,
                isSubmenu: false,
                component: customer
            },
            {
                path: '/news',
                name: 'navigation.menu.news',
                icon: 'mdi-alarm-check',
                hasSubmenus: false,
                isSubmenu: false,
                component: inDevelopment
            },
            {
                path: '/info',
                name: 'navigation.menu.info',
                icon: 'mdi-alert-circle',
                hasSubmenus: false,
                isSubmenu: false,
                component: aboutUs
            },
            {
                path: "*",
                name: "navigation.menu.notFound",
                component: notFound
            }*/
        ],
    },
}

export default state