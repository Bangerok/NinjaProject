import general from './../views/General'
import vacancies from './../views/Vacancies'
import technologies from './../views/Technologies'
import inDevelopment from './../views/InDevelopment'
import executor from './../views/Executor'
import customer from './../views/Customer'
import aboutUs from './../views/AboutUs'
import login from '../views/Login'
import notFound from './../views/NotFound'
import alfrescoLogo from './../assets/technologies/TechIcon_alfresco-logo-ru.png'
import html5 from './../assets/technologies/TechIcon_html5.png'
import gwt from './../assets/technologies/TechIcon_GWT.png'
import jdbc from './../assets/technologies/TechIcon_jdbc.png'
import jquery from './../assets/technologies/TechIcon_jquery.png'
import logo_teh1 from './../assets/technologies/TechIcon_logo_teh1.jpg'
import logo_teh2 from './../assets/technologies/TechIcon_logo_teh2.jpg'
import logo_teh3 from './../assets/technologies/TechIcon_logo_teh3.jpg'
import logo_teh6 from './../assets/technologies/TechIcon_logo_teh6.jpg'
import logo_teh7 from './../assets/technologies/TechIcon_logo_teh7.jpg'
import logo_teh9 from './../assets/technologies/TechIcon_logo_teh9.jpg'
import logo_teh10 from './../assets/technologies/TechIcon_logo_teh10.jpg'
import logo_teh15 from './../assets/technologies/TechIcon_logo_teh15.jpg'
import web20_logo from './../assets/technologies/TechIcon_web20_logo.png'
import yii from './../assets/technologies/TechIcon_yii.png'

let state = {
  user: [],
  notification: {
    color: '',
    text: '',
    show: false,
  },
  pages: {
    vacancies: [],
    technologies: {
      statsCards: [
        {
          type: "web",
          iconImg: alfrescoLogo
        },
        {
          type: "web",
          iconImg: html5
        },
        {
          type: "database",
          iconImg: jdbc
        },
        {
          type: "web",
          iconImg: jquery
        },
        {
          type: "database",
          iconImg: logo_teh1
        },
        {
          type: "database",
          iconImg: logo_teh2
        },
        {
          type: "database",
          iconImg: logo_teh3
        },
        {
          type: "programming",
          iconImg: logo_teh6
        },
        {
          type: "web",
          iconImg: logo_teh7
        },
        {
          type: "programming",
          iconImg: logo_teh9
        },
        {
          type: "programming",
          iconImg: logo_teh10
        },
        {
          type: "programming",
          iconImg: logo_teh15
        },
        {
          type: "web",
          iconImg: web20_logo
        },
        {
          type: "web",
          iconImg: gwt
        },
        {
          type: "web",
          iconImg: yii
        }
      ]
    }
  },
  navigation: {
    minVariant: null,
    links: [
      {
        path: '/',
        name: 'navigation.menu.general',
        icon: 'fa-home',
        hasSubmenus: false,
        isSubmenu: false,
        component: general
      },
      {
        path: '/vacancies',
        name: 'navigation.menu.vacancies.title',
        icon: 'fa-user-plus',
        hasSubmenus: true,
        isSubmenu: false,
      },
      {
        path: '/vacancies/language1',
        name: 'navigation.menu.vacancies.submenu.java',
        icon: 'fa-check',
        hasSubmenus: false,
        isSubmenu: true,
        component: vacancies
      },
      {
        path: '/vacancies/language2',
        name: 'navigation.menu.vacancies.submenu.cplusplus',
        icon: 'fa-check',
        hasSubmenus: false,
        isSubmenu: true,
        component: vacancies
      },
      {
        path: '/vacancies/language3',
        name: 'navigation.menu.vacancies.submenu.csharp',
        icon: 'fa-check',
        hasSubmenus: false,
        isSubmenu: true,
        component: vacancies
      },
      {
        path: '/technologies',
        name: 'navigation.menu.technologies',
        icon: 'fa-clipboard-list',
        hasSubmenus: false,
        isSubmenu: false,
        component: technologies
      },
      {
        path: '/executor',
        name: 'navigation.menu.executor',
        icon: 'fa-user-check',
        hasSubmenus: false,
        isSubmenu: false,
        component: executor
      },
      {
        path: '/customer',
        name: 'navigation.menu.customer',
        icon: 'fa-users',
        hasSubmenus: false,
        isSubmenu: false,
        component: customer
      },
      {
        path: '/news',
        name: 'navigation.menu.news',
        icon: 'fa-rss',
        hasSubmenus: false,
        isSubmenu: false,
        component: inDevelopment
      },
      {
        path: '/info',
        name: 'navigation.menu.info',
        icon: 'fa-info',
        hasSubmenus: false,
        isSubmenu: false,
        component: aboutUs
      },
      {
        path: '/login',
        component: login
      },
      {
        path: "*",
        name: "navigation.menu.notFound",
        component: notFound
      }
    ],
  },
}

export default state