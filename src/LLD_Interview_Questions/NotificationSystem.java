package LLD_Interview_Questions;

/*

Description:
	Any event our users are notified via different channels
		EMail, SMS, Push
	For Events:
		OrderPlayed
		Payments
		User, Notifcation

Functional
	1. send Notification to the user
	2. Support multiple channels
	3. respect user prefences [user will have there prefernces]
Non-Functional
	Extensible
	Loose Coupling


Classes and Relationship
	Notification
	User
	NotificationChannel(interface)
		EmailNotificationChannel
		SMSNotificationChannel
		PushNotificationChannel

	NotificationService

Code:

*/



enum ChannelType{
    EMAIL,
    SMS,
    PUSH
}

enum NotificationType{
    OrderPlayed,
    Payment,
    Alert
}

class User{
    private String id;
    private String email;
    private String phone;

    Private Set<ChannelType> enabledChannels;

    public User(String id, String email, String phone, Set<ChannelType> enabledChannels){
        this.id = id;
        this.email = email;
		.....
		...
		..
		..
    }
    public boolean isChannelPresent(ChannelType type){
        return enabledChannels.contains(type);
    }
}

class Notification{
    private NotificationType type;
    private String message;

    public Notification(NotificationType type, String message){
        this.type = type;
        this.message = message;
    }

    //getters and setters

}

interface NotificationChannel{
    void send(User, Notification notification);
}

class EmailNotification implements NotificationChannel{
    public void send(User user, Notification notification) {
        prints("Sending Email to user");
    }
}


class SMSNotification implements NotificationChannel{
    public void send(User user, Notification notification) {
        prints("Sending SMS to user");
    }
}


class PushNotification implements NotificationChannel{
    public void send(User user, Notification notification) {
        prints("Sending Push to user");
    }
}


class Retry_Decorator_Notification implements NotificationChannel{

    private NotificationChannel wrappedChannel;
    private int maxAttempts;

    public Retry_Decorator_Notification(NotificationChannel channels, int maxAttempts){
        this.wrappedChannel = channel;
        this.maxAttempts = maxAttempts;
    }
    @override
    public void send(User user, Notification notification) {

        int attempt = 0;
        while( attempt < maxAttempts){
            try{
                wrappedChannel.send(user, message);
                System.out.println("Success on attempt" + attempt);
                attempt++;
            }catch(Exception e){
                System.out.println("dasdasasdad on dasd" + attempt);

                if(attempt == maxAttempts){
                    System.out.println("all retries gone");
                }
            }
        }
    }
}



class NotificationFactory{

    Map<ChannelType, NotificationChannel> channelMap = new HashMap<>();
    public static NotificationChannel getChannel(ChannelType type){
        if(channelMap.containsKey(type))
            return channelMap.get(type);

        switch(type){

            NotificationChannel currentChannel;
            case EMAIL:
            {
                currentChannel = new EmailNotification();
                break;
            }
            case SMS:
            {
                currentChannel = new SMSNotification();
                break;
            }
            case Push:
            {
                currentChannel = new PushNotification();
                break;
            }
            default:
                currentChannel = new SMSNotification();

        }
        Retry_Decorator_Notification retry_enabled_channel = new Retry_Decorator_Notification(currentChannel,5);
        channelMap.put(type, currentChannel);
        return currentChannel;
    }
}


class NotificationService{

    private ExecutorService executor;
    public NotificationService(int threadPoolSize){
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void sendNotification(User user, Notification notification){
        for(ChannelType type : ChannelType.values()){
            if(user.isChannelPresent(type)){

                executor.submit( () -> {
                    NotificationChannel channel = NotificationFactory.getChannel(type);
                    channel.send(user, notification);
                });

            }
        }
    }

    public void shutdown(){
        executor.shutdown
    }
}












public class NotificationSystem{
    public static void main(String[] args) {
        Set<ChannelType> channels = new HashSet<>();
        channels.ad(ChannelType.EMAIL);
        channels.add(ChannelType.PUSH);


        User user = new User("1","pradeep@gmail.com","9389129",channels);

        Notification notification = new Notification(NotificationType.OrderPlayed,  "addjasdaskdasd");

        NotificationService notificationService = new NotificationService();
        notificationService.sendNotification(user, notification);
    }
}


//Adding ASYNC Service...










// Apply retryMechanism on this sendNotification
//

// SendNotification()








16Feb
        Onwards
URLShortner
        NotificationSystem































