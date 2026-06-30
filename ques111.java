import java.util.ArrayList;
import java.util.Scanner;

class Movie {
    int movieId;
    String title;
    int totalSeats;
    int bookedSeats;
    double price;
    boolean[] seats; // false = available, true = booked

    Movie(int movieId, String title, int totalSeats, double price) {
        this.movieId = movieId;
        this.title = title;
        this.totalSeats = totalSeats;
        this.price = price;
        this.bookedSeats = 0;
        this.seats = new boolean[totalSeats]; // all false initially
    }

    int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public String toString() {
        return "ID: " + movieId + " | " + title + " | Price: ₹" + price + 
               " | Available: " + getAvailableSeats() + "/" + totalSeats;
    }
}

class Booking {
    int bookingId;
    String customerName;
    int movieId;
    String movieTitle;
    int seatNo;
    double amount;

    Booking(int bookingId, String customerName, Movie movie, int seatNo) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.movieId = movie.movieId;
        this.movieTitle = movie.title;
        this.seatNo = seatNo;
        this.amount = movie.price;
    }

    public String toString() {
        return "Booking ID: " + bookingId + " | Name: " + customerName + 
               " | Movie: " + movieTitle + " | Seat: " + seatNo + " | Amount: ₹" + amount;
    }
}

public class TicketBookingSystem {
    static ArrayList<Movie> movies = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int bookingCounter = 5001;

    public static void main(String[] args) {
        // Add default movies
        movies.add(new Movie(1, "Jawan", 50, 250.0));
        movies.add(new Movie(2, "Animal", 40, 300.0));
        movies.add(new Movie(3, "Pathaan", 60, 200.0));

        int choice;
        do {
            showMenu();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: viewMovies(); break;
                case 2: bookTicket(); break;
                case 3: viewBooking(); break;
                case 4: cancelTicket(); break;
                case 5: viewSeatLayout(); break;
                case 6: System.out.println("Thank you for using Ticket Booking System!"); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice!= 6);
    }

    static void showMenu() {
        System.out.println("\n--- Ticket Booking System ---");
        System.out.println("1: View All Movies");
        System.out.println("2: Book Ticket");
        System.out.println("3: View Booking by ID");
        System.out.println("4: Cancel Ticket");
        System.out.println("5: View Seat Layout");
        System.out.println("6: Exit");
    }

    static Movie findMovieById(int id) {
        for (Movie m : movies) {
            if (m.movieId == id) return m;
        }
        return null;
    }

    static Booking findBookingById(int id) {
        for (Booking b : bookings) {
            if (b.bookingId == id) return b;
        }
        return null;
    }

    static void viewMovies() {
        System.out.println("\n--- Now Showing ---");
        for (Movie m : movies) {
            System.out.println(m);
        }
    }

    static void bookTicket() {
        viewMovies();
        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();
        Movie movie = findMovieById(movieId);

        if (movie == null) {
            System.out.println("Movie not found!");
            return;
        }

        if (movie.getAvailableSeats() == 0) {
            System.out.println("Sorry, houseful!");
            return;
        }

        showSeats(movie);
        System.out.print("Enter Seat No to book (1-" + movie.totalSeats + "): ");
        int seatNo = sc.nextInt();

        if (seatNo < 1 || seatNo > movie.totalSeats) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (movie.seats[seatNo - 1]) {
            System.out.println("Seat already booked!");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        movie.seats[seatNo - 1] = true;
        movie.bookedSeats++;
        Booking newBooking = new Booking(bookingCounter, name, movie, seatNo);
        bookings.add(newBooking);

        System.out.println("Ticket booked successfully!");
        System.out.println(newBooking);
        bookingCounter++;
    }

    static void showSeats(Movie movie) {
        System.out.println("\nSeat Layout for " + movie.title + ":");
        System.out.println("X = Booked, O = Available");
        for (int i = 0; i < movie.totalSeats; i++) {
            if (movie.seats[i]) System.out.print("[X] ");
            else System.out.print("[" + (i + 1) + "] ");
            
            if ((i + 1) % 10 == 0) System.out.println(); // 10 seats per row
        }
        System.out.println();
    }

    static void viewSeatLayout() {
        System.out.print("Enter Movie ID: ");
        int movieId = sc.nextInt();
        Movie movie = findMovieById(movieId);
        if (movie!= null) showSeats(movie);
        else System.out.println("Movie not found!");
    }

    static void viewBooking() {
        System.out.print("Enter Booking ID: ");
        int bookingId = sc.nextInt();
        Booking booking = findBookingById(bookingId);
        
        if (booking!= null) System.out.println(booking);
        else System.out.println("Booking not found!");
    }

    static void cancelTicket() {
        System.out.print("Enter Booking ID to cancel: ");
        int bookingId = sc.nextInt();
        Booking booking = findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Booking not found!");
            return;
        }

        Movie movie = findMovieById(booking.movieId);
        if (movie!= null) {
            movie.seats[booking.seatNo - 1] = false; // Free the seat
            movie.bookedSeats--;
        }

        bookings.remove(booking);
        System.out.println("Booking cancelled. Refund: ₹" + booking.amount);
    }
}