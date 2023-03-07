[vue js + spring](https://blog.codecentric.de/spring-boot-vuejs) 
# Design

## Rest API.

### Get Point

#### Get user info
Get the existing user
```
http://localhost:8080/user/{userId}
```

#### Get todo items
Get the user's all todo items
```
http://localhost:8080/user/{userId}/items
```

Get unfinished items
```
http://localhost:8080/user/{userId}/items/undone
```

Get finished items
```
http://localhost:8080/user/{userId}/items/done
```


### POST method

#### Create new user
POST with header body
```
http://localhost:8080/user/create
```
Send with "name":
```javascript
{
    "name": "Jonny"
}
```
The response with be in the following.
```javascript
{
    "id": 2,
    "name": "Jonny",
    "items": []
}
```
It's newly created user with empty item.

#### Create new item
POST with header body
```
http://localhost:8080/user/{userId}/item/create
```
Send POST request with json:
```javascript
{
    "title": "java programming"
}
```
Only send "title" is enough

### Patch method

Right now use `Patch` method because there is not much to update for now. If there are more fields, I will create an `PUT` endpoint for that.

#### Change user info
Patch method with body
```
http://localhost:8080/user/{userId}
```
Send request by this json format:
```javascript
{
    "name": "Derek"
}
```

Right now, the user only has name field. So it only needs name field.

#### Update item
Patch method
```
http://localhost:8080/user/{userId}/item
```

The request body is ItemForm:
`id` is `ItemId`.
```javascript
{
    "id": 1,
    "title": "buy grocery",
    "completed": false
}
```
You can change item title and is it done at the same time.

## Database

An user has multiple todo items. One todo item belongs to one user.

3 tables:

`User` table has:
1. userId
2. user name
3. items

`Item` table has attributes:
1. item id
1. item name
1. is completed

The 3rd table maps userId to itemId

| userId | itemId |
|--------|--------|
| 101    | 1      |


