const messagesEn = {
    navigation: {
        companyName: 'SmartLight',
        companyDescription: 'Outsourcing company',
        menu: {
            general: 'General',
            vacancies: {
                title: 'Vacancies',
                submenu: {
                    java: 'Java',
                    cplusplus: 'C++',
                    csharp: 'C#',
                },
            },
            executor: 'Executor',
            technologies: 'Technologies',
            customer: 'Customer',
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
            title: 'The page is under construction !!!',
            description: 'Please come back a little later.'
        },
        vacancies: {
            dontActive: 'There are currently no open vacancies. Come back later.',
            data: {
                titles: {
                    generalInfo: 'General information',
                    vacanciesInfo: 'Job Information',
                    requirements: 'Requirements for a candidate',
                },
                subTitles: {
                    organization: 'Organization',
                    city: 'City',
                    address: 'Address',
                    phone: 'Phone',
                    contact: 'Contact',
                    salary: 'Salary (rub)',
                    workType: 'Nature of work',
                    workSchedule: 'Schedule',
                    workWithUser: 'Work with users',
                    description: 'Description',
                    education: 'Education',
                    professional: 'Professional requirements',
                    comments: 'Comments',
                },
            },
        },
        technologies: {
            web: 'WEB technologies',
            database: 'Databases',
            programming: 'Programming and frameworks',
        },
    },
    components: {
        auth: {
            name: 'Name',
            nameRequired: 'Name is required',
            emailRequired: 'E-mail is required',
            isValidNameMsg: 'Name must be less than 10 characters',
            isValidEmailMsg: 'E-mail must be valid: *@*.*',
        },
    },
    notification: {
        authSuccess: 'Login successful.',
        authError: 'Authorisation Error!!!',
    },
    buttons: {
        authBtn: 'Authorization',
        clearBtn: 'Clear',
        closeBtn: 'Close',
        openAll: 'Open all',
        closeAll: 'Close all',
    },
    filters: {
        title: 'Filter',
        emptyResults: 'No results with current filtering.',
    },
}

export default messagesEn