# Ticket booking app
Implementation of simple booking app
## Entity relationship diagram
![ticketbookingapp - ERD png](https://user-images.githubusercontent.com/62755330/124813681-74d8fa80-df65-11eb-88ee-dc272a017552.png)


## Business scenario (use case)
1. The user selects the day and the time when he/she would like to see the movie.
2. The system lists movies available in the given time interval - title and screening
times.
3. The user chooses a particular screening.
4. The system gives information regarding screening room and available seats.
5. The user chooses seats, and gives the name of the person doing the reservation
(name and surname).
6. The system gives back the total amount to pay and reservation expiration time.

## How to build and run
### Using maven
Run this command in a terminal window in project directory:
```./mvnw spring-boot:run```

## How application works
### Database
H2 Console  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
Username: sa  
Password: password  

### Endpoints
**All screenings**  
GET [http://localhost:8080/screenings/all](http://localhost:8080/screenings/all)  

**Screenings beetween 14 July 2021 14:00 and 14 July 2021 20:00**  
GET [http://localhost:8080/screenings?dateFrom=2021-07-14T14:00:00&dateTo=2021-07-14T20:00:00](http://localhost:8080/screenings?dateFrom=2021-07-14T14:00:00&dateTo=2021-07-14T20:00:00)  

**Details about screening**  
GET [http://localhost:8080/screenings/9](http://localhost:8080/screenings/9)  

**Booking seats for a screening 9**  
POST [http://localhost:8080/screenings/9/reserve](http://localhost:8080/screenings/9/reserve)  
request body:  
``` 
{
    "name": "Piotr",
    "surname": "Kowalski",
    "seats": [
        {
            "seatNumber": 1,
            "ticketType": "ADULT"
        },
        {
            "seatNumber": 2,
            "ticketType": "STUDENT"
        }]
}
```
## Demo
todo




