# multiplex-reservation

## Table of contents
* [Informacje Ogólne](#informacje-ogólne)
* [Komunikacja z aplikacją](#komunikacja-z-aplikacją)
* [Warstwa Service](#warstwa-service)
* [Generowanie Kina](#generowanie-kina)
* [Odnośnie wymagań](#odnośnie-wymagań)

## Informacje Ogólne

Aplikacja webowa napisana w języku Java z użyciem frameworka Spring umozliwuająca rezerwację miejsc w kinie typu multiplex.
Aplikacja nie posiada zaimplementowanego frontendu. Komunikowałem się z nią i testowałem za pomocą aplikacji Postman.
Aplikacja korzysta z JPA oraz bazy danych H2 pod URL *"http://localhost:8080/console"* z adresem URL JDBC *"jdbc:h2:file:./bazaDanych"*.

Baza danych zbudowana jest za pomocą 6 encji: User, Reservation, Screening, Movie, CinemaRoom, Seat.
Każda z nich posiada swoje prywatne atrybuty oraz jest połączona z odpowiednimi innymi encjami.
Każda encja korzysta z Repozytorium, które implementuje *PagingAndSortingRepository*.

## Komunikacja z aplikacją

Apliakcja obsługuje REST API i korzysta z dwóch komunikatów *GET* i *POST* w wielu wariantach:

* Wypisanie pokazów filmowych konkretnego dnia w konkretnym przedziale czasowym za pomocą scieżki: 
*/multiplex/reservation/screening/interval*

Podać należy 3 paramentry w następującej kolejności: dzień, godzina *Od*, godzina *Do*.  
Przykładowo: localhost:8080/multiplex/reservation/screening/interval?date=2&timeMin=10&timeMax=17  
Aplikacja na wyjściu daje plik JSON wypisujący wszystkie dane na temat pokazów filmowych, które mieszczą się w podanym przedziale czasowym.

* Wypisanie posortowanych alfabetycznie tytułów filmów w konkretnym przedziale czasowym za pomocą scieżki: 
*/multiplex/reservation/screening/titles*

Podać należy 3 paramentry w następującej kolejności: dzień, godzina *Od*, godzina *Do*.  
Przykładowo: localhost:8080/multiplex/reservation/screening/titles?date=2&timeMin=10&timeMax=17  
Aplikacja na wyjściu wyświetla listę posorotwanych alfabetycznie tytułów filmów.

* Wyświetlenie niezarezerwowanych miejsc dla konkretnego pokazu filmowego za pomocą ścieżki:
*/multiplex/reservation/chooseseat*

Należy podać jeden parametr będący Id pokazu filmowego (zostałby przesłany poprzez frontend)  
Przykładowo: localhost:8080/multiplex/reservation/chooseseat?screeningId=1  
Aplikacaja zwraca listę wolnych miejsc wraz z ich danymi w formacie JSON

* Dodanie nowego użytkownika (właściciela rezerwacji) pod ścieżką:
*/multiplex/reservation/screening/user*

Należy podać 3 parametry: imię, nazwisko i email.  
Przykładowo: localhost:8080/multiplex/reservation/user?name=test&surname=testtest&email=test@test.pl  
Aplikacja dodaje nowego użytkownika do bazy danych.

*Dodanie nowej rezerwacji (na jedno miejsce) pod ścieżką:
*/multiplex/reservation/screening/new*

Należy podać 4 parametry(któe byłyby przesyłane przez frontend): userId, screeningId, seatId, typ biletu.  
Przykładowo: localhost:8080/multiplex/reservation/new?userId=2&screeningId=1&seatId=8&ticketType=child,  
Zarezerwowałby dla użytkowni ko Id = 2 miejsce o Id = 8 dla pokazu filmowego o Id = 1 bilet z ulgą dziecięcą.

*Wyświetlenie sumy do zapłaty i terminu wygśnięcia rezerwacji pod ścieżką:
*/multiplex/reservation/screening/total*

Należy podać 1 parametr będacy Id użytkownika.  
Przykładowo: localhost:8080/multiplex/reservation/total?userId=2  
Wyświetli ilość zł do zapłacenia oraz datę i godzinę na 15min przed pierwszym pokazem, na który są rezerwacje.


## Warstwa Service
Za warstwę logiki biznesowej odpowiada jedna klasa typu Service, w któej rozpisane są wszelkie metody.

## Generowanie Kina

Przy włączeniu aplikacji generowane jest z kino na podstawie poniższych parametrów:  
*Ilość sal kinowych = 2,  
*Ilość filmów = 3,  
*Ilość filmów/dzień = 3,  
*Ilość kolumn i ilość wierszy w sali kinowej: kolumny = 3, wiersze = 2,  
*Ilość dni wprzód na które zaplanowane są seanse = 3,  
*Prawdopodobieństwo, że siedzenie jest na starcie zarezerwowane = 30%  

System generuje filmy na następne 3 dni od dnia następnego (Nie generuje filmów na dzień w którym odpalana jest aplikacja).  
Zarezerwowane miejsca są rezerwowane bez nalicznaia opłaty dla użytkownika "Admin".


## Odnośnie wymagań
Mam świadomość, że nie spełniłem wszystkich wymagań i założeń tego projektu. Niestety nie zdążyłem też zrobić demo, które by ten projekt ładnei prezentowało.  
Proszę jednak wziąć pod uwagę, że nigdy wcześniej nie pisałem nic we frameworku Spring i nie mam doświadczenia w tego typu aplikacjach, a w 2 tygodnie udało mi się napisać 
taki projekt.
Wszystkie pliki projektu są w osobnej gałęzi *newbranch*.
