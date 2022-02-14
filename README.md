# Looqbox Backend Challenge
![Looqbox](https://github.com/looqbox/looqbox-backend-challenge/blob/master/logo.png)

## Challenge

First, sorting by the initial letters of the pokemons is performed. Then, the order by length of the pokemon names is performed. If there are pokemon names of equal size, they will be sorted alphabetically.

Just an example response:

```
{
    "results": [
        {
            "name": "beedrill",
        },
        {
            "name": "blastoise",
        },
        {
            "name": "bulbasaur",
        },
        {
            "name": "butterfree",
        },
        {
            "name": "caterpie",
        },
        {
            "name": "charizard",
        },
        {
            "name": "charmander",
        },
        {
            "name": "charmeleon",
        },
    ] 
}


```
Endpoint:

```
localhost:8080/pokemon?q

```

## Docker

```
./gradlew bootjar
docker build -t looqbox-pokemon-api .     
docker run -p 8080:8080 looqbox-pokemon-api
```

## Class diagram 

![classDiagram](https://github.com/RamonBecker/looqbox-backend-challenge/blob/master/diagrams/UML%20Class%20Diagram.png)



## Stack
- Java/Kotlin
- `Spring Boot` for the framework
- `Gradle` for dependency management and local deployment


# Guidelines
You need to make a HTTP REST API that 
- Consumes the [PokeAPI](https://pokeapi.co/) data.
- Provides an endpoint to query pokemons based on the substring of its name. For example:
  - Request: `GET /pokemons?q=pidge`
  - Expected response: ```{"result" : ["pidgey", "pidgeotto", "pidgeot"]}```
- You need to apply sorting by two algorithms (it is not permitted to use a sorting library, for this particular feature you must implement by yourself). And it’s very important to explain your implemented logic (For instance, you can use inline comments on the source code): 
  - the pokemon name's length and; 
  - the pokemon name's alphabetical order 
 
- Find a way to indicate the pokemon name highlight regarding the piece of its queried name. For example:
  - The queried name was `pi`
  - The highlight object must be ```{"name": "pikachu", "highlight": "<pre>pi</pre>kachu"}``` or ```{"name": "pikachu", "start": 0, "end": 2}```
