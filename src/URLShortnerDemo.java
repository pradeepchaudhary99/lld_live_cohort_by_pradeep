

/*
* URL shortner
* longURL --> ShortURL
* bit.ly/abc123 that maps back to the original long URL
* TinyURL, Bitly, t.co used by twiiter rely on URL shortners
* Imporve link sharing...
* Track clicks and analytics
* Redirect users quickly and efficinelty
* Low level design of the url shortner
*
* 1. Clarifying requirements
* --> Before starting the design..
* default:generate a unique short URL automatically..
* use can also provide custom alias if they prefer.
*
* should the short URL have an expiration policy, or should
* they remain valid indefinitely.
*
* For now basics click count tracking will suffice
*
* Functional:
* - Automatically generate a unique shortusers to optionally speicfy a cust URL for any given long URl
* - Allow ome alias for the short URL
* ALlow usres to define and opitonal expiration dates for short URLs
* Redirect users to the orginal long URL when the short URL is accessed.
* Handle URL conficts gracefully (when custom alias is already taken)
*
* How we generate the short URL?
* 1. counter-based system
* Everytime a new URL is submitted, we get a unique, incrementing ID form ta counter
* convert this numbericalID into a short, aplhanumebric String
*
* base-62 encoding
*
* Integer --> base62 --> [alphanumeric conversion]
* ID 10 in base-10 = k in base-62
* id 61 in base-10 = Z in base-62
*[0-9][a-z][A-Z]
*this appoach guarantees that every ID will produce a unique string, and the string
* will grow slowly in length.
*
*
* Core Entities:
* EventType:  URL_CREATED, URL_ACCESSED for use by the observer pattern
* ShortenedURL: mapping between longURL and generatedShortURL
* KeyGenerationStrategy: defines the contract for various algorithms used to generate the short key.
* random, Base62
*
* URLRepository:
* An interface(Repository Pattern) that abstracts the data persistence layer.
* defines methods for saving, retrieving, and querying ShortenedURL data,
* decoupling the service from the storage implementation.
*
* Observer: An interface for objects that need to be notified of system events.
* AnalyticsService is a concrete example that tracks URL creation and access Counts.
*
* URLShortnerService: The central Engine and Facade of the systrem.
* orchestrates the entire workflow, inclduing key genration, collision handling
* data persistence, notifying observers. unified API for clients to shorten and resolve URLs.
*
*enums, data-holding classes, and core classes that having system's primary logic..
*
* ENum:
* EventTYpe
*
*
*
*
* */



public class URLShortnerDemo {
}
