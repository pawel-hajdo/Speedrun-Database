# Speedruns api

## User related endpoints
- `GET` [/speedruns/api/users](#get-speedrunsapiusers)
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
- `GET` [/speedruns/api/games/{gameId}](#delete-speedrunsapigameid)
- `POST` [/speedruns/api/games](#post-speedrunsapigames)
- `PUT` [/speedruns/api/games/{gameId}](#put-speedrunsapigamesgameid)
- `PUT` [/speedruns/api/games/{gameId}/platform/{platformId}](#put-speedrunsapigamesgameidplatformplatformid)
- `DELETE` [/speedruns/api/games/{gameId}](#delete-speedrunsapigameid)
## Platform related endpoints
- `GET` [/speedruns/api/platforms](#get-speedrunsapiplatforms)
- `POST` [/speedruns/api/platforms](#post-speedrunsapiplatforms)
- `PUT` [/speedruns/api/platforms/{platformId}](#put-speedrunsapiplatformsplatformid)
- `DELETE` [/speedruns/api/platforms/{platformId}](#delete-speedrunsapiplatformsplatformid)
## Rating related endpoints
- `POST` [/speedruns/api/game-ratings](#post-speedrunsapigame-ratings)
## Run related endpoints
- `GET`[/speedruns/api/runs](#get-speedunsapiruns)
- `POST` [/speedruns/api/runs](#post-speedrunsapiruns)
- `PUT` [/speedruns/api/runs/{runId}](#put-speedrunsapirunsrunid)
- `DELETE` [/speedruns/api/runs/{runId}](#delete-speedrunsapirunsrunid)

### GET /speedruns/api/users
Endpoint used to get information about all users.
##### Parameters
```json
Path:
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

### POST /speedruns/api/users
Endpoint used to register new user.
##### Parameters
```json
Path:
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
String with status description
```

### POST /speedruns/api/users/login
Endpoint used to log in user.
##### Parameters
```json
Path:
    none
Body:
{
    "login": "userLogin",
    "password": "userPassword"
}    
```
##### Response
```
String with status description
```

### PUT /speedruns/api/users/{userId}
Endpoint used to change user details.
##### Parameters
```json
Path:
    userId - id of user
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
        "image": null, //bytearray
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
    "image": null, //bytearray
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

### POST /speedruns/api/games
Endpoint used to add new game to Database.
#### Parameters
```json
Path:
    none
Body:
{
    "name": "GameName",
    "releaseYear": 2021,
    "description": "Game description",
    "image": "" //bytearray
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
Body:
{
    "name": "GameName",
    "releaseYear": 2021,
    "description": "Game description",
    "image": "" //bytearray
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
Body:
    none
```
#### Response
```
String with status description
```

### DELETE /speedruns/api/{gameId}
Endpoint used to delete game with provided id.
#### Parameters
```json
Path:
    gameId - id of game to delete
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

### POST /speedruns/api/platforms
Endpoint used to add new platform.
#### Parameters
```json
Path:
    none
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

### GET /speeduns/api/runs
Endpoint used to get information about all runs.
#### Parameters
```json
Path:
    none
Body:
    none
```
#### Response
```json
[
    {
        "runId": 1,
        "userId": 1,
        "gameId": 1,
        "time": "PT2H5M6S",
        "type": "idk",
        "videoLink": "youtube.com",
        "date": "2024-01-02T18:52:58.061358",
        "platform": 1,
        "confirmedBy": 0
    }
]
```

### POST /speedruns/api/runs
Endpoint used to add new run.
#### Parameters
```json
Path:
    none
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

### DELETE /speedruns/api/runs/{runId}
Endpoint used to delete run with provided id.
#### Parameters
```json
Path:
    runId - id of run
Body:
    none
```
#### Response
```
String with status description
```
