/**
 * Файл локализации системы на английском языке.
 */
const messagesEn = {
  navigation: {
    companyName: 'SmartLight',
    companyDescription: 'Outsourcing company',
    menu: {
      general: 'General',
      news: 'News',
      info: 'About project',
      notFound: 'Page not found',
    }
  },
  pages: {
    notFound: {
      title: '404 - not found',
      description: 'Oops! This page does not seem to exist.',
    },
    inDevelopment: {
      title: 'The page is under construction!',
      description: 'Please come back a little later.'
    },
    auth: {
      login: {
        formName: 'Authorization form',
        google: 'Sign in with Google',
      },
      register: {
        formName: 'Register',
        username: 'Username',
        matchingPassword: 'Matching password',
        emailRequired: 'Email is required',
        google: 'Sign up with Google',
      },
      general: {
        email: 'Email',
        password: 'Password',
        nameRequired: 'Username is required',
        passwordRequired: 'Password is required',
      },
    },
  },
  buttons: {
    authBtn: 'Authorization',
    registerBtn: 'Register',
    closeBtn: 'Close',
    backBtn: 'Back',
    exitBtn: 'Exit',
  },
  tooltips: {
    nightMode: 'On/Off night mode',
    minVariant: 'Collapse/Expand the menu',
    changeLanguageSystem: 'Change system language',
  },
  errors: {
    invalid: {
      empty: {
        username: 'Username must be filled',
        email: 'Email must be filled',
        password: 'Password must be filled',
        matchingPassword: 'Re-password must be filled',
      },
      matchingPassword: 'The entered passwords do not match',
      email: 'Invalid mail format. Is required - *@*.*',
      credential: 'Invalid credentials used',
    },
  },
}

export default messagesEn