# Speedruns api

## User related endpoints
- `GET` [/speedruns/api/users](#get-speedrunsapiusers)
- `GET` [/speedruns/api/users/{userId}](#get-speedrunsapiusersuserid)
- `POST` [/speedruns/api/users](#post-speedrunsapiusers)
- `POST` [/speedruns/api/users/login](#post-speedrunsapiuserslogin)
- `PUT` [/speedruns/api/users/{userId}](#put-speedrunsapiusersuserid)
- `DELETE` [/speedruns/api/users/{userId}](#delete-speedrunsapiusersuserid)
## Follow related endpoints
- `GET` [/speedruns/api/follows](#get-speedrunsapifollows)
- `GET` [/speedruns/api/follows/{followerId}](#get-speedrunsapifollowsfollowerid)
- `POST` [/speedruns/api/follows](#post-speedrunsapifollows)
- `DELETE` [/speedruns/api/follows/{followerId}/following/{followingId}](#delete-speedrunsapifollowsfolloweridfollowingfollowingid)
## Games related endpoints
- `GET` [/speedruns/api/games](#get-speedrunsapigames)
- `GET` [/speedruns/api/games/{gameId}](#get-speedrunsapigamesgameid)
- `GET` [/speedruns/api/games/{gameId}/runs](#get-speedrunsapigamesgameidruns)
- `POST` [/speedruns/api/games](#post-speedrunsapigames)
- `PUT` [/speedruns/api/games/{gameId}](#put-speedrunsapigamesgameid)
- `PUT` [/speedruns/api/games/{gameId}/platform/{platformId}](#put-speedrunsapigamesgameidplatformplatformid)
- `DELETE` [/speedruns/api/games/{gameId}](#delete-speedrunsapigamesgameid)
## Platform related endpoints
- `GET` [/speedruns/api/platforms](#get-speedrunsapiplatforms)
- `GET` [/speedruns/api/platforms/{platformId}](#get-speedrunsapiplatformsplatformid)
- `POST` [/speedruns/api/platforms](#post-speedrunsapiplatforms)
- `PUT` [/speedruns/api/platforms/{platformId}](#put-speedrunsapiplatformsplatformid)
- `DELETE` [/speedruns/api/platforms/{platformId}](#delete-speedrunsapiplatformsplatformid)
## Rating related endpoints
- `POST` [/speedruns/api/game-ratings](#post-speedrunsapigame-ratings)
## Run related endpoints
- `GET`[/speedruns/api/runs](#get-speedrunsapiruns)
- `GET` [/speedruns/api/runs/{runId}](#get-speedrunsapirunsrunid)
- `POST` [/speedruns/api/runs](#post-speedrunsapiruns)
- `PUT` [/speedruns/api/runs/{runId}](#put-speedrunsapirunsrunid)
- `PUT` [/speedruns/api/runs/{runId}/confirm](#put-speedrunsapirunsrunidconfirm)
- `DELETE` [/speedruns/api/runs/{runId}](#delete-speedrunsapirunsrunid)

### GET /speedruns/api/users
Endpoint used to get information about all users.
##### Parameters
```json
Path:
    none
Headers: 
    none
Body:
    none
```
##### Response
```json
[
    {
        "userId": 1,
        "login": "userLogin",
        "email": "userMail@mail.com",
        "role": "USER"
    }
]
```

### GET /speedruns/api/users/{userId}
Endpoint used to get information about user with provided id.
##### Parameters
```json
Path:
    userId - id of user
Headers: 
    none
Body:
    none
```
##### Response
```json
{
    "userId": 1,
    "login": "userLogin",
    "email": "userMail@mail.com",
    "role": "USER"
}
```

### POST /speedruns/api/users
Endpoint used to register new user.
##### Parameters
```json
Path:
    none
Headers:
    none
Body: 
{
    "login": "userLogin",
    "email": "userMail@mail.com",
    "password": "userPassword",    
    "role": "USER"
}    
```
##### Response
```
JWT token
or
String with status description
```

### POST /speedruns/api/users/login
Endpoint used to log in user.
##### Parameters
```json
Path:
    none
Headers:
    none
Body:
{
    "login": "userLogin",
    "password": "userPassword"
}    
```
##### Response
```
JWT token
or
String with status description
```

### PUT /speedruns/api/users/{userId}
Endpoint used to change user details.
##### Parameters
```json
Path:
    userId - id of user
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body: 
{
    "login": "newUserLogin",
    "email": "newUserMail@mail.com",
    "password": "newUserPassword",    
    "role": "USER"
}    
```
##### Response
```
String with status description
```

### DELETE /speedruns/api/users/{userId}
Endpoint used to delete user with provided id.
#### Parameters
```json
Path:
    userId - id of user
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body: 
    none  
```
#### Response
```
String with status description
```

### GET /speedruns/api/follows
Endpoint used to get information about all follows.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```json
[
    {
        "followerId": 1,
        "followingId": 2,
        "followTime": "2024-01-02T18:23:35.456367"
    }
]
```

### GET /speedruns/api/follows/{followerId}
Endpoint used to get information about users that are followed by user with provided id.
#### Parameters
```json
Path:
    followerId - id of user
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```json
[
    {
        "followerId": 1,
        "followingId": 2,
        "followTime": "2024-01-02T18:23:35.456367"
    }
]
```

### POST /speedruns/api/follows
Endpoint used to follow other user.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "followerId": 1,
    "followingId": 2
}
```
#### Response
```
String with status description
```

### DELETE /speedruns/api/follows/{followerId}/following/{followingId}
Endpoint used to unfollow other user.
#### Parameters
```json
Path:
    followerId - id of user that wants to unfollow someone
    followingId - id of user you not longer want to follow
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```
String with status description
```

### GET /speedruns/api/games
Endpoint used to get information about all games.
#### Parameters
```json
Path:
    none
Headers:
    none
Body:
    none
```
#### Response
```json
[
    {
        "gameId": 1,
        "name": "GameName",
        "releaseYear": 2021,
        "description": "Game description",
        "image": null,
        "averageRating": 5.0,
        "gameOnPlatforms": [
            {
                "platformId": 2,
                "type": "CONSOLE",
                "name": "Playstation 5"
            }
        ]
    }
]
```

### GET /speedruns/api/games/{gameId}
Endpoint used to get information about one game with provided id.
#### Parameters
```json
Path:
    gameId - id of game
Headers:
    none
Body:
    none
```
#### Response
```json
{
    "gameId": 1,
    "name": "GameName",
    "releaseYear": 2021,
    "description": "Game description",
    "image": null,
    "averageRating": 5.0,
    "gameOnPlatforms": [
        {
            "platformId": 2,
            "type": "CONSOLE",
            "name": "Playstation 5"
        }
    ]
}
```

### GET /speedruns/api/games/{gameId}/runs
Endpoint used to get information about runs in game with provided id.
#### Parameters
```json
Path:
    gameId - id of game
Headers:
    none
Body:
    none
```
#### Response
```json
[
  {
    "runId": 1,
    "user": {
      "userId": 1,
      "login": "user1"
    },
    "game": {
      "gameId": 1,
      "name": "game",
      "image": ""
    },
    "time": "PT25M19S",
    "type": "Any%",
    "videoLink": "https://www.youtube.com/",
    "date": "2024-01-13T23:22:43.646241",
    "platform": {
      "platformId": 1,
      "type": "PC",
      "name": "PC"
    },
    "confirmedBy": 0
  }
]
```

### POST /speedruns/api/games
Endpoint used to add new game to Database.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "name": "GameName",
    "releaseYear": 2021,
    "description": "Game description",
    "image": ""
}
```
#### Response
```
String with status description
```

### PUT /speedruns/api/games/{gameId}
Endpoint used to change details of game with provided id.
#### Parameters
```json
Path:
    gameId - id of game
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "name": "GameName",
    "releaseYear": 2021,
    "description": "Game description",
    "image": ""
}
```
#### Response
```
String with status description
```

### PUT /speedruns/api/games/{gameId}/platform/{platformId}
Endpoint used to assign platform to game.
#### Parameters
```json
Path:
    gameId - id of game
    platformId - id of platform
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```
String with status description
```

### DELETE /speedruns/api/games/{gameId}
Endpoint used to delete game with provided id.
#### Parameters
```json
Path:
    gameId - id of game to delete
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```
String with status description
```

### GET /speedruns/api/platforms
Endpoint used to get all platforms.
#### Parameters
```json
Path:
    none
Headers:
    none
Body:
    none
```
#### Response
```json
[
    {
        "platformId": 1,
        "type": "PC",
        "name": "PC"
    },
    {
        "platformId": 2,
        "type": "CONSOLE",
        "name": "Playstation 5"
    }
]
```

### GET /speedruns/api/platforms/{platformId}
Endpoint used to get information about platform with provided id.
#### Parameters
```json
Path:
    platformId - id of plaftorm
Headers:
    none
Body:
    none
```
#### Response
```json
{
    "platformId": 1,
    "type": "PC",
    "name": "PC"
}
```

### POST /speedruns/api/platforms
Endpoint used to add new platform.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "name": "Playstation 4",
    "type": "CONSOLE" //ENUM [PC,CONSOLE,MOBILE]
}
```
#### Response
```
String with status description
```

### PUT /speedruns/api/platforms/{platformId}
Endpoint used to change details of platform with provided id.
#### Parameters
```json
Path:
    platformId - id of platform
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "name": "Playstation 4",
    "type": "CONSOLE" //ENUM [PC,CONSOLE,MOBILE]
}
```
#### Response
```
String with status description
```

### DELETE /speedruns/api/platforms/{platformId}
Endpoint used to delete platform.
#### Parameters
```json
Path:
    platformId - id of platform
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```
String with status description
```

### POST /speedruns/api/game-ratings
Endpoint used to add game rating.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "userId": 1,
    "gameId": 1,
    "score": 10 //int with range (1-10)
}
```
#### Response
```
String with status description
```

### GET /speedruns/api/runs
Endpoint used to get information about all runs.
#### Parameters
```json
Path:
    none
Headers:
    none
Body:
    none
```
#### Response
```json
[
  {
    "runId": 2,
    "user": {
      "userId": 1,
      "login": "test2"
    },
    "game": {
      "gameId": 1,
      "name": "game",
      "image": ""
    },
    "time": "PT2H5M6S",
    "type": "idk",
    "videoLink": "youtube.com",
    "date": "2024-01-13T21:47:00.81043",
    "platform": {
      "platformId": 1,
      "type": "PC",
      "name": "PC"
    },
    "confirmedBy": 0
  }
]
```

### GET /speedruns/api/runs/{runId}
Endpoint used to get information about run with provided id
#### Parameters
```json
Path:
    runId - id of run
Headers:
    none
Body:
    none
```
#### Response
```json
{
    "runId": 2,
    "user": {
      "userId": 1,
      "login": "test2"
    },
    "game": {
      "gameId": 1,
      "name": "game",
      "image": ""
    },
    "time": "PT2H5M6S",
    "type": "idk",
    "videoLink": "youtube.com",
    "date": "2024-01-13T21:47:00.81043",
    "platform": {
      "platformId": 1,
      "type": "PC",
      "name": "PC"
    },
    "confirmedBy": 0
}
```

### POST /speedruns/api/runs
Endpoint used to add new run.
#### Parameters
```json
Path:
    none
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "userId": 1,
    "gameId": 1,
    "time": "PT2H5M6S",
    "videoLink": "youtube.com",
    "type": "idk",
    "platformId": 1
}
```
#### Response
```
String with status description
```

### PUT /speedruns/api/runs/{runId}
Endpoint used to update details of run with provided id
#### Parameters
```json
Path:
    runId - id of run
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "userId": 1,
    "gameId": 2,
    "time": "PT2H5M6S",
    "videoLink": "youtube.com",
    "type": "idk",
    "platformId": 3
}
```
#### Response
```
String with status description
```

### PUT /speedruns/api/runs/{runId}/confirm
Endpoint used to confirm run with provided id
#### Parameters
```json
Path:
    runId - id of run
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
{
    "userId": 1, //user has to be an admin
}
```
#### Response
```
String with status description
```

### DELETE /speedruns/api/runs/{runId}
Endpoint used to delete run with provided id.
#### Parameters
```json
Path:
    runId - id of run
Headers:
    Authorization: Bearer {JWT_TOKEN}
Body:
    none
```
#### Response
```
String with status description
```
