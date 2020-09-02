

const CONFIG =  {
    "appTitle": "UIBCC",
    "DEBUG": true,
    "ENV": "DEV",
    "EXPORT": {
        "defaultResults": 1000,
        "maxResults": 10000000
    },
    "REST": {
        "clientLogin": "user",
        "clientSecret": "User1",
        "apiPath": "api/",
        "timeout": 1800000,
        "serverAuthUrl": "http://localhost:8082",
        "serverUrl": "http://localhost:8888"
    },
    "TABLE": {
        "numberOfResultsPerPage": 10,
        "numberOfResultsOptions": [
            10,
            25,
            50,
            100
        ]
    },
    "VERSION": "1.0.0"
};
export class AppConstants{
    public static get CONFIG(): any { return CONFIG; };
  }
  

