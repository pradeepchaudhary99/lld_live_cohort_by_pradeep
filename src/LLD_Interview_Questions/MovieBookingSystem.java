import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

enum SeatStatus {
    AVAILABLE,
    LOCKED,
    BOOKED
}

class Seat {
    private String seatId;

    public Seat(String seatId) {
        this.seatId = seatId;
    }

    public String getSeatId() {
        return seatId;
    }
}

class Screen {
    private String screenId;
    private List<Seat> seats;

    public Screen(String screenId, List<Seat> seats) {
        this.screenId = screenId;
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}

class Movie {
    private String movieId;
    private String title;

    public Movie(String movieId, String title) {
        this.movieId = movieId;
        this.title = title;
    }
}

class Show {
    private String showId;
    private Movie movie;
    private Screen screen;
    private TimeStamp timestamp;

    public Show(String showId, Movie movie, Screen screen) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
    }

    public String getShowId() {
        return showId;
    }

    public Screen getScreen() {
        return screen;
    }
}

class User {
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

class Booking {
    private String bookingId;
    private User user;
    private Show show;
    private List<String> seatIds;
                        paymentId;

    public Booking(String bookingId, User user, Show show, List<String> seatIds) {
        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seatIds = seatIds;
    }
}

class SeatLock {

    private String seatId;
    private String showId;
    private User user;
    private long lockTime;

    public SeatLock(String seatId, String showId, User user) {
        this.seatId = seatId;
        this.showId = showId;
        this.user = user;
        this.lockTime = System.currentTimeMillis();
    }

    public String getSeatId() {
        return seatId;
    }

    public String getShowId() {
        return showId;
    }
}

interface ShowRepository {
    Show findById(String showId);
}

class InMemoryShowRepository implements ShowRepository {

    private Map<String, Show> shows = new HashMap<>();

    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
    }

    public Show findById(String showId) {
        return shows.get(showId);
    }
}

interface BookingRepository {
    void save(Booking booking);
}

class InMemoryBookingRepository implements BookingRepository {

    private Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(UUID.randomUUID().toString(), booking);
    }
}

class LockManager {

    private Map<String, SeatLock> lockedSeats = new ConcurrentHashMap<>();

    private String getKey(String showId, String seatId) {
        return showId + "_" + seatId;
    }

    public synchronized boolean lockSeats(User user, String showId, List<String> seatIds) {

        for (String seatId : seatIds) {

            String key = getKey(showId, seatId);

            if (lockedSeats.containsKey(key)) {
                return false;
            }
        }

        for (String seatId : seatIds) {

            String key = getKey(showId, seatId);

            lockedSeats.put(key, new SeatLock(seatId, showId, user));
        }

        return true;
    }

    public synchronized void unlockSeats(String showId, List<String> seatIds) {

        for (String seatId : seatIds) {

            String key = getKey(showId, seatId);

            lockedSeats.remove(key);
        }
    }

    public synchronized void confirmSeats(String showId, List<String> seatIds) {

        for (String seatId : seatIds) {

            String key = getKey(showId, seatId);

            lockedSeats.remove(key);
        }
    }
}

class PaymentService {

    public boolean processPayment(User user, double amount) {

        System.out.println("Payment processed");

        return true;
    }
}

class BookingService {

    private ShowRepository showRepository;
    private BookingRepository bookingRepository;
    private SeatLockManager lockManager;
    private PaymentService paymentService;

    public BookingService(
            ShowRepository showRepository,
            BookingRepository bookingRepository,
            SeatLockManager lockManager,
            PaymentService paymentService) {

        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
        this.lockManager = lockManager;
        this.paymentService = paymentService;
    }

    public boolean bookSeats(User user, String showId, List<String> seatIds) {

        Show show = showRepository.findById(showId);

        //Lock the seat
        boolean locked = lockManager.lockSeats(user, showId, seatIds);

        if (!locked) {
            System.out.println("Seats already locked");
            return false;
        }

        //Lock success --> ask for the payment

        Payment paymentSuccess = paymentService.processPayment(user, 500);

        if (!paymentSuccess) {

            lockManager.unlockSeats(showId, seatIds);

            return false;
        }

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                user,
                show,
                seatIds,
        );

        bookingRepository.save(booking);

        lockManager.confirmSeats(showId, seatIds);

        System.out.println("Booking successful");

        return true;
    }


    //add other functionalities
    // Search
    // Recommendation
    // BookMyShow
}

public class MovieBookingSystem {

    public static void main(String[] args) {

        List<Seat> seats = Arrays.asList(
                new Seat("A1"),
                new Seat("A2"),
                new Seat("A3")
        );

        Screen screen = new Screen("Screen1", seats);

        Movie movie = new Movie("M1", "Avengers");

        Show show = new Show("SHOW1", movie, screen);

        InMemoryShowRepository showRepo = new InMemoryShowRepository();
        showRepo.addShow(show);

        InMemoryBookingRepository bookingRepo = new InMemoryBookingRepository();

        SeatLockManager lockManager = new SeatLockManager();

        PaymentService paymentService = new PaymentService();

        BookingService bookingService =
                new BookingService(showRepo, bookingRepo, lockManager, paymentService);

        User user = new User("U1");

        bookingService.bookSeats(user, "SHOW1", Arrays.asList("A1", "A2"));
    }
}


Entities
    Seat 
    SeatLock 
    Movie

Repositiory
Services

Concurrency Handling







