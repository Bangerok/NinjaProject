import general from './../pages/General'
import vacancies from './../pages/Vacancies'
import technologies from './../pages/Technologies'
import inDevelopment from './../pages/InDevelopment'
import executor from './../pages/Executor'
import customer from './../pages/Customer'
import aboutUs from './../pages/AboutUs'
import notFound from './../pages/NotFound'
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
        icon: 'mdi-view-dashboard',
        hasSubmenus: false,
        isSubmenu: false,
        component: general
      },
      {
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
      }
    ],
  },
}

export default state